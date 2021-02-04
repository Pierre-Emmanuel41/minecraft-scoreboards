package fr.pederobien.minecraftscoreboards.impl.updaters;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import fr.pederobien.minecraftscoreboards.impl.PeriodicEntryUpdater;
import fr.pederobien.minecraftscoreboards.interfaces.IEntryUpdater;
import fr.pederobien.minecraftscoreboards.interfaces.IEventEntryUpdater;

public class UpdatersFactory {

	/**
	 * Creates a periodic updater based on the given parameters.
	 * 
	 * @param delay  The number of server tick to wait before the first update.
	 * @param period The number of server tick between two updates.
	 * 
	 * @return A periodic entry updater.
	 */
	public static IEntryUpdater periodic(long delay, long period) {
		return new PeriodicEntryUpdater(delay, period);
	}

	/**
	 * Creates a periodic updater based on the given parameters.
	 * 
	 * @param period The number of server tick between two updates.
	 * 
	 * @return A periodic entry updater.
	 */
	public static IEntryUpdater periodic(long period) {
		return new PeriodicEntryUpdater(period);
	}

	/**
	 * @return An updater that update the entry when a block is broken by the player associated to the entry.
	 */
	public static IEventEntryUpdater<BlockBreakEvent> blockBreak() {
		return new BlockBreakUpdater();
	}

	/**
	 * @return An updater that update the entry when the player associated to the entry takes damages.
	 */
	public static IEventEntryUpdater<EntityDamageEvent> entityDamage() {
		return new EntityDamageUpdater();
	}

	/**
	 * @return An updater that update the entry when the player associated to the entry dies.
	 */
	public static IEventEntryUpdater<EntityDeathEvent> entityDeath() {
		return new EntityDeathUpdater();
	}

	/**
	 * @return An updater that update the entry when the player associated to the entry regains health.
	 */
	public static IEventEntryUpdater<EntityRegainHealthEvent> entityRegainHealth() {
		return new EntityRegainHealthUpdater();
	}

	public static IEventEntryUpdater<PlayerGameModeChangeEvent> playerGameModeChange() {
		return new PlayerGameModeChangeUpdater();
	}

	/**
	 * @return An updater that update the entry when the player associated to the the entry moves.
	 */
	public static IEventEntryUpdater<PlayerMoveEvent> playerMove() {
		return new PlayerMoveUpdater();
	}

	/**
	 * @return An updater that update the entry when the player associated to the entry respawns.
	 */
	public static IEventEntryUpdater<PlayerRespawnEvent> playerRespawn() {
		return new PlayerRespawnUpdater();
	}
}
