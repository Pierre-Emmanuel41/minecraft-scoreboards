package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class SimpleObjective implements ISimpleObjective {
	private String name, criteria, displayName;
	private DisplaySlot displaySlot;
	private Player player;

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 */
	public SimpleObjective(Player player, String name, String displayName) {
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
	public SimpleObjective(Player player, String name, String displayName, DisplaySlot displaySlot) {
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
	public SimpleObjective(Player player, String name, String displayName, String criteria, DisplaySlot displaySlot) {
		this.player = player;
		this.name = name;
		this.criteria = criteria;
		this.displayName = displayName;
		this.displaySlot = displaySlot;
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
}
