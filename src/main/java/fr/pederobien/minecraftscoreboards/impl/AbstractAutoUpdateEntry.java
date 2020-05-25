package fr.pederobien.minecraftscoreboards.impl;

import fr.pederobien.minecraftscoreboards.interfaces.IAutoUpdateEntry;
import fr.pederobien.minecraftscoreboards.interfaces.IAutoUpdateObjective;

public abstract class AbstractAutoUpdateEntry extends AbstractEntry implements IAutoUpdateEntry {

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
	public IAutoUpdateObjective getObjective() {
		return (IAutoUpdateObjective) super.getObjective();
	}

	@Override
	public void setObjective(IAutoUpdateObjective objective) {
		super.setObjective(objective);
	}

	@Override
	public final void initialize() {
		getObjective().getPlugin().getServer().getPluginManager().registerEvents(this, getObjective().getPlugin());
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
