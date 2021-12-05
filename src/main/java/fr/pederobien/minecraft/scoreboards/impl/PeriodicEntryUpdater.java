package fr.pederobien.minecraft.scoreboards.impl;

import org.bukkit.scheduler.BukkitTask;

import fr.pederobien.minecraft.managers.BukkitManager;

public class PeriodicEntryUpdater extends EntryUpdater {
	private long delay, period;
	private Runnable periodicUpdate;
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
		periodicUpdate = new PeriodicUpdate();
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
			task = BukkitManager.getScheduler().runTaskTimer(getPlugin(), periodicUpdate, delay, period);
		super.setActivated(isActivated);
	}

	/**
	 * Method called when the update is about to be updated. This method do nothing if not overridden but should be in order to do
	 * additional treatment before the update.
	 */
	protected void onUpdate() {

	}

	private class PeriodicUpdate implements Runnable {

		@Override
		public void run() {
			onUpdate();
			update();
		}
	}
}
