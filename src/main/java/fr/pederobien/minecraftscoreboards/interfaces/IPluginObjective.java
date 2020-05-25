package fr.pederobien.minecraftscoreboards.interfaces;

import org.bukkit.plugin.Plugin;

public interface IPluginObjective<T extends IEntry> extends IObjective<T> {

	/**
	 * @return The plugin used to register each entry as event listener.
	 */
	Plugin getPlugin();
}
