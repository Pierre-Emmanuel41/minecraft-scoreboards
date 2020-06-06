package fr.pederobien.minecraftscoreboards.impl.entries.periodic;

import org.bukkit.GameMode;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Team;

import fr.pederobien.minecraftscoreboards.impl.PeriodicUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.TeamPlayerOnModeEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class TeamPlayerOnModePeriodicUpdater extends PeriodicUpdater<TeamPlayerOnModeEntry> {

	/**
	 * Create an entry updated periodically.
	 * 
	 * @param plugin    The plugin to register this entry to be periodically updated.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 * 
	 * @see TeamPlayerOnModeEntry
	 */
	public TeamPlayerOnModePeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, TeamPlayerOnModeEntry source) {
		super(plugin, objective, delay, period, source);
	}

	/**
	 * Create an entry updated periodically. The default value for the delay is 0.
	 * 
	 * @param plugin    The plugin to register this entry to be periodically updated.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 * 
	 * @see TeamPlayerOnModeEntry
	 */
	public TeamPlayerOnModePeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, TeamPlayerOnModeEntry source) {
		super(plugin, objective, 0, period, source);
	}

	/**
	 * Create an entry updated periodically. This entry display the number of player in the given team who have the given game mode.
	 * 
	 * @param plugin    The plugin to register this entry to be periodically updated.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param team      The team associated to this entry.
	 * @param mode      The player game mode used to filter team players.
	 * @param colored   True if the team name is colored, false otherwise.
	 * 
	 * @see TeamPlayerOnModeEntry
	 */
	public TeamPlayerOnModePeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, int score, Team team, GameMode mode, boolean colored) {
		this(plugin, objective, delay, period, new TeamPlayerOnModeEntry(score, team, mode, colored));
	}

	/**
	 * Create an entry updated periodically. The default value for the delay is 0. This entry display the number of player in the
	 * given team who have the given game mode.
	 * 
	 * @param plugin    The plugin to register this entry to be periodically updated.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param team      The team associated to this entry.
	 * @param mode      The player game mode used to filter team players.
	 * @param after     The sequence of characters to be displayed after the player number.
	 * @param colored   True if the team name is colored, false otherwise.
	 * 
	 * @see TeamPlayerOnModeEntry
	 */
	public TeamPlayerOnModePeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, int score, Team team, GameMode mode, boolean colored) {
		this(plugin, objective, period, new TeamPlayerOnModeEntry(score, team, mode, colored));
	}
}
