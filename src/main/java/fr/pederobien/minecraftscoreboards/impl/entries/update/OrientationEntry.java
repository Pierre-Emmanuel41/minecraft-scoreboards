package fr.pederobien.minecraftscoreboards.impl.entries.update;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.pederobien.minecraftmanagers.EArrows;
import fr.pederobien.minecraftmanagers.WorldManager;
import fr.pederobien.minecraftscoreboards.impl.AbstractAutoUpdateEntry;

public class OrientationEntry extends AbstractAutoUpdateEntry {
	private String before;
	private Block block;
	private int call;

	/**
	 * Create an entry that displays the orientation to follow to reach the given block.
	 * 
	 * @param score  The line number of this entry.
	 * @param before The sequence of characters to be displayed before the orientation to follow.
	 * @param block  The target block.
	 */
	public OrientationEntry(int score, String before, Block block) {
		super(score);
		this.before = before;
		this.block = block;
		call = 0;
	}

	@Override
	protected String onInitialize(Player player) {
		return updateCurrentValue(player);
	}

	@Override
	protected String updateCurrentValue(Player player) {
		return before + EArrows.getArrow(WorldManager.getYaw(player, block.getLocation())).getUnicode();
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		if (!event.getPlayer().getName().equals(getObjective().getPlayer().getName()))
			return;

		// Updating player objective each 3 calls.
		call++;
		if (call > 2) {
			update();
			call = 0;
		}
	}
}
