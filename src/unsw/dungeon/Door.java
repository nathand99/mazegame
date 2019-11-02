package unsw.dungeon;

public class Door extends Entity {
	
	Dungeon dungeon;
	private int doorID;
	
	public Door(Dungeon dungeon, int x, int y, int doorID, Movement movement) {
		super(x, y, movement);
		this.dungeon = dungeon;
		this.doorID = doorID;
	}
	
	/**
	 * if player has key with same ID as door, open door
	 */
	public boolean openDoor(Player player) {
		if (player.getKey() != null) {
			if (player.getKey().getkeyID() == doorID) {				
				// remove the locked door
				dungeon.removeEntity(this);
				// add in an unlocked door where the locked one was
				Door new_door = new Door(this.dungeon, this.getX(), this.getY(), this.getdoorID(), new Moveable());
				dungeon.addEntity(new_door);
				// remove key from players inventory
				player.setKey(null);
				return true;
			}
		}
		return false;
	}

	public int getdoorID() {
        return doorID;
    }
}
