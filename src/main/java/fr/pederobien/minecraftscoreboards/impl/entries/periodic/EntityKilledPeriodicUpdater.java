package fr.pederobien.minecraftscoreboards.impl.entries.periodic;

import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.PeriodicUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.EntityKilledEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class EntityKilledPeriodicUpdater extends PeriodicUpdater<EntityKilledEntry> {

	/**
	 * Create an entry updated periodically.
	 * 
	 * @param plugin     The plugin to register this entry to be periodically updated.
	 * @param objective  The objective associated to the source entry.
	 * @param delay      Represents the number of server ticks to wait before updating the objective.
	 * @param period     Represents the number of server ticks between two objective updates.
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * 
	 * @see EntityKilledEntry
	 */
	public EntityKilledPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, EntityKilledEntry source) {
		super(plugin, objective, delay, period, source);
	}

	/**
	 * Create an entry updated periodically. The default value for the delay is 0.
	 * 
	 * @param plugin     The plugin to register this entry to be periodically updated
	 * @param objective  The objective associated to the source entry.
	 * @param period     Represents the number of server ticks between two objective updates.
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * 
	 * @see EntityKilledEntry
	 */
	public EntityKilledPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, EntityKilledEntry source) {
		super(plugin, objective, 0, period, source);
	}

	/**
	 * Create an entry updated periodically. This entry displays the number of entity corresponding to the given entity type a player
	 * has killed.
	 * 
	 * @param plugin     The plugin to register this entry to be periodically updated.
	 * @param objective  The objective associated to the source entry.
	 * @param delay      Represents the number of server ticks to wait before updating the objective.
	 * @param period     Represents the number of server ticks between two objective updates.
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * 
	 * @see EntityType
	 * @see EntityKilledEntry
	 */
	public EntityKilledPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, int score, EntityType entityType) {
		this(plugin, objective, delay, period, new EntityKilledEntry(score, entityType));
	}

	/**
	 * Create an entry updated periodically. The default value for the delay is 0. This entry displays the number of entity
	 * corresponding to the given entity type a player has killed.
	 * 
	 * @param plugin     The plugin to register this entry to be periodically updated.
	 * @param objective  The objective associated to the source entry.
	 * @param period     Represents the number of server ticks between two objective updates.
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * 
	 * @see EntityType
	 * @see EntityKilledEntry
	 */
	public EntityKilledPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, int score, EntityType entityType) {
		this(plugin, objective, period, new EntityKilledEntry(score, entityType));
	}
}
