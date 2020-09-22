package fr.pederobien.minecraftscoreboards;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftmanagers.PlayerManager;
import fr.pederobien.minecraftmanagers.ScoreboardManager;
import fr.pederobien.minecraftscoreboards.interfaces.IObjective;
import fr.pederobien.minecraftscoreboards.interfaces.IObjectiveUpdater;
import fr.pederobien.minecraftscoreboards.interfaces.IUpdateObjective;

public class ObjectiveUpdater extends ScoreboardManager implements IObjectiveUpdater {
	private List<IObjective> updates;
	private boolean isRunning;
	private InternalListener listener;
	private Plugin plugin;

	private ObjectiveUpdater(Plugin plugin) {
		this.plugin = plugin;
		updates = new ArrayList<IObjective>();
		listener = new InternalListener();
	}

	/**
	 * Get an instance of an objective updater. This updater is responsible of the update of each registered objective. When the
	 * method {@link #start()} is called, if a player quit and join the server, this updater set the joining player score board. If
	 * the method {@link #stop(boolean)} is called, the objective update is stopped and when a player quit and join, nothing happen on
	 * the updater side.
	 * 
	 * @param plugin The plugin to which this objective updater is associated.
	 * @return An objective updater.
	 */
	public static IObjectiveUpdater getInstance(Plugin plugin) {
		return new ObjectiveUpdater(plugin);
	}

	@Override
	public void register(IObjective objective) {
		updates.add(objective);
		if (isRunning) {
			objective.initialize();
			objective.start();
		}
	}

	@Override
	public void unregister(IObjective objective) {
		if (updates.remove(objective) && isRunning)
			objective.stop();
	}

	@Override
	public void clear() {
		if (isRunning)
			updates.forEach(obj -> obj.stop());
		updates.clear();
	}

	@Override
	public void start() {
		if (isRunning)
			return;

		for (IUpdateObjective update : updates) {
			update.initialize();
			update.start();
		}

		listener.setIgnore(false);
		isRunning = true;
	}

	@Override
	public void stop(boolean removeScoreboard) {
		if (!isRunning)
			return;

		for (IUpdateObjective update : updates)
			update.stop();

		if (removeScoreboard)
			PlayerManager.getPlayers().forEach(player -> removePlayerScoreboard(player));

		listener.setIgnore(true);
		isRunning = false;
	}

	@Override
	public Optional<IObjective> getObjective(Player player) {
		for (IObjective obj : updates)
			if (obj.getPlayer().equals(player))
				return Optional.of(obj);
		return Optional.empty();
	}

	private class InternalListener implements Listener {
		private boolean ignore;

		public InternalListener() {
			ignore = false;
			plugin.getServer().getPluginManager().registerEvents(this, plugin);
		}

		@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
		public void onPlayerJoinEvent(PlayerJoinEvent event) {
			if (!isRunning || ignore)
				return;

			updates.forEach(obj -> {
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
