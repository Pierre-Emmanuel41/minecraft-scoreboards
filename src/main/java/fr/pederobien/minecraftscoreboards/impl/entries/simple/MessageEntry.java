package fr.pederobien.minecraftscoreboards.impl.entries.simple;

import org.bukkit.entity.Player;

import fr.pederobien.minecraftscoreboards.impl.AbstractEntry;

public class MessageEntry extends AbstractEntry {
	private String entry;

	/**
	 * Create a simple entry. The given entry is returned by method {@link #updateCurrentValue(Player)}. This kind of entry is not
	 * player dependant.
	 * 
	 * @param score The line number of this entry.
	 * @param entry The entry to display in player's scoreboard.
	 */
	public MessageEntry(int score, String entry) {
		super(score);
		this.entry = entry;
	}

	@Override
	protected final String updateCurrentValue(Player player) {
		return entry;
	}
}
