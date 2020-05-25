package fr.pederobien.minecraftscoreboards.interfaces;

public interface IObjective extends ISimpleObjective, IEntriesObjective, IUpdateObjective {

	/**
	 * Initialize each registered entry if not already initialized.
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
