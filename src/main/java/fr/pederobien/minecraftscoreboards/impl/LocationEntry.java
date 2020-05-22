package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LocationEntry extends AbstractEntry {
	private String before, delimiter, after;

	/**
	 * Create an entry that displays the player location. The message associated to this entry is :</br>
	 * Location loc = player.getLocation();</br>
	 * return before + delimiter + loc.getBlockX() + delimiter + loc.getBlockY() + delimiter + loc.getBlockZ() + delimiter +
	 * after;</br>
	 * This constructor use " " as default delimiter and "" as value for parameter <code>after</code>.
	 * 
	 * @param score  The line number of this entry.
	 * @param before The sequence of characters to be displayed before the player location. See above.
	 */
	protected LocationEntry(int score, String before) {
		this(score, before, " ");
	}

	/**
	 * Create an entry that displays the player location. The message associated to this entry is :</br>
	 * Location loc = player.getLocation();</br>
	 * return before + delimiter + loc.getBlockX() + delimiter + loc.getBlockY() + delimiter + loc.getBlockZ() + delimiter +
	 * after;</br>
	 * This constructor "" as value for parameter <code>after</code>.
	 * 
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the player location. See above.
	 * @param delimiter The sequence of characters to be displayed between each element added. See above.
	 */
	protected LocationEntry(int score, String before, String delimiter) {
		this(score, before, delimiter, "");
	}

	/**
	 * Create an entry that displays the player location. The message associated to this entry is :</br>
	 * Location loc = player.getLocation();</br>
	 * return before + delimiter + loc.getBlockX() + delimiter + loc.getBlockY() + delimiter + loc.getBlockZ() + delimiter +
	 * after;</br>
	 * 
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the player location. See above.
	 * @param delimiter The sequence of characters to be displayed between each element added. See above.
	 * @param after     The sequence of characters to be displayed after the player location. See above.
	 */
	protected LocationEntry(int score, String before, String delimiter, String after) {
		super(score);
		this.before = before;
		this.delimiter = delimiter;
		this.after = after;
	}

	@Override
	protected String updateCurrentValue(Player player) {
		Location loc = player.getLocation();
		return before + delimiter + loc.getBlockX() + delimiter + loc.getBlockY() + delimiter + loc.getBlockZ() + delimiter + after;
	}
}
