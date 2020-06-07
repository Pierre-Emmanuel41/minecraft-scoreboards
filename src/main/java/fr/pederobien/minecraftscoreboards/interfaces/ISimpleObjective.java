package fr.pederobien.minecraftscoreboards.interfaces;

import java.util.Optional;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public interface ISimpleObjective {

	/**
	 * @return The plugin used to update this objective.
	 */
	Plugin getPlugin();

	/**
	 * @return The player tracked by this objective.
	 */
	Player getPlayer();

	/**
	 * Set the player tracked by this objective. Normally this method should be never called. But when a player quit and join the
	 * server during an update, the player lose its scoreboard.
	 * 
	 * @param player The player to track.
	 */
	void setPlayer(Player player);

	/**
	 * @return The name of this objective.
	 */
	String getName();

	/**
	 * Gets the criteria this objective tracks.
	 * 
	 * @return this objective's criteria.
	 */
	String getCriteria();

	/**
	 * Gets the name displayed to players for this objective.
	 * 
	 * @return this objective's display name
	 */
	String getDisplayName();

	/**
	 * Gets the display slot this objective is displayed at.
	 *
	 * @return the display slot for this objective, or null if not displayed
	 */
	DisplaySlot getDisplaySlot();

	/**
	 * Set the scoreboard of this objective. This objective is also attached to this scoreboard.
	 * 
	 * @param scoreboard The scoreboard attached to this objective.
	 */
	void setScoreboard(Scoreboard scoreboard);

	/**
	 * Gets the scoreboard to which this objective is attached if it exist.
	 * 
	 * @return An optional that contains the scoreboard to which this objective is attached if it exist, an empty optional otherwise.
	 */
	Optional<Scoreboard> getScoreboard();

	/**
	 * Get the bukkit objective equivalent of this objective.
	 * 
	 * @return An optional that contains the bukkit objective equivalent if this objective is attached to a scoreboard, an empty
	 *         optional otherwise.
	 */
	Optional<Objective> getObjective();

	/**
	 * Update this objective for the tracked player. This method reset the objective for each entry old value and then display each
	 * entry current value.
	 */
	void update();

	/**
	 * Update this objective for the tracked player and the specified entry.
	 * 
	 * @param entry The entry to update in this objective.
	 */
	void update(IEntry entry);
}
