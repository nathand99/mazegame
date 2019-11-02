package unsw.dungeon;

public interface EnemyObserver {
	public void register();
	public void update(int[] playerXY); // TODO: add what is passed.
}
