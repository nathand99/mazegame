package unsw.dungeon;

public interface EnemyObserver {
	public void register();
	public void registerNoMove();
	public void update(int[] playerXY, PlayerGoal goals); // TODO: add what is passed.
	public void die(int x, int y, PlayerGoal goals);
}
