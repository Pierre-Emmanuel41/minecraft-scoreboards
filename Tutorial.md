# How to use this API

The first object you will need is an Objective. An objective is used to be attached to a minecraft scoreboard in order to be displayed on player screen. There are three different constructors of an objective :

```java
	Objective obj1 = new Objective(Plugin plugin, Player player, String name, String displayName, String criteria, DisplaySlot displaySlot);
	Objective obj2 = new Objective(Plugin plugin, Player player, String name, String displayName, DisplaySlot displaySlot); // default criteria value is "dummy"
	Objective obj3 = new Objective(Plugin plugin, Player player, String name, String displayName); // default display slot is SIDEBAR.
```

Please see the [Objective](https://github.com/Pierre-Emmanuel41/minecraft-scoreboards/blob/master/src/main/java/fr/pederobien/minecraftscoreboards/impl/Objective.java) class to learn more about parameters.

Once this is done, let's add some entries to our objective. Those entries are represented by the interface [IEntry](https://github.com/Pierre-Emmanuel41/minecraft-scoreboards/blob/master/src/main/java/fr/pederobien/minecraftscoreboards/interfaces/IEntry.java) whose implementation is the [AbstractEntry](https://github.com/Pierre-Emmanuel41/minecraft-scoreboards/blob/master/src/main/java/fr/pederobien/minecraftscoreboards/impl/AbstractEntry.java) class. There is only one implementation of this abstract entry : [MessageEntry](https://github.com/Pierre-Emmanuel41/minecraft-scoreboards/blob/master/src/main/java/fr/pederobien/minecraftscoreboards/impl/MessageEntry.java). This entry is a simple entry that display a message that will never changed.

However, it is also possible to create your own entry. To do so, you need to create a new class that extends AbstractEntry. Let's call it PlayerLocationEntry and let's say that it displays the current player location :

```java
	import org.bukkit.entity.Player;

	import fr.pederobien.minecraftscoreboards.impl.AbstractEntry;
	
	public class PlayerLocationEntry extends AbstractEntry {
	
		public PlayerLocationEntry(int score) {
			super(score);
		}
	
		@Override
		protected String updateCurrentValue(Player player) {
			Location loc = player.getLocation();
			return loc.getBlockX() + "/" + loc.getBlockY() + "/" + loc.getBlockZ();
		}
	}
```

Now that we have created the class, how to update the player location when it moves ? To update an entry, we need an [IEntryUpdater](https://github.com/Pierre-Emmanuel41/minecraft-scoreboards/blob/master/src/main/java/fr/pederobien/minecraftscoreboards/interfaces/IEntryUpdater.java) interface. There are few already implemented entry updater in the class [UpdatersFactory](https://github.com/Pierre-Emmanuel41/minecraft-scoreboards/blob/master/src/main/java/fr/pederobien/minecraftscoreboards/impl/updaters/UpdatersFactory.java). So, in order to update the entry when the player moves, we simply need to attach a [PlayerMoveUpdater](https://github.com/Pierre-Emmanuel41/minecraft-scoreboards/blob/master/src/main/java/fr/pederobien/minecraftscoreboards/impl/updaters/PlayerMoveUpdater.java). Here is how to attach an entry updater to an entry and add it to an Objective :

```java
	// plugin parameter should be replaced by a true plugin instance
	// player parameter should be replaced by a true player instance
	Objective obj = new Objective(plugin, player, "location", "Player location");
	
	IEntry entry = new PlayerLocationEntry(0);
	entry.addUpdater(UpdatersFactory.playerMove());
	obj.addEntry(entry);
```

Now that we are able to create an objective, to create an entry, to attach an updater to it and add the entry to the objective, that does not mean that the objective is automatically updated when the player moves. There is one step more. We need to register the objective in an [ObjectiveUpdater](https://github.com/Pierre-Emmanuel41/minecraft-scoreboards/blob/master/src/main/java/fr/pederobien/minecraftscoreboards/ObjectiveUpdater.java) class. This class is associated to a plugin and is usefull when several objectives need to be automatically updated and stopped at the same time.

```java
	// plugin parameter should be replaced by a true plugin instance
	// player parameter should be replaced by a true player instance
	Objective obj = new Objective(plugin, player, "location", "Player location");
	
	IEntry entry = new PlayerLocationEntry(0);
	entry.addUpdater(UpdatersFactory.playerMove());
	obj.addEntry(entry);
	
	ObjectiveUpdater.getInstance(plugin).register(obj).start();
```