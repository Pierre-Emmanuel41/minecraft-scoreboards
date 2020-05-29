package fr.pederobien.minecraftscoreboards.impl.entries.periodic;

import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.PeriodicUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.MinedBlockEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class MinedBlockPeriodicUpdater extends PeriodicUpdater<MinedBlockEntry> {

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
	public MinedBlockPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, MinedBlockEntry source) {
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
	public MinedBlockPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, MinedBlockEntry source) {
		this(plugin, objective, 0, period, source);
	}

	/**
	 * Create an entry updated when a player mine a block of the given {@link Material}.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param material  The type of material used to update this entry.
	 * @param before    The sequence of characters to be displayed before the player statistic.
	 * @param after     The sequence of characters to be displayed after the player statistic.
	 */
	public MinedBlockPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, int score, Material material, String before, String after) {
		this(plugin, objective, delay, period, new MinedBlockEntry(score, material, before, after));
	}

	/**
	 * Create an entry updated when a player mine a block of the given {@link Material}.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param material  The type of material used to update this entry.
	 * @param before    The sequence of characters to be displayed before the player statistic.
	 * @param after     The sequence of characters to be displayed after the player statistic.
	 */
	public MinedBlockPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, int score, Material material, String before, String after) {
		this(plugin, objective, period, new MinedBlockEntry(score, material, before, after));
	}

	/**
	 * Create an entry updated when a player mine a block of the given {@link Material}.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param material  The type of material used to update this entry.
	 * @param before    The sequence of characters to be displayed before the player statistic.
	 */
	public MinedBlockPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, int score, Material material, String before) {
		this(plugin, objective, delay, period, new MinedBlockEntry(score, material, before));
	}

	/**
	 * Create an entry updated when a player mine a block of the given {@link Material}.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param material  The type of material used to update this entry.
	 * @param before    The sequence of characters to be displayed before the player statistic.
	 */
	public MinedBlockPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, int score, Material material, String before) {
		this(plugin, objective, 0, period, new MinedBlockEntry(score, material, before));
	}
}
