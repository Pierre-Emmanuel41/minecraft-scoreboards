package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;

import fr.pederobien.minecraftscoreboards.interfaces.IAutoUpdateEntry;

public class AutoUpdateObjective extends AbstractPluginObjective<IAutoUpdateEntry> {
	private boolean isRegistered;

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 */
	public AutoUpdateObjective(Player player, String name, String displayName, Plugin plugin) {
		this(player, name, displayName, DisplaySlot.SIDEBAR, plugin);
	}

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 * @param displaySlot The slot where this objective is displayed on player screen.
	 */
	public AutoUpdateObjective(Player player, String name, String displayName, DisplaySlot displaySlot, Plugin plugin) {
		this(player, name, displayName, "dummy", displaySlot, plugin);
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
	public AutoUpdateObjective(Player player, String name, String displayName, String criteria, DisplaySlot displaySlot, Plugin plugin) {
		super(player, name, displayName, criteria, displaySlot, plugin);
		isRegistered = false;
	}

	@Override
	public void start() {
		action(entry -> entry.setActivated(true));
	}

	@Override
	public void stop() {
		action(entry -> entry.setActivated(false));
	}

	@Override
	protected void onInitialize() {
		if (!isRegistered)
			action(entry -> getPlugin().getServer().getPluginManager().registerEvents(entry, getPlugin()));
		isRegistered = true;
	}
}