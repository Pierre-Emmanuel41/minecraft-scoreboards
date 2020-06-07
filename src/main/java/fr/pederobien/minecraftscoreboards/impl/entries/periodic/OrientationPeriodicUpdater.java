package fr.pederobien.minecraftscoreboards.impl.entries.periodic;

import org.bukkit.block.Block;

import fr.pederobien.minecraftscoreboards.impl.PeriodicUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.OrientationEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class OrientationPeriodicUpdater extends PeriodicUpdater<OrientationEntry> {

	/**
	 * Create an entry updated periodically.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 * 
	 * @see OrientationEntry
	 */
	public OrientationPeriodicUpdater(ISimpleObjective objective, long delay, long period, OrientationEntry source) {
		super(objective, delay, period, source);
	}

	/**
	 * Create an entry updated periodically. The default value for the delay is 0.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 * 
	 * @see OrientationEntry
	 */
	public OrientationPeriodicUpdater(ISimpleObjective objective, long period, OrientationEntry source) {
		super(objective, 0, period, source);
	}

	/**
	 * Create an entry updated periodically. This entry display the direction to follow to reach the given block.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param block     The target block.
	 * 
	 * @see OrientationEntry
	 */
	public OrientationPeriodicUpdater(ISimpleObjective objective, long delay, long period, int score, Block block) {
		this(objective, delay, period, new OrientationEntry(score, block));
	}

	/**
	 * Create an entry updated periodically. The default value for the delay is 0. This entry display the direction to follow to reach
	 * the given block.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param block     The target block.
	 * 
	 * @see OrientationEntry
	 */
	public OrientationPeriodicUpdater(ISimpleObjective objective, long period, int score, Block block) {
		this(objective, period, new OrientationEntry(score, block));
	}
}
