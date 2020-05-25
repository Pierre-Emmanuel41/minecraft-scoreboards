package fr.pederobien.minecraftscoreboards.interfaces;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public interface ISimpleObjective {

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
}
