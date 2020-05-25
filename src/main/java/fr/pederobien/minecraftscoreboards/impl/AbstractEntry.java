package fr.pederobien.minecraftscoreboards.impl;

import java.util.function.Function;

import org.bukkit.entity.Player;

import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public abstract class AbstractEntry implements IEntry {
	private String oldValue, currentValue, before, after;
	private int score;
	private ISimpleObjective objective;
	private boolean isActivated;

	/**
	 * Create an entry.
	 * 
	 * @param score  The line number of this entry in the player objective.
	 * @param before The sequence of characters to be displayed before the value to update.
	 * @param after  The sequence of characters to be displayed after the value to update.
	 */
	protected AbstractEntry(int score, String before, String after) {
		this.score = score;
		this.before = before;
		this.after = after;
	}

	@Override
	public ISimpleObjective getObjective() {
		return objective;
	}

	@Override
	public void setObjective(ISimpleObjective objective) {
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
	public void initialize() {

	}

	@Override
	public boolean isActivated() {
		return isActivated;
	}

	@Override
	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
		if (!isActivated())
			getObjective().getScoreboard().get().resetScores(getCurrentValue());
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
		currentValue = before + function.apply(player) + after;
		oldValue = currentValue;
		return currentValue;
	}

	/**
	 * @return The sequence of characters to be displayed before the value to update.
	 */
	protected String getBefore() {
		return before;
	}

	/**
	 * @return The sequence of characters to be displayed after the value to update.
	 */
	protected String getAfter() {
		return after;
	}

	/**
	 * Update the objective to which this entry is attached. If the objective is null or this entry is not activated, nothing happen.
	 * Is arrives that when a player is joining the server or respawning, the objective is not updated. An other way to update it is
	 * to use {@link #updateLater(long)}.
	 */
	protected void update() {
		if (getObjective() == null || !isActivated())
			return;

		getObjective().update(this);
	}

	/**
	 * Update the objective after the given server ticks.
	 * 
	 * @param delay The number of server ticks to wait before updating the objective.
	 */
	protected void updateLater(long delay) {
		new MinecraftRunnable() {

			@Override
			public void run() {
				update();
			}
		}.runTaskLater(getObjective().getPlugin(), delay);
	}
}
