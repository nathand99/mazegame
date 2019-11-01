package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Sword implements Pickup_item {
	
	private IntegerProperty x, y;
	int swordID;
	Dungeon dungeon;
	
    /**
     * Create a sword positioned in square (x,y)
     * @param dungeon
     * @param x
     * @param y
     * @param swordID - ID of sword
     */
    public Sword(Dungeon dungeon, int x, int y, int swordID) {
		this.dungeon = dungeon;
		this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.swordID = swordID;
    }
    
	@Override
	public Pickup_item pickup(Player p, Dungeon d) {
		// if player has no sword, put this sword in inventory - return null
		if (p.sword == null) {
			p.sword = this;
			d.removePickup_item(this);
			return null;
		// if player has sword, swap sword - place players sword on ground
		} else {			
			Sword temp = p.sword;
			d.removePickup_item(this);
			p.sword = this;
			return new Sword(dungeon, temp.getX(), temp.getY(), temp.swordID);			
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
