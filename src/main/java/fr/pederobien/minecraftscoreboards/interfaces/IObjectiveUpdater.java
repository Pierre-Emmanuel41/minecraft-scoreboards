package fr.pederobien.minecraftscoreboards.interfaces;

import java.util.Optional;

import org.bukkit.entity.Player;

public interface IObjectiveUpdater {

	/**
	 * Register the given objective to be updated when calling method {@link #start()}. If this updater is running then the objective
	 * is updated.
	 * 
	 * @param objective The objective to update.
	 */
	void register(IObjective objective);

	/**
	 * Unregister the given objective to be updated when calling method {@link #start()}. If this updater is running then the method
	 * {@link IUpdateObjective#stop()} is called.
	 * 
	 * @param objective The objective to unregister.
	 */
	void unregister(IObjective objective);

	/**
	 * Remove all registered objective. If this updater is running then the method {@link IUpdateObjective#stop()} is called on each
	 * registered objective.
	 */
	void clear();

	/**
	 * Start the update of each registered objective.
	 */
	void start();

	/**
	 * Stop the update of each registered objective.
	 * 
	 * @param removeScoreboard True if the score board of each player should be removed, false otherwise.
	 */
	void stop(boolean removeScoreboard);

	/**
	 * Get the given player's objective if its objective is registered.
	 * 
	 * @param player The player used to get its objective.
	 * 
	 * @return An optional that contains the player's objective if registered, an empty optional otherwise.
	 */
	Optional<IObjective> getObjective(Player player);
}
