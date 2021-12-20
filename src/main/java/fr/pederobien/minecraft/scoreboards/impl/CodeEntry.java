package fr.pederobien.minecraft.scoreboards.impl;

import org.bukkit.entity.Player;

import fr.pederobien.minecraft.dictionary.impl.MinecraftDictionaryContext;
import fr.pederobien.minecraft.dictionary.impl.MinecraftMessageEvent;
import fr.pederobien.minecraft.dictionary.interfaces.IMinecraftCode;

public abstract class CodeEntry extends AbstractEntry {
	private String before, after;

	/**
	 * Constructs an entry that propose methods to display player language sensitive messages.
	 * 
	 * @param score The line number of this entry.
	 */
	protected CodeEntry(int score) {
		super(score);
	}

	/**
	 * If not overridden, it is possible to furnish a code used to display a language sensitive information using
	 * {@link #getBeforeAsCode(Player)}.
	 */
	@Override
	public String getBefore() {
		IMinecraftCode code = getBeforeAsCode(getPlayer());
		if (code == null)
			return "";
		if (before == null || !isBeforeConstant())
			before = MinecraftDictionaryContext.instance().getMessage(MinecraftMessageEvent.builder(getPlayer(), code).build());
		return before;
	}

	/**
	 * If not overridden, it is possible to furnish a code used to display a language sensitive information using
	 * {@link #getAfterAsCode(Player)}.
	 */
	@Override
	public String getAfter() {
		IMinecraftCode code = getAfterAsCode(getPlayer());
		if (code == null)
			return "";
		if (after == null || !isAfterConstant())
			after = MinecraftDictionaryContext.instance().getMessage(MinecraftMessageEvent.builder(getPlayer(), code).build());
		return after;
	}

	/**
	 * Because method {@link #getBefore(Player)} is overridden by this class to propose some language sensitive entry, this method
	 * allow you to give a code corresponding to the translated message to display on player screen. The message associated to the
	 * returned code is displayed before the value to update. If not overridden, This method returns null. This means that the
	 * sequence of character displayed before the value to update is "";
	 * 
	 * @param player The player whose score board is updated.
	 * @return A minecraft message code used to display messages in player language.
	 * 
	 * @see #isAfterConstant() To precise if the message should not be updated at each entry update.
	 */
	protected IMinecraftCode getBeforeAsCode(Player player) {
		return null;
	}

	/**
	 * Because method {@link #getAfter(Player)} is overridden by this class to propose some language sensitive entry, this method
	 * allow you to give a code corresponding to the translated message to display on player screen. The message associated to the
	 * returned code is displayed after the value to update. If not overridden, This method returns null. This means that the sequence
	 * of character displayed after the value to update is "";
	 * 
	 * @param player The player whose score board is updated.
	 * @return A minecraft message code used to display messages in player language.
	 * 
	 * @see #isAfterConstant() To precise if the message should not be updated at each entry update.
	 */
	protected IMinecraftCode getAfterAsCode(Player player) {
		return null;
	}

	/**
	 * Method used to know if when this entry is updated, the message associated to the code returned by method
	 * {@link #getBeforeAsCode(Player)} should be also updated. In other words, if this method returns true, then this entry search in
	 * registered dictionaries the message associated to the code.
	 * 
	 * @return True if this entry search in registered dictionaries when update, false otherwise.
	 */
	protected boolean isBeforeConstant() {
		return true;
	}

	/**
	 * Method used to know if when this entry is updated, the message associated to the code returned by method
	 * {@link #getAfterAsCode(Player)} should be also updated. In other words, if this method returns true, then this entry search in
	 * registered dictionaries the message associated to the code.
	 * 
	 * @return True if this entry search in registered dictionaries when update, false otherwise.
	 */
	protected boolean isAfterConstant() {
		return true;
	}
}
