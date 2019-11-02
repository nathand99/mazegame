package unsw.dungeon;

public class Interactable implements Movement{

	@Override
	public boolean canMove(Entity movingEntity, Entity stationaryEntity, String direction) {
		if (movingEntity instanceof Player) {
			// should only be sometimes true.
			if (stationaryEntity instanceof Boulder) {
				return successPush((Boulder) stationaryEntity, direction);
			}
			else if (stationaryEntity instanceof Portal) {
				return teleport((Player) movingEntity,(Portal) stationaryEntity, direction);
			}
			else if (stationaryEntity instanceof Door) {
				return openDoor((Player) movingEntity,(Door) stationaryEntity, direction);
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

	public boolean teleport(Player player, Portal portal, String direction) {
		//System.out.println("teleporting");
		return portal.bestTele(player);
	}
	
	public boolean openDoor(Player player, Door door, String direction) {
		//System.out.println("teleporting");
		return door.openDoor(player);
	}


}
