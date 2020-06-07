package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;

import fr.pederobien.minecraftmanagers.BukkitManager;
import fr.pederobien.minecraftmanagers.ScoreboardManager;
import fr.pederobien.minecraftmanagers.TeamManager;
import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.IObjective;

public class Objective extends EntriesObjective implements IObjective, Listener {
	private boolean isInitialized, isActivated;
	private Runnable colorUpdater;
	private ChatColor color;
	private int taskId;

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param plugin      The plugin used to update this objective.
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 */
	public Objective(Plugin plugin, Player player, String name, String displayName) {
		this(plugin, player, name, displayName, DisplaySlot.SIDEBAR);
	}

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param plugin      The plugin used to update this objective.
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 * @param displaySlot The slot where this objective is displayed on player screen.
	 */
	public Objective(Plugin plugin, Player player, String name, String displayName, DisplaySlot displaySlot) {
		this(plugin, player, name, displayName, "dummy", displaySlot);
	}

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param plugin      The plugin used to update this objective.
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 * @param criteria    The criteria tracked by this objective.
	 * @param displaySlot The slot where this objective is displayed on player screen.
	 */
	public Objective(Plugin plugin, Player player, String name, String displayName, String criteria, DisplaySlot displaySlot) {
		super(plugin, player, name, displayName, criteria, displaySlot);
		isInitialized = false;
		isActivated = false;
		colorUpdater = new ColorUpdater();
	}

	@Override
	public void setPlayer(Player player) {
		super.setPlayer(player);
		color = TeamManager.getColor(getPlayer());
	}

	@Override
	public final void start() {
		ScoreboardManager.setPlayerScoreboard(getPlayer(), getScoreboard().get());
		action(entry -> entry.setActivated(true));
		update();
		taskId = BukkitManager.getScheduler().runTaskTimer(getPlugin(), colorUpdater, 0, 5).getTaskId();
	}

	@Override
	public void stop() {
		action(entry -> entry.setActivated(false));
		BukkitManager.getScheduler().cancelTask(taskId);
	}

	@Override
	public final void initialize() {
		if (isInitialized)
			return;

		action(entry -> entry.initialize());
		isInitialized = true;
	}

	@Override
	public boolean isActivated() {
		return isActivated;
	}

	@Override
	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		if (isActivated && event.getPlayer().getName().equals(event.getPlayer().getName()))
			setPlayer(event.getPlayer());
	}

	private class ColorUpdater implements Runnable {

		@Override
		public void run() {
			ChatColor oldColor = color, newColor = TeamManager.getColor(getPlayer());
			if (oldColor == null || !newColor.equals(oldColor)) {
				getObjective().get().setDisplayName(newColor + getDisplayName() + ChatColor.RESET);
				for (IEntry entry : entries()) {
					entry.setColor(newColor);
					update(entry);
				}
			}
		}
	}
}
