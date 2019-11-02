package unsw.dungeon;

public interface PickupItem {
	/**
	 * Pickup_item - an item that can be picked up by player (Key, Treasure, Sword)
	 * when a player is on a Pickup_item they will call pickup
	 * 
	 * @param p - player
	 * @param d - dungeon (honestly, can remove).
	 * 
	 * @return Pickup_item: This method returns a Pickup_item that is left on the ground from the players inventory
	 * if they swap their item in inventory with the item on the ground
	 * 
	 * returns null if no Pickup_item is swapped and put on the ground
	 */
	public Entity pickup(Player p, Dungeon d);
}
