package fr.pederobien.minecraftscoreboards.impl;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftmanagers.PlayerManager;
import fr.pederobien.minecraftscoreboards.exceptions.ObjectiveNotAttachedException;
import fr.pederobien.minecraftscoreboards.interfaces.IObjective;
import fr.pederobien.minecraftscoreboards.interfaces.IScoreboardUpdate;

public class ScoreboardUpdate extends AbstractScoreboardUpdate implements IScoreboardUpdate {
	private List<Player> players;

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
		super(plugin, objective, delay, period);
	}

	@Override
	public void update() {
		if (!getObjective().getScoreboard().isPresent())
			throw new ObjectiveNotAttachedException(getObjective());

		players = PlayerManager.getPlayers().collect(Collectors.toList());
		action(player -> player.setScoreboard(getObjective().getScoreboard().get()));

		if (getPeriod() == Long.MIN_VALUE && getDelay() == Long.MIN_VALUE)
			runTask(getPlugin());
		else if (getPeriod() == Long.MIN_VALUE)
			runTaskLater(getPlugin(), getDelay());
		else
			runTaskTimer(getPlugin(), getDelay(), getPeriod());
	}

	@Override
	public void run() {
		action(player -> getObjective().update(player));
	}

	private void action(Consumer<Player> consumer) {
		for (Player player : players)
			consumer.accept(player);
	}
}
