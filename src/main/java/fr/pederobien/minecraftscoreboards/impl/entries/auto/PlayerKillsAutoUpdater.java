package fr.pederobien.minecraftscoreboards.impl.entries.auto;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.pederobien.minecraftscoreboards.impl.AutoUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.PlayerKillsEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class PlayerKillsAutoUpdater extends AutoUpdater<PlayerKillsEntry> {

	/**
	 * Create an entry updated when {@link PlayerDeathEvent} is thrown by the server.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param source    The source tracked by this updater.
	 * 
	 * @see PlayerKillsEntry
	 */
	public PlayerKillsAutoUpdater(ISimpleObjective objective, PlayerKillsEntry source) {
		super(objective, source);
	}

	/**
	 * Create an entry that is updated when {@link PlayerDeathEvent} is thrown by the server. This entry display the number of player
	 * a player has killed.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param score     The line number of this entry.
	 * 
	 * @see PlayerKillsEntry
	 */
	public PlayerKillsAutoUpdater(ISimpleObjective objective, int score) {
		super(objective, new PlayerKillsEntry(score));
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		if (event.getEntity().getKiller().equals(getObjective().getPlayer()))
			update();
	}
}
