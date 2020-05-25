package fr.pederobien.minecraftscoreboards.impl.entries.common;

import org.bukkit.Material;
import org.bukkit.Statistic;

public class MinedBlockEntry extends StatisticEntry {

	/**
	 * Create an entry updated when a player mine a block of the given {@link Material}.
	 * 
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * @param before     The sequence of characters to be displayed before the player statistic.
	 * @param after      The sequence of characters to be displayed after the player statistic.
	 */
	public MinedBlockEntry(int score, Material material, String before, String after) {
		super(score, Statistic.MINE_BLOCK, material, before, after);
	}

	/**
	 * Create an entry updated when a player mine a block of the given {@link Material}.
	 * 
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * @param before     The sequence of characters to be displayed before the player statistic.
	 */
	public MinedBlockEntry(int score, Material material, String before) {
		super(score, Statistic.MINE_BLOCK, material, before);
	}
}