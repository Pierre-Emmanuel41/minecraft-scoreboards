package fr.pederobien.minecraftscoreboards.impl.entries.auto;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.impl.AutoUpdater;
import fr.pederobien.minecraftscoreboards.impl.entries.simple.HealthEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class HealthAutoUpdater extends AutoUpdater<HealthEntry> {

	/**
	 * Create an entry updater. This entry is responsible to update the source entry.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param source    The source tracked by this updater.
	 */
	public HealthAutoUpdater(Plugin plugin, ISimpleObjective objective, HealthEntry source) {
		super(plugin, objective, source);
	}

	/**
	 * Create an entry that display the player's health that looks like "<code>before + (int) player.getHealth() + after</code>".</br>
	 * However if the value of parameter <code>after</code> is null, then it is replaced by :</br>
	 * <code>"/" + (int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()</code>.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the player health. See above.
	 * @param after     The sequence of characters to be displayed before the player health. See above.
	 * @param pattern   A string used to format the player health on screen.
	 */
	public HealthAutoUpdater(Plugin plugin, ISimpleObjective objective, int score, String before, String after, String pattern) {
		this(plugin, objective, new HealthEntry(score, before, after, pattern));
	}

	/**
	 * Create an entry that display the player's health that looks like
	 * "<code>before + (int) player.getHealth() + "/" + (int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()</code>".
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param score     The line number of this entry.
	 * @param before    The sequence of characters to be displayed before the player health. See above.
	 * @param pattern   A string used to format the player health on screen.
	 */
	public HealthAutoUpdater(Plugin plugin, ISimpleObjective objective, int score, String before, String pattern) {
		this(plugin, objective, new HealthEntry(score, before, pattern));
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onEntityDamageEvent(PlayerDeathEvent event) {
		if (event.getEntity().equals(getObjective().getPlayer()))
			update();
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onEntityDamageEvent(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player && ((Player) event.getEntity()).equals(getObjective().getPlayer()))
			update();
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onEntityRegainHealthEvent(EntityRegainHealthEvent event) {
		if (event.getEntity() instanceof Player && ((Player) event.getEntity()).equals(getObjective().getPlayer()))
			update();
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
		if (event.getPlayer().equals(getObjective().getPlayer()))
			update();
	}
}
