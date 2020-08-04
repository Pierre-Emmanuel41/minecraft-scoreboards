package fr.pederobien.minecraftscoreboards.impl.updaters;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDeathEvent;

import fr.pederobien.minecraftscoreboards.impl.EventEntryUpdater;

public class EntityDeathUpdater extends EventEntryUpdater<EntityDeathEvent> {

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onEntityDeathEvent(EntityDeathEvent event) {
		update(event);
	}
}
