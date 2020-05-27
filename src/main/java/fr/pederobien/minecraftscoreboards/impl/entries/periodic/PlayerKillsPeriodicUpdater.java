package fr.pederobien.minecraftscoreboards.impl.entries.periodic;

import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.PeriodicUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.common.PlayerKillsEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class PlayerKillsPeriodicUpdater extends PeriodicUpdater<PlayerKillsEntry> {

	/**
	 * Create an entry updater. This entry is responsible to update the source entry.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 */
	public PlayerKillsPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, PlayerKillsEntry source) {
		super(plugin, objective, delay, period, source);
	}

	/**
	 * Create an entry updater. This entry is responsible to update the source entry.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 */
	public PlayerKillsPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, PlayerKillsEntry source) {
		this(plugin, objective, 0, period, source);
	}

	/**
	 * Create an entry that is updated when a player kills another player.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the player kill number.
	 * @param after     The sequence of characters to be displayed after the player kill number.
	 */
	public PlayerKillsPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, int score, String before, String after) {
		this(plugin, objective, delay, period, new PlayerKillsEntry(score, before, after));
	}

	/**
	 * Create an entry that is updated when a player kills another player.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the player kill number.
	 * @param after     The sequence of characters to be displayed after the player kill number.
	 */
	public PlayerKillsPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, int score, String before, String after) {
		this(plugin, objective, period, new PlayerKillsEntry(score, before, after));
	}

	/**
	 * Create an entry that is updated when a player kills another player.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the player kill number.
	 */
	public PlayerKillsPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, int score, String before) {
		this(plugin, objective, delay, period, new PlayerKillsEntry(score, before));
	}

	/**
	 * Create an entry that is updated when a player kills another player.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the player kill number.
	 */
	public PlayerKillsPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, int score, String before) {
		this(plugin, objective, period, new PlayerKillsEntry(score, before));
	}
}