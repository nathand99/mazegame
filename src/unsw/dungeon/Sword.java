package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Sword extends Entity implements Pickup_item {
	
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
    public Sword(Dungeon dungeon, int x, int y, int swordID, Movement movement) {
		super(x, y, movement);
    	this.dungeon = dungeon;
        this.swordID = swordID;
    }
    
	@Override
	public Entity pickup(Player p, Dungeon d) {
		// if player has no sword, put this sword in inventory - return null
		if (p.getSword() == null) {
			p.setSword(this);
			d.removeEntity(this);
			return null;
		// if player has sword, swap sword - place players sword on ground
		} else {			
			Sword temp = p.getSword();
			d.removeEntity(this);
			p.setSword(this);
			// drop key where the player is with the ID of the key the player had
			return new Sword(dungeon, p.getX(), p.getY(), temp.swordID, new Collectable());			
		}
	}
    
    public int getswordID() {
        return swordID;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
}
