package fr.pederobien.minecraftscoreboards.impl.entries.simple;

import java.text.DecimalFormat;

import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

import fr.pederobien.minecraftmanagers.WorldManager;
import fr.pederobien.minecraftscoreboards.impl.AbstractSimpleEntry;

public class WorldBorderSizeEntry extends AbstractSimpleEntry {
	private World world;
	private DecimalFormat format;
	private boolean displayHalfSize;

	/**
	 * Create an entry that display the current size of the {@link WorldBorder} associated to the given world.
	 * 
	 * @param score   The line number of this entry in the player objective.
	 * @param world   The world used to display the current size of its world border.
	 * @param pattern A string used to format the world border size.
	 */
	public WorldBorderSizeEntry(int score, World world, String pattern) {
		super(score);
		this.world = world;
		this.format = new DecimalFormat(pattern);
	}

	@Override
	protected String updateCurrentValue(Player player) {
		return !displayHalfSize ? format.format(world.getWorldBorder().getSize()) : "\u00b1" + format.format(world.getWorldBorder().getSize() / 2.0);
	}

	/**
	 * @return The world to which this entry is associated.
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * Used to to display the "diameter" or the "radius" of the world border. By default, this entry display the diameter. When set to
	 * true, this entry display the following message : <code>WorldName : \u00b1 + radius + after</code>. The code \u00b1 correspond
	 * to the symbol +/-.
	 * 
	 * @param displayHalfSize True to display the radius, false to display the diameter.
	 * @return This entry.
	 */
	public WorldBorderSizeEntry setDisplayHalfSize(boolean displayHalfSize) {
		this.displayHalfSize = displayHalfSize;
		return this;
	}

	@Override
	protected final String getBefore(Player player) {
		return WorldManager.getWorldNameNormalised(world) + " : ";
	}
}
