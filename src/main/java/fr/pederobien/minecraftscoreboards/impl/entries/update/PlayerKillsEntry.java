package fr.pederobien.minecraftscoreboards.impl.entries.update;

import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.pederobien.minecraftscoreboards.impl.entries.common.StatisticEntry;

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

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		if (event.getEntity().getKiller().getName().equals(getObjective().getPlayer().getName()))
			return;
	}
}
