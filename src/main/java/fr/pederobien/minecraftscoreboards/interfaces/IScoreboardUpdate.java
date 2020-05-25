package fr.pederobien.minecraftscoreboards.interfaces;

import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;

import fr.pederobien.minecraftscoreboards.exceptions.ObjectiveNotAttachedException;

public interface IScoreboardUpdate {

	/**
	 * @return plugin the reference to the plugin scheduling task.
	 */
	Plugin getPlugin();

	/**
	 * @return The objective to update.
	 */
	ISimpleObjective getObjective();

	/**
	 * @return The number of ticks to wait before running the task.
	 */
	long getDelay();

	/**
	 * @return period the number of ticks to wait between two updates.
	 */
	long getPeriod();

	/**
	 * Start the objective update based on the plugin, delay and the period.
	 * 
	 * @throws ObjectiveNotAttachedException If the objective to update is not attached to a {@link Scoreboard}
	 */
	void start();

	/**
	 * Stop the update of the objective.
	 */
	void stop();
}
