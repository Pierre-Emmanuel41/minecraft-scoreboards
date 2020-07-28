package fr.pederobien.minecraftscoreboards.impl;

public class PeriodicEntryUpdater extends EntryUpdater implements Runnable {
	private long delay, period;

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
	public void initialize() {
		getPlugin().getServer().getScheduler().runTaskTimer(getPlugin(), this, delay, period);
	}

	@Override
	public void run() {
		update();
	}
}
