package fr.pederobien.minecraftscoreboards.impl.entries.update;

import java.util.Optional;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Team;

import fr.pederobien.minecraftmanagers.TeamManager;
import fr.pederobien.minecraftscoreboards.impl.AutoUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.common.TeamPlayerOnModeEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class TeamPlayerOnModeAutoUpdater extends AutoUpdater<TeamPlayerOnModeEntry> {

	/**
	 * Create an entry updater. This entry is responsible to update the source entry. This updater is a
	 * {@link PlayerGameModeChangeEvent} listener. This means that when an this event is thrown by the server, this updater update the
	 * player objective.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param source    The source tracked by this updater.
	 */
	public TeamPlayerOnModeAutoUpdater(Plugin plugin, ISimpleObjective objective, TeamPlayerOnModeEntry source) {
		super(plugin, objective, source);
	}

	/**
	 * Create an entry updater. This entry is responsible to update the source entry. This updater is a
	 * {@link PlayerGameModeChangeEvent} listener. This means that when an this event is thrown by the server, this updater update the
	 * player objective.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param score     The line number of this entry.
	 * @param team      The team associated to this entry.
	 * @param mode      The player game mode used to filter team players.
	 * @param after     The sequence of characters to be displayed after the player number.
	 * @param colored   True if the team name is colored, false otherwise.
	 */
	public TeamPlayerOnModeAutoUpdater(Plugin plugin, ISimpleObjective objective, int score, Team team, GameMode mode, String after, boolean colored) {
		this(plugin, objective, new TeamPlayerOnModeEntry(score, team, mode, after, colored));
	}

	/**
	 * Create an entry updater. This entry is responsible to update the source entry. This updater is a
	 * {@link PlayerGameModeChangeEvent} listener. This means that when an this event is thrown by the server, this updater update the
	 * player objective.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param score     The line number of this entry.
	 * @param team      The team associated to this entry.
	 * @param mode      The player game mode used to filter team players.
	 * @param colored   True if the team name is colored, false otherwise.
	 */
	public TeamPlayerOnModeAutoUpdater(Plugin plugin, ISimpleObjective objective, int score, Team team, GameMode mode, boolean colored) {
		this(plugin, objective, new TeamPlayerOnModeEntry(score, team, mode, colored));
	}

	/**
	 * Create an entry updater. This entry is responsible to update the source entry. This updater is a
	 * {@link PlayerGameModeChangeEvent} listener. This means that when an this event is thrown by the server, this updater update the
	 * player objective.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param score     The line number of this entry.
	 * @param team      The team associated to this entry.
	 * @param mode      The player game mode used to filter team players.
	 * @param after     The sequence of characters to be displayed after the player number.
	 */
	public TeamPlayerOnModeAutoUpdater(Plugin plugin, ISimpleObjective objective, int score, Team team, GameMode mode, String after) {
		this(plugin, objective, new TeamPlayerOnModeEntry(score, team, mode, after));
	}

	/**
	 * Create an entry updater. This entry is responsible to update the source entry. This updater is a
	 * {@link PlayerGameModeChangeEvent} listener. This means that when an this event is thrown by the server, this updater update the
	 * player objective.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param score     The line number of this entry.
	 * @param team      The team associated to this entry.
	 * @param mode      The player game mode used to filter team players.
	 */
	public TeamPlayerOnModeAutoUpdater(Plugin plugin, ISimpleObjective objective, int score, Team team, GameMode mode) {
		this(plugin, objective, new TeamPlayerOnModeEntry(score, team, mode));
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerDeathEvent(PlayerGameModeChangeEvent event) {
		Optional<Team> optTeam = TeamManager.getTeam(event.getPlayer());
		if (optTeam.isPresent() && optTeam.get().equals(getSource().getTeam()))
			update();
	}
}
