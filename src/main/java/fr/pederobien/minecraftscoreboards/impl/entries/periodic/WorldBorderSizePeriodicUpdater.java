package fr.pederobien.minecraftscoreboards.impl.entries.periodic;

import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.PeriodicUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.WorldBorderSizeEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class WorldBorderSizePeriodicUpdater extends PeriodicUpdater<WorldBorderSizeEntry> {

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
	protected WorldBorderSizePeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, WorldBorderSizeEntry source) {
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
	protected WorldBorderSizePeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, WorldBorderSizeEntry source) {
		this(plugin, objective, 0, period, source);
	}

	/**
	 * Create an entry that display the current size of the {@link WorldBorder} associated to the given world.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry in the player objective.
	 * @param world     The world used to display the current size of its world border.
	 * @param after     The sequence of characters to be displayed after the world border size.
	 * @param pattern   A string used to format the world border size.
	 */
	protected WorldBorderSizePeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, int score, World world, String after, String pattern) {
		this(plugin, objective, delay, period, new WorldBorderSizeEntry(score, world, after, pattern));
	}

	/**
	 * Create an entry that display the current size of the {@link WorldBorder} associated to the given world.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry in the player objective.
	 * @param world     The world used to display the current size of its world border.
	 * @param after     The sequence of characters to be displayed after the world border size.
	 * @param pattern   A string used to format the world border size.
	 */
	protected WorldBorderSizePeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, int score, World world, String after, String pattern) {
		this(plugin, objective, period, new WorldBorderSizeEntry(score, world, after, pattern));
	}
}
