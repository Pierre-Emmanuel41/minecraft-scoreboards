package fr.pederobien.minecraft.scoreboards.impl.updaters;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

import fr.pederobien.minecraft.scoreboards.impl.EventEntryUpdater;

public class PlayerGameModeChangeUpdater extends EventEntryUpdater<PlayerGameModeChangeEvent> {

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onPlayerGameModeChangeEvent(PlayerGameModeChangeEvent event) {
		update(event);
	}
}
