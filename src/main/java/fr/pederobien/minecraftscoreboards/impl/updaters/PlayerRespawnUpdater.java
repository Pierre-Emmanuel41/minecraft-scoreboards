package fr.pederobien.minecraftscoreboards.impl.updaters;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerRespawnEvent;

import fr.pederobien.minecraftscoreboards.impl.EventEntryUpdater;

public class PlayerRespawnUpdater extends EventEntryUpdater<PlayerRespawnEvent> {

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
		update(event);
	}
}
