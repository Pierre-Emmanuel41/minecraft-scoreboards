package fr.pederobien.minecraftscoreboards.impl.updaters;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.pederobien.minecraftscoreboards.impl.EventEntryUpdater;

public class EntityDamageUpdater extends EventEntryUpdater<EntityDamageEvent> {

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onEntityDamageEvent(EntityDamageEvent event) {
		update(event);
	}
}
