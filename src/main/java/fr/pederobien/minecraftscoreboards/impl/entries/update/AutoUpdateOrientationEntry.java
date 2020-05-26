package fr.pederobien.minecraftscoreboards.impl.entries.update;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.pederobien.minecraftscoreboards.impl.entries.common.OrientationEntry;

public class AutoUpdateOrientationEntry extends OrientationEntry {
	private int call;

	/**
	 * Create an entry that displays the orientation to follow to reach the given block.
	 * 
	 * @param score  The line number of this entry.
	 * @param before The sequence of characters to be displayed before the orientation to follow.
	 * @param block  The target block.
	 * @param after  The sequence of characters to be displayed after the orientation to follow.
	 */
	public AutoUpdateOrientationEntry(int score, String before, Block block, String after) {
		super(score, before, block, after);
		call = 0;
	}

	/**
	 * Create an entry that displays the orientation to follow to reach the given block.
	 * 
	 * @param score  The line number of this entry.
	 * @param before The sequence of characters to be displayed before the orientation to follow.
	 * @param block  The target block.
	 */
	public AutoUpdateOrientationEntry(int score, String before, Block block) {
		this(score, before, block, "");
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		/*
		 * if (!event.getPlayer().getName().equals(getObjective().getPlayer().getName())) return;
		 * 
		 * // Updating player objective each 2 calls. call++; if (call > 2) { call = 0; }
		 */
	}
}
