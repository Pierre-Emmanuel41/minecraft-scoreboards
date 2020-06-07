package fr.pederobien.minecraftscoreboards.impl.entries.periodic;

import org.bukkit.World;
import org.bukkit.WorldBorder;

import fr.pederobien.minecraftscoreboards.impl.PeriodicUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.WorldBorderSizeEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class WorldBorderSizePeriodicUpdater extends PeriodicUpdater<WorldBorderSizeEntry> {

	/**
	 * Create an entry updated periodically.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 * 
	 * @see WorldBorderSizeEntry
	 */
	protected WorldBorderSizePeriodicUpdater(ISimpleObjective objective, long delay, long period, WorldBorderSizeEntry source) {
		super(objective, delay, period, source);
	}

	/**
	 * Create an entry updated periodically. The default value for the delay is 0.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 * 
	 * @see WorldBorderSizeEntry
	 */
	protected WorldBorderSizePeriodicUpdater(ISimpleObjective objective, long period, WorldBorderSizeEntry source) {
		super(objective, 0, period, source);
	}

	/**
	 * Create an entry updated periodically. This entry displays the current size of the world border associated to the given world.
	 * 
	 * @param objective       The objective associated to the source entry.
	 * @param delay           Represents the number of server ticks to wait before updating the objective.
	 * @param period          Represents the number of server ticks between two objective updates.
	 * @param score           The line number of this entry in the player objective.
	 * @param world           The world used to display the current size of its world border.
	 * @param pattern         A string used to format the world border size.
	 * @param displayHalfSize True to display the radius, false to display the diameter.
	 * 
	 * @see WorldBorder
	 * @see WorldBorderSizeEntry
	 */
	protected WorldBorderSizePeriodicUpdater(ISimpleObjective objective, long delay, long period, int score, World world, String pattern, boolean displayHalfSize) {
		this(objective, delay, period, new WorldBorderSizeEntry(score, world, pattern).setDisplayHalfSize(displayHalfSize));
	}

	/**
	 * Create an entry updated periodically. The default value for the delay is 0. This entry displays the current size of the world
	 * border associated to the given world.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry in the player objective.
	 * @param world     The world used to display the current size of its world border.
	 * @param pattern   A string used to format the world border size.
	 * 
	 * @see WorldBorder
	 * @see WorldBorderSizeEntry
	 */
	protected WorldBorderSizePeriodicUpdater(ISimpleObjective objective, long period, int score, World world, String pattern) {
		this(objective, period, new WorldBorderSizeEntry(score, world, pattern).setDisplayHalfSize(false));
	}
}
