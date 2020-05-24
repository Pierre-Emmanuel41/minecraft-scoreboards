package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.IObjective;
import fr.pederobien.minecraftscoreboards.interfaces.IScoreboardUpdate;

public abstract class AbstractScoreboardUpdate extends MinecraftRunnable implements IScoreboardUpdate, Listener {
	private Plugin plugin;
	private IObjective objective;
	private long delay, period;
	private boolean isRegistered, isRunning;

	/**
	 * Create a scheduled scoreboard update. If period and delay equals {@link Long#MIN_VALUE} then the update is scheduled to run on
	 * the next tick when calling {@link #update()}. If only the period equals {@link Long#MIN_VALUE} then the update is scheduled to
	 * run after the number of server ticks. If non of them equals {@link Long#MIN_VALUE} then
	 * 
	 * @param plugin    the reference to the plugin scheduling task.
	 * @param objective The objective to update.
	 * @param delay     The number of ticks to wait before running the task.
	 * @param period    the number of ticks to wait between two updates.
	 */
	protected AbstractScoreboardUpdate(Plugin plugin, IObjective objective, long delay, long period) {
		this.plugin = plugin;
		this.objective = objective;
		this.delay = delay;
		this.period = period;

		isRegistered = false;
	}

	@Override
	public Plugin getPlugin() {
		return plugin;
	}

	@Override
	public IObjective getObjective() {
		return objective;
	}

	@Override
	public long getDelay() {
		return delay;
	}

	@Override
	public long getPeriod() {
		return period;
	}

	@Override
	public final void start() {
		if (!isRegistered)
			getPlugin().getServer().getPluginManager().registerEvents(this, getPlugin());
		onStart();
		isRunning = true;
	}

	@Override
	public final void stop() {
		cancel();
		for (IEntry entry : objective.entries())
			getObjective().getScoreboard().get().resetScores(entry.getCurrentValue());
		isRunning = false;
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		if (isRunning && event.getPlayer().getName().equals(event.getPlayer().getName()))
			getObjective().setPlayer(event.getPlayer());
	}

	/**
	 * Because method {@link #start()} is declared final, this is the only way to start the objective update.
	 */
	protected abstract void onStart();
}
