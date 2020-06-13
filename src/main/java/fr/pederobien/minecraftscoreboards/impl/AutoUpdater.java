package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.event.Listener;

import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.IObjective;

public class AutoUpdater<T extends IEntry> extends AbstractEntryUpdater<T> implements Listener {

	/**
	 * Create an entry updater. This entry is responsible to update the source entry.
	 * 
	 * @param objective The objective associated to the source entry.
	 * @param source    The source tracked by this updater.
	 */
	protected AutoUpdater(IObjective objective, T source) {
		super(objective, source);
	}

	@Override
	public void initialize() {
		getPlugin().getServer().getPluginManager().registerEvents(this, getPlugin());
	}
}
