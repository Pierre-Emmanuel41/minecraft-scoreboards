package fr.pederobien.minecraftscoreboards.impl.entries.auto;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;

import fr.pederobien.minecraftscoreboards.impl.AutoUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.MinedBlockEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class MinedBlockAutoUpdater extends AutoUpdater<MinedBlockEntry> {

	/**
	 * Create an entry updated when a {@link BlockBreakEvent} is thrown by the server.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param source    The source tracked by this updater.
	 * 
	 * @see MinedBlockEntry
	 */
	public MinedBlockAutoUpdater(ISimpleObjective objective, MinedBlockEntry source) {
		super(objective, source);
	}

	/**
	 * Create an entry updated when a {@link BlockBreakEvent} is thrown by the server. This entry displays the number of block
	 * corresponding to the given material a player has brocken.
	 * 
	 * @param objective  The objective associated to the source entry.
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * 
	 * @see Material
	 * @see MinedBlockEntry
	 */
	public MinedBlockAutoUpdater(ISimpleObjective objective, int score, Material material) {
		super(objective, new MinedBlockEntry(score, material));
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerMineBlock(BlockBreakEvent event) {
		if (getSource().getMaterial().equals(event.getBlock().getType()) && event.getPlayer().getName().equals(getObjective().getPlayer().getName()))
			update();
	}
}
