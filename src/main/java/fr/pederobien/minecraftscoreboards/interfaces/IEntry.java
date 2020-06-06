package fr.pederobien.minecraftscoreboards.interfaces;

import org.bukkit.entity.Player;

public interface IEntry {

	/**
	 * @return The player associated to this entry.
	 */
	Player getPlayer();

	/**
	 * Set the player associated to this entry. Normally this method should be never called. But when a player quit and join the
	 * server during an update, the player lose its score board. This method is called only by the objective to which this entry has
	 * been added. Do not call this method.
	 * 
	 * @param player The player to track.
	 */
	IEntry setPlayer(Player player);

	/**
	 * Get the old value of this entry. This value is used to clear or reset the player's objective.
	 * 
	 * @return The old value of this entry.
	 */
	String getOldValue();

	/**
	 * @return The current value of this entry. This value is displayed on player's objective.
	 */
	String getCurrentValue();

	/**
	 * Update this entry. The old value equals the current value and the current value is updated.
	 */
	void update();

	/**
	 * Get the score of this entry. This score represents the line number of this entry in an objective.
	 * 
	 * @return The score of this entry.
	 */
	int getScore();

	/**
	 * Set the score of this entry. The score represents the line number of this entry in an objective.
	 * 
	 * @param score The new line number of this entry.
	 */
	void setScore(int score);

	/**
	 * Initialize this entry before starting its update.
	 */
	void initialize();

	/**
	 * @return True if this entry can update the objective, false otherwise.
	 */
	boolean isActivated();

	/**
	 * If activated, the listener will react on minecraft events.
	 * 
	 * @param isActivated True if this listener is activated, false otherwise.
	 */
	void setActivated(boolean isActivated);
}
