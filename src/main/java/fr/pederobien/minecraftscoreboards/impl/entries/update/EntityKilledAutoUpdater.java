package fr.pederobien.minecraftscoreboards.impl.entries.update;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.AutoUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.common.EntityKilledEntry;
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

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onEntityDeathEvent(EntityDeathEvent event) {
		if (!getSource().getEntityType().equals(event.getEntityType()) || event.getEntity().getKiller() == null
				|| !event.getEntity().getKiller().equals(getObjective().getPlayer()))
			return;

		update();
	}
}
