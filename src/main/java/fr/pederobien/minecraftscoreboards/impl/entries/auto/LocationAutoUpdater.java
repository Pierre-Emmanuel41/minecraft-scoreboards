package fr.pederobien.minecraftscoreboards.impl.entries.auto;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.pederobien.minecraftscoreboards.impl.AutoUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.LocationEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class LocationAutoUpdater extends AutoUpdater<LocationEntry> {

	/**
	 * Create an entry updated when a {@link PlayerMoveEvent} is thrown by the server.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param source    The source tracked by this updater.
	 * 
	 * @see LocationEntry
	 */
	public LocationAutoUpdater(ISimpleObjective objective, LocationEntry source) {
		super(objective, source);
	}

	/**
	 * Create an entry updated when a {@link PlayerMoveEvent} is thrown by the server. This entry displays the current location of a
	 * player.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param score     The line number of this entry.
	 * @param delimiter The sequence of characters to be displayed between each element added. See above.
	 * 
	 * @see LocationEntry
	 */
	public LocationAutoUpdater(ISimpleObjective objective, int score, String delimiter) {
		super(objective, new LocationEntry(score, delimiter));
	}

	/**
	 * Create an entry updated when a {@link PlayerMoveEvent} is thrown by the server. This entry displays the current location of a
	 * player.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param score     The line number of this entry.
	 * 
	 * @see LocationEntry
	 */
	public LocationAutoUpdater(ISimpleObjective objective, int score) {
		super(objective, new LocationEntry(score));
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		if (event.getPlayer().equals(getObjective().getPlayer()))
			update();
	}
}
