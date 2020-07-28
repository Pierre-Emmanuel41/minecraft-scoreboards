package fr.pederobien.minecraftscoreboards.impl;

import fr.pederobien.minecraftscoreboards.interfaces.IEntry;
import fr.pederobien.minecraftscoreboards.interfaces.IEntryUpdater;

public class EntryUpdater implements IEntryUpdater {
	private IEntry source;
	private boolean isActivated;

	@Override
	public IEntry getSource() {
		return source;
	}

	@Override
	public void setSource(IEntry source) {
		this.source = source;
	}

	@Override
	public void initialize() {
	}

	@Override
	public boolean isActivated() {
		return isActivated;
	}

	@Override
	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	protected void update() {

	}
}
