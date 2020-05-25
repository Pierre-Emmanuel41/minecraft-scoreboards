package fr.pederobien.minecraftscoreboards.impl;

import java.util.function.Consumer;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;

import fr.pederobien.minecraftmanagers.ScoreboardManager;
import fr.pederobien.minecraftscoreboards.interfaces.IAutoUpdateEntry;
import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.IObjective;

public class Objective<T extends IEntry> extends EntriesObjective<T> implements IObjective<T>, Listener {
	private boolean isActivated;

	public Objective(Player player, String name, String displayName, Plugin plugin) {
		super(player, name, displayName);
	}

	public Objective(Player player, String name, String displayName, DisplaySlot displaySlot, Plugin plugin) {
		super(player, name, displayName, displaySlot);
	}

	public Objective(Player player, String name, String displayName, String criteria, DisplaySlot displaySlot, Plugin plugin) {
		super(player, name, displayName, criteria, displaySlot);
	}

	@Override
	public void initialize() {
		ScoreboardManager.setPlayerScoreboard(getPlayer(), getScoreboard().get());
		action(entry -> {
			getObjective().get().getScore(entry.initialize(getPlayer())).setScore(entry.getScore());
		});
	}

	@Override
	public boolean isActivated() {
		return isActivated;
	}

	@Override
	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
		action(entry -> entry.setActivated(isActivated));
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		if (isActivated && event.getPlayer().getName().equals(event.getPlayer().getName()))
			setPlayer(event.getPlayer());
	}

	private void action(Consumer<IAutoUpdateEntry> consumer) {
		for (IEntry entry : entries())
			consumer.accept((IAutoUpdateEntry) entry);
	}
}
