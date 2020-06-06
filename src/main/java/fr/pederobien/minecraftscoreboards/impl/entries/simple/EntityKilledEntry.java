package fr.pederobien.minecraftscoreboards.impl.entries.simple;

import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;

public class EntityKilledEntry extends StatisticEntry {

	/**
	 * Create an entry that displays the number of entity corresponding to the given entity type a player has killed.
	 * 
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 */
	public EntityKilledEntry(int score, EntityType entityType) {
		super(score, Statistic.KILL_ENTITY, entityType);
	}
}
