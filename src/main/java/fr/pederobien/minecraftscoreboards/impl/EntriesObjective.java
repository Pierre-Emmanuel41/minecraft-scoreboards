package fr.pederobien.minecraftscoreboards.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

import fr.pederobien.minecraftscoreboards.impl.entries.simple.MessageEntry;
import fr.pederobien.minecraftscoreboards.interfaces.IEntriesObjective;
import fr.pederobien.minecraftscoreboards.interfaces.IEntry;

public class EntriesObjective extends AbstractSimpleObjective implements IEntriesObjective {
	private Map<Integer, ExtendedEntry> entries;
	private List<IEntry> entriesList;
	private int emptyEntryCount;

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param player      IEntryhe player associated to this objective. IEntryhis player is used to display its informations.
	 * @param name        IEntryhe name of this objective.
	 * @param displayName IEntryhe name displayed on the given player score board.
	 */
	public EntriesObjective(Player player, String name, String displayName) {
		this(player, name, displayName, DisplaySlot.SIDEBAR);
	}

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param player      IEntryhe player associated to this objective. IEntryhis player is used to display its informations.
	 * @param name        IEntryhe name of this objective.
	 * @param displayName IEntryhe name displayed on the given player score board.
	 * @param displaySlot IEntryhe slot where this objective is displayed on player screen.
	 */
	public EntriesObjective(Player player, String name, String displayName, DisplaySlot displaySlot) {
		this(player, name, displayName, "dummy", displaySlot);
	}

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param player      IEntryhe player associated to this objective. IEntryhis player is used to display its informations.
	 * @param name        IEntryhe name of this objective.
	 * @param displayName IEntryhe name displayed on the given player score board.
	 * @param criteria    IEntryhe criteria tracked by this objective.
	 * @param displaySlot IEntryhe slot where this objective is displayed on player screen.
	 */
	public EntriesObjective(Player player, String name, String displayName, String criteria, DisplaySlot displaySlot) {
		super(player, name, displayName, criteria, displaySlot);
		entries = new HashMap<Integer, ExtendedEntry>();
		entriesList = Collections.unmodifiableList(new ArrayList<IEntry>(entries.values()));
		emptyEntryCount = 0;
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

	private void updateEntry(IEntry entry, boolean checkScoreboard) {
		if (getPlayer() == null || checkScoreboard && !getScoreboard().isPresent())
			return;
		getScoreboard().get().resetScores(entry.getOldValue());
		entry.update();
		getObjective().get().getScore(entry.getCurrentValue()).setScore(entry.getScore());
	}

	/**
	 * Convenient method to do something on each registered entry.
	 * 
	 * @param consumer IEntryhe action to do on each registered entry.
	 */
	protected void action(Consumer<IEntry> consumer) {
		for (IEntry entry : entries())
			consumer.accept(entry);
	}

	/**
	 * Convenient method to do something on each registered entry that verify the given predicate.
	 * 
	 * @param predicate IEntryhe predicate to filter each registered entry.
	 * @param consumer  IEntryhe action to do on each registered entry.
	 */
	protected void action(Predicate<IEntry> predicate, Consumer<IEntry> consumer) {
		for (IEntry entry : entries())
			if (predicate.test(entry))
				consumer.accept(entry);
	}

	private class ExtendedEntry implements IEntry {
		private IEntry source;
		private boolean isEmpty;

		public ExtendedEntry(IEntry source, boolean isEmpty) {
			this.source = source;
			this.isEmpty = isEmpty;
		}

		@Override
		public Player getPlayer() {
			return source.getPlayer();
		}

		@Override
		public IEntry setPlayer(Player player) {
			return source.setPlayer(player);
		}

		@Override
		public String getOldValue() {
			return source.getOldValue();
		}

		@Override
		public String getCurrentValue() {
			return source.getCurrentValue();
		}

		@Override
		public void update() {
			source.update();
		}

		@Override
		public int getScore() {
			return source.getScore();
		}

		@Override
		public void setScore(int score) {
			source.setScore(score);
		}

		@Override
		public void initialize() {
			source.initialize();
		}

		@Override
		public boolean isActivated() {
			return source.isActivated();
		}

		@Override
		public void setActivated(boolean isActivated) {
			source.setActivated(isActivated);
		}

		public boolean isEmpty() {
			return isEmpty;
		}
	}
}
