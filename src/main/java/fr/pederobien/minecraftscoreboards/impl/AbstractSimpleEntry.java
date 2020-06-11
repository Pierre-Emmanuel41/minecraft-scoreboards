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
	public String getBefore(Player player) {
		return "";
	}

	@Override
	public String getAfter(Player player) {
		return "";
	}
}
