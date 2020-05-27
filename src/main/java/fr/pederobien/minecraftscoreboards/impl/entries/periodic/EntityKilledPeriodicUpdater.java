package fr.pederobien.minecraftscoreboards.impl.entries.periodic;

import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.PeriodicUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.common.EntityKilledEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class EntityKilledPeriodicUpdater extends PeriodicUpdater<EntityKilledEntry> {

	/**
	 * Create an entry updater. This entry is responsible to update the source entry.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 */
	public EntityKilledPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, EntityKilledEntry source) {
		super(plugin, objective, delay, period, source);
	}

	/**
	 * Create an entry updater. This entry is responsible to update the source entry.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param source    The source tracked by this updater.
	 */
	public EntityKilledPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, EntityKilledEntry source) {
		this(plugin, objective, 0, period, source);
	}

	/**
	 * Create an entry updated when an entity of the given {@link EntityType} is killed.
	 * 
	 * @param plugin     The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                   create periodic entry update.
	 * @param objective  The objective associated to the source entry.
	 * @param delay      Represents the number of server ticks to wait before updating the objective.
	 * @param period     Represents the number of server ticks between two objective updates.
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * @param before     The sequence of characters to be displayed before the player statistic.
	 * @param after      The sequence of characters to be displayed after the player statistic.
	 */
	public EntityKilledPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, int score, EntityType entityType, String before,
			String after) {
		this(plugin, objective, delay, period, new EntityKilledEntry(score, entityType, before, after));
	}

	/**
	 * Create an entry updated when an entity of the given {@link EntityType} is killed.
	 * 
	 * @param plugin     The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                   create periodic entry update.
	 * @param objective  The objective associated to the source entry.
	 * @param period     Represents the number of server ticks between two objective updates.
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * @param before     The sequence of characters to be displayed before the player statistic.
	 * @param after      The sequence of characters to be displayed after the player statistic.
	 */
	public EntityKilledPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, int score, EntityType entityType, String before, String after) {
		this(plugin, objective, 0, period, new EntityKilledEntry(score, entityType, before, after));
	}

	/**
	 * Create an entry updated when an entity of the given {@link EntityType} is killed.
	 * 
	 * @param plugin     The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                   create periodic entry update.
	 * @param objective  The objective associated to the source entry.
	 * @param delay      Represents the number of server ticks to wait before updating the objective.
	 * @param period     Represents the number of server ticks between two objective updates.
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * @param before     The sequence of characters to be displayed before the player statistic.
	 */
	public EntityKilledPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, int score, EntityType entityType, String before) {
		this(plugin, objective, delay, period, new EntityKilledEntry(score, entityType, before));
	}

	/**
	 * Create an entry updated when an entity of the given {@link EntityType} is killed.
	 * 
	 * @param plugin     The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                   create periodic entry update.
	 * @param objective  The objective associated to the source entry.
	 * @param delay      Represents the number of server ticks to wait before updating the objective.
	 * @param period     Represents the number of server ticks between two objective updates.
	 * @param score      The line number of this entry.
	 * @param entityType The type of entity used to update this entry.
	 * @param before     The sequence of characters to be displayed before the player statistic.
	 */
	public EntityKilledPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, int score, EntityType entityType, String before) {
		this(plugin, objective, 0, period, new EntityKilledEntry(score, entityType, before));
	}
}
