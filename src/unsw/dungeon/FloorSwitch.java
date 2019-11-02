package unsw.dungeon;

import java.util.List;

public class FloorSwitch extends Entity implements GoalObserver {
	
	private Dungeon dungeon;
	private Player player;
	private boolean on;  // doesn't do anything yet.
	
	public FloorSwitch(Dungeon dungeon, int x, int y, Movement movement) {
		super(x, y, movement);
		this.dungeon = dungeon;
		this.player = dungeon.getPlayer();
		this.on = checkOnOff();
		player.registerObserver(this);
		startingGoal(player.getGoals());
	}
	
	private void startingGoal(PlayerGoal goals) { 
		// only run at the start, if the switch starts on, then it reduces goal by 1.
		if (this.on == true) {
			goals.addComplete("switch");
		}
	}

	public boolean checkOnOff() {  
		List<Entity> entities = dungeon.getCurrentEntity(this.getX(), this.getY());
		for (Entity entity : entities) {
			if (entity instanceof Boulder) {
				System.out.println("There is a boulder now");
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void update(PlayerGoal goals, int[] playerXY) {
		boolean prevOn = this.on;
		this.on = checkOnOff();
		if (prevOn != on && on == true) {
			goals.addComplete("switch");
		} else if (prevOn != on && on == false) {
			goals.removeComplete("switch");
		}
		
	}

	
	
}
