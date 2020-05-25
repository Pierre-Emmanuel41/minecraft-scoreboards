package fr.pederobien.minecraftscoreboards.interfaces;

import java.util.List;
import java.util.Optional;

import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public interface IEntriesObjective<T extends IEntry> extends ISimpleObjective {

	/**
	 * Append an entry to this objective. If an entry is already registered for the given {@link IEntry#getScore()} then the former
	 * entry is replaced.
	 * 
	 * @param entry The entry to add to the objective.
	 */
	void addEntry(T entry);

	/**
	 * Remove the given entry from the list of entry for this objective.
	 * 
	 * @param entry The entry to remove.
	 */
	void removeEntry(T entry);

	/**
	 * @return A list that contains all registered entries for this objective. This list is unmodifiable.
	 */
	List<T> entries();

	/**
	 * Set the scoreboard of this objective. This objective is also attached to this scoreboard.
	 * 
	 * @param scoreboard The scoreboard attached to this objective.
	 */
	void setScoreboard(Scoreboard scoreboard);

	/**
	 * Gets the scoreboard to which this objective is attached if it exist.
	 * 
	 * @return An optional that contains the scoreboard to which this objective is attached if it exist, an empty optional otherwise.
	 */
	Optional<Scoreboard> getScoreboard();

	/**
	 * Get the bukkit objective equivalent of this objective.
	 * 
	 * @return An optional that contains the bukkit objective equivalent if this objective is attached to a scoreboard, an empty
	 *         optional otherwise.
	 */
	Optional<Objective> getObjective();

	/**
	 * Update this objective for the tracked player. This method reset the objective for each entry old value and then display each
	 * entry current value.
	 */
	void update();

	/**
	 * Update this objective for the tracked player and the specified entry.
	 * 
	 * @param entry The entry to update in this objective.
	 */
	void update(T entry);
}
