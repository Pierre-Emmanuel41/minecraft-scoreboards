package fr.pederobien.minecraftscoreboards.impl.entries.update;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.AutoUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.common.MinedBlockEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class MinedBlockAutoUpdater extends AutoUpdater<MinedBlockEntry> {

	/**
	 * Create an entry updater. This entry is responsible to update the source entry. This updater is a {@link BlockBreakEvent}
	 * listener. This means that when an this event is thrown by the server, this updater update the player objective.
	 * 
	 * @param plugin    The plugin to register this entry as event listener.
	 * @param objective The objective associated to the source entry.
	 * @param source    The source tracked by this updater.
	 */
	public MinedBlockAutoUpdater(Plugin plugin, ISimpleObjective objective, MinedBlockEntry source) {
		super(plugin, objective, source);
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerMineBlock(BlockBreakEvent event) {
		if (getSource().getMaterial().equals(event.getBlock().getType()) && event.getPlayer().getName().equals(getObjective().getPlayer().getName()))
			update();
	}
}
