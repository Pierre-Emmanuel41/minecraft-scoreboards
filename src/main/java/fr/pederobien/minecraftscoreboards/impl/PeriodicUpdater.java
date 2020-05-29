package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftmanagers.BukkitManager;
import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class PeriodicUpdater<T extends IEntry> extends AbstractEntryUpdater<T> {
	private long period, delay;
	private Runnable update;

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
		this.delay = delay;
		this.period = period;
		update = new InternalUpdater();
	}

	/**
	 * Create an entry updater. This entry is responsible to update the source entry.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 */
	protected PeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, T source) {
		this(plugin, objective, 0, period, source);
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
