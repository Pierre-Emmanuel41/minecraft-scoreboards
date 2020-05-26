package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.event.Listener;

import fr.pederobien.minecraftscoreboards.interfaces.IEntry;

public class AutoUpdater extends AbstractEntryUpdater implements Listener {

	/**
	 * Create an entry updater. This entry is responsible to update the source entry.
	 * 
	 * @param source The source tracked by this updater.
	 */
	protected AutoUpdater(IEntry source) {
		super(source);
	}

	@Override
	public void initialize() {
		getObjective().getPlugin().getServer().getPluginManager().registerEvents(this, getObjective().getPlugin());
	}
}
