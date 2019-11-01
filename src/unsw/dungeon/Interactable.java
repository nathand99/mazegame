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
				return teleport((Player) movingEntity,(Portal) stationaryEntity);
			}
			else if (stationaryEntity instanceof Invincibility) {
				return goInvincible((Player) movingEntity,(Invincibility) stationaryEntity);
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

	public boolean teleport(Player player, Portal portal) {
		return portal.bestTele(player);
	}
	
	private boolean goInvincible(Player player, Invincibility invPotion) {
		return invPotion.invincible(player);
	}


}
