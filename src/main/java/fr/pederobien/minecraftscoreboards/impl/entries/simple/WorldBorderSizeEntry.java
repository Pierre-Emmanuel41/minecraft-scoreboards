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

	/**
	 * Create an entry that display the current size of the {@link WorldBorder} associated to the given world.
	 * 
	 * @param score   The line number of this entry in the player objective.
	 * @param world   The world used to display the current size of its world border.
	 * @param after   The sequence of characters to be displayed after the world border size.
	 * @param pattern A string used to format the world border size.
	 */
	public WorldBorderSizeEntry(int score, World world, String after, String pattern) {
		super(score, WorldManager.getWorldNameNormalised(world) + " : ", after);
		this.world = world;
		this.format = new DecimalFormat(pattern);
	}

	@Override
	protected String updateCurrentValue(Player player) {
		return format.format(world.getWorldBorder().getSize());
	}

	/**
	 * @return The world to which this entry is associated.
	 */
	public World getWorld() {
		return world;
	}
}
