package unsw.dungeon;

// the strategy pattern.

public interface Movement {
	// "entity" is the entity passed into it (eg. if a 
	// player moves in, then the Entity is a instanceof
	// player. We need this because some things can be
	// exclusively interacted with the player.
	public boolean canMove(Entity entity);
}
