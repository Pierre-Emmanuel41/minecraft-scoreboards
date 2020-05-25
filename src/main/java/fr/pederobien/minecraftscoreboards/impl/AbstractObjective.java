package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;

import fr.pederobien.minecraftmanagers.ScoreboardManager;
import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.IObjective;

public abstract class AbstractObjective<T extends IEntry> extends EntriesObjective<T> implements IObjective<T>, Listener {
	private boolean isInitialized, isActivated;

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 */
	public AbstractObjective(Player player, String name, String displayName) {
		this(player, name, displayName, DisplaySlot.SIDEBAR);
	}

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 * @param displaySlot The slot where this objective is displayed on player screen.
	 */
	public AbstractObjective(Player player, String name, String displayName, DisplaySlot displaySlot) {
		this(player, name, displayName, "dummy", displaySlot);
	}

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 * @param criteria    The criteria tracked by this objective.
	 * @param displaySlot The slot where this objective is displayed on player screen.
	 */
	public AbstractObjective(Player player, String name, String displayName, String criteria, DisplaySlot displaySlot) {
		super(player, name, displayName, criteria, displaySlot);
		isInitialized = false;
		isActivated = false;
	}

	@Override
	public final void start() {
		onStart();
		update();
	}

	@Override
	public void stop() {
		action(entry -> entry.setActivated(false));
	}

	@Override
	public final void initialize() {
		if (isInitialized)
			return;

		ScoreboardManager.setPlayerScoreboard(getPlayer(), getScoreboard().get());
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

	/**
	 * Because method {@link #start()} is declared final, this is the only way to start the update of this objective.
	 */
	protected abstract void onStart();
}
