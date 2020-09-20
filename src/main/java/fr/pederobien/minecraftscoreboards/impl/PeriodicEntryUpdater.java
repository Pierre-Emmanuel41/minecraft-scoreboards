package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.scheduler.BukkitTask;

import fr.pederobien.minecraftmanagers.BukkitManager;

public class PeriodicEntryUpdater extends EntryUpdater implements Runnable {
	private long delay, period;
	private BukkitTask task;

	/**
	 * Construct a periodic entry updater.
	 * 
	 * @param delay  The number of server ticks to wait before the first update.
	 * @param period The number of server ticks between two entry updates.
	 */
	public PeriodicEntryUpdater(long delay, long period) {
		this.delay = delay;
		this.period = period;
	}

	/**
	 * Construct a periodic entry updater.
	 * 
	 * @param period The number of server ticks between two entry updates.
	 */
	public PeriodicEntryUpdater(long period) {
		this(0, period);
	}

	@Override
	public void setActivated(boolean isActivated) {
		if (isActivated() && !isActivated)
			getPlugin().getServer().getScheduler().cancelTask(task.getTaskId());
		else if (!isActivated() && isActivated)
			task = BukkitManager.getScheduler().runTaskTimer(getPlugin(), this, delay, period);
		super.setActivated(isActivated);
	}

	@Override
	public void run() {
		update();
	}
}
