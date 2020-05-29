package fr.pederobien.minecraftscoreboards.impl.entries.auto;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.AutoUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.EntityKilledEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class EntityKilledAutoUpdater extends AutoUpdater<EntityKilledEntry> {

	/**
	 * Create an entry updater. This entry is responsible to update the source entry. This updater is a {@link EntityDeathEvent}
	 * listener. This means that when an this event is thrown by the server, this updater verify the killer and the {@link EntityType}
	 * of the dead entity and update the player objective.
	 * 
	 * @param plugin    The plugin to register this entry as event listener.
	 * @param objective The objective associated to the source entry.
	 * @param source    The source tracked by this updater.
	 */
	public EntityKilledAutoUpdater(Plugin plugin, ISimpleObjective objective, EntityKilledEntry source) {
		super(plugin, objective, source);
	}

	/**
	 * Create an entry updated when an entity of the given {@link EntityType} is killed.
	 * 
	 * @param plugin     The plugin to register this entry as event listener.
	 * @param objective  The objective associated to the source entry.
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * @param before     The sequence of characters to be displayed before the player statistic.
	 * @param after      The sequence of characters to be displayed after the player statistic.
	 */
	public EntityKilledAutoUpdater(Plugin plugin, ISimpleObjective objective, int score, EntityType entityType, String before, String after) {
		this(plugin, objective, new EntityKilledEntry(score, entityType, before, after));
	}

	/**
	 * Create an entry updated when an entity of the given {@link EntityType} is killed.
	 * 
	 * @param plugin     The plugin to register this entry as event listener.
	 * @param objective  The objective associated to the source entry.
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * @param before     The sequence of characters to be displayed before the player statistic.
	 */
	public EntityKilledAutoUpdater(Plugin plugin, ISimpleObjective objective, int score, EntityType entityType, String before) {
		this(plugin, objective, new EntityKilledEntry(score, entityType, before));
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onEntityDeathEvent(EntityDeathEvent event) {
		if (!getSource().getEntityType().equals(event.getEntityType()) || event.getEntity().getKiller() == null
				|| !event.getEntity().getKiller().equals(getObjective().getPlayer()))
			return;

		update();
	}
}
