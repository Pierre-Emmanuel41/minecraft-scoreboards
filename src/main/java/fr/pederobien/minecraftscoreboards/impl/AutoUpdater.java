package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class AutoUpdater extends AbstractEntryUpdater implements Listener {

	/**
	 * Create an entry updater. This entry is responsible to update the source entry.
	 * 
	 * @param plugin    The plugin of this objective. The plugin could be useful to register its entries as event listener of to
	 *                  create periodic entry update.
	 * @param objective The objective associated to the source entry.
	 * @param source    The source tracked by this updater.
	 */
	protected AutoUpdater(Plugin plugin, ISimpleObjective objective, IEntry source) {
		super(plugin, objective, source);
	}

	@Override
	public void initialize() {
		getPlugin().getServer().getPluginManager().registerEvents(this, getPlugin());
	}
}
