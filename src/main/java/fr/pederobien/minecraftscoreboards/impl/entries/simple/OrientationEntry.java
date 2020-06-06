package fr.pederobien.minecraftscoreboards.impl.entries.simple;

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
	 * @param score The line number of this entry.
	 * @param block The target block.
	 */
	public OrientationEntry(int score, Block block) {
		super(score);
		this.block = block;
	}

	@Override
	protected String updateCurrentValue(Player player) {
		return EArrows.getArrow(WorldManager.getYaw(player, block.getLocation())).getUnicode();
	}
}
