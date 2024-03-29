package fr.pederobien.minecraft.scoreboards.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.entity.Player;

import fr.pederobien.minecraft.managers.EColor;
import fr.pederobien.minecraft.scoreboards.interfaces.IEntry;
import fr.pederobien.minecraft.scoreboards.interfaces.IEntryUpdater;
import fr.pederobien.minecraft.scoreboards.interfaces.IObjective;

public abstract class AbstractEntry implements IEntry {
	private IObjective objective;
	private String oldValue, currentValue;
	private int score;
	private boolean isActivated;
	private EColor color;
	private List<IEntryUpdater> updaters;

	/**
	 * Create an entry.
	 * 
	 * @param score The line number of this entry in the player objective.
	 */
	protected AbstractEntry(int score) {
		this.score = score;
		updaters = new ArrayList<IEntryUpdater>();
	}

	@Override
	public int compareTo(IEntry o) {
		return Integer.compare(getScore(), o.getScore());
	}

	@Override
	public IObjective getObjective() {
		return objective;
	}

	@Override
	public void setObjective(IObjective objective) {
		this.objective = objective;
	}

	@Override
	public String getOldValue() {
		return oldValue == null ? "" : oldValue;
	}

	@Override
	public String getCurrentValue() {
		return currentValue == null ? "" : currentValue;
	}

	@Override
	public String getBefore() {
		return "";
	}

	@Override
	public final void update() {
		currentValue = (color == null ? EColor.RESET : color).getInColor(getBefore());
		currentValue += updateCurrentValue(getObjective().getPlayer()) + getAfter();
		oldValue = currentValue;
	}

	@Override
	public String getAfter() {
		return "";
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void initialize() {
		for (IEntryUpdater updater : updaters)
			updater.initialize();
	}

	@Override
	public boolean isActivated() {
		return isActivated;
	}

	@Override
	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
		updaters.forEach(updater -> updater.setActivated(isActivated));
	}

	@Override
	public EColor getColor() {
		return color;
	}

	@Override
	public void setColor(EColor color) {
		this.color = color;
	}

	@Override
	public IEntry addUpdater(IEntryUpdater updater) {
		updaters.add(updater);
		updater.setSource(this);
		if (isActivated) {
			updater.initialize();
			updater.setActivated(true);
		}
		return this;
	}

	@Override
	public IEntry removeUpdater(IEntryUpdater updater) {
		updaters.remove(updater);
		updater.setSource(null);
		updater.setActivated(false);
		return this;
	}

	@Override
	public List<IEntryUpdater> updaters() {
		return Collections.unmodifiableList(updaters);
	}

	/**
	 * Because method {@link #update(Player)} is declared final, this method is the only way to update the value of the entry
	 * according to the given player.
	 * 
	 * @param player The player whose scoreboard is updated.
	 * @return The new value represented by this entry.
	 */
	protected abstract String updateCurrentValue(Player player);

	/**
	 * @return The player tracked by the objective associated to this entry. This is a convenient method and is equivalent to
	 *         <code>getObjective().getPlayer()</code>
	 */
	protected Player getPlayer() {
		return getObjective().getPlayer();
	}
}
