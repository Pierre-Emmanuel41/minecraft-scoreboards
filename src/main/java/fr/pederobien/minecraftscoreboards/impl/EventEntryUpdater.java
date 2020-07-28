package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.event.Listener;

public class EventEntryUpdater extends EntryUpdater implements Listener {

	@Override
	public void initialize() {
		getPlugin().getServer().getPluginManager().registerEvents(this, getPlugin());
	}
}
