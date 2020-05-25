package fr.pederobien.minecraftscoreboards.interfaces;

import java.util.List;

public interface IEntriesObjective extends ISimpleObjective {

	/**
	 * Append an entry to this objective. If an entry is already registered for the given {@link IEntry#getScore()} then the former
	 * entry is replaced.
	 * 
	 * @param entry The entry to add to the objective.
	 */
	void addEntry(IEntry entry);

	/**
	 * Remove the given entry from the list of entry for this objective.
	 * 
	 * @param entry The entry to remove.
	 */
	void removeEntry(IEntry entry);

	/**
	 * @return A list that contains all registered entries for this objective. This list is unmodifiable.
	 */
	List<IEntry> entries();
}
