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
		System.out.println("teleporting");
		
		//get information of other portal with same id 
		Portal otherPortal = portal.getRelatedPortal();
		
		System.out.println(otherPortal.getX());
		System.out.println(otherPortal.getY());
		System.out.println(direction);
		
		//this makes it look fine but is wrong 
		/*
		switch(direction) {
		case "UP":
			player.x().set(otherPortal.getX());
			player.y().set(otherPortal.getY()+1);
			break;
		case "DOWN":
			player.x().set(otherPortal.getX());
			player.y().set(otherPortal.getY()-1);
			break;
		case "LEFT":
			player.x().set(otherPortal.getX()+1);
			player.y().set(otherPortal.getY());
			break;
		case "RIGHT":
			player.x().set(otherPortal.getX()-1);
			player.y().set(otherPortal.getY());
			break;
		}
		*/
		
		//correct values but weird visual output 
		player.x().set(otherPortal.getX());
		player.y().set(otherPortal.getY());
		
		//values of player have changed 
		System.out.println(player.x());
		System.out.println(player.y());
		System.out.println(player.getX());
		System.out.println(player.getY());
		
		//return portal.jumpToNewPosition();//portal.jumpToNewPosition(player.getX(), player.getY(), portal.getId());
		return true;
	}


}
