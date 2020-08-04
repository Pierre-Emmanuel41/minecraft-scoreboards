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

	public static IEntryUpdater periodic(long delay, long period) {
		return new PeriodicEntryUpdater(delay, period);
	}

	public static IEntryUpdater periodic(long period) {
		return new PeriodicEntryUpdater(period);
	}

	public static IEventEntryUpdater<BlockBreakEvent> blockBreak() {
		return new BlockBreakUpdater();
	}

	public static IEventEntryUpdater<EntityDamageEvent> entityDamage() {
		return new EntityDamageUpdater();
	}

	public static IEventEntryUpdater<EntityDeathEvent> entityDeath() {
		return new EntityDeathUpdater();
	}

	public static IEventEntryUpdater<EntityRegainHealthEvent> entityRegainHealth() {
		return new EntityRegainHealthUpdater();
	}

	public static IEventEntryUpdater<PlayerGameModeChangeEvent> playerGameModeChange() {
		return new PlayerGameModeChangeUpdater();
	}

	public static IEventEntryUpdater<PlayerMoveEvent> playerMove() {
		return new PlayerMoveUpdater();
	}

	public static IEventEntryUpdater<PlayerRespawnEvent> playerRespawn() {
		return new PlayerRespawnUpdater();
	}
}
