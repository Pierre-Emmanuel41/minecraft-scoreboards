package fr.pederobien.minecraftscoreboards.impl;

import java.util.function.Function;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.pederobien.minecraftscoreboards.interfaces.IEntry;

public abstract class AbstractEntry implements IEntry {
	private String oldValue, currentValue;
	private int score;
	private boolean isActivated;
	private ChatColor color;

	/**
	 * Create an entry.
	 * 
	 * @param score The line number of this entry in the player objective.
	 */
	protected AbstractEntry(int score) {
		this.score = score;
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
	public final void update(Player player) {
		internalUpdate(player, p -> updateCurrentValue(p));
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
	public boolean isActivated() {
		return isActivated;
	}

	@Override
	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	@Override
	public ChatColor getColor() {
		return color;
	}

	@Override
	public void setColor(ChatColor color) {
		this.color = color;
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
	 * Method use internally, do not use.
	 * 
	 * @param player   The player whose objective is updated.
	 * @param function The function used to update the current value of this entry.
	 * @return A string that correspond to the current value of this entry.
	 */
	protected String internalUpdate(Player player, Function<Player, String> function) {
		currentValue = getBeforeColored(player) + function.apply(player) + getAfter(player);
		oldValue = currentValue;
		return currentValue;
	}

	/**
	 * Get a string displayed before the value to update.
	 * 
	 * @param Player The player whose score board is updated.
	 * @return The sequence of characters to be displayed before the value to update.
	 */
	protected abstract String getBefore(Player player);

	/**
	 * Get a string displayed after the value to update.
	 * 
	 * @param Player The player whose score board is updated.
	 * @return The sequence of characters to be displayed after the value to update.
	 */
	protected abstract String getAfter(Player player);

	private String getBeforeColored(Player player) {
		return (color == null ? ChatColor.RESET : color) + getBefore(player);
	}
}
