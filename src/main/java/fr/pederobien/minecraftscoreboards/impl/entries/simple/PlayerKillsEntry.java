package fr.pederobien.minecraftscoreboards.impl.entries.simple;

import org.bukkit.Statistic;

public class PlayerKillsEntry extends StatisticEntry {

	/**
	 * Create an entry that is updated when a player kills another player.
	 * 
	 * @param score  The line number of this entry.
	 * @param before The sequence of characters to be displayed before the player kill number.
	 */
	public PlayerKillsEntry(int score, String before) {
		super(score, Statistic.PLAYER_KILLS, before);
	}

	/**
	 * Create an entry that is updated when a player kills another player.
	 * 
	 * @param score  The line number of this entry.
	 * @param before The sequence of characters to be displayed before the player kill number.
	 * @param after  The sequence of characters to be displayed after the player kill number.
	 */
	public PlayerKillsEntry(int score, String before, String after) {
		super(score, Statistic.PLAYER_KILLS, before, after);
	}
}
