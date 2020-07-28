package fr.pederobien.minecraftscoreboards.impl;

import java.util.List;

import org.bukkit.ChatColor;

import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.IEntryUpdater;
import fr.pederobien.minecraftscoreboards.interfaces.IObjective;

public class EntryWrapper<T extends IEntry> implements IEntry {
	private T source;

	/**
	 * Constructs a wrapper for the given entry.
	 * 
	 * @param source The entry to wrap.
	 */
	public EntryWrapper(T source) {
		this.source = source;
	}

	@Override
	public IObjective getObjective() {
		return source.getObjective();
	}

	@Override
	public void setObjective(IObjective objective) {
		source.setObjective(objective);
	}

	@Override
	public String getOldValue() {
		return getSource().getOldValue();
	}

	@Override
	public String getCurrentValue() {
		return getSource().getCurrentValue();
	}

	@Override
	public String getBefore() {
		return getSource().getBefore();
	}

	@Override
	public void update() {
		getSource().update();
	}

	@Override
	public String getAfter() {
		return getSource().getAfter();
	}

	@Override
	public int getScore() {
		return getSource().getScore();
	}

	@Override
	public void setScore(int score) {
		getSource().setScore(score);
	}

	@Override
	public void initialize() {
		getSource().initialize();
	}

	@Override
	public boolean isActivated() {
		return getSource().isActivated();
	}

	@Override
	public void setActivated(boolean isActivated) {
		getSource().setActivated(isActivated);
	}

	@Override
	public ChatColor getColor() {
		return getSource().getColor();
	}

	@Override
	public void setColor(ChatColor color) {
		getSource().setColor(color);
	}

	@Override
	public void addUpdater(IEntryUpdater updater) {
		source.addUpdater(updater);
	}

	@Override
	public void removeUpdater(IEntryUpdater updater) {
		source.removeUpdater(updater);
	}

	@Override
	public List<IEntryUpdater> updaters() {
		return source.updaters();
	}

	/**
	 * @return The source entry wrapped by this wrapper.
	 */
	protected T getSource() {
		return source;
	}
}
