package fr.pederobien.minecraftscoreboards.impl.entries.periodic;

import org.bukkit.entity.EntityType;

import fr.pederobien.minecraftscoreboards.impl.PeriodicUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.EntityKilledEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class EntityKilledPeriodicUpdater extends PeriodicUpdater<EntityKilledEntry> {

	/**
	 * Create an entry updated periodically.
	 * 
	 * @param objective  The objective associated to the source entry.
	 * @param delay      Represents the number of server ticks to wait before updating the objective.
	 * @param period     Represents the number of server ticks between two objective updates.
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * 
	 * @see EntityKilledEntry
	 */
	public EntityKilledPeriodicUpdater(ISimpleObjective objective, long delay, long period, EntityKilledEntry source) {
		super(objective, delay, period, source);
	}

	/**
	 * Create an entry updated periodically. The default value for the delay is 0.
	 * 
	 * @param objective  The objective associated to the source entry.
	 * @param period     Represents the number of server ticks between two objective updates.
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * 
	 * @see EntityKilledEntry
	 */
	public EntityKilledPeriodicUpdater(ISimpleObjective objective, long period, EntityKilledEntry source) {
		super(objective, 0, period, source);
	}

	/**
	 * Create an entry updated periodically. This entry displays the number of entity corresponding to the given entity type a player
	 * has killed.
	 * 
	 * @param objective  The objective associated to the source entry.
	 * @param delay      Represents the number of server ticks to wait before updating the objective.
	 * @param period     Represents the number of server ticks between two objective updates.
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * 
	 * @see EntityType
	 * @see EntityKilledEntry
	 */
	public EntityKilledPeriodicUpdater(ISimpleObjective objective, long delay, long period, int score, EntityType entityType) {
		this(objective, delay, period, new EntityKilledEntry(score, entityType));
	}

	/**
	 * Create an entry updated periodically. The default value for the delay is 0. This entry displays the number of entity
	 * corresponding to the given entity type a player has killed.
	 * 
	 * @param objective  The objective associated to the source entry.
	 * @param period     Represents the number of server ticks between two objective updates.
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * 
	 * @see EntityType
	 * @see EntityKilledEntry
	 */
	public EntityKilledPeriodicUpdater(ISimpleObjective objective, long period, int score, EntityType entityType) {
		this(objective, period, new EntityKilledEntry(score, entityType));
	}
}
