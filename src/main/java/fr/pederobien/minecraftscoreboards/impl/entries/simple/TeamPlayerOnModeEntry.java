package fr.pederobien.minecraftscoreboards.impl.entries.simple;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import fr.pederobien.minecraftmanagers.TeamManager;
import fr.pederobien.minecraftscoreboards.impl.AbstractSimpleEntry;

public class TeamPlayerOnModeEntry extends AbstractSimpleEntry {
	private Team team;
	private GameMode mode;

	/**
	 * Create an entry that display the number of player in the given team that have the given game mode.
	 * 
	 * @param score   The line number of this entry.
	 * @param team    The team associated to this entry.
	 * @param mode    The player game mode used to filter team players.
	 * @param after   The sequence of characters to be displayed after the player number.
	 * @param colored True if the team name is colored, false otherwise.
	 */
	public TeamPlayerOnModeEntry(int score, Team team, GameMode mode, String after, boolean colored) {
		super(score, (colored ? team.getColor() + team.getName() + ChatColor.RESET : team.getName()) + " : ", after);
		this.team = team;
		this.mode = mode;
	}

	/**
	 * Create an entry that display the number of player in the given team that have the given game mode.
	 * 
	 * @param score The line number of this entry.
	 * @param team  The team associated to this entry.
	 * @param mode  The player game mode used to filter team players.
	 * @param after The sequence of characters to be displayed after the player number.
	 */
	public TeamPlayerOnModeEntry(int score, Team team, GameMode mode, String after) {
		this(score, team, mode, after, true);
	}

	/**
	 * Create an entry that display the number of player in the given team that have the given game mode.
	 * 
	 * @param score   The line number of this entry.
	 * @param team    The team associated to this entry.
	 * @param mode    The player game mode used to filter team players.
	 * @param colored True if the team name is colored, false otherwise.
	 */
	public TeamPlayerOnModeEntry(int score, Team team, GameMode mode, boolean colored) {
		this(score, team, mode, "", colored);
	}

	/**
	 * Create an entry that display the number of player in the given team that have the given game mode.
	 * 
	 * @param score The line number of this entry.
	 * @param team  The team associated to this entry.
	 * @param mode  The player game mode used to filter team players.
	 */
	public TeamPlayerOnModeEntry(int score, Team team, GameMode mode) {
		this(score, team, mode, "", true);
	}

	@Override
	protected String updateCurrentValue(Player player) {
		return "" + TeamManager.getNumberTeamPlayersOnMode(team, mode);
	}

	/**
	 * @return The team associated to this entry.
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * 
	 * @return
	 */
	public GameMode getGameMode() {
		return mode;
	}
}
