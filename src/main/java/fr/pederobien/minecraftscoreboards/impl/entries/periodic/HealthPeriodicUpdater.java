package fr.pederobien.minecraftscoreboards.impl.entries.periodic;

import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.PeriodicUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.HealthEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class HealthPeriodicUpdater extends PeriodicUpdater<HealthEntry> {

	/**
	 * Create an entry updated periodically.
	 * 
	 * @param plugin    The plugin to register this entry to be periodically updated.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param pattern   A string used to format the player health on screen.
	 */
	public HealthPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, HealthEntry source) {
		super(plugin, objective, delay, period, source);
	}

	/**
	 * Create an entry updated periodically. The default value for the delay is 0.
	 * 
	 * @param plugin    The plugin to register this entry to be periodically updated.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param pattern   A string used to format the player health on screen.
	 */
	public HealthPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, HealthEntry source) {
		super(plugin, objective, 0, period, source);
	}

	/**
	 * Create an entry updated periodically.
	 * 
	 * @param plugin    The plugin to register this entry to be periodically updated.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param pattern   A string used to format the player health on screen.
	 */
	public HealthPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, int score, String pattern) {
		super(plugin, objective, delay, period, new HealthEntry(score, pattern));
	}

	/**
	 * Create an entry updated periodically. The default value for the delay is 0.
	 * 
	 * @param plugin    The plugin to register this entry to be periodically updated.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param pattern   A string used to format the player health on screen.
	 */
	public HealthPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, int score, String pattern) {
		super(plugin, objective, 0, period, new HealthEntry(score, pattern));
	}
}
