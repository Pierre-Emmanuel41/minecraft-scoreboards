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
}
