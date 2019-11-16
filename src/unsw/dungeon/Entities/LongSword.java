package unsw.dungeon.Entities;

import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;

public class LongSword extends Entity implements PickupItem, Weapons {
	

	private int longSwordID;
	private Dungeon dungeon;
	private int hitsLeft = 3;

	
    /**
     * Create a sword positioned in square (x,y)
     * @param dungeon
     * @param x
     * @param y
     * @param swordID - ID of sword
     */
    public LongSword(Dungeon dungeon, int x, int y, Movement movement) {
		super(x, y, movement);
		this.dungeon = dungeon;
    }
    
	@Override
	public Entity pickup(Player p) {
		// if player has no sword, put this sword in inventory - return null
		if (p.getWeapon() == null) {
			p.setWeapon(this);
			this.getEntityView().setVisible(false);
			dungeon.removeEntity(this);
			return null;
		// if player has sword, swap sword - return players sword - to be placed on ground
		} else {			
			dungeon.removeEntity(this);
			p.setWeapon(this);
			// drop sword where the player is with the ID of the sword the player had
			return new LongSword(dungeon, p.getX(), p.getY(), new Collectable());			
		}
	}
    
	
	@Override
	public void attackLeft(Player player) {
		int now = (int) System.currentTimeMillis();
    	if (now - player.getLastWeaponSwing() < player.getMinClickDelay()) return;
		if (player.getWeapon() != null) {
			player.notifyEnemyWeapon(player.getX()-1, player.getY());
			player.notifyEnemyWeapon(player.getX()-2, player.getY());
			player.setLastWeaponSwing(now);
			useHit();
		}
	}
	@Override
	public void attackRight(Player player) {
		int now = (int) System.currentTimeMillis();
    	if (now - player.getLastWeaponSwing() < player.getMinClickDelay()) return;
		if (player.getWeapon() != null) {
			player.notifyEnemyWeapon(player.getX()+1, player.getY());
			player.notifyEnemyWeapon(player.getX()+2, player.getY());
			player.setLastWeaponSwing(now);
			useHit();
		}
	}
	@Override
	public void attackAbove(Player player) {
		int now = (int) System.currentTimeMillis();
    	if (now - player.getLastWeaponSwing() < player.getMinClickDelay()) return;
		if (player.getWeapon() != null) {
			player.notifyEnemyWeapon(player.getX(), player.getY()-1);
			player.notifyEnemyWeapon(player.getX(), player.getY()-2);
			player.setLastWeaponSwing(now);
			useHit();
		}
	}
	@Override
	public void attackBelow(Player player) {
		int now = (int) System.currentTimeMillis();
    	if (now - player.getLastWeaponSwing() < player.getMinClickDelay()) return;
		if (player.getWeapon() != null) {
			player.notifyEnemyWeapon(player.getX(), player.getY()+1);
			player.notifyEnemyWeapon(player.getX(), player.getY()+2);
			player.setLastWeaponSwing(now);
			useHit();
		}
	}
	
	/**
	 * -1 durability to sword.
	 */
	public void useHit() {
		dungeon.getPlayer().getWeapon().setHitsLeft(dungeon.getPlayer().getWeapon().getHitsLeft()-1);
		if (dungeon.getPlayer().getWeapon().getHitsLeft() == 0) {
			dungeon.getPlayer().setWeapon(null);
		}
	}
	@Override
    public int getWeaponID() {
        return this.longSwordID;
    }
    @Override
	public int getHitsLeft() {
		return hitsLeft;
	}
    @Override
	public void setHitsLeft(int hitsLeft) {
		this.hitsLeft = hitsLeft;
	}
}