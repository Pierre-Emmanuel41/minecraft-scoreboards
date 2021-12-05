package fr.pederobien.minecraft.scoreboards.interfaces;

public interface IEntryUpdater {

	/**
	 * @return The source entry this updater update.
	 */
	IEntry getSource();

	/**
	 * Set the entry this updater update.
	 * 
	 * @param source The source entry to update by this updater.
	 */
	void setSource(IEntry source);

	/**
	 * Initialize this updater before starting updates.
	 */
	void initialize();

	/**
	 * @return True if this updater is activated, false otherwise.
	 */
	boolean isActivated();

	/**
	 * If activated, this updater will update the entry source.
	 * 
	 * @param isActivated True if this updater is activated, false otherwise.
	 */
	void setActivated(boolean isActivated);
}
