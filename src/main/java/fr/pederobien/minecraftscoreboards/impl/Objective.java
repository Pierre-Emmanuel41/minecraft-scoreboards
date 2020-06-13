package fr.pederobien.minecraftscoreboards.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

import fr.pederobien.minecraftmanagers.BukkitManager;
import fr.pederobien.minecraftmanagers.ScoreboardManager;
import fr.pederobien.minecraftmanagers.TeamManager;
import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.IObjective;

public class Objective extends AbstractSimpleObjective implements IObjective {
	private Map<Integer, ExtendedEntry> entries;
	private List<IEntry> entriesList;
	private boolean isInitialized, isActivated;
	private Runnable colorUpdater;
	private ChatColor color;
	private int emptyEntryCount, taskId;

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param plugin      The plugin used to update this objective.
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 */
	public Objective(Plugin plugin, Player player, String name, String displayName) {
		this(plugin, player, name, displayName, DisplaySlot.SIDEBAR);
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
	 * @param criteria    The criteria tracked by this objective.
	 * @param displaySlot The slot where this objective is displayed on player screen.
	 */
	public Objective(Plugin plugin, Player player, String name, String displayName, String criteria, DisplaySlot displaySlot) {
		super(plugin, player, name, displayName, criteria, displaySlot);
		entries = new HashMap<Integer, ExtendedEntry>();
		entriesList = Collections.unmodifiableList(new ArrayList<IEntry>(entries.values()));
		isInitialized = false;
		isActivated = false;
		colorUpdater = new ColorUpdater();
		emptyEntryCount = 0;
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
	}

	@Override
	public void addEntry(IEntry entry) {
		entries.put(entry.getScore(), new ExtendedEntry(entry, false));
		entriesList = Collections.unmodifiableList(new ArrayList<IEntry>(entries.values()));
	}

	@Override
	public void removeEntry(int score) {
		ExtendedEntry entry = entries.remove(score);
		if (entry == null)
			return;

		if (entry.isEmpty())
			emptyEntryCount--;

		entriesList = Collections.unmodifiableList(new ArrayList<IEntry>(entries.values()));
	}

	@Override
	public void emptyEntry(int score) {
		String spaces = " ";
		for (int i = 0; i < emptyEntryCount; i++)
			spaces = spaces.concat(" ");
		emptyEntryCount++;
		addEntry(new ExtendedEntry(new MessageEntry(score, spaces), true));
	}

	@Override
	public List<IEntry> entries() {
		return entriesList;
	}

	@Override
	public void initialize() {
		if (isInitialized)
			return;

		entries().forEach(entry -> entry.initialize());
		isInitialized = true;
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
		getScoreboard().get().resetScores(entry.getOldValue());
		entry.update(getPlayer());
		getObjective().get().getScore(entry.getCurrentValue()).setScore(entry.getScore());
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
