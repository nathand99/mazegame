package unsw.dungeon;

public class Interactable implements Movement{

	@Override
	public boolean canMove(Entity movingEntity, Entity stationaryEntity, String direction) {
		if (movingEntity instanceof Player) {
			// should only be sometimes true.
			return true;
		}
		return false;
	}

}
