package fr.pederobien.minecraftscoreboards.impl.entries.periodic;

import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.PeriodicUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.common.OrientationEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class OrientationPeriodicUpdater extends PeriodicUpdater<OrientationEntry> {

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
	public OrientationPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, OrientationEntry source) {
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
	public OrientationPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, OrientationEntry source) {
		this(plugin, objective, 0, period, source);
	}

	/**
	 * Create an entry that displays the orientation to follow to reach the given block.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the orientation to follow.
	 * @param block     The target block.
	 * @param before    The sequence of characters to be displayed after the orientation to follow.
	 */
	public OrientationPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, int score, String before, Block block, String after) {
		this(plugin, objective, delay, period, new OrientationEntry(score, before, block, after));
	}

	/**
	 * Create an entry that displays the orientation to follow to reach the given block.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the orientation to follow.
	 * @param block     The target block.
	 * @param before    The sequence of characters to be displayed after the orientation to follow.
	 */
	public OrientationPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, int score, String before, Block block, String after) {
		this(plugin, objective, period, new OrientationEntry(score, before, block, after));
	}

	/**
	 * Create an entry that displays the orientation to follow to reach the given block.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the orientation to follow.
	 * @param block     The target block.
	 */
	public OrientationPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, int score, String before, Block block) {
		this(plugin, objective, delay, period, new OrientationEntry(score, before, block));
	}

	/**
	 * Create an entry that displays the orientation to follow to reach the given block.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the orientation to follow.
	 * @param block     The target block.
	 */
	public OrientationPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, int score, String before, Block block) {
		this(plugin, objective, period, new OrientationEntry(score, before, block));
	}
}
