package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftmanagers.BukkitManager;
import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public abstract class AbstractEntryUpdater<T extends IEntry> extends EntryWrapper<T> {
	private ISimpleObjective objective;
	private Runnable update;

	/**
	 * Create an entry updater. This entry is responsible to update the source entry.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param source    The source tracked by this updater.
	 */
	protected AbstractEntryUpdater(ISimpleObjective objective, T source) {
		super(source);
		this.objective = objective;

		update = new InternalRunnable();
	}

	/**
	 * @return The plugin associated to this updater.
	 */
	public Plugin getPlugin() {
		return objective.getPlugin();
	}

	public ISimpleObjective getObjective() {
		return objective;
	}

	/**
	 * Update the objective associated to the source entry for the source entry.
	 */
	protected void update() {
		BukkitManager.getScheduler().runTaskLater(getPlugin(), update, 2);
	}

	private class InternalRunnable implements Runnable {

		@Override
		public void run() {
			getObjective().update(getSource());
		}
	}
}
