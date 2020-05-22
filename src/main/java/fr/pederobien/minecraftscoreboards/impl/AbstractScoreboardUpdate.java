package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.interfaces.IObjective;
import fr.pederobien.minecraftscoreboards.interfaces.IScoreboardUpdate;

public abstract class AbstractScoreboardUpdate extends MinecraftRunnable implements IScoreboardUpdate {
	private Plugin plugin;
	private IObjective objective;
	private long delay, period;

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
	public void stop() {
		cancel();

	}
}
