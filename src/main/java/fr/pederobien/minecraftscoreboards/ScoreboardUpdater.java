package fr.pederobien.minecraftscoreboards;

import java.util.ArrayList;
import java.util.List;

import fr.pederobien.minecraftmanagers.PlayerManager;
import fr.pederobien.minecraftmanagers.ScoreboardManager;
import fr.pederobien.minecraftscoreboards.interfaces.IUpdateObjective;

public class ScoreboardUpdater extends ScoreboardManager {
	private static final List<IUpdateObjective> UPDATES = new ArrayList<IUpdateObjective>();
	private static boolean isRunning;

	public static void register(IUpdateObjective objective) {
		UPDATES.add(objective);
		if (isRunning) {
			objective.initialize();
			objective.start();
		}
	}

	/**
	 * Start the update of each registered objective.
	 */
	public static void start() {
		if (isRunning)
			return;

		for (IUpdateObjective update : UPDATES) {
			update.initialize();
			update.start();
		}
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

		for (IUpdateObjective update : UPDATES)
			update.stop();

		if (removeScoreboard)
			PlayerManager.getPlayers().forEach(player -> removePlayerScoreboard(player));

		isRunning = false;
	}
}
