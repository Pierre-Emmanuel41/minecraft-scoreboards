package fr.pederobien.minecraftscoreboards.impl.entries.auto;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.AutoUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.PlayerKillsEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class PlayerKillsAutoUpdater extends AutoUpdater<PlayerKillsEntry> {

	/**
	 * Create an entry updated when {@link PlayerDeathEvent} is thrown by the server.
	 * 
	 * @param plugin    The plugin to register this entry as event listener.
	 * @param objective The objective associated to the source entry.
	 * @param source    The source tracked by this updater.
	 * 
	 * @see PlayerKillsEntry
	 */
	public PlayerKillsAutoUpdater(Plugin plugin, ISimpleObjective objective, PlayerKillsEntry source) {
		super(plugin, objective, source);
	}

	/**
	 * Create an entry that is updated when {@link PlayerDeathEvent} is thrown by the server. This entry display the number of player
	 * a player has killed.
	 * 
	 * @param plugin    The plugin to register this entry as event listener.
	 * @param objective The objective associated to the source entry.
	 * @param score     The line number of this entry.
	 * 
	 * @see PlayerKillsEntry
	 */
	public PlayerKillsAutoUpdater(Plugin plugin, ISimpleObjective objective, int score) {
		super(plugin, objective, new PlayerKillsEntry(score));
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		if (event.getEntity().getKiller().equals(getObjective().getPlayer()))
			internalUpdate();
	}
}
