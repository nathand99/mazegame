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
    private Sword sword = null;
    private Key key = null;
    private int treasure = 0; 
    //game starts on normal state being true 
    //when normal state is true enemy can kill player
    //when normal state is false enemy can't kill player
    private boolean normalState = true;
    private int numThreads = 0;

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
        dungeon.alert(); // remove in final iteration
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
        dungeon.alert(); // remove in final iteration
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
        dungeon.alert(); // remove in final iteration
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
        dungeon.alert(); // remove in final iteration
    }
    
    public void pickup() {
    	List<Entity> entities = dungeon.getCurrentEntity(getX(), getY());
    	Pickup_item item = null;
    	// check if there is a Pickup_item on players location
    	for (Entity e : entities) {
    		if (e instanceof Pickup_item) {
    			item = (Pickup_item) e;
    			break;
    		}
    	}
    	// there is a Pickup_item on the players location
    	if (item != null) {
    		Entity dropped = null;
			dropped = item.pickup(this, dungeon);
			// if player drops a Pickup_item, add it to the dungeon
			if (dropped != null) {
				dungeon.addEntity(dropped);
			}
    	}
    }

	public boolean isNormalState() {
		return normalState;
	}

	public void setNormalState(boolean normalState) {
		this.normalState = normalState;
	}

	public int getNumThreads() {
		return numThreads;
	}

	public void setNumThreads(int numThreads) {
		this.numThreads = numThreads;
	}


	public Sword getSword() {
		return sword;
	}

	public void setSword(Sword sword) {
		this.sword = sword;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public int getTreasure() {
		return treasure;
	}

	public void setTreasure(int treasure) {
		this.treasure = treasure;
	}
	
	public void addTreasure() {
    	this.setTreasure(this.getTreasure() + 1);
    }
	 
}
