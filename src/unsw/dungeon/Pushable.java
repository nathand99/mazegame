package unsw.dungeon;

public class Pushable implements Movement{

	@Override
	public boolean canMove(Entity entity) {
		if (entity instanceof Player) {
			return true;
		}
		return false;
	}

}
