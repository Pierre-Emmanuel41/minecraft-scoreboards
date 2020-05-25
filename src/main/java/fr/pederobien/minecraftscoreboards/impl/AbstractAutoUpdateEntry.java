package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.event.Listener;

public abstract class AbstractAutoUpdateEntry extends AbstractEntry implements Listener {

	/**
	 * Create an entry capable to update the objective when minecraft events are thrown.
	 * 
	 * @param score  The line number of this entry in the player objective.
	 * @param before The sequence of characters to be displayed before the value to update.
	 * @param after  The sequence of characters to be displayed after the value to update.
	 */
	protected AbstractAutoUpdateEntry(int score, String before, String after) {
		super(score, before, after);
	}

	@Override
	public final void initialize() {
		getObjective().getPlugin().getServer().getPluginManager().registerEvents(this, getObjective().getPlugin());
	}
}
