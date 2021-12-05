package fr.pederobien.minecraft.scoreboards.interfaces;

import java.util.function.Predicate;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;

public interface IEventEntryUpdater<T extends Event> extends IEntryUpdater, Listener {

	/**
	 * @return The event used to update the source entry.
	 */
	T getEvent();

	/**
	 * Append some conditions before updating the source entry. During the update, all conditions are checked. If one of them return
	 * false, the update is called.
	 * 
	 * @param predicate The predicate used to add a condition before updating the entry source.
	 * @return This event updater.
	 */
	IEventEntryUpdater<T> condition(Predicate<T> predicate);
}
