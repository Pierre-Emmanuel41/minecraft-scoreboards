package fr.pederobien.minecraftscoreboards.impl.entries.update;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import fr.pederobien.minecraftscoreboards.impl.AbstractAutoUpdateEntry;

public class AutoUpdateStatisticEntry extends AbstractAutoUpdateEntry {
	private Statistic statistic;
	private EntityType entityType;
	private Material material;
	private String before, after;

	private AutoUpdateStatisticEntry(int score, Statistic statistic, EntityType entityType, Material material, String before, String after) {
		super(score);
		this.statistic = statistic;
		this.entityType = entityType;
		this.material = material;
		this.before = before;
		this.after = after;
	}

	public AutoUpdateStatisticEntry(int score, Statistic statistic, String before, String after) {
		this(score, statistic, null, null, before, after);
	}

	public AutoUpdateStatisticEntry(int score, Statistic statistic, String before) {
		this(score, statistic, null, null, before, "");
	}

	public AutoUpdateStatisticEntry(int score, Statistic statistic, EntityType entityType, String before, String after) {
		this(score, statistic, entityType, null, before, after);
	}

	public AutoUpdateStatisticEntry(int score, Statistic statistic, EntityType entityType, String before) {
		this(score, statistic, entityType, null, before, "");
	}

	public AutoUpdateStatisticEntry(int score, Statistic statistic, Material material, String before, String after) {
		this(score, statistic, null, material, before, after);
	}

	public AutoUpdateStatisticEntry(int score, Statistic statistic, Material material, String before) {
		this(score, statistic, null, material, before, "");
	}

	@Override
	protected String onInitialize(Player player) {
		return updateCurrentValue(player);
	}

	@Override
	protected String updateCurrentValue(Player player) {
		if (material == null && entityType != null)
			return before + player.getStatistic(statistic, entityType) + after;
		if (entityType == null && material != null)
			return before + player.getStatistic(statistic, material) + after;

		return before + player.getStatistic(statistic) + after;
	}

	/**
	 * @return The {@link Statistic} used to update this entry.
	 */
	public Statistic getStatistic() {
		return statistic;
	}

	/**
	 * @return The {@link EntityType} used by the statistic to update this entry. Could be null.
	 */
	public EntityType getEntityType() {
		return entityType;
	}

	/**
	 * @return The {@link Material} used by the {@link Statistic} to update this entry. Could be null.
	 */
	public Material getMaterial() {
		return material;
	}
}
