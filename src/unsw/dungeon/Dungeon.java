/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 * 
 * A dungeon can contain many Pickup_items, each occupy a square. Only one
 * pickup_item can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    public void alert() {
    	// test code, should not actually be in final iteration.
    	for (Entity e : entities) {
    		if (e instanceof Enemy) {
    			((Enemy) e).escape();
    		}
    	}
    }
    // from Alek's Code

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public List<Entity> getEntities () {
		return this.entities;
    }

    public List<Entity> getCurrentEntity (int x, int y) {
    	List<Entity> particular_entity = new ArrayList<Entity>();
    	
    	for (Entity curr_e: this.entities) {
    		if (curr_e == null) continue;
    		if ((curr_e.getX() == x) && (curr_e.getY() == y)) {
    			particular_entity.add(curr_e);
    		}
    	}
		return particular_entity;
    }

}
