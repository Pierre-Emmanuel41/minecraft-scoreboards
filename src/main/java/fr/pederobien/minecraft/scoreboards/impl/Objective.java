package fr.pederobien.minecraft.scoreboards.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.Semaphore;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

import fr.pederobien.minecraft.managers.BukkitManager;
import fr.pederobien.minecraft.managers.EColor;
import fr.pederobien.minecraft.managers.ScoreboardManager;
import fr.pederobien.minecraft.managers.TeamManager;
import fr.pederobien.minecraft.scoreboards.interfaces.IEntry;
import fr.pederobien.minecraft.scoreboards.interfaces.IObjective;

public class Objective implements IObjective {
	private String name, criteria, displayName;
	private DisplaySlot displaySlot;
	private Plugin plugin;
	private Player player;
	private Scoreboard scoreboard;
	private org.bukkit.scoreboard.Objective objective;
	private Map<Integer, ExtendedEntry> entries;
	private List<IEntry> entriesList;
	private boolean isInitialized, isActivated;
	private Runnable colorUpdater;
	private ChatColor color;
	private int emptyEntryCount, taskId;
	private Semaphore semaphore;

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param plugin      The plugin used to update this objective.
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 * @param criteria    The criteria tracked by this objective.
	 * @param displaySlot The slot where this objective is displayed on player screen.
	 */
	public Objective(Plugin plugin, Player player, String name, String displayName, String criteria, DisplaySlot displaySlot) {
		this.plugin = plugin;
		this.player = player;
		this.name = name;
		this.criteria = criteria;
		this.displayName = displayName;
		this.displaySlot = displaySlot;
		entries = new TreeMap<Integer, ExtendedEntry>();
		entriesList = Collections.unmodifiableList(new ArrayList<IEntry>(entries.values()));
		isInitialized = false;
		isActivated = false;
		colorUpdater = new ColorUpdater();
		emptyEntryCount = 0;
		semaphore = new Semaphore(1, true);
	}

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param plugin      The plugin used to update this objective.
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 * @param displaySlot The slot where this objective is displayed on player screen.
	 */
	public Objective(Plugin plugin, Player player, String name, String displayName, DisplaySlot displaySlot) {
		this(plugin, player, name, displayName, "dummy", displaySlot);
	}

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param plugin      The plugin used to update this objective.
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 */
	public Objective(Plugin plugin, Player player, String name, String displayName) {
		this(plugin, player, name, displayName, "dummy", DisplaySlot.SIDEBAR);
	}

	@Override
	public void start() {
		ScoreboardManager.setPlayerScoreboard(getPlayer(), getScoreboard().get());
		setActivated(true);
		update();
		taskId = BukkitManager.getScheduler().runTaskTimer(getPlugin(), colorUpdater, 0, 5).getTaskId();
	}

	@Override
	public void stop() {
		setActivated(false);
		BukkitManager.getScheduler().cancelTask(taskId);
		entries.clear();
	}

