package fr.pederobien.minecraftscoreboards.impl.entries.common;

import java.text.DecimalFormat;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import fr.pederobien.minecraftscoreboards.impl.AbstractEntry;

public class HealthEntry extends AbstractEntry {
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
	 * @param after   The sequence of characters to be displayed before the player health. See above.
	 * @param pattern A string used to format the player health on screen.
	 */
	public HealthEntry(int score, String before, String after, String pattern) {
		super(score, before, after);
		format = new DecimalFormat(pattern);
	}

	@Override
	public void initialize() {

	}

	@Override
	protected String updateCurrentValue(Player player) {
		return format.format(player.getHealth()) + getAfter(player);
	}

	private String getAfter(Player player) {
		return getAfter() == null ? "/" + (int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() : getAfter();
	}
}
