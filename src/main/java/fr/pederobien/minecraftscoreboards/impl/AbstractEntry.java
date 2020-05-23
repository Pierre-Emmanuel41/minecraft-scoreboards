package fr.pederobien.minecraftscoreboards.impl;

import java.util.function.Function;

import org.bukkit.entity.Player;

import fr.pederobien.minecraftscoreboards.interfaces.IEntry;

public abstract class AbstractEntry implements IEntry {
	private String oldValue, currentValue;
	private int score;

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
		currentValue = function.apply(player);
		oldValue = currentValue;
		return currentValue;
	}
}
