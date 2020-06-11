package fr.pederobien.minecraftscoreboards.impl;

import org.bukkit.entity.Player;

import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class AbstractEntryWrapperUpdater<T extends IEntry, U extends EntryWrapper<T>> extends AbstractEntryUpdater<T> {
	private EntryWrapper<T> wrapper;

	protected AbstractEntryWrapperUpdater(ISimpleObjective objective, U wrapper) {
		super(objective, wrapper.getSource());
	}

	@Override
	public String getBefore(Player player) {
		return wrapper.getBefore(player);
	}

	@Override
	public String getAfter(Player player) {
		return wrapper.getAfter(player);
	}

	@Override
	protected T getSource() {
		return wrapper.getSource();
	}
}
