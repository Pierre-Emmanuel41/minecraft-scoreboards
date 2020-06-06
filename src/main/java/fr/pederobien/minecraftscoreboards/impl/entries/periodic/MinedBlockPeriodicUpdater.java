package fr.pederobien.minecraftscoreboards.impl.entries.periodic;

import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.PeriodicUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.MinedBlockEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class MinedBlockPeriodicUpdater extends PeriodicUpdater<MinedBlockEntry> {

	/**
	 * Create an entry updated periodically.
	 * 
	 * @param plugin    The plugin to register this entry to be periodically updated.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 * 
	 * @see MinedBlockEntry
	 */
	public MinedBlockPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, MinedBlockEntry source) {
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
	 * @see MinedBlockEntry
	 */
	public MinedBlockPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, MinedBlockEntry source) {
		super(plugin, objective, 0, period, source);
	}

	/**
	 * Create an entry updated periodically. The entry display the number of block of the given material a player has broken.
	 * 
	 * @param plugin    The plugin to register this entry to be periodically updated.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param material  The type of material used to update this entry.
	 * 
	 * @see Material
	 * @see MinedBlockEntry
	 */
	public MinedBlockPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, int score, Material material) {
		this(plugin, objective, delay, period, new MinedBlockEntry(score, material));
	}

	/**
	 * Create an entry updated periodically. The default value for the delay is 0. The entry display the number of block of the given
	 * material a player has broken.
	 * 
	 * @param plugin    The plugin to register this entry to be periodically updated.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param material  The type of material used to update this entry.
	 * 
	 * @see Material
	 * @see MinedBlockEntry
	 */
	public MinedBlockPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, int score, Material material) {
		this(plugin, objective, period, new MinedBlockEntry(score, material));
	}
}
