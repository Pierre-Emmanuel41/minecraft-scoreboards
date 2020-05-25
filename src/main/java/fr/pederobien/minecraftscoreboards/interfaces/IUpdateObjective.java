package fr.pederobien.minecraftscoreboards.interfaces;

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
}
