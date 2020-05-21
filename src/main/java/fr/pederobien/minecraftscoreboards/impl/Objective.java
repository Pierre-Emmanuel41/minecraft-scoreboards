package fr.pederobien.minecraftscoreboards.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

import fr.pederobien.minecraftmanagers.ScoreboardManager;
import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.IObjective;

public class Objective implements IObjective {
	private Scoreboard scoreboard;
	private org.bukkit.scoreboard.Objective objective;
	private Map<Integer, IEntry> entries;
	private List<IEntry> entriesList;
	private String name, criteria, displayName;
	private DisplaySlot displaySlot;

	public Objective(String name, String displayName) {
		this(name, displayName, DisplaySlot.SIDEBAR);
	}

	public Objective(String name, String displayName, DisplaySlot displaySlot) {
		this(name, displayName, "dummy", displaySlot);
	}

	public Objective(String name, String displayName, String criteria, DisplaySlot displaySlot) {
		this.name = name;
		this.criteria = criteria;
		this.displayName = displayName;
		this.displaySlot = displaySlot;
		entries = new HashMap<Integer, IEntry>();
		entriesList = Collections.unmodifiableList(new ArrayList<IEntry>(entries.values()));
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getCriteria() {
		return criteria;
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public DisplaySlot getDisplaySlot() {
		return displaySlot;
	}

	@Override
	public void addEntry(IEntry entry) {
		entries.put(entry.getScore(), entry);
		entriesList = Collections.unmodifiableList(new ArrayList<IEntry>(entries.values()));
		updateObjective();
	}

	@Override
	public void removeEntry(IEntry entry) {
		entries.remove(entry.getScore());
		entriesList = Collections.unmodifiableList(new ArrayList<IEntry>(entries.values()));
		updateObjective();
	}

	@Override
	public void update(Player player) {
		Optional<Scoreboard> optScoreboard = getScoreboard();
		if (!optScoreboard.isPresent())
			return;

		for (IEntry entry : entries.values()) {
			scoreboard.resetScores(entry.getCurrentValue());
			entry.update(player);
			objective.getScore(entry.getCurrentValue()).setScore(entry.getScore());
		}
	}

	@Override
	public List<IEntry> entries() {
		return entriesList;
	}

	@Override
	public void setScoreboard(Scoreboard scoreboard) {
		this.scoreboard = scoreboard;
		if (scoreboard == null)
			return;

		objective = ScoreboardManager.createObjective(scoreboard, getName(), getCriteria(), getDisplayName(), getDisplaySlot());
		updateObjective();
	}

	@Override
	public Optional<Scoreboard> getScoreboard() {
		return scoreboard == null ? Optional.empty() : Optional.of(scoreboard);
	}

	@Override
	public Optional<org.bukkit.scoreboard.Objective> getObjective() {
		return objective == null ? Optional.empty() : Optional.of(objective);
	}

	private void updateObjective() {
		if (!getObjective().isPresent())
			return;
		for (IEntry entry : entries.values()) {
			scoreboard.resetScores(entry.getCurrentValue());
			objective.getScore(entry.getCurrentValue()).setScore(entry.getScore());
		}
	}
}