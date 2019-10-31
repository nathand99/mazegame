package unsw.dungeon;

import java.util.List;

public class FloorSwitch extends Entity {
	
	private Dungeon dungeon;
	private boolean on;  // doesn't do anything yet.
	
	public FloorSwitch(Dungeon dungeon, int x, int y, Movement movement) {
		super(x, y, movement);
		this.dungeon = dungeon;
		this.on = checkOnOff();
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

	
	
}
