package fr.pederobien.minecraftscoreboards.interfaces;

import org.bukkit.plugin.Plugin;

public interface IPluginObjective extends IObjective<IAutoUpdateEntry> {

	/**
	 * @return The plugin used to register each entry as event listener.
	 */
	Plugin getPlugin();
}
