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
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private List<Entity> initialEntities;
    private Player player;
    private Player initialPlayer;

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
    
    /*public void copyInitialEntities() {
    	this.initialEntities = new ArrayList<Entity>(entities);
    }
    
    public void copyInitialPlayer() {
    	this.initialPlayer = this.player;
    }
    
    public void loadInitial() {
    	this.entities = new ArrayList<Entity>(initialEntities);
    	this.player = this.initialPlayer;
    }*/

}
