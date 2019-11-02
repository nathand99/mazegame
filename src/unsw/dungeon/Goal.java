package unsw.dungeon;

/*
 * Actual goals
 */
public interface Goal {
	public boolean checkComplete();
	public boolean onlyExit();
	public void addComplete(String obj);
	public void removeComplete(String obj);
}
