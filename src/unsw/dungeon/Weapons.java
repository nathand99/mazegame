package unsw.dungeon;

import unsw.dungeon.Entities.*;

public interface Weapons {
	public void attackLeft(Player player);
	public void attackRight(Player player);
	public void attackAbove(Player player);
	public void attackBelow(Player player);
	
	public int getHitsLeft();
	public void setHitsLeft(int hitsLeft);
	public int getWeaponID();
}
