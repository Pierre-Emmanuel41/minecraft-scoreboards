package fr.pederobien.minecraftscoreboards.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.bukkit.event.Event;

import fr.pederobien.minecraftscoreboards.interfaces.IEventEntryUpdater;

public class EventEntryUpdater<T extends Event> extends EntryUpdater implements IEventEntryUpdater<T> {
	private T event;
	private List<Predicate<T>> conditions;

	public EventEntryUpdater() {
		conditions = new ArrayList<Predicate<T>>();
	}

	@Override
	public void initialize() {
		getPlugin().getServer().getPluginManager().registerEvents(this, getPlugin());
	}

	@Override
	public T getEvent() {
		return event;
	}

	@Override
	public IEventEntryUpdater<T> condition(Predicate<T> predicate) {
		conditions.add(predicate);
		return this;
	}

	/**
	 * Common method to update this entry on player screen. First verify if this updater is activated then ask to the objective to
	 * update the entry source of this updater.
	 * 
	 * @param event The event used to throw an update.
	 */
	protected void update(T event) {
		this.event = event;
		if (getSource() != null && isActivated()) {
			if (conditions.isEmpty()) {
				scheduleUpdate(2);
				return;
			}

			boolean canUpdate = conditions.get(0).test(event);
			for (int i = 1; i < conditions.size(); i++) {
				if (!canUpdate)
					break;

				canUpdate &= conditions.get(i).test(event);
			}
			if (canUpdate)
				scheduleUpdate(2);
		}
	}
}
