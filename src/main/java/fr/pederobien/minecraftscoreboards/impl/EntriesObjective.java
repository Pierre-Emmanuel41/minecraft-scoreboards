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
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

import fr.pederobien.minecraftscoreboards.interfaces.IEntriesObjective;
import fr.pederobien.minecraftscoreboards.interfaces.IEntry;

public class EntriesObjective<T extends IEntry> extends AbstractSimpleObjective implements IEntriesObjective<T> {
	private Map<Integer, T> entries;
	private List<T> entriesList;

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param plugin      The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                    create periodic entry update.
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 */
	public EntriesObjective(Plugin plugin, Player player, String name, String displayName) {
		this(plugin, player, name, displayName, DisplaySlot.SIDEBAR);
	}

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param plugin      The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                    create periodic entry update.
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 * @param displaySlot The slot where this objective is displayed on player screen.
	 */
	public EntriesObjective(Plugin plugin, Player player, String name, String displayName, DisplaySlot displaySlot) {
		this(plugin, player, name, displayName, "dummy", displaySlot);
	}

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param plugin      The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                    create periodic entry update.
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 * @param criteria    The criteria tracked by this objective.
	 * @param displaySlot The slot where this objective is displayed on player screen.
	 */
	public EntriesObjective(Plugin plugin, Player player, String name, String displayName, String criteria, DisplaySlot displaySlot) {
		super(plugin, player, name, displayName, criteria, displaySlot);
		entries = new HashMap<Integer, T>();
		entriesList = Collections.unmodifiableList(new ArrayList<T>(entries.values()));
	}

	@Override
	public void addEntry(T entry) {
		entry.setObjective(this);
		entries.put(entry.getScore(), entry);
		entriesList = Collections.unmodifiableList(new ArrayList<T>(entries.values()));
	}

	@Override
	public void removeEntry(T entry) {
		entry.setObjective(null);
		entries.remove(entry.getScore());
		entriesList = Collections.unmodifiableList(new ArrayList<T>(entries.values()));
	}

	@Override
	public List<T> entries() {
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
		entry.update(getPlayer());
		getObjective().get().getScore(entry.getCurrentValue()).setScore(entry.getScore());
	}

	/**
	 * Convenient method to do something on each registered entry.
	 * 
	 * @param consumer The action to do on each registered entry.
	 */
	protected void action(Consumer<T> consumer) {
		for (T entry : entries())
			consumer.accept(entry);
	}

	/**
	 * Convenient method to do something on each registered entry that verify the given predicate.
	 * 
	 * @param predicate The predicate to filter each registered entry.
	 * @param consumer  The action to do on each registered entry.
	 */
	protected void action(Predicate<T> predicate, Consumer<T> consumer) {
		for (T entry : entries())
			if (predicate.test(entry))
				consumer.accept(entry);
	}
}
