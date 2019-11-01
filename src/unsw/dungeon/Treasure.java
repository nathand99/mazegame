package unsw.dungeon;

public class Treasure extends Entity implements Pickup_item {
	
	int treasureID;
	Dungeon dungeon;

    /**
     * Create a treasure positioned in square (x,y)
     * @param dungeon
     * @param x
     * @param y
     * @param treasureID - ID of treasure
     */
    public Treasure(Dungeon dungeon, int x, int y, int treasureID, Movement movement) {
    	super(x, y, movement);
    	this.dungeon = dungeon;
        this.treasureID = treasureID;
    }
    
	@Override
	public Pickup_item pickup(Player p, Dungeon d) {	
		// add treasure to players treasure inventory
		//p.treasure.add(this);
		p.addTreasure(this);
		// remove picked up treasure from the dungeon
		d.removePickup_item(this); // this is just for testing 
		d.removeEntity(this); // this removes it from dungeon 
		// you can only pick up treasure - cannot drop it, so return null
		return null;
	}
	
    public int gettreasureID() {
        return treasureID;
    }

	public boolean pickUpTreasure(Player player) {
		pickup(player,dungeon);
		return true;
	}
}
