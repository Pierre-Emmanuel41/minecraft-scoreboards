package fr.pederobien.minecraftscoreboards.impl.entries.simple;

import org.bukkit.Statistic;

public class PlayerKillsEntry extends StatisticEntry {

	/**
	 * Create an entry that displays the number of player a player has killed.
	 * 
	 * @param score The line number of this entry.
	 */
	public PlayerKillsEntry(int score) {
		super(score, Statistic.PLAYER_KILLS);
	}
}
