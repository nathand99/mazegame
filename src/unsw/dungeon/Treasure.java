package unsw.dungeon;

public class Treasure implements Pickup_item {

	@Override
	public void pickup(Player p) {
		// remove treasure from ground
		
		// add treasure to players treasure inventory
		p.treasure.add(this);
		
	}

}
