package fr.pederobien.minecraftscoreboards.impl.entries.update;

import java.text.DecimalFormat;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import fr.pederobien.minecraftscoreboards.impl.AbstractAutoUpdateEntry;

public class HealthEntry extends AbstractAutoUpdateEntry {
	private EntityDamageEvent damageEvent;
	private EntityRegainHealthEvent regainEvent;
	private Source source;
	private Player target;
	private DecimalFormat format;

	/**
	 * Create an entry that display the player's health that looks like
	 * "<code>before + (int) player.getHealth() + "/" + (int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()</code>".
	 * 
	 * @param score   The line number of this entry.
	 * @param before  The sequence of characters to be displayed before the player health. See above.
	 * @param pattern A string used to format the player health on screen.
	 */
	public HealthEntry(int score, String before, String pattern) {
		this(score, before, "", pattern);
	}

	/**
	 * Create an entry that display the player's health that looks like "<code>before + (int) player.getHealth() + after</code>".</br>
	 * However if the value of parameter <code>after</code> is null, then it is replaced by :</br>
	 * <code>"/" + (int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()</code>.
	 * 
	 * @param score   The line number of this entry.
	 * @param before  The sequence of characters to be displayed before the player health. See above.
	 * @param after   The sequence of characters to be displayed after the player health. See above.
	 * @param pattern A string used to format the player health on screen.
	 */
	public HealthEntry(int score, String before, String after, String pattern) {
		super(score, before, after);
		source = Source.UNKNOWN;
		format = new DecimalFormat(pattern);

	}

	@Override
	protected String updateCurrentValue(Player player) {
		Source src = player.equals(target) ? source : Source.UNKNOWN;

		String health;
		switch (src) {
		case DEATH:
			health = format.format(0);
			break;
		case DAMAGE:
			health = format.format(player.getHealth() - damageEvent.getDamage());
			break;
		case REGAIN:
			health = format.format(player.getHealth() + regainEvent.getAmount());
			break;
		default:
			health = format.format(player.getHealth());
		}
		return health + getAfter(player);
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onEntityDamageEvent(PlayerDeathEvent event) {
		if (!(event.getEntity() instanceof Player))
			return;
		updateEvent(Source.DEATH, (Player) event.getEntity());
		update();
		source = Source.UNKNOWN;
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onEntityDamageEvent(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player))
			return;
		updateEvent(Source.DAMAGE, (Player) event.getEntity());
		damageEvent = event;
		update();
		source = Source.UNKNOWN;
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onEntityRegainHealthEvent(EntityRegainHealthEvent event) {
		if (!(event.getEntity() instanceof Player))
			return;
		updateEvent(Source.REGAIN, (Player) event.getEntity());
		regainEvent = event;
		update();
		source = Source.UNKNOWN;
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
		target = event.getPlayer();
		updateLater(20);
	}

	private void updateEvent(Source source, Player target) {
		this.source = source;
		this.target = target;
	}

	private String getAfter(Player player) {
		return getAfter() == "" ? "/" + (int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() : getAfter();
	}

	private enum Source {
		UNKNOWN, DEATH, DAMAGE, REGAIN;
	}
}