	@Override
	public Plugin getPlugin() {
		return plugin;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getCriteria() {
		return criteria;
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public DisplaySlot getDisplaySlot() {
		return displaySlot;
	}

	@Override
	public Optional<Scoreboard> getScoreboard() {
		return Optional.ofNullable(scoreboard);
	}

	@Override
	public void setScoreboard(Scoreboard scoreboard) {
		this.scoreboard = scoreboard;
		if (scoreboard == null)
			return;

		objective = ScoreboardManager.createObjective(scoreboard, getName(), getCriteria(), getDisplayName(), getDisplaySlot());
	}

	@Override
	public Optional<org.bukkit.scoreboard.Objective> getObjective() {
		return Optional.ofNullable(objective);
	}

	@Override
	public void initialize() {
		if (isInitialized)
			return;

		entries().forEach(entry -> entry.initialize());
		isInitialized = true;
	}

	@Override
	public void addEntry(IEntry entry) {
		internalAdd(new ExtendedEntry(entry, false));
		updateAndSortEntriesList();
	}

	@Override
	public void addEntry(int index, IEntry entry) {
		entry.setScore(entriesList.isEmpty() ? 0 : entriesList.get(entriesList.size() - index - 1).getScore());
		reorganizeWhenAdded(index);
		internalAdd(new ExtendedEntry(entry, false));
		updateAndSortEntriesList();
	}

	@Override
	public void removeEntry(int score) {
		ExtendedEntry entry = entries.remove(score);
		if (entry == null)
			return;

		if (getScoreboard().isPresent())
			getScoreboard().get().resetScores(entry.getCurrentValue());

		if (isActivated())
			entry.setActivated(false);

		entry.setObjective(null);

		if (entry.isEmpty())
			emptyEntryCount--;

		reorganizeWhenRemoved(toIndex(entry.getScore()) - 1);
		updateAndSortEntriesList();
	}

	@Override
	public void emptyEntry(int score) {
		String spaces = " ";
		for (int i = 0; i < emptyEntryCount; i++)
			spaces = spaces.concat(" ");
		emptyEntryCount++;
		internalAdd(new ExtendedEntry(new MessageEntry(score, spaces), true));
		updateAndSortEntriesList();
	}

	@Override
	public List<IEntry> entries() {
		return entriesList;
	}

	@Override
	public void update() {
		Optional<Scoreboard> optScoreboard = getScoreboard();
		if (!optScoreboard.isPresent())
			return;

		for (IEntry entry : entries.values())
			updateEntry(entry, false);
	}

	@Override
	public void update(IEntry entry) {
		updateEntry(entry, true);
	}

	@Override
	public void update(int score) {
		IEntry entry = entries.get(score);
		if (entry != null)
			update(entry);
	}

	@Override
	public boolean isActivated() {
		return isActivated;
	}

	@Override
	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
		entries().forEach(entry -> entry.setActivated(isActivated));
	}

	private void updateEntry(IEntry entry, boolean checkScoreboard) {
		if (getPlayer() == null || checkScoreboard && !getScoreboard().isPresent())
			return;

		doSafely(() -> {
			getScoreboard().get().resetScores(entry.getOldValue());
			entry.update();
			getObjective().get().getScore(entry.getCurrentValue()).setScore(entry.getScore());
		});
	}

	private int toIndex(int score) {
		int index = 0;
		for (IEntry entry : entriesList) {
			if (entry.getScore() > score) {
				index--;
				break;
			}
			index++;
		}
		return index;
	}

	private void reorganizeWhenAdded(int index) {
		List<IEntry> copy = new ArrayList<IEntry>(entriesList);
		for (int i = 0; i < copy.size() - index; i++) {
			ExtendedEntry e = (ExtendedEntry) entriesList.get(i);
			internalRemove(e);
			e.setScore(e.getScore() - 1);
			internalAdd(e);
		}
	}

	private void reorganizeWhenRemoved(int index) {
		for (int i = index; 0 <= i; i--) {
			ExtendedEntry e = (ExtendedEntry) entriesList.get(i);
			internalRemove(e);
			e.setScore(e.getScore() + 1);
			internalAdd(e);
		}
	}

	private void internalAdd(ExtendedEntry entry) {
		entry.setObjective(this);
		doSafely(() -> entries.put(entry.getScore(), entry));

		if (isActivated()) {
			entry.initialize();
			entry.setActivated(true);
			entry.setColor(color);
			update(entry);
		}
	}

	private void internalRemove(ExtendedEntry entry) {
		entry.setActivated(false);
		entry.setColor(EColor.RESET.getChatColor());
		doSafely(() -> entries.remove(entry.getScore()));
	}

	private void updateAndSortEntriesList() {
		List<IEntry> list = new ArrayList<IEntry>(entries.values());
		Collections.sort(list);
		entriesList = Collections.unmodifiableList(list);
	}

	private void doSafely(Runnable runnable) {
		try {
			semaphore.acquire();
			runnable.run();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}

	private class ExtendedEntry extends EntryWrapper<IEntry> {
		private boolean isEmpty;

		public ExtendedEntry(IEntry source, boolean isEmpty) {
			super(source);
			this.isEmpty = isEmpty;
		}

		public boolean isEmpty() {
			return isEmpty;
		}
	}

	private class ColorUpdater implements Runnable {

		@Override
		public void run() {
			ChatColor oldColor = color, newColor = TeamManager.getColor(getPlayer());
			if (oldColor == null || !newColor.equals(oldColor)) {
				getObjective().get().setDisplayName(newColor + getDisplayName() + ChatColor.RESET);
				for (IEntry entry : entries()) {
					entry.setColor(newColor);
					update(entry);
				}
				color = newColor;
			}
		}
	}
}
