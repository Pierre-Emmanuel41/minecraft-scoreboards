package fr.pederobien.minecraftscoreboards.interfaces;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public interface IAutoUpdateEntry extends IEntry, Listener {

	/**
	 * Initialize this entry for the given player.
	 * 
	 * @param player The player whose objective is updated.
	 * 
	 * @return The current value of this entry.
	 */
	String initialize(Player player);

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
