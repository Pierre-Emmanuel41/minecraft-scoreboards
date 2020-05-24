package fr.pederobien.minecraftscoreboards.impl;

import java.util.function.Consumer;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;

import fr.pederobien.minecraftmanagers.PlayerManager;
import fr.pederobien.minecraftmanagers.ScoreboardManager;
import fr.pederobien.minecraftscoreboards.interfaces.IAutoUpdateEntry;
import fr.pederobien.minecraftscoreboards.interfaces.IAutoUpdateObjective;
import fr.pederobien.minecraftscoreboards.interfaces.IEntry;

public class AutoUpdateObjective extends Objective implements IAutoUpdateObjective {
	private Plugin plugin;
	private boolean isActivated;

	public AutoUpdateObjective(Player player, String name, String displayName, Plugin plugin) {
		super(player, name, displayName);
		this.plugin = plugin;
	}

	public AutoUpdateObjective(Player player, String name, String displayName, DisplaySlot displaySlot, Plugin plugin) {
		super(player, name, displayName, displaySlot);
		this.plugin = plugin;
	}

	public AutoUpdateObjective(Player player, String name, String displayName, String criteria, DisplaySlot displaySlot, Plugin plugin) {
		super(player, name, displayName, criteria, displaySlot);
		this.plugin = plugin;
	}

	@Override
	public Plugin getPlugin() {
		return plugin;
	}

	@Override
	public void addEntry(IAutoUpdateEntry entry) {
		super.addEntry(entry);
		entry.setObjective(this);
	}

	@Override
	public void removeEntry(IAutoUpdateEntry entry) {
		super.removeEntry(entry);
		entry.setObjective(null);
		entry.setActivated(false);
	}

	@Override
	public void register() {
		action(entry -> getPlugin().getServer().getPluginManager().registerEvents(entry, getPlugin()));
	}

	@Override
	public void initialize() {
		PlayerManager.getPlayers().forEach(player -> {
			action(entry -> {
				getObjective().get().getScore(entry.initialize(player)).setScore(entry.getScore());
			});
			ScoreboardManager.setPlayerScoreboard(player, getScoreboard().get());
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

	private void action(Consumer<IAutoUpdateEntry> consumer) {
		for (IEntry entry : entries())
			consumer.accept((IAutoUpdateEntry) entry);
	}
}
