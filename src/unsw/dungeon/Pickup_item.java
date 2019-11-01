package unsw.dungeon;

public interface Pickup_item {
	/**
	 * when a player is on a Pickup_item (an item that can be pciekd up
	 * and wants to pick it up, they will call pickup
	 * 
	 * @param p - player
	 * 
	 * @return Pickup_item: This method returns a Pickup_item that is left on the ground from the players inventory
	 * if they swap their item in inventory with the item on the ground
	 * 
	 * returns null if no Pickup_item is swapped and put on the ground
	 */
	public Pickup_item pickup(Player p);
}
