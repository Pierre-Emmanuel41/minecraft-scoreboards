package fr.pederobien.minecraftscoreboards.interfaces;

import java.util.List;
import java.util.Optional;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public interface IObjective extends IUpdateObjective {

	/**
	 * @return The plugin used to update this objective.
	 */
	Plugin getPlugin();

	/**
	 * @return The player tracked by this objective.
	 */
	Player getPlayer();

	/**
	 * Set the player tracked by this objective. Normally this method should be never called. But when a player quit and join the
	 * server during an update, the player lose its score board.
	 * 
	 * @param player The player to track.
	 */
	void setPlayer(Player player);

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
	 * Gets the score board to which this objective is attached if it exist.
	 * 
	 * @return An optional that contains the score board to which this objective is attached if it exist, an empty optional otherwise.
	 */
	Optional<Scoreboard> getScoreboard();

	/**
	 * Set the score board of this objective. This objective is also attached to this score board.
	 * 
	 * @param scoreboard The score board attached to this objective.
	 */
	void setScoreboard(Scoreboard scoreboard);

	/**
	 * Get the bukkit objective equivalent of this objective.
	 * 
	 * @return An optional that contains the bukkit objective equivalent if this objective is attached to a score board, an empty
	 *         optional otherwise.
	 */
	Optional<Objective> getObjective();

	/**
	 * Initialize each registered entry if not already initialized.
	 */
	void initialize();

	/**
	 * Appends an entry to this objective. If an entry is already registered for the given {@link IEntry#getScore()} then the former
	 * entry is replaced.
	 * 
	 * @param entry The entry to add to the objective.
	 */
	void addEntry(IEntry entry);

	/**
	 * Inserts an entry to this objective. This method update also the score value of the given entry.
	 * 
	 * @param index The index of the entry. The score is calculated according to the entries already registered.
	 * @param entry The entry to add to the objective.
	 */
	void addEntry(int index, IEntry entry);

	/**
	 * Create an empty entry and insert it to registered entries.
	 * 
	 * @param score the score, ie the line number, of the empty entry.
	 */
	void emptyEntry(int score);

	/**
	 * Remove the entry associated to the given score from the list of entry for this objective.
	 * 
	 * @param score the score, ie the line number, of the entry to remove.
	 */
	void removeEntry(int score);

	/**
	 * @return A list that contains all registered entries for this objective. This list is unmodifiable.
	 */
	List<IEntry> entries();

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
	void update(IEntry entry);

	/**
	 * Update this objective for the tracked player and the entry associated to the given score.
	 * 
	 * @param score the score, ie the line number, of the entry to remove.
	 */
	void update(int score);

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
