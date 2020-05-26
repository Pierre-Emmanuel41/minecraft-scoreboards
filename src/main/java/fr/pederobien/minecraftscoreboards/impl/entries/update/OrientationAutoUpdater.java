package fr.pederobien.minecraftscoreboards.impl.entries.update;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.AutoUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.common.OrientationEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class OrientationAutoUpdater extends AutoUpdater<OrientationEntry> {
	private int call;

	/**
	 * Create an entry updater. This entry is responsible to update the source entry. This updater is a {@link PlayerMoveEvent}
	 * listener. This means that when an this event is thrown by the server, this updater update the player objective.
	 * 
	 * @param plugin    The plugin to register this entry as event listener.
	 * @param objective The objective associated to the source entry.
	 * @param source    The source tracked by this updater.
	 */
	public OrientationAutoUpdater(Plugin plugin, ISimpleObjective objective, OrientationEntry source) {
		super(plugin, objective, source);
		call = 0;
	}

	/**
	 * Create an entry that displays the orientation to follow to reach the given block.
	 * 
	 * @param plugin    The plugin to register this entry as event listener.
	 * @param objective The objective associated to the source entry.
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the orientation to follow.
	 * @param block     The target block.
	 */
	public OrientationAutoUpdater(Plugin plugin, ISimpleObjective objective, int score, String before, Block block) {
		this(plugin, objective, new OrientationEntry(score, before, block));
	}

	/**
	 * Create an entry that displays the orientation to follow to reach the given block.
	 * 
	 * @param plugin    The plugin to register this entry as event listener.
	 * @param objective The objective associated to the source entry.
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the orientation to follow.
	 * @param block     The target block.
	 */
	public OrientationAutoUpdater(Plugin plugin, ISimpleObjective objective, int score, String before, Block block, String after) {
		this(plugin, objective, new OrientationEntry(score, before, block, after));
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		if (!event.getPlayer().getName().equals(getObjective().getPlayer().getName()))
			return;

		// Updating player objective each 2 calls.
		call++;
		if (call > 2) {
			update();
			call = 0;
		}
	}
}
