package fr.pederobien.minecraftscoreboards.impl.entries.update;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.AutoUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.common.PlayerKillsEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class PlayerKillsAutoUpdater extends AutoUpdater<PlayerKillsEntry> {

	/**
	 * Create an entry updater. This entry is responsible to update the source entry. This updater is a {@link PlayerDeathEvent}
	 * listener. This means that when an this event is thrown by the server, this updater update the player objective.
	 * 
	 * @param plugin    The plugin to register this entry as event listener.
	 * @param objective The objective associated to the source entry.
	 * @param source    The source tracked by this updater.
	 */
	protected PlayerKillsAutoUpdater(Plugin plugin, ISimpleObjective objective, PlayerKillsEntry source) {
		super(plugin, objective, source);
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		if (event.getEntity().getKiller().equals(getObjective().getPlayer()))
			update();
	}
}
