package fr.pederobien.minecraftscoreboards.interfaces;

import org.bukkit.event.Listener;

public interface IAutoUpdateEntry extends IEntry, Listener {

	/**
	 * @return The objective updated by this entry.
	 */
	IAutoUpdateObjective getObjective();

	/**
	 * Set the objective to update for this entry.
	 * 
	 * @param objective The objective To update.
	 */
	void setObjective(IAutoUpdateObjective objective);

	/**
	 * Initialize this entry before starting its update.
	 */
	void initialize();

	/**
	 * @return True if this entry can update the objective, false otherwise.
	 */
	boolean isActivated();

	/**
	 * If activated, the listener will react on minecraft events.
	 * 
	 * @param isActivated True if this listener is activated, false otherwise.
	 */
	void setActivated(boolean isActivated);
}
