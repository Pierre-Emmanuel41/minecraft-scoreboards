package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public abstract class AbstractEntryUpdater implements IEntry {
	private Plugin plugin;
	private ISimpleObjective objective;
	private IEntry source;

	/**
	 * Create an entry updater. This entry is responsible to update the source entry.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param source    The source tracked by this updater.
	 */
	protected AbstractEntryUpdater(Plugin plugin, ISimpleObjective objective, IEntry source) {
		this.plugin = plugin;
		this.objective = objective;
		this.source = source;
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
		getObjective().update(source);
	}

	/**
	 * @return The source entry updated by this updater.
	 */
	protected IEntry getSource() {
		return source;
	}
}
