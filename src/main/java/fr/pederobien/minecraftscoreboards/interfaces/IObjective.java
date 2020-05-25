package fr.pederobien.minecraftscoreboards.interfaces;

import org.bukkit.plugin.Plugin;

public interface IObjective extends ISimpleObjective, IEntriesObjective<IAutoUpdateEntry> {

	/**
	 * @return The plugin used to register each entry as event listener.
	 */
	Plugin getPlugin();

	/**
	 * Initialize each registered entry.
	 */
	void initialize();

	/**
	 * @return True if this objective can be updated by its entry, false otherwise.
	 */
	boolean isActivated();

	/**
	 * If activated, this objective can be updated by its entry.
	 * 
	 * @param isActivated True if this objective can be updated by its entry, false otherwise.
	 */
	void setActivated(boolean isActivated);
}
