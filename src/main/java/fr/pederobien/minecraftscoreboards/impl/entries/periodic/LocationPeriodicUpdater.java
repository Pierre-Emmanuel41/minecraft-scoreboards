package fr.pederobien.minecraftscoreboards.impl.entries.periodic;

import fr.pederobien.minecraftscoreboards.impl.PeriodicUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.LocationEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class LocationPeriodicUpdater extends PeriodicUpdater<LocationEntry> {

	/**
	 * Create an entry updated periodically.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 * 
	 * @see LocationEntry
	 */
	public LocationPeriodicUpdater(ISimpleObjective objective, long delay, long period, LocationEntry source) {
		super(objective, delay, period, source);
	}

	/**
	 * Create an entry updated periodically. The default value for the delay is 0.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 * 
	 * @see LocationEntry
	 */
	public LocationPeriodicUpdater(ISimpleObjective objective, long period, LocationEntry source) {
		super(objective, 0, period, source);
	}

	/**
	 * Create an entry updated periodically. This entry displays the current location of a player.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param delimiter The sequence of characters to be displayed between each element added. See above.
	 * 
	 * @see LocationEntry
	 */
	public LocationPeriodicUpdater(ISimpleObjective objective, long delay, long period, int score, String pattern) {
		this(objective, delay, period, new LocationEntry(score, pattern));
	}

	/**
	 * Create an entry updated periodically. The default value for the delay is 0. This entry displays the current location of a
	 * player.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param delimiter The sequence of characters to be displayed between each element added. See above.
	 * 
	 * @see LocationEntry
	 */
	public LocationPeriodicUpdater(ISimpleObjective objective, long period, int score, String pattern) {
		this(objective, period, new LocationEntry(score, pattern));
	}

	/**
	 * Create an entry updated periodically. This entry displays the current location of a player.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * 
	 * @see LocationEntry
	 */
	public LocationPeriodicUpdater(ISimpleObjective objective, long delay, long period, int score) {
		this(objective, delay, period, new LocationEntry(score));
	}

	/**
	 * Create an entry updated periodically. The default value for the delay is 0. This entry displays the current location of a
	 * player.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * 
	 * @see LocationEntry
	 */
	public LocationPeriodicUpdater(ISimpleObjective objective, long period, int score) {
		this(objective, period, new LocationEntry(score));
	}
}
