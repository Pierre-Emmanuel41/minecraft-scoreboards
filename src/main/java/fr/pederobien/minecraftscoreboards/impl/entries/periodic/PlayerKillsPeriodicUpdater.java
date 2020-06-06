package fr.pederobien.minecraftscoreboards.impl.entries.periodic;

import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.PeriodicUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.PlayerKillsEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class PlayerKillsPeriodicUpdater extends PeriodicUpdater<PlayerKillsEntry> {

	/**
	 * Create an entry updated periodically.
	 * 
	 * @param plugin    The plugin to register this entry to be periodically updated.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 * 
	 * @see PlayerKillsEntry
	 */
	public PlayerKillsPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, PlayerKillsEntry source) {
		super(plugin, objective, delay, period, source);
	}

	/**
	 * Create an entry updated periodically. The default value for the delay is 0.
	 * 
	 * @param plugin    The plugin to register this entry to be periodically updated.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 * 
	 * @see PlayerKillsEntry
	 */
	public PlayerKillsPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, PlayerKillsEntry source) {
		super(plugin, objective, 0, period, source);
	}

	/**
	 * Create an entry updated periodically. This entry display the number of player a player has killed.
	 * 
	 * @param plugin    The plugin to register this entry to be periodically updated.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * 
	 * @see PlayerKillsEntry
	 */
	public PlayerKillsPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, int score) {
		this(plugin, objective, delay, period, new PlayerKillsEntry(score));
	}

	/**
	 * Create an entry updated periodically. The default value for the delay is 0. This entry display the number of player a player
	 * has killed.
	 * 
	 * @param plugin    The plugin to register this entry to be periodically updated.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * 
	 * @see PlayerKillsEntry
	 */
	public PlayerKillsPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, int score) {
		this(plugin, objective, period, new PlayerKillsEntry(score));
	}
}
