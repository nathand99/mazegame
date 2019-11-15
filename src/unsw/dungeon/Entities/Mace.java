package unsw.dungeon.Entities;

import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;

public class Mace extends Entity implements PickupItem, Weapons {
	

	private int longSwordID;
	private Dungeon dungeon;
	private int hitsLeft = 1;

	
    /**
     * Create a sword positioned in square (x,y)
     * @param dungeon
     * @param x
     * @param y
     * @param swordID - ID of sword
     */
    public Mace(Dungeon dungeon, int x, int y, Movement movement) {
		super(x, y, movement);
		this.dungeon = dungeon;
    }
    
	@Override
	public Entity pickup(Player p) {
		// if player has no sword, put this sword in inventory - return null
		if (p.getMace() == null) {
			p.setMace(this);
			this.getEntityView().setVisible(false);
			dungeon.removeEntity(this);
			return null;
		// if player has sword, swap sword - return players sword - to be placed on ground
		} else {			
			Mace temp = p.getMace();
			dungeon.removeEntity(this);
			p.setMace(this);
			// drop sword where the player is with the ID of the sword the player had
			return new Mace(dungeon, p.getX(), p.getY(), new Collectable());			
		}
	}
    
	
	@Override
	public void attackLeft(Player player) {
		MaceSwing(player);
	}
	@Override
	public void attackRight(Player player) {
		MaceSwing(player);
	}
	@Override
	public void attackAbove(Player player) {
		MaceSwing(player);
	}
	@Override
	public void attackBelow(Player player) {
		MaceSwing(player);
	}
	
	public void MaceSwing(Player player) {
		int now = (int) System.currentTimeMillis();
    	if (now - player.getLastWeaponSwing() < player.getMinClickDelay()) return;
		if (player.getMace() != null) {
			player.notifyEnemyWeapon(player.getX()-1, player.getY());
			player.notifyEnemyWeapon(player.getX()+1, player.getY());
			player.notifyEnemyWeapon(player.getX(), player.getY()-1);
			player.notifyEnemyWeapon(player.getX(), player.getY()+1);
			player.setLastWeaponSwing(now);
			useHit();
		}
	}
	/**
	 * -1 durability to sword.
	 */
	public void useHit() {
		dungeon.getPlayer().getMace().setHitsLeft(dungeon.getPlayer().getMace().getHitsLeft()-1);
		if (dungeon.getPlayer().getMace().getHitsLeft() == 0) {
			dungeon.getPlayer().setMace(null);
		}
	}
    public int getLongSwordID() {
        return this.longSwordID;
    }

	public int getHitsLeft() {
		return hitsLeft;
	}

	public void setHitsLeft(int hitsLeft) {
		this.hitsLeft = hitsLeft;
	}
}
