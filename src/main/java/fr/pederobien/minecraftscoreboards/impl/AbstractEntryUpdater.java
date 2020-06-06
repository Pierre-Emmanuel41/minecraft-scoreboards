package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftmanagers.BukkitManager;
import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public abstract class AbstractEntryUpdater<T extends IEntry> implements IEntry {
	private Plugin plugin;
	private ISimpleObjective objective;
	private T source;
	private Runnable update;

	/**
	 * Create an entry updater. This entry is responsible to update the source entry.
	 * 
	 * @param plugin    The plugin to register this entry to be periodically updated.
	 * @param objective The objective associated to the source entry.
	 * @param source    The source tracked by this updater.
	 */
	protected AbstractEntryUpdater(Plugin plugin, ISimpleObjective objective, T source) {
		this.plugin = plugin;
		this.objective = objective;
		this.source = source;

		update = new InternalRunnable();
	}

	/**
	 * @return The plugin associated to this updater.
	 */
	public Plugin getPlugin() {
		return plugin;
	}

	public ISimpleObjective getObjective() {
		return objective;
	}

	@Override
	public String getOldValue() {
		return source.getOldValue();
	}

	@Override
	public String getCurrentValue() {
		return source.getCurrentValue();
	}

	@Override
	public void update(Player player) {
		source.update(player);
	}

	@Override
	public int getScore() {
		return source.getScore();
	}

	@Override
	public void setScore(int score) {
		source.setScore(score);
	}

	@Override
	public boolean isActivated() {
		return source.isActivated();
	}

	@Override
	public void setActivated(boolean isActivated) {
		source.setActivated(isActivated);
	}

	/**
	 * Update the objective associated to the source entry for the source entry.
	 */
	protected void update() {
		BukkitManager.getScheduler().runTaskLater(getPlugin(), update, 2);
	}

	/**
	 * @return The source entry updated by this updater.
	 */
	protected T getSource() {
		return source;
	}

	private class InternalRunnable implements Runnable {

		@Override
		public void run() {
			getObjective().update(source);
		}
	}
}
