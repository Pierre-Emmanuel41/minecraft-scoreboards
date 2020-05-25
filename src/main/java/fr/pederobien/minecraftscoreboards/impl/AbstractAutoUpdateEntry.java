package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.entity.Player;

import fr.pederobien.minecraftscoreboards.interfaces.IAutoUpdateEntry;
import fr.pederobien.minecraftscoreboards.interfaces.IPluginObjective;

public abstract class AbstractAutoUpdateEntry extends AbstractEntry implements IAutoUpdateEntry {
	private boolean isActivated;

	/**
	 * Create an entry capable to update the objective when minecraft events are thrown.
	 * 
	 * @param score  The line number of this entry in the player objective.
	 * @param before The sequence of characters to be displayed before the value to update.
	 * @param after  The sequence of characters to be displayed after the value to update.
	 */
	protected AbstractAutoUpdateEntry(int score, String before, String after) {
		super(score, before, after);
	}

	@Override
	public IPluginObjective getObjective() {
		return (IPluginObjective) super.getObjective();
	}

	@Override
	public void setObjective(IPluginObjective objective) {
		super.setObjective(objective);
	}

	@Override
	public final String initialize(Player player) {
		return internalUpdate(player, p -> onInitialize(p));
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

	/**
	 * Because method {@link #initialize(Player)} is declared final, this method is the only way to initialise this entry.
	 * 
	 * @param player The player whose objective is updated.
	 * @return A String that represent the initial value of this entry.
	 */
	protected abstract String onInitialize(Player player);
}
