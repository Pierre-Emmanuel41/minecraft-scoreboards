package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class PeriodicUpdater<T extends IEntry> extends AbstractEntryUpdater<T> {
	private InternalUpdater updater;

	/**
	 * Create an entry updater. This entry is responsible to update the source entry.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 */
	protected PeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, T source) {
		super(plugin, objective, source);
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
				runTask(getPlugin());
			if (delay != 0 && period == 0)
				runTaskLater(getPlugin(), delay);
			else
				runTaskTimer(getPlugin(), delay, period);
		}
	}
}
