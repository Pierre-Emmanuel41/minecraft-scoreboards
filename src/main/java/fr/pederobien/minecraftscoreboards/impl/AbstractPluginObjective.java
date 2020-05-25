package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;

import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.IPluginObjective;

public abstract class AbstractPluginObjective<T extends IEntry> extends AbstractObjective<T> implements IPluginObjective<T> {
	private Plugin plugin;

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 * @param plugin      The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                    create periodic entry update.
	 */
	public AbstractPluginObjective(Player player, String name, String displayName, Plugin plugin) {
		this(player, name, displayName, DisplaySlot.SIDEBAR, plugin);
	}

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 * @param displaySlot The slot where this objective is displayed on player screen.
	 * @param plugin      The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                    create periodic entry update.
	 */
	public AbstractPluginObjective(Player player, String name, String displayName, DisplaySlot displaySlot, Plugin plugin) {
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
	 * @param plugin      The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                    create periodic entry update.
	 */
	public AbstractPluginObjective(Player player, String name, String displayName, String criteria, DisplaySlot displaySlot, Plugin plugin) {
		super(player, name, displayName, criteria, displaySlot);
		this.plugin = plugin;
	}

	@Override
	public Plugin getPlugin() {
		return plugin;
	}
}
