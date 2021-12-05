package fr.pederobien.minecraft.scoreboards.impl.updaters;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.pederobien.minecraft.scoreboards.impl.EventEntryUpdater;

public class PlayerMoveUpdater extends EventEntryUpdater<PlayerMoveEvent> {

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		update(event);
	}
}
