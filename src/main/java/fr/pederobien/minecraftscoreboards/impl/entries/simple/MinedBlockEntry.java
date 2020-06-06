package fr.pederobien.minecraftscoreboards.impl.entries.simple;

import org.bukkit.Material;
import org.bukkit.Statistic;

public class MinedBlockEntry extends StatisticEntry {

	/**
	 * Create an entry that displays the number of block of the given material a player has broken.
	 * 
	 * @param score    The line number of this entry.
	 * @param material The type of block.
	 */
	public MinedBlockEntry(int score, Material material) {
		super(score, Statistic.MINE_BLOCK, material);
	}
}
