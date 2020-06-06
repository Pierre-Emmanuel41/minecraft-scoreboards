package fr.pederobien.minecraftscoreboards.impl.entries.simple;

import java.util.StringJoiner;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.pederobien.minecraftscoreboards.impl.AbstractSimpleEntry;

public class LocationEntry extends AbstractSimpleEntry {
	private String delimiter;

	/**
	 * Create an entry that displays the current player location.
	 * 
	 * @param score     The line number of this entry.
	 * @param delimiter The sequence of characters to be displayed between each element added.
	 */
	public LocationEntry(int score, String delimiter) {
		super(score);
		this.delimiter = delimiter;
	}

	@Override
	protected String updateCurrentValue(Player player) {
		Location loc = player.getLocation();
		return new StringJoiner(delimiter).add("" + loc.getBlockX()).add("" + loc.getBlockY()).add("" + loc.getBlockZ()).toString();
	}
}
