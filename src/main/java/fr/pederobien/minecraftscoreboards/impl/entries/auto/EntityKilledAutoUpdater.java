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
	 * Create an entry updated when an {@link EntityDeathEvent} is thrown by the server.
	 * 
	 * @param plugin    The plugin to register this entry as event listener.
	 * @param objective The objective associated to the source entry.
	 * @param source    The source tracked by this updater.
	 * 
	 * @see EntityKilledEntry
	 */
	public EntityKilledAutoUpdater(Plugin plugin, ISimpleObjective objective, EntityKilledEntry source) {
		super(plugin, objective, source);
	}

	/**
	 * Create an entry updated when an {@link EntityDeathEvent} is thrown by the server. This entry displays the number of entity
	 * corresponding to the given entity type a player has killed.
	 * 
	 * @param plugin     The plugin to register this entry as event listener.
	 * @param objective  The objective associated to the source entry.
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * 
	 * @see EntityType
	 * @see EntityKilledEntry
	 */
	public EntityKilledAutoUpdater(Plugin plugin, ISimpleObjective objective, int score, EntityType entityType) {
		this(plugin, objective, new EntityKilledEntry(score, entityType));
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onEntityDeathEvent(EntityDeathEvent event) {
		if (!getSource().getEntityType().equals(event.getEntityType()) || event.getEntity().getKiller() == null
				|| !event.getEntity().getKiller().equals(getObjective().getPlayer()))
			return;

		update();
	}
}
