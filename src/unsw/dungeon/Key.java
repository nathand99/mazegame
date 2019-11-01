package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Key implements Pickup_item {
	
	private IntegerProperty x, y;
	int keyID;	
	Dungeon dungeon;
	
    /**
     * Create a keypositioned in square (x,y) with keyID
     * @param dungeon
     * @param x
     * @param y
     * @param keyID - ID of key
     */
    public Key(Dungeon dungeon, int x, int y, int keyID) {
    	this.dungeon = dungeon;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.keyID = keyID;
    }
    
    /**
     * return null if no key is swapped to the ground
     * 
     * return key if player is swapping keys
     * 
     * also removes key being picked up from the dungeon
     */
	@Override
	public Pickup_item pickup(Player p, Dungeon d) {		
		// if player has no key, put this key in inventory - return null
		if (p.key == null) {
			p.key = this;
			d.removePickup_item(this);
			return null;
		// if player has key, swap keys - place players key on ground
		} else {			
			Key temp = p.key;
			d.removePickup_item(this);
			p.key = this;
			// drop key where the player is with the ID of the key the player had
			return new Key(dungeon, p.getX(), p.getY(), temp.keyID);			
		}
	}	

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }
    
    public int getkeyID() {
        return keyID;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    


}
