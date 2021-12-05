package fr.pederobien.minecraft.scoreboards.exceptions;

import fr.pederobien.minecraft.scoreboards.interfaces.IObjective;

public class ObjectiveNotAttachedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private IObjective objective;

	public ObjectiveNotAttachedException(IObjective objective) {
		super("The objective " + objective.getName() + " is not attached to a scoreboard");
	}

	/**
	 * @return The not registered objective.
	 */
	public IObjective getObjective() {
		return objective;
	}
}
