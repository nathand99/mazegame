package unsw.dungeon;

public class Collectable implements Movement{

	@Override
	public boolean canMove(Entity movingEntity, Entity stationaryEntity, String direction) {
		if (movingEntity instanceof Boulder) {
			return false;
		}
		return true;
	}

}
