package fr.pederobien.minecraftscoreboards.impl.entries.common;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import fr.pederobien.minecraftmanagers.EArrows;
import fr.pederobien.minecraftmanagers.WorldManager;
import fr.pederobien.minecraftscoreboards.impl.AbstractSimpleEntry;

public class OrientationEntry extends AbstractSimpleEntry {
	private Block block;

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

	/**
	 * Create an entry that displays the orientation to follow to reach the given block.
	 * 
	 * @param score  The line number of this entry.
	 * @param before The sequence of characters to be displayed before the orientation to follow.
	 * @param block  The target block.
	 * @param before The sequence of characters to be displayed after the orientation to follow.
	 */
	public OrientationEntry(int score, String before, Block block, String after) {
		super(score, before, after);
		this.block = block;
	}

	@Override
	protected String updateCurrentValue(Player player) {
		return EArrows.getArrow(WorldManager.getYaw(player, block.getLocation())).getUnicode();
	}
}
