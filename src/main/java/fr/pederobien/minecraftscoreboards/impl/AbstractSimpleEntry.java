package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.entity.Player;

public abstract class AbstractSimpleEntry extends AbstractEntry {

	/**
	 * Create an entry.
	 * 
	 * @param score The line number of this entry in the player objective.
	 */
	protected AbstractSimpleEntry(int score) {
		super(score);
	}

	@Override
	public void initialize() {
	}

	@Override
	protected String getBefore(Player player) {
		return "";
	}

	@Override
	protected String getAfter(Player player) {
		return "";
	}
}
