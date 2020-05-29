package fr.pederobien.minecraftscoreboards.impl.entries.periodic;

import org.bukkit.GameMode;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Team;

import fr.pederobien.minecraftscoreboards.impl.PeriodicUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.TeamPlayerOnModeEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class TeamPlayerOnModePeriodicUpdater extends PeriodicUpdater<TeamPlayerOnModeEntry> {

	/**
	 * Create an entry updater. This entry is responsible to update the source entry.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 */
	public TeamPlayerOnModePeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, TeamPlayerOnModeEntry source) {
		super(plugin, objective, delay, period, source);
	}

	/**
	 * Create an entry updater. This entry is responsible to update the source entry.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 */
	public TeamPlayerOnModePeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, TeamPlayerOnModeEntry source) {
		super(plugin, objective, 0, period, source);
	}

	/**
	 * Create an entry that display the number of player in the given team that have the given game mode.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param team      The team associated to this entry.
	 * @param mode      The player game mode used to filter team players.
	 * @param after     The sequence of characters to be displayed after the player number.
	 * @param colored   True if the team name is colored, false otherwise.
	 */
	public TeamPlayerOnModePeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, int score, Team team, GameMode mode, String after,
			boolean colored) {
		this(plugin, objective, delay, period, new TeamPlayerOnModeEntry(score, team, mode, after, colored));
	}

	/**
	 * Create an entry that display the number of player in the given team that have the given game mode.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param team      The team associated to this entry.
	 * @param mode      The player game mode used to filter team players.
	 * @param after     The sequence of characters to be displayed after the player number.
	 * @param colored   True if the team name is colored, false otherwise.
	 */
	public TeamPlayerOnModePeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, int score, Team team, GameMode mode, String after, boolean colored) {
		this(plugin, objective, period, new TeamPlayerOnModeEntry(score, team, mode, after, colored));
	}

	/**
	 * Create an entry that display the number of player in the given team that have the given game mode.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param team      The team associated to this entry.
	 * @param mode      The player game mode used to filter team players.
	 * @param colored   True if the team name is colored, false otherwise.
	 */
	public TeamPlayerOnModePeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, int score, Team team, GameMode mode, boolean colored) {
		this(plugin, objective, delay, period, new TeamPlayerOnModeEntry(score, team, mode, colored));
	}

	/**
	 * Create an entry that display the number of player in the given team that have the given game mode.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param team      The team associated to this entry.
	 * @param mode      The player game mode used to filter team players.
	 * @param colored   True if the team name is colored, false otherwise.
	 */
	public TeamPlayerOnModePeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, int score, Team team, GameMode mode, boolean colored) {
		this(plugin, objective, period, new TeamPlayerOnModeEntry(score, team, mode, colored));
	}

	/**
	 * Create an entry that display the number of player in the given team that have the given game mode.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param team      The team associated to this entry.
	 * @param mode      The player game mode used to filter team players.
	 * @param after     The sequence of characters to be displayed after the player number.
	 */
	public TeamPlayerOnModePeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, int score, Team team, GameMode mode, String after) {
		this(plugin, objective, delay, period, new TeamPlayerOnModeEntry(score, team, mode, after));
	}

	/**
	 * Create an entry that display the number of player in the given team that have the given game mode.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param team      The team associated to this entry.
	 * @param mode      The player game mode used to filter team players.
	 * @param after     The sequence of characters to be displayed after the player number.
	 */
	public TeamPlayerOnModePeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, int score, Team team, GameMode mode, String after) {
		this(plugin, objective, period, new TeamPlayerOnModeEntry(score, team, mode, after));
	}

	/**
	 * Create an entry that display the number of player in the given team that have the given game mode.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param team      The team associated to this entry.
	 * @param mode      The player game mode used to filter team players.
	 */
	public TeamPlayerOnModePeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, int score, Team team, GameMode mode) {
		this(plugin, objective, delay, period, new TeamPlayerOnModeEntry(score, team, mode));
	}

	/**
	 * Create an entry that display the number of player in the given team that have the given game mode.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param team      The team associated to this entry.
	 * @param mode      The player game mode used to filter team players.
	 */
	public TeamPlayerOnModePeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, int score, Team team, GameMode mode) {
		this(plugin, objective, period, new TeamPlayerOnModeEntry(score, team, mode));
	}
}
