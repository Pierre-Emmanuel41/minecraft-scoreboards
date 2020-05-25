package fr.pederobien.minecraftscoreboards.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import fr.pederobien.minecraftscoreboards.interfaces.IEntriesObjective;
import fr.pederobien.minecraftscoreboards.interfaces.IEntry;

public class EntriesObjective<T extends IEntry> extends SimpleObjective implements IEntriesObjective<T> {
	private Map<Integer, T> entries;
	private List<T> entriesList;

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 */
	public EntriesObjective(Player player, String name, String displayName) {
		this(player, name, displayName, DisplaySlot.SIDEBAR);
	}

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 * @param displaySlot The slot where this objective is displayed on player screen.
	 */
	public EntriesObjective(Player player, String name, String displayName, DisplaySlot displaySlot) {
		this(player, name, displayName, "dummy", displaySlot);
	}

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 * @param criteria    The criteria tracked by this objective.
	 * @param displaySlot The slot where this objective is displayed on player screen.
	 */
	public EntriesObjective(Player player, String name, String displayName, String criteria, DisplaySlot displaySlot) {
		super(player, name, displayName, criteria, displaySlot);
		entries = new HashMap<Integer, T>();
		entriesList = Collections.unmodifiableList(new ArrayList<T>(entries.values()));
	}

	@Override
	public void addEntry(T entry) {
		entries.put(entry.getScore(), entry);
		entriesList = Collections.unmodifiableList(new ArrayList<T>(entries.values()));
	}

	@Override
	public void removeEntry(T entry) {
		entries.remove(entry.getScore());
		entriesList = Collections.unmodifiableList(new ArrayList<T>(entries.values()));
	}

	@Override
	public List<T> entries() {
		return entriesList;
	}
}
