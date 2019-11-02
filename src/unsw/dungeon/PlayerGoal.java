package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class PlayerGoal {
	private Player player;
	private List<Goal> goals; // should do the tree leaf thing
	
	public PlayerGoal(Player player) {
		this.setPlayer(player);
		this.goals = new ArrayList<Goal>();
	}
	
	// it works like this: every MultipleGoal is an 'and', so every SingleGoal is an 'or'.
	public boolean checkCompletion() {
		for (Goal goal : goals) {
			if (goal.checkComplete() == true) {
				return true;
			}
		}
		return false;
	}
	
	// only needs to exit.
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
	
	public void addComplete(String obj) {
		for (Goal goal : goals) {
			goal.addComplete(obj);
		}
	}
	
	public void removeComplete(String obj) {
		for (Goal goal : goals) {
			goal.removeComplete(obj);
		}
	}
	
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
