package fr.pederobien.minecraftscoreboards.interfaces;

import java.util.List;
import java.util.Optional;

import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public interface IAutoUpdateObjective {

	/**
	 * @return The name of this objective.
	 */
	String getName();

	/**
	 * Gets the criteria this objective tracks.
	 * 
	 * @return this objective's criteria.
	 */
	String getCriteria();

	/**
	 * Gets the name displayed to players for this objective.
	 * 
	 * @return this objective's display name
	 */
	String getDisplayName();

	/**
	 * Gets the display slot this objective is displayed at.
	 *
	 * @return the display slot for this objective, or null if not displayed
	 */
	DisplaySlot getDisplaySlot();

	/**
	 * @return The plugin used to register each entry as event listener.
	 */
	Plugin getPlugin();

	/**
	 * Append an entry to this objective. If an entry is already registered for the given {@link IEntry#getScore()} then the former
	 * entry is replaced.
	 * 
	 * @param entry The entry to add to the objective.
	 */
	void addEntry(IAutoUpdateEntry entry);

	/**
	 * Remove the given entry from the list of entry for this objective.
	 * 
	 * @param entry The entry to remove.
	 */
	void removeEntry(IAutoUpdateEntry entry);

	/**
	 * Register each registered entry as event listener for the plugin returned by {@link #getPlugin()}
	 */
	void register();

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

	/**
	 * @return A list that contains all registered entries for this objective. This list is unmodifiable.
	 */
	List<IEntry> entries();

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
}
