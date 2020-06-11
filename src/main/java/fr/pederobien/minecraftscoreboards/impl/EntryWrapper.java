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
		return source.getOldValue();
	}

	@Override
	public String getCurrentValue() {
		return source.getCurrentValue();
	}

	@Override
	public String getBefore(Player player) {
		return source.getBefore(player);
	}

	@Override
	public void update(Player player) {
		source.update(player);
	}

	@Override
	public String getAfter(Player player) {
		return source.getAfter(player);
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
	public void initialize() {
		source.initialize();
	}

	@Override
	public boolean isActivated() {
		return source.isActivated();
	}

	@Override
	public void setActivated(boolean isActivated) {
		source.setActivated(isActivated);
	}

	@Override
	public ChatColor getColor() {
		return source.getColor();
	}

	@Override
	public void setColor(ChatColor color) {
		source.setColor(color);
	}

	/**
	 * @return The source entry wrapped by this wrapper.
	 */
	protected T getSource() {
		return source;
	}
}
