package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * PlayerGoal - the goals of the player.
 * 
 *
 */
public class PlayerGoal {
	private Player player;
	private List<Goal> goals; // should do the tree leaf thing
	
	
	public PlayerGoal() {
		this.setPlayer(null);
		this.goals = new ArrayList<Goal>();
	}
	
	public PlayerGoal(Player player) {
		this.setPlayer(player);
		this.goals = new ArrayList<Goal>();
	}
	
	/**
	 * Checks if all goals complete.
	 * @return true or false.
	 */
	public boolean checkCompletion() {
		for (Goal goal : goals) {
			if (goal.checkComplete() == true) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * onlyExit - checks if the player only needs to exit to win.
	 * @return true or false.
	 */
	public boolean onlyExit() {
		for (Goal goal: goals) {
			if (goal.onlyExit() == true) {
				return true;
			}
		}
		return false;
	}
	
	public void addGoal(Goal goal) {
		goals.add(goal);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public List<Goal> getGoal() {
		return this.goals;
	}
	
	public void setGoal(List<Goal> goals) {
		this.goals = goals;
	}
	
	/**
	 * adds a complete goal
	 * @param obj - the goal that is complete
	 */
	public void addComplete(String obj) {
		for (Goal goal : goals) {
			goal.addComplete(obj);
		}
	}
	
	/**
	 * removes a complete goal, making it incomplete
	 * @param obj - the goal to be removed.
	 */
	public void removeComplete(String obj) {
		for (Goal goal : goals) {
			goal.removeComplete(obj);
		}
	}
	
	/**
	 * printing, for manual testing.
	 */
	public void printGoals() {
		for (int i = 0; i < goals.size(); i++) {
			Goal goal = goals.get(i);
			if (i == goals.size() - 1) {
				System.out.println(goal.toString());
				continue;
			}
			System.out.println(goal.toString());
			System.out.print("Or ");
		}
	}
}
