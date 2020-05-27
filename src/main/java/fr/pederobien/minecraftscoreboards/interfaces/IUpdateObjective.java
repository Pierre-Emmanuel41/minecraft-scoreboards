package fr.pederobien.minecraftscoreboards.interfaces;

import java.util.Optional;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

public interface IUpdateObjective {

	/**
	 * Initialize this objective before starting the update.
	 */
	void initialize();

	/**
	 * Start the update of this objective.
	 */
	void start();

	/**
	 * Stop the update of this objective.
	 */
	void stop();

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
	 * Gets the scoreboard to which this objective is attached if it exist.
	 * 
	 * @return An optional that contains the scoreboard to which this objective is attached if it exist, an empty optional otherwise.
	 */
	Optional<Scoreboard> getScoreboard();
}
