package fr.pederobien.minecraftscoreboards.impl.entries.update;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.AutoUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.common.LocationEntry;
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
