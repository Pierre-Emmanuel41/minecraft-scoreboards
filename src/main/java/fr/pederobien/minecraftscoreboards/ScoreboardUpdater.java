package fr.pederobien.minecraftscoreboards;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftmanagers.PlayerManager;
import fr.pederobien.minecraftmanagers.ScoreboardManager;
import fr.pederobien.minecraftscoreboards.impl.ScoreboardUpdate;
import fr.pederobien.minecraftscoreboards.interfaces.IObjective;
import fr.pederobien.minecraftscoreboards.interfaces.IScoreboardUpdate;

public class ScoreboardUpdater extends ScoreboardManager {
	private static final List<IScoreboardUpdate> UPDATES = new ArrayList<IScoreboardUpdate>();
	private static boolean isRunning;

	/**
	 * Register an update for the given objective.
	 * 
	 * @param plugin    the reference to the plugin scheduling task.
	 * @param objective The objective to update.
	 */
	public static IScoreboardUpdate register(Plugin plugin, IObjective objective) {
		return register(plugin, objective, Long.MIN_VALUE, Long.MIN_VALUE);
	}

	/**
	 * Register an update for the given objective.
	 * 
	 * @param plugin    the reference to the plugin scheduling task.
	 * @param objective The objective to update.
	 * @param delay     The number of ticks to wait before running the task.
	 */
	public static IScoreboardUpdate register(Plugin plugin, IObjective objective, long delay) {
		return register(plugin, objective, Long.MIN_VALUE, Long.MIN_VALUE);
	}

	/**
	 * Register an update for the given objective.
	 * 
	 * @param plugin    the reference to the plugin scheduling task.
	 * @param objective The objective to update.
	 * @param delay     The number of ticks to wait before running the task.
	 * @param period    the number of ticks to wait between two updates.
	 */
	public static IScoreboardUpdate register(Plugin plugin, IObjective objective, long delay, long period) {
		IScoreboardUpdate update = new ScoreboardUpdate(plugin, objective, delay, period);
		register(update);
		return update;
	}

	/**
	 * Append the given update to the list of updates. If the updater is running then the update is also updated.
	 * 
	 * @param update The update used to update its objective.
	 */
	public static void register(IScoreboardUpdate update) {
		UPDATES.add(update);
		if (isRunning)
			update.update();
	}

	/**
	 * Start the update of each registered objective.
	 */
	public static void start() {
		if (isRunning)
			return;

		for (IScoreboardUpdate update : UPDATES)
			update.update();
		isRunning = true;
	}

	/**
	 * Stop the update of each registered objective.
	 * 
	 * @param removeScoreboard True if the scoreboard of each player should be removed, false otherwise.
	 */
	public static void stop(boolean removeScoreboard) {
		if (!isRunning)
			return;

		for (IScoreboardUpdate update : UPDATES)
			update.stop();

		if (removeScoreboard)
			PlayerManager.getPlayers().forEach(player -> removePlayerScoreboard(player));

		isRunning = false;
	}
}
