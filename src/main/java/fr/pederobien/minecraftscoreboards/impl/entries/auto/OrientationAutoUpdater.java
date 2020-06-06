package fr.pederobien.minecraftscoreboards.impl.entries.auto;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.AutoUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.OrientationEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class OrientationAutoUpdater extends AutoUpdater<OrientationEntry> {

	/**
	 * Create an entry updated when a {@link PlayerMoveEvent} is thrown by the server.
	 * 
	 * @param plugin    The plugin to register this entry as event listener.
	 * @param objective The objective associated to the source entry.
	 * @param source    The source tracked by this updater.
	 * 
	 * @see OrientationEntry
	 */
	public OrientationAutoUpdater(Plugin plugin, ISimpleObjective objective, OrientationEntry source) {
		super(plugin, objective, source);
	}

	/**
	 * Create an entry updated when a {@link PlayerMoveEvent} is thrown by the server. This entry display the direction to follow to
	 * reach the given block.
	 * 
	 * @param plugin    The plugin to register this entry as event listener.
	 * @param objective The objective associated to the source entry.
	 * @param score     The line number of this entry.
	 * @param block     The target block.
	 * 
	 * @see OrientationEntry
	 */
	public OrientationAutoUpdater(Plugin plugin, ISimpleObjective objective, int score, Block block) {
		super(plugin, objective, new OrientationEntry(score, block));
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		if (event.getPlayer().equals(getObjective().getPlayer()))
			update();
	}
}
