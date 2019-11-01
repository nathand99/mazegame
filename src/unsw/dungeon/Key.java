package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Key implements Pickup_item {
	
	private IntegerProperty x, y;
	int keyID;	
	
    /**
     * Create a keypositioned in square (x,y) with keyID
     * @param x
     * @param y
     * @param keyID - ID of key
     */
    public Key(int x, int y, int keyID) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.keyID = keyID;
    }
    
    /**
     * return null if no key is swapped to the ground
     * 
     * return key if player is swapping keys
     */
	@Override
	public Pickup_item pickup(Player p) {		
		// if player has no key, put this key in inventory - return null
		if (p.key == null) {
			p.key = this;
			return null;
		// if player has key, swap keys - place players key on ground
		} else {			
			Key temp = p.key;
			p.key = this;
			return new Key(temp.getX(), temp.getY(), temp.keyID);			
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
