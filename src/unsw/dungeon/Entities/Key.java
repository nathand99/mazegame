package unsw.dungeon.Entities;

import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;

public class Key extends Entity implements PickupItem {
	
	private int keyID;	
	private Dungeon dungeon;
	private boolean show = true;
	
    /**
     * Create a keypositioned in square (x,y) with keyID
     * @param dungeon
     * @param x
     * @param y
     * @param keyID - ID of key
     */
    public Key(Dungeon dungeon, int x, int y, int keyID, Movement movement) {
    	super(x, y, movement);
    	this.dungeon = dungeon;
        this.keyID = keyID;
        this.show = true;
    }
    
    /**
     * pickup - picks the key up.
     * 
     * @return null if no key is swapped to the ground
     * 
     * @return key if player is swapping keys
     * 
     * also removes key being picked up from the dungeon
     */
	@Override
	public Entity pickup(Player p) {		
		// if player has no key, put this key in inventory - return null
		if (p.getKey() == null) {
			p.setKey(this);
			p.getKey().setShow(false);
			dungeon.removeEntity(this);
			return null;
		// if player has key, swap keys - return players key - to be placed on ground
		} else {			
			Key temp = p.getKey();
			dungeon.removeEntity(this);
			p.setKey(this);
			// drop key where the player is, with the ID of the key the player had
			return new Key(dungeon, p.getX(), p.getY(), temp.keyID, new Collectable());			
		}
	}	
    
    public int getkeyID() {
        return keyID;
    }

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

}
