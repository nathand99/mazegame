package unsw.dungeon;

public class Collectable implements Movement {
	
	/**
	 * @return false if moving entity is a boulder, else true
	 */
	@Override
	public boolean canMove(Entity movingEntity, Entity stationaryEntity, String direction) {
		if (movingEntity instanceof Boulder) {
			return false;
		} 
		return true;
	}

}
