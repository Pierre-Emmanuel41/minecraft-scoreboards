package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.IEntryUpdater;

public class EntryUpdater implements IEntryUpdater, Runnable {
	private IEntry source;
	private boolean isActivated;

	@Override
	public IEntry getSource() {
		return source;
	}

	@Override
	public void setSource(IEntry source) {
		this.source = source;
	}

	@Override
	public void initialize() {
	}

	@Override
	public boolean isActivated() {
		return isActivated;
	}

	@Override
	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	/**
	 * Common method to update this entry on player screen. First verify if this updater is activated then ask to the objective to
	 * update the entry source of this updater.
	 */
	protected void update() {
		if (source != null && isActivated())
			scheduleUpdate(2);
	}

	/**
	 * @return The plugin associated to this updater. This is a convenient method and is equivalent to
	 *         <code>getSource().getObjective().getPlugin()</code>
	 */
	protected Plugin getPlugin() {
		return source == null ? null : getSource().getObjective().getPlugin();
	}

	/**
	 * Schedule the update of the entry.
	 * 
	 * @param delay The number of server tick to wait before the entry update is called.
	 */
	protected void scheduleUpdate(long delay) {
		if (getPlugin() != null)
			getPlugin().getServer().getScheduler().runTaskLater(getPlugin(), this, delay);
	}

	@Override
	public void run() {
		getSource().getObjective().update(getSource());
	}
}
