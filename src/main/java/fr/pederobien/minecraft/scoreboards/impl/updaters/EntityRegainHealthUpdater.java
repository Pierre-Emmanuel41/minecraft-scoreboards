package fr.pederobien.minecraft.scoreboards.impl.updaters;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import fr.pederobien.minecraft.scoreboards.impl.EventEntryUpdater;

public class EntityRegainHealthUpdater extends EventEntryUpdater<EntityRegainHealthEvent> {

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onEntityRegainHealthEvent(EntityRegainHealthEvent event) {
		update(event);
	}
}
