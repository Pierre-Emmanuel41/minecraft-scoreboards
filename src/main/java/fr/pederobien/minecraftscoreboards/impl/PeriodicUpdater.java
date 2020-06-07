package fr.pederobien.minecraftscoreboards.impl;

import fr.pederobien.minecraftmanagers.BukkitManager;
import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class PeriodicUpdater<T extends IEntry> extends AbstractEntryUpdater<T> {
	private long period, delay;
	private Runnable update;

	/**
	 * Create an entry updated periodically.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 */
	protected PeriodicUpdater(ISimpleObjective objective, long delay, long period, T source) {
		super(objective, source);
		this.delay = delay;
		this.period = period;
		update = new InternalUpdater();
	}

	/**
	 * Create an entry updated periodically.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 */
	protected PeriodicUpdater(ISimpleObjective objective, long period, T source) {
		this(objective, 0, period, source);
	}

	@Override
	public void initialize() {
		if (delay == 0 && period == 0)
			BukkitManager.getScheduler().runTask(getPlugin(), update);
		if (delay != 0 && period == 0)
			BukkitManager.getScheduler().runTaskLater(getPlugin(), update, delay);
		else
			BukkitManager.getScheduler().runTaskTimer(getPlugin(), update, delay, period);
	}

	private class InternalUpdater implements Runnable {

		@Override
		public void run() {
			update();
		}
	}
}
