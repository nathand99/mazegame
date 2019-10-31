package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Key implements Pickup_item {
	
	int keyID;
	
	private IntegerProperty x, y;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Key(int x, int y, int keyID) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.keyID = keyID;
    }
    
	@Override
	public Pickup_item pickup(Player p) {		
		// if player has no key, put this key in inventory
		if (p.key == null) {
			p.key = this;
		// if player has key, swap keys
		} else {			
			// create a new key on the ground that was in the players inventory
			//TODO
			Key temp = this;
			p.key = temp;
			return new Key(p.key.getX(), p.key.getY(), p.key.keyID);
			
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
