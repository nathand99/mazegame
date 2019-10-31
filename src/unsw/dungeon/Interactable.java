package unsw.dungeon;

public class Interactable implements Movement{

	@Override
	public boolean canMove(Entity movingEntity, Entity stationaryEntity, String direction) {
		if (movingEntity instanceof Player) {
			// should only be sometimes true.
			if (stationaryEntity instanceof Boulder) {
				return successPush((Boulder) stationaryEntity, direction);
			}
			return true; // not always true for exit, must remember when implementing goals
		}
		return false;
	}
	
	public boolean successPush(Boulder boulder, String direction) {
		switch(direction) {
		case "UP":
			return boulder.pushUp();
		case "DOWN":
			return boulder.pushDown();
		case "LEFT":
			return boulder.pushLeft();
		case "RIGHT":
			return boulder.pushRight();
		}
		return false;
	}

}
