package fr.pederobien.minecraftscoreboards.impl;

import fr.pederobien.minecraftscoreboards.interfaces.IEntry;

public class PeriodicUpdater extends AbstractEntryUpdater {
	private InternalUpdater updater;

	/**
	 * Create an entry updater. This entry is responsible to update the source entry.
	 * 
	 * @param source The source tracked by this updater.
	 */
	protected PeriodicUpdater(IEntry source, long delay, long period) {
		super(source);
		updater = new InternalUpdater(delay, period);
	}

	@Override
	public void initialize() {
		updater.schedule();
	}

	private class InternalUpdater extends MinecraftRunnable {
		private long delay, period;

		public InternalUpdater(long delay, long period) {
			this.delay = delay;
			this.period = period;
		}

		@Override
		public void run() {
			update();
		}

		public void schedule() {
			if (delay == 0 && period == 0)
				runTask(getObjective().getPlugin());
			if (delay != 0 && period == 0)
				runTaskLater(getObjective().getPlugin(), delay);
			else
				runTaskTimer(getObjective().getPlugin(), delay, period);
		}
	}
}
