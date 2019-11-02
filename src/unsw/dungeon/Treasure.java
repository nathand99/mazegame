package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Treasure extends Entity implements PickupItem {
	
	private IntegerProperty x, y;
	Dungeon dungeon;

    /**
     * Create a treasure positioned in square (x,y)
     * @param dungeon
     * @param x
     * @param y
     */
    public Treasure(Dungeon dungeon, int x, int y, Movement movement) {
    	super (x, y, movement);
    	this.dungeon = dungeon;
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

}
