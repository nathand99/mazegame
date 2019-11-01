package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Sword implements Pickup_item {
	
	private IntegerProperty x, y;
	int swordID;
	
    /**
     * Create a sword positioned in square (x,y)
     * @param x
     * @param y
     * @param swordID - ID of sword
     */
    public Sword(int x, int y, int swordID) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.swordID = swordID;
    }
    
	@Override
	public Pickup_item pickup(Player p) {
		// if player has no sword, put this sword in inventory - return null
		if (p.sword == null) {
			p.sword = this;
			return null;
		// if player has sword, swap sword - place players sword on ground
		} else {			
			Sword temp = p.sword;
			p.sword = this;
			return new Sword(temp.getX(), temp.getY(), temp.swordID);			
		}
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
