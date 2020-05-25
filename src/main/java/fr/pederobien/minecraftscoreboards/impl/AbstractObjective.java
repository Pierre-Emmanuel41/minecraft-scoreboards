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
	private boolean isActivated;

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param player      The player associated to this objective. This player is used to display its informations.
	 * @param name        The name of this objective.
	 * @param displayName The name displayed on the given player score board.
	 */
	public AbstractObjective(Player player, String name, String displayName) {
		super(player, name, displayName);
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
		super(player, name, displayName, displaySlot);
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
	}

	@Override
	public final void initialize() {
		ScoreboardManager.setPlayerScoreboard(getPlayer(), getScoreboard().get());
		onInitialize();
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
	 * Because method {@link #initialize()} is declared final, this is the only way to initialize this objective.
	 */
	protected abstract void onInitialize();
}
