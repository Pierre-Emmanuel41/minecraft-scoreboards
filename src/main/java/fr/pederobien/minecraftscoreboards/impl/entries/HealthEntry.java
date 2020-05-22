package fr.pederobien.minecraftscoreboards.impl.entries;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import fr.pederobien.minecraftscoreboards.impl.AbstractEntry;

public class HealthEntry extends AbstractEntry {
	private String before, after;

	/**
	 * Create an entry that display the player's health that looks like
	 * "<code>before + (int) player.getHealth() + "/" + (int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()</code>".
	 * 
	 * @param score  The line number of this entry.
	 * @param before The sequence of characters to be displayed before the player health. See above.
	 */
	protected HealthEntry(int score, String before) {
		this(score, before, null);
	}

	/**
	 * Create an entry that display the player's health that looks like "<code>before + (int) player.getHealth() + after</code>".</br>
	 * However if the value of parameter <code>after</code> is null, then it is replaced by :</br>
	 * <code>"/" + (int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()</code>.
	 * 
	 * @param score  The line number of this entry.
	 * @param before The sequence of characters to be displayed before the player health. See above.
	 * @param after  The sequence of characters to be displayed before the player health. See above.
	 */
	protected HealthEntry(int score, String before, String after) {
		super(score);
		this.before = before;
		this.after = after;
	}

	@Override
	protected String updateCurrentValue(Player player) {
		if (after != null)
			return before + (int) player.getHealth() + after;
		return before + (int) player.getHealth() + "/" + (int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
	}
}
