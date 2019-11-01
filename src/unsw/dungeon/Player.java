package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    
    // inventory
    Sword sword = null;
    Key key = null;
    ArrayList<Treasure> treasure = new ArrayList<Treasure>(); 

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y, Movement movement) {
        super(x, y, movement);
        this.dungeon = dungeon;
    }

    public void moveUp() {
    	List<Entity> moveTo = dungeon.getCurrentEntity(this.getX(), this.getY() - 1);
        if (getY() > 0 && moveTo.size() == 0) {
            y().set(getY() - 1);  
        } else if (moveTo.size() != 0) {
        	for (Entity entity : moveTo) {
        		//System.out.println(entity.checkMovement(this));
        		if (entity.canMove(this, entity, "UP") == false) {
        			return;
        		}
        	}
        	y().set(getY() - 1);  
        }
    }

    public void moveDown() {
    	List<Entity> moveTo = dungeon.getCurrentEntity(this.getX(), this.getY() + 1);
        if (getY() < dungeon.getHeight() - 1 && moveTo.size() == 0) {
            y().set(getY() + 1);
        } else if (moveTo.size() != 0) {
        	for (Entity entity : moveTo) {
        		if (entity.canMove(this, entity, "DOWN") == false) {
        			return;
        		}
        	}
        	y().set(getY() + 1);  
        } 
    }

    public void moveLeft() {
    	List<Entity> moveTo = dungeon.getCurrentEntity(this.getX() - 1, this.getY());
        if (getX() > 0 && moveTo.size() == 0) {
            x().set(getX() - 1);
        } else if (moveTo.size() != 0) {
        	for (Entity entity : moveTo) {
        		if (entity.canMove(this, entity, "LEFT") == false) {
        			return;
        		}
        	}
        	x().set(getX() - 1);  
        } 
    }

    public void moveRight() {
    	List<Entity> moveTo = dungeon.getCurrentEntity(this.getX() + 1, this.getY());
        if (getX() < dungeon.getWidth() - 1 && moveTo.size() == 0) {
            x().set(getX() + 1);
        } else if (moveTo.size() != 0) {
        	for (Entity entity : moveTo) {
        		if (entity.canMove(this, entity, "RIGHT") == false) {
        			return;
        		}
        	}
        	x().set(getX() + 1);  
        } 
    }
    
    public void pickup() {
    	Pickup_item item = dungeon.getCurrentPickup_item(getX(), getY());
    	// if there is a Pickup_item on the players location
    	if (item != null) {
    		Pickup_item dropped = null;
			dropped = item.pickup(this, dungeon);
			// if player drops a Pickup_item, add it to the dungeon
			if (dropped != null) {
				dungeon.addPickup_item(dropped);
			}
    	}
    }
    
}
