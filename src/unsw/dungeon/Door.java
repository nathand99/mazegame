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
		if (player.key.getkeyID() == doorID) {
			return true;
		} else {
			return false;
		}
	}

	public int getdoorID() {
        return doorID;
    }
}
