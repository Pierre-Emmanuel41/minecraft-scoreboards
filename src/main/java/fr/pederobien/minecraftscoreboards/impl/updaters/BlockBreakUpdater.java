package fr.pederobien.minecraftscoreboards.impl.updaters;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;

import fr.pederobien.minecraftscoreboards.impl.EventEntryUpdater;

public class BlockBreakUpdater extends EventEntryUpdater<BlockBreakEvent> {

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onBlockBreakEvent(BlockBreakEvent event) {
		update(event);
	}
}
