package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Treasure extends Entity implements Pickup_item {
	
	private IntegerProperty x, y;
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
    	super (x, y, movement);
    	this.dungeon = dungeon;
        this.treasureID = treasureID;
    }
    
	@Override
	public Entity pickup(Player p, Dungeon d) {	
		// increment players treasure counter
		p.addTreasure();
		// remove picked up treasure from the dungeon
		d.removeEntity(this);
		// you can only pick up treasure - cannot drop it, so return null
		return null;
	}
    
    public int gettreasureID() {
        return treasureID;
    }
    
    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

}
