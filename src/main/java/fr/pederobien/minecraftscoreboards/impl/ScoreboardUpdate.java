package fr.pederobien.minecraftscoreboards.impl;

import java.util.stream.Stream;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftmanagers.PlayerManager;
import fr.pederobien.minecraftmanagers.ScoreboardManager;
import fr.pederobien.minecraftscoreboards.exceptions.ObjectiveNotAttachedException;
import fr.pederobien.minecraftscoreboards.interfaces.IObjective;
import fr.pederobien.minecraftscoreboards.interfaces.IScoreboardUpdate;

public class ScoreboardUpdate extends MinecraftRunnable implements IScoreboardUpdate {
	private Plugin plugin;
	private IObjective objective;
	private long delay, period;
	private Stream<Player> players;

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
	public ScoreboardUpdate(Plugin plugin, IObjective objective, long delay, long period) {
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
	public void update() {
		if (!getObjective().getScoreboard().isPresent())
			throw new ObjectiveNotAttachedException(getObjective());

		players = PlayerManager.getPlayers();
		ScoreboardManager.setPlayersScoreboard(players, getObjective().getScoreboard().get());

		if (period == Long.MIN_VALUE && delay == Long.MIN_VALUE)
			runTask(getPlugin());
		else if (period == Long.MIN_VALUE)
			runTaskLater(getPlugin(), getDelay());
		else
			runTaskTimer(getPlugin(), getDelay(), getPeriod());
	}

	@Override
	public void run() {
		PlayerManager.getPlayers().peek(player -> getObjective().update(player));
	}

	@Override
	public void stop() {
		cancel();
	}
}
