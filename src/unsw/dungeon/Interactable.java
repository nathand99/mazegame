package unsw.dungeon;

public class Interactable implements Movement{

	@Override
	public boolean canMove(Entity entity) {
		if (entity instanceof Player) {
			// should only be sometimes true.
			return true;
		}
		return false;
	}

}
