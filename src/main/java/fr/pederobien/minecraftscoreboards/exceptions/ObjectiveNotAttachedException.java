package fr.pederobien.minecraftscoreboards.exceptions;

import fr.pederobien.minecraftscoreboards.interfaces.ISimpleObjective;

public class ObjectiveNotAttachedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private ISimpleObjective objective;

	public ObjectiveNotAttachedException(ISimpleObjective objective) {
		super("The objective " + objective.getName() + " is not attached to a scoreboard");
	}

	/**
	 * @return The not registered objective.
	 */
	public ISimpleObjective getObjective() {
		return objective;
	}
}
