package fr.pederobien.minecraftscoreboards.impl.entries.auto;

import java.util.Optional;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.scoreboard.Team;

import fr.pederobien.minecraftmanagers.TeamManager;
import fr.pederobien.minecraftscoreboards.impl.AutoUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.TeamPlayerOnModeEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class TeamPlayerOnModeAutoUpdater extends AutoUpdater<TeamPlayerOnModeEntry> {

	/**
	 * Create an entry updated when a {@link PlayerGameModeChangeEvent} is thrown by the server.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param source    The source tracked by this updater.
	 * 
	 * @see TeamPlayerOnModeEntry
	 */
	public TeamPlayerOnModeAutoUpdater(ISimpleObjective objective, TeamPlayerOnModeEntry source) {
		super(objective, source);
	}

	/**
	 * Create an entry updated when a {@link PlayerGameModeChangeEvent} is thrown by the server. This entry displays the number of
	 * player in the given team who have the given game mode.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param score     The line number of this entry.
	 * @param team      The team associated to this entry.
	 * @param mode      The player game mode used to filter team players.
	 * @param colored   True if the team name is colored, false otherwise.
	 * 
	 * @see TeamPlayerOnModeEntry
	 */
	public TeamPlayerOnModeAutoUpdater(ISimpleObjective objective, int score, Team team, GameMode mode, boolean colored) {
		super(objective, new TeamPlayerOnModeEntry(score, team, mode, colored));
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerDeathEvent(PlayerGameModeChangeEvent event) {
		Optional<Team> optTeam = TeamManager.getTeam(event.getPlayer());
		if (optTeam.isPresent() && optTeam.get().equals(getSource().getTeam()))
			update();
	}
}
