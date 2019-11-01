package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Treasure implements Pickup_item {
	
	private IntegerProperty x, y;
	int treasureID;

    /**
     * Create a treasure positioned in square (x,y)
     * @param x
     * @param y
     * @param treasureID - ID of treasure
     */
    public Treasure(int x, int y, int treasureID) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.treasureID = treasureID;
    }
    
	@Override
	public Pickup_item pickup(Player p, Dungeon d) {	
		// add treasure to players treasure inventory
		p.treasure.add(this);
		// remove picked up treasure from the dungeon
		d.removePickup_item(this);
		// you can only pick up treasure - cannot drop it, so return null
		return null;
	}
	
	

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

}
