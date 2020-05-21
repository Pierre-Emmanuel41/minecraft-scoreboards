package fr.pederobien.minecraftscoreboards.impl;

import java.util.function.Function;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public abstract class MinecraftRunnable implements Runnable {
	private boolean isCancelled;
	private int taskId;

	public MinecraftRunnable() {
		isCancelled = false;
	}

	/**
	 * Returns true if this task has been cancelled.
	 *
	 * @return true if the task has been cancelled
	 * @throws IllegalStateException if task was not scheduled yet
	 */
	public synchronized boolean isCancelled() throws IllegalStateException {
		return isCancelled;
	}

	/**
	 * Attempts to cancel this task.
	 *
	 * @throws IllegalStateException if task was not scheduled yet
	 */
	public synchronized void cancel() throws IllegalStateException {
		Bukkit.getScheduler().cancelTask(taskId);
		isCancelled = true;
	}

	/**
	 * Schedules this in the Bukkit scheduler to run on next tick.
	 *
	 * @param plugin the reference to the plugin scheduling task
	 * @return a BukkitTask that contains the id number
	 * @throws IllegalArgumentException if plugin is null
	 * @throws IllegalStateException    if this was already scheduled
	 * @see BukkitScheduler#runTask(Plugin, Runnable)
	 */
	public synchronized void runTask(Plugin plugin) throws IllegalArgumentException, IllegalStateException {
		checkBeforeRun(o -> Bukkit.getScheduler().runTask(plugin, (Runnable) this).getTaskId());
	}

	/**
	 * Schedules this to run after the specified number of server ticks.
	 *
	 * @param plugin the reference to the plugin scheduling task
	 * @param delay  the ticks to wait before running the task
	 * @return a BukkitTask that contains the id number
	 * @throws IllegalArgumentException if plugin is null
	 * @throws IllegalStateException    if this was already scheduled
	 * @see BukkitScheduler#runTaskLater(Plugin, Runnable, long)
	 */
	public synchronized void runTaskLater(Plugin plugin, long delay) throws IllegalArgumentException, IllegalStateException {
		checkBeforeRun(o -> Bukkit.getScheduler().runTaskLater(plugin, (Runnable) this, delay).getTaskId());
	}

	/**
	 * Schedules this to repeatedly run until cancelled, starting after the specified number of server ticks.
	 *
	 * @param plugin the reference to the plugin scheduling task
	 * @param delay  the ticks to wait before running the task
	 * @param period the ticks to wait between runs
	 * @return a BukkitTask that contains the id number
	 * @throws IllegalArgumentException if plugin is null
	 * @see BukkitScheduler#runTaskTimer(Plugin, Runnable, long, long)
	 */
	public synchronized void runTaskTimer(Plugin plugin, long delay, long period) throws IllegalArgumentException, IllegalStateException {
		checkBeforeRun(o -> Bukkit.getScheduler().runTaskTimer(plugin, (Runnable) this, delay, period).getTaskId());
	}

	private void checkBeforeRun(Function<Object, Integer> function) {
		if (!isCancelled) {
			taskId = function.apply(null);
			isCancelled = false;
		}
	}
}
