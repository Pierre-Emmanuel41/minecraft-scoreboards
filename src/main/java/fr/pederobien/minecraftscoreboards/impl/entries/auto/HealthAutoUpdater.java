package fr.pederobien.minecraftscoreboards.impl.entries.auto;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.AutoUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.HealthEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class HealthAutoUpdater extends AutoUpdater<HealthEntry> {

	/**
	 * Create an entry updated when {@link PlayerDeathEvent}, {@link EntityDamageEvent}, {@link EntityRegainHealthEvent},
	 * {@link PlayerRespawnEvent} are thrown by the server.
	 * 
	 * @param plugin    The plugin to register this entry as event listener.
	 * @param objective The objective associated to the source entry.
	 * @param source    The source tracked by this updater.
	 * 
	 * @see HealthEntry
	 */
	public HealthAutoUpdater(Plugin plugin, ISimpleObjective objective, HealthEntry source) {
		super(plugin, objective, source);
	}

	/**
	 * Create an entry updated when {@link PlayerDeathEvent}, {@link EntityDamageEvent}, {@link EntityRegainHealthEvent},
	 * {@link PlayerRespawnEvent} are thrown by the server. This entry displays the current player health.
	 * 
	 * @param plugin    The plugin to register this entry as event listener.
	 * @param objective The objective associated to the source entry.
	 * @param score     The line number of this entry.
	 * @param pattern   A string used to format the player health on screen.
	 * 
	 * @see HealthEntry
	 */
	public HealthAutoUpdater(Plugin plugin, ISimpleObjective objective, int score, String pattern) {
		this(plugin, objective, new HealthEntry(score, pattern));
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onEntityDamageEvent(PlayerDeathEvent event) {
		if (event.getEntity().equals(getObjective().getPlayer()))
			internalUpdate();
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onEntityDamageEvent(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player && ((Player) event.getEntity()).equals(getObjective().getPlayer()))
			internalUpdate();
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onEntityRegainHealthEvent(EntityRegainHealthEvent event) {
		if (event.getEntity() instanceof Player && ((Player) event.getEntity()).equals(getObjective().getPlayer()))
			internalUpdate();
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
		if (event.getPlayer().equals(getObjective().getPlayer()))
			internalUpdate();
	}
}
