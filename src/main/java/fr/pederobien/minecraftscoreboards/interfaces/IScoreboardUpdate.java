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
	IObjective getObjective();

	/**
	 * @return The number of ticks to wait before running the task.
	 */
	long getDelay();

	/**
	 * @return period the number of ticks to wait between two updates.
	 */
	long getPeriod();

	/**
	 * Update the scoreboard based on the plugin, objective, delay and the period.
	 * 
	 * @throws ObjectiveNotAttachedException If the objective to update is not attached to a {@link Scoreboard}
	 */
	void update();

	/**
	 * Stop the update of the objective.
	 */
	void stop();
}
