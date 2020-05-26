package fr.pederobien.minecraftscoreboards.impl.entries.common;

import java.util.Optional;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import fr.pederobien.minecraftmanagers.TeamManager;
import fr.pederobien.minecraftscoreboards.impl.AbstractSimpleEntry;

public class TeamEntry extends AbstractSimpleEntry {
	private boolean colored;

	/**
	 * Create an entry that displays the player team name. If any team has been found, the symbol "?" is displayed.
	 * 
	 * @param score   The line number of this entry.
	 * @param before  The sequence of characters to be displayed before the player team name.
	 * @param after   The sequence of characters to be displayed after the player team name.
	 * @param colored True if the team name should be displayed in team color, false otherwise.
	 */
	public TeamEntry(int score, String before, String after, boolean colored) {
		super(score, before, after);
		this.colored = colored;
	}

	/**
	 * Create an entry that displays the player team name. If any team has been found, the symbol "?" is displayed.
	 * 
	 * @param score   The line number of this entry.
	 * @param before  The sequence of characters to be displayed before the player team name.
	 * @param colored True if the team name should be displayed in team color, false otherwise.
	 */
	public TeamEntry(int score, String before, boolean colored) {
		this(score, before, "", colored);
	}

	@Override
	protected String updateCurrentValue(Player player) {
		Optional<Team> optTeam = TeamManager.getTeam(player);
		return !optTeam.isPresent() ? "?" : colored ? optTeam.get().getColor() + optTeam.get().getName() + ChatColor.RESET : optTeam.get().getName();
	}
}
