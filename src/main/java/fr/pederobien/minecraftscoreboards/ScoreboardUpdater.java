package fr.pederobien.minecraftscoreboards;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftmanagers.PlayerManager;
import fr.pederobien.minecraftmanagers.ScoreboardManager;
import fr.pederobien.minecraftscoreboards.interfaces.IUpdateObjective;

public class ScoreboardUpdater extends ScoreboardManager {
	private static final List<IUpdateObjective> UPDATES = new ArrayList<IUpdateObjective>();
	private static boolean isRunning;
	private static InternalListener listener;
	private static Plugin plugin;

	/**
	 * Register the given objective to be updated when calling method {@link #start()}. If this updater is running then the objective
	 * is updated.
	 * 
	 * @param objective The objective to update.
	 */
	public static void register(IUpdateObjective objective) {
		UPDATES.add(objective);
		if (isRunning) {
			objective.initialize();
			objective.start();
		}
	}

	/**
	 * Unregister the given objective to be updated when calling method {@link #start()}. If this updater is running then the method
	 * {@link IUpdateObjective#stop()} is called.
	 * 
	 * @param objective The objective to unregister.
	 */
	public static void unregister(IUpdateObjective objective) {
		if (UPDATES.remove(objective) && isRunning)
			objective.stop();
	}

	/**
	 * Remove all registered objective. If this updater is running then the method {@link IUpdateObjective#stop()} is called.
	 */
	public static void clear() {
		if (isRunning)
			UPDATES.forEach(obj -> obj.stop());
		UPDATES.clear();
	}

	/**
	 * Start the update of each registered objective.
	 */
	public static void start(Plugin plugin) {
		if (isRunning)
			return;

		if (ScoreboardUpdater.plugin == null || !ScoreboardUpdater.plugin.equals(plugin))
			listener = new InternalListener();

		ScoreboardUpdater.plugin = plugin;
		listener.setIgnore(false);
		plugin.getServer().getPluginManager().registerEvents(listener, plugin);

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

		listener.setIgnore(true);

		isRunning = false;
	}

	private static class InternalListener implements Listener {
		private boolean ignore = false;

		@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
		public void onPlayerJoinEvent(PlayerJoinEvent event) {
			if (!isRunning || ignore)
				return;

			UPDATES.forEach(obj -> {
				if (obj.getPlayer().getName().equals(event.getPlayer().getName())) {
					event.getPlayer().setScoreboard(obj.getScoreboard().get());
					obj.setPlayer(event.getPlayer());
				}
			});
		}

		public void setIgnore(boolean ignore) {
			this.ignore = ignore;
		}
	}
}
