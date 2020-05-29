package fr.pederobien.minecraftscoreboards.interfaces;

public interface IObjectiveUpdater {

	/**
	 * Register the given objective to be updated when calling method {@link #start()}. If this updater is running then the objective
	 * is updated.
	 * 
	 * @param objective The objective to update.
	 */
	void register(IUpdateObjective objective);

	/**
	 * Unregister the given objective to be updated when calling method {@link #start()}. If this updater is running then the method
	 * {@link IUpdateObjective#stop()} is called.
	 * 
	 * @param objective The objective to unregister.
	 */
	void unregister(IUpdateObjective objective);

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
}
