package unsw.dungeon.Entities;

import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;

public class Sword extends Entity implements PickupItem {
	

	private int swordID;
	private Dungeon dungeon;
	private int hitsLeft = 5;

	
    /**
     * Create a sword positioned in square (x,y)
     * @param dungeon
     * @param x
     * @param y
     * @param swordID - ID of sword
     */
    public Sword(Dungeon dungeon, int x, int y, Movement movement) {
		super(x, y, movement);
    	this.dungeon = dungeon;
    }
    
	@Override
	public Entity pickup(Player p) {
		// if player has no sword, put this sword in inventory - return null
		if (p.getSword() == null) {
			p.setSword(this);
			this.getEntityView().setVisible(false);
			dungeon.removeEntity(this);
			return null;
		// if player has sword, swap sword - return players sword - to be placed on ground
		} else {			
			Sword temp = p.getSword();
			dungeon.removeEntity(this);
			p.setSword(this);
			// drop sword where the player is with the ID of the sword the player had
			return new Sword(dungeon, p.getX(), p.getY(), new Collectable());			
		}
	}
    
    public int getswordID() {
        return this.swordID;
    }

	public int getHitsLeft() {
		return hitsLeft;
	}

	public void setHitsLeft(int hitsLeft) {
		this.hitsLeft = hitsLeft;
	}
}
