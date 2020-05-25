package fr.pederobien.minecraftscoreboards.impl.entries.update;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.pederobien.minecraftmanagers.EArrows;
import fr.pederobien.minecraftmanagers.WorldManager;
import fr.pederobien.minecraftscoreboards.impl.AbstractEntry;

public class OrientationEntry extends AbstractEntry {
	private Block block;
	private int call;

	/**
	 * Create an entry that displays the orientation to follow to reach the given block.
	 * 
	 * @param score  The line number of this entry.
	 * @param before The sequence of characters to be displayed before the orientation to follow.
	 * @param block  The target block.
	 * @param after  The sequence of characters to be displayed after the orientation to follow.
	 */
	public OrientationEntry(int score, String before, Block block, String after) {
		super(score, before, after);
		this.block = block;
		call = 0;
	}

	/**
	 * Create an entry that displays the orientation to follow to reach the given block.
	 * 
	 * @param score  The line number of this entry.
	 * @param before The sequence of characters to be displayed before the orientation to follow.
	 * @param block  The target block.
	 */
	public OrientationEntry(int score, String before, Block block) {
		this(score, before, block, "");
	}

	@Override
	protected String updateCurrentValue(Player player) {
		return EArrows.getArrow(WorldManager.getYaw(player, block.getLocation())).getUnicode();
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
