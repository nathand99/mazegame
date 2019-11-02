package unsw.dungeon;

/*
 * GoalObserver should:
 * observe when a player moves (or tries to).
 * observe when a player attacks (for enemy kills).
 * 
 */
public interface GoalObserver {
	public void update(PlayerGoal goals, int[] playerXY); // TODO: add what is passed.
}
