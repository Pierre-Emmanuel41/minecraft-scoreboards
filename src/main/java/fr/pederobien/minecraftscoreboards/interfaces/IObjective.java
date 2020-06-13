package fr.pederobien.minecraftscoreboards.interfaces;

import java.util.List;

public interface IObjective extends ISimpleObjective, IUpdateObjective {

	/**
	 * Append an entry to this objective. If an entry is already registered for the given {@link IEntry#getScore()} then the former
	 * entry is replaced.
	 * 
	 * @param entry The entry to add to the objective.
	 */
	void addEntry(IEntry entry);

	/**
	 * Remove the entry associated to the given score from the list of entry for this objective.
	 * 
	 * @param score the score, ie the line number, of the entry to remove.
	 */
	void removeEntry(int score);

	/**
	 * Create an empty entry and add it to the list of entry for this objective.
	 * 
	 * @param score The line number of the empty entry.
	 */
	void emptyEntry(int score);

	/**
	 * @return A list that contains all registered entries for this objective. This list is unmodifiable.
	 */
	List<IEntry> entries();

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
