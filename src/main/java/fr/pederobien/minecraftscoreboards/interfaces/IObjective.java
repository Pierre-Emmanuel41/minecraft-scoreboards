package fr.pederobien.minecraftscoreboards.interfaces;

public interface IObjective<T extends IEntry> extends ISimpleObjective, IEntriesObjective<T> {

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
