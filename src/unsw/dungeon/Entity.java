package unsw.dungeon;

//import java.util.ArrayList;
//import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {
	
    // IntegerProperty is used so that changes to the entities position can be
    // externally observed
    private IntegerProperty x, y;
    private Movement movement;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y, Movement movement) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.movement = movement;
    }
    // note to self: make keys, etc an entity.

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    
    public int[] getXY() {
    	// returns both the X and Y co-ord in a nice 2 block int array.
    	int xy[] = new int[2];
    	xy[0] = x().get();
    	xy[1] = y().get();
    	return xy;
    }
    
    /**
     * canMove - checks if an entity can move to another square.
     * @param movingEntity
     * @param stationaryEntity
     * @param direction
     * @return
     */
	public boolean canMove(Entity movingEntity, Entity stationaryEntity, String direction) {
		return movement.canMove(movingEntity, stationaryEntity, direction);
	}
    
}
