package fr.pederobien.minecraftscoreboards.impl.entries.update;

import java.util.StringJoiner;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.pederobien.minecraftscoreboards.impl.AbstractAutoUpdateEntry;

public class LocationEntry extends AbstractAutoUpdateEntry {
	private String before, delimiter, after;
	private int call;

	/**
	 * Create an entry that displays the player location. The message associated to this entry is :</br>
	 * Location loc = player.getLocation();</br>
	 * return before + loc.getBlockX() + delimiter + loc.getBlockY() + delimiter + loc.getBlockZ()</br>
	 * This constructor use " " as default delimiter.
	 * 
	 * @param score  The line number of this entry.
	 * @param before The sequence of characters to be displayed before the player location. See above.
	 */
	public LocationEntry(int score, String before) {
		this(score, before, " ");
	}

	/**
	 * Create an entry that displays the player location. The message associated to this entry is :</br>
	 * Location loc = player.getLocation();</br>
	 * return before + loc.getBlockX() + delimiter + loc.getBlockY() + delimiter + loc.getBlockZ()
	 * 
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the player location. See above.
	 * @param delimiter The sequence of characters to be displayed between each element added. See above.
	 */
	public LocationEntry(int score, String before, String delimiter) {
		this(score, before, delimiter, "");
	}

	/**
	 * Create an entry that displays the player location. The message associated to this entry is :</br>
	 * Location loc = player.getLocation();</br>
	 * return before + loc.getBlockX() + delimiter + loc.getBlockY() + delimiter + loc.getBlockZ() + after;</br>
	 * 
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the player location. See above.
	 * @param delimiter The sequence of characters to be displayed between each element added. See above.
	 * @param after     The sequence of characters to be displayed after the player location. See above.
	 */
	public LocationEntry(int score, String before, String delimiter, String after) {
		super(score);
		this.before = before;
		this.delimiter = delimiter;
		this.after = after;
		call = 0;
	}

	@Override
	protected String onInitialize(Player player) {
		return updateCurrentValue(player);
	}

	@Override
	protected String updateCurrentValue(Player player) {
		Location loc = player.getLocation();
		return before.concat(new StringJoiner(delimiter).add("" + loc.getBlockX()).add("" + loc.getBlockY()).add("" + loc.getBlockZ()).toString()).concat(after);
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		if (!event.getPlayer().getName().equals(getObjective().getPlayer().getName()))
			return;

		// Updating player objective each 3 calls.
		call++;
		if (call > 4) {
			update();
			call = 0;
		}
	}
}
