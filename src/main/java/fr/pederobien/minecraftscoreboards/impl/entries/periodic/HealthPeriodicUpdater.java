package fr.pederobien.minecraftscoreboards.impl.entries.periodic;

import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.PeriodicUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.common.HealthEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class HealthPeriodicUpdater extends PeriodicUpdater<HealthEntry> {

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
	public HealthPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, HealthEntry source) {
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
	public HealthPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, HealthEntry source) {
		this(plugin, objective, 0, period, source);
	}

	/**
	 * Create an entry that display the player's health that looks like "<code>before + (int) player.getHealth() + after</code>".</br>
	 * However if the value of parameter <code>after</code> is null, then it is replaced by :</br>
	 * <code>"/" + (int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()</code>.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the player health. See above.
	 * @param after     The sequence of characters to be displayed before the player health. See above.
	 * @param pattern   A string used to format the player health on screen.
	 */
	public HealthPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, int score, String before, String after, String pattern) {
		this(plugin, objective, delay, period, new HealthEntry(score, before, after, pattern));
	}

	/**
	 * Create an entry that display the player's health that looks like "<code>before + (int) player.getHealth() + after</code>".</br>
	 * However if the value of parameter <code>after</code> is null, then it is replaced by :</br>
	 * <code>"/" + (int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()</code>.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the player health. See above.
	 * @param after     The sequence of characters to be displayed before the player health. See above.
	 * @param pattern   A string used to format the player health on screen.
	 */
	public HealthPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, int score, String before, String after, String pattern) {
		this(plugin, objective, period, new HealthEntry(score, before, after, pattern));
	}

	/**
	 * Create an entry that display the player's health that looks like
	 * "<code>before + (int) player.getHealth() + "/" + (int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()</code>".
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param delay     Represents the number of server ticks to wait before updating the objective.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the player health. See above.
	 * @param pattern   A string used to format the player health on screen.
	 */
	public HealthPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long delay, long period, int score, String before, String pattern) {
		this(plugin, objective, delay, period, new HealthEntry(score, before, pattern));
	}

	/**
	 * Create an entry that display the player's health that looks like
	 * "<code>before + (int) player.getHealth() + "/" + (int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()</code>".
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param period    Represents the number of server ticks between two objective updates.
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the player health. See above.
	 * @param pattern   A string used to format the player health on screen.
	 */
	public HealthPeriodicUpdater(Plugin plugin, ISimpleObjective objective, long period, int score, String before, String pattern) {
		this(plugin, objective, period, new HealthEntry(score, before, pattern));
	}
}
