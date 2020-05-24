package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.entity.Player;

import fr.pederobien.minecraftscoreboards.interfaces.IAutoUpdateEntry;
import fr.pederobien.minecraftscoreboards.interfaces.IAutoUpdateObjective;
import fr.pederobien.minecraftscoreboards.interfaces.IObjective;

public abstract class AbstractAutoUpdateEntry extends AbstractEntry implements IAutoUpdateEntry {
	private IObjective objective;
	private boolean isActivated;

	protected AbstractAutoUpdateEntry(int score) {
		super(score);
	}

	@Override
	public final String initialize(Player player) {
		return internalUpdate(player, p -> onInitialize(p));
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
	public boolean isActivated() {
		return isActivated;
	}

	@Override
	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
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
		}.runTaskLater(((IAutoUpdateObjective) getObjective()).getPlugin(), delay);
	}

	/**
	 * Because method {@link #initialize(Player)} is declared final, this method is the only way to initialise this entry.
	 * 
	 * @param player The player whose objective is updated.
	 * @return A String that represent the initial value of this entry.
	 */
	protected abstract String onInitialize(Player player);
}
