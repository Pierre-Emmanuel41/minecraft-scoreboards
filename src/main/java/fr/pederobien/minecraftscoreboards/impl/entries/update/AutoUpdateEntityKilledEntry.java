package fr.pederobien.minecraftscoreboards.impl.entries.update;

import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDeathEvent;

public class AutoUpdateEntityKilledEntry extends AutoUpdateStatisticEntry {

	/**
	 * Create an entry updated when an entity of the given {@link EntityType} is killed.
	 * 
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * @param before     The sequence of characters to be displayed before the player statistic.
	 * @param after      The sequence of characters to be displayed after the player statistic.
	 */
	public AutoUpdateEntityKilledEntry(int score, EntityType entityType, String before, String after) {
		super(score, Statistic.KILL_ENTITY, entityType, before, after);
	}

	/**
	 * Create an entry updated when an entity of the given {@link EntityType} is killed.
	 * 
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * @param before     The sequence of characters to be displayed before the player statistic.
	 */
	public AutoUpdateEntityKilledEntry(int score, EntityType entityType, String before) {
		super(score, Statistic.KILL_ENTITY, entityType, before);
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onEntityDeathEvent(EntityDeathEvent event) {
		if (getEntityType().equals(event.getEntityType()))
			update();
	}
}
