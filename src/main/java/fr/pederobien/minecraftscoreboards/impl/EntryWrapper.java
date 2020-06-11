package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.pederobien.minecraftscoreboards.interfaces.IEntry;

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
	public String getOldValue() {
		return getSource().getOldValue();
	}

	@Override
	public String getCurrentValue() {
		return getSource().getCurrentValue();
	}

	@Override
	public String getBefore(Player player) {
		return getSource().getBefore(player);
	}

	@Override
	public void update(Player player) {
		getSource().update(player);
	}

	@Override
	public String getAfter(Player player) {
		return getSource().getAfter(player);
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

	/**
	 * @return The source entry wrapped by this wrapper.
	 */
	protected T getSource() {
		return source;
	}
}
