package fr.pederobien.minecraftscoreboards.impl.entries.simple;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import fr.pederobien.minecraftscoreboards.impl.AbstractSimpleEntry;

public class StatisticEntry extends AbstractSimpleEntry {
	private Statistic statistic;
	private EntityType entityType;
	private Material material;

	/**
	 * Create a statistic entry.
	 * 
	 * @param score      The line number of this entry.
	 * @param statistic  The statistic tracked by this entry.
	 * @param entityType The type of entity used to update this entry.
	 * @param material   The material used to update this entry.
	 */
	private StatisticEntry(int score, Statistic statistic, EntityType entityType, Material material) {
		super(score);
		this.statistic = statistic;
		this.entityType = entityType;
		this.material = material;
	}

	/**
	 * Create a statistic entry.
	 * 
	 * @param score     The line number of this entry.
	 * @param statistic The statistic tracked by this entry.
	 */
	public StatisticEntry(int score, Statistic statistic) {
		this(score, statistic, null, null);
	}

	/**
	 * Create a statistic entry.
	 * 
	 * @param score     The line number of this entry.
	 * @param statistic The statistic tracked by this entry.
	 * @param before    The sequence of characters to be displayed before the player statistic.
	 */
	public StatisticEntry(int score, Statistic statistic, String before) {
		this(score, statistic, null, null);
	}

	/**
	 * Create a statistic entry.
	 * 
	 * @param score      The line number of this entry.
	 * @param statistic  The statistic tracked by this entry.
	 * @param entityType The type of entity used to update this entry.
	 */
	public StatisticEntry(int score, Statistic statistic, EntityType entityType) {
		this(score, statistic, entityType, null);
	}

	/**
	 * Create a statistic entry.
	 * 
	 * @param score     The line number of this entry.
	 * @param statistic The statistic tracked by this entry.
	 * @param material  The material used to update this entry.
	 */
	public StatisticEntry(int score, Statistic statistic, Material material) {
		this(score, statistic, null, material);
	}

	@Override
	protected String updateCurrentValue(Player player) {
		if (material == null && entityType != null)
			return "" + player.getStatistic(statistic, entityType);
		if (entityType == null && material != null)
			return "" + player.getStatistic(statistic, material);

		return "" + player.getStatistic(statistic);
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
