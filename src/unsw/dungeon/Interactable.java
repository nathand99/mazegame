package unsw.dungeon;

import unsw.dungeon.Entities.*;

/**
 * Interactable - has some interactions with other entities.
 * Interaction depends heavily on both entities.
 *
 */
public class Interactable implements Movement{

	@Override
	public boolean canMove(Entity movingEntity, Entity stationaryEntity, String direction) {
		if (movingEntity instanceof Player) {
			// should only be sometimes true.
			if (stationaryEntity instanceof Boulder) {
				return successPush((Boulder) stationaryEntity, direction);
			}
			else if (stationaryEntity instanceof Portal) {
				return teleport((Player) movingEntity,(Portal) stationaryEntity);
			}
			else if (stationaryEntity instanceof Door) {
				return openDoor((Player) movingEntity,(Door) stationaryEntity, direction);
			}
			else if (stationaryEntity instanceof Fire) {
				((Player) movingEntity).die();
				return true;
			}
			return true; // for exit, always true. However, does not always win.
		} else if (movingEntity instanceof Enemy) {
			if (stationaryEntity instanceof Exit) {
				return true;
			} else if (stationaryEntity instanceof Portal) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param boulder
	 * @param direction
	 * @return true if boulder can successfully be pushed, false if not
	 */
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

	public boolean teleport(Player player, Portal portal) {
		return portal.bestTele(player);
	}
	
	public boolean openDoor(Player player, Door door, String direction) {
		return door.openDoor(player);
	}
}
