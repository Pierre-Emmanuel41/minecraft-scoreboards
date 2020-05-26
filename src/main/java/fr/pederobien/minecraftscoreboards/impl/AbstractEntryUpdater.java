package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.entity.Player;

import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public abstract class AbstractEntryUpdater implements IEntry {
	private IEntry source;

	/**
	 * Create an entry updater. This entry is responsible to update the source entry.
	 * 
	 * @param source The source tracked by this updater.
	 */
	protected AbstractEntryUpdater(IEntry source) {
		this.source = source;
	}

	@Override
	public ISimpleObjective getObjective() {
		return source.getObjective();
	}

	@Override
	public void setObjective(ISimpleObjective objective) {
		source.setObjective(objective);
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
		source.getObjective().update(source);
	}

	/**
	 * @return The source entry updated by this updater.
	 */
	protected IEntry getSource() {
		return source;
	}
}
