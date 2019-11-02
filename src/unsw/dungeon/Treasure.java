package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Treasure extends Entity implements PickupItem, GoalObserver {
	
	Dungeon dungeon;
	Player player;

    /**
     * Create a treasure positioned in square (x,y)
     * @param dungeon
     * @param x
     * @param y
     */
    public Treasure(Dungeon dungeon, int x, int y, Movement movement) {
    	super (x, y, movement);
    	this.dungeon = dungeon;
    	this.player = null;
    	
    }
    
    @Override
    public void register() {
    	this.player = dungeon.getPlayer();
    	player.registerObserver(this);
    }
    
	@Override
	public Entity pickup(Player p, Dungeon d) {	
		// increment players treasure counter
		p.addTreasure();
		// remove picked up treasure from the dungeon
		d.removeEntity(this);
		// you can only pick up treasure - cannot drop it, so return null
		return null;
	}

	@Override
	public void update(PlayerGoal goals, int[] playerXY) {
		// TODO Auto-generated method stub
		if (this.getY() == playerXY[1] && this.getX() == playerXY[0]) {
			goals.addComplete("treasure");
			player.removeObserver(this);
		}
	}

}
