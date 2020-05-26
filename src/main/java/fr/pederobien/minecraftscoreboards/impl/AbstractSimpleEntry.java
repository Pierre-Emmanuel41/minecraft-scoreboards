package fr.pederobien.minecraftscoreboards.impl;

public abstract class AbstractSimpleEntry extends AbstractEntry {

	/**
	 * Create an entry.
	 * 
	 * @param score  The line number of this entry in the player objective.
	 * @param before The sequence of characters to be displayed before the value to update.
	 * @param after  The sequence of characters to be displayed after the value to update.
	 */
	protected AbstractSimpleEntry(int score, String before, String after) {
		super(score, before, after);
	}

	@Override
	public void initialize() {
	}
}
