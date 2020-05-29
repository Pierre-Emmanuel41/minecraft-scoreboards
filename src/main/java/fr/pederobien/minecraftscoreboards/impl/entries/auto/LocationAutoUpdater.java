package fr.pederobien.minecraftscoreboards.impl.entries.auto;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.AutoUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.LocationEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class LocationAutoUpdater extends AutoUpdater<LocationEntry> {
	private int call;

	/**
	 * Create an entry updater. This entry is responsible to update the source entry. This updater is a {@link PlayerMoveEvent}
	 * listener. This means that when an this event is thrown by the server, this updater update the player objective.
	 * 
	 * @param plugin    The plugin to register this entry as event listener.
	 * @param objective The objective associated to the source entry.
	 * @param source    The source tracked by this updater.
	 */
	public LocationAutoUpdater(Plugin plugin, ISimpleObjective objective, LocationEntry source) {
		super(plugin, objective, source);
		call = 0;
	}

	/**
	 * Create an entry that displays the player location. The message associated to this entry is :</br>
	 * Location loc = player.getLocation();</br>
	 * return before + loc.getBlockX() + delimiter + loc.getBlockY() + delimiter + loc.getBlockZ()</br>
	 * This constructor use " " as default delimiter.
	 * 
	 * @param plugin    The plugin to register this entry as event listener.
	 * @param objective The objective associated to the source entry.
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the player location. See above.
	 */
	public LocationAutoUpdater(Plugin plugin, ISimpleObjective objective, int score, String before) {
		this(plugin, objective, new LocationEntry(score, before));
	}

	/**
	 * Create an entry that displays the player location. The message associated to this entry is :</br>
	 * Location loc = player.getLocation();</br>
	 * return before + loc.getBlockX() + delimiter + loc.getBlockY() + delimiter + loc.getBlockZ()
	 * 
	 * @param plugin    The plugin to register this entry as event listener.
	 * @param objective The objective associated to the source entry.
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the player location. See above.
	 * @param delimiter The sequence of characters to be displayed between each element added. See above.
	 */
	public LocationAutoUpdater(Plugin plugin, ISimpleObjective objective, int score, String before, String delimiter) {
		this(plugin, objective, new LocationEntry(score, before, delimiter));
	}

	/**
	 * Create an entry that displays the player location. The message associated to this entry is :</br>
	 * Location loc = player.getLocation();</br>
	 * return before + loc.getBlockX() + delimiter + loc.getBlockY() + delimiter + loc.getBlockZ() + after;</br>
	 * 
	 * @param plugin    The plugin to register this entry as event listener.
	 * @param objective The objective associated to the source entry.
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the player location. See above.
	 * @param delimiter The sequence of characters to be displayed between each element added. See above.
	 * @param after     The sequence of characters to be displayed after the player location. See above.
	 */
	public LocationAutoUpdater(Plugin plugin, ISimpleObjective objective, int score, String before, String delimiter, String after) {
		this(plugin, objective, new LocationEntry(score, before, delimiter, after));
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		if (!event.getPlayer().getName().equals(getObjective().getPlayer().getName()))
			return;

		// Updating player objective each 4 calls.
		call++;
		if (call > 4) {
			update();
			call = 0;
		}
	}
}
