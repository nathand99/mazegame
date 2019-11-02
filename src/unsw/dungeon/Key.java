package unsw.dungeon;

public class Key extends Entity implements Pickup_item {
	
	private int keyID;	
	private Dungeon dungeon;
	
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
    }
    
    /**
     * return null if no key is swapped to the ground
     * 
     * return key if player is swapping keys
     * 
     * also removes key being picked up from the dungeon
     */
	@Override
	public Entity pickup(Player p, Dungeon d) {		
		// if player has no key, put this key in inventory - return null
		if (p.getKey() == null) {
			p.setKey(this);
			d.removeEntity(this);
			return null;
		// if player has key, swap keys - place players key on ground
		} else {			
			Key temp = p.getKey();
			d.removeEntity(this);
			p.setKey(this);
			// drop key where the player is with the ID of the key the player had
			return new Key(dungeon, p.getX(), p.getY(), temp.keyID, new Moveable());			
		}
	}	
    
    public int getkeyID() {
        return keyID;
    }

}
