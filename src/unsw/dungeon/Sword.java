package unsw.dungeon;


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
			// drop sword where the player is with the ID of the sword the player had
			return new Sword(dungeon, p.getX(), p.getY(), temp.swordID, new Collectable());			
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
