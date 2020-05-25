package fr.pederobien.minecraftscoreboards.impl.entries.common;

import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;

public class EntityKilledEntry extends StatisticEntry {

	/**
	 * Create an entry updated when an entity of the given {@link EntityType} is killed.
	 * 
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * @param before     The sequence of characters to be displayed before the player statistic.
	 * @param after      The sequence of characters to be displayed after the player statistic.
	 */
	public EntityKilledEntry(int score, EntityType entityType, String before, String after) {
		super(score, Statistic.KILL_ENTITY, entityType, before, after);
	}

	/**
	 * Create an entry updated when an entity of the given {@link EntityType} is killed.
	 * 
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * @param before     The sequence of characters to be displayed before the player statistic.
	 */
	public EntityKilledEntry(int score, EntityType entityType, String before) {
		super(score, Statistic.KILL_ENTITY, entityType, before);
	}
}
