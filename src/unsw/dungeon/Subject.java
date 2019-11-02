package unsw.dungeon;

public interface Subject {
	public void registerObserver(EnemyObserver o);
	public void removeObserver(EnemyObserver o);
	public void notifyObservers();
}
