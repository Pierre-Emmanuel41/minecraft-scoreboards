package fr.pederobien.minecraftscoreboards.interfaces;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public interface IEntry {

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
	 * Get a sequence of character displayed before the value to update.
	 * 
	 * @param Player The player whose score board is updated.
	 * @return The sequence of characters to be displayed before the value to update.
	 */
	String getBefore(Player player);

	/**
	 * Update this entry. The old value equals the current value and the current value is updated.
	 * 
	 * @param player The player whose score board is updated.
	 */
	void update(Player player);

	/**
	 * Get a sequence of character displayed after the value to update.
	 * 
	 * @param Player The player whose score board is updated.
	 * @return The sequence of characters to be displayed after the value to update.
	 */
	String getAfter(Player player);

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

	/**
	 * @return The color in which this entry display messages. Generally, this color correspond to the player team color.
	 */
	ChatColor getColor();

	/**
	 * Set the color in which this entry display messages.
	 * 
	 * @param color The color used to display messages on player screen.
	 */
	void setColor(ChatColor color);
}
