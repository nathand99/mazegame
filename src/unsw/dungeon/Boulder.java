package unsw.dungeon;

import java.util.List;

public class Boulder extends Entity{
	
	private Dungeon dungeon;
	
	public Boulder(Dungeon dungeon, int x, int y, Movement movement) {
		super(x, y, movement);
		this.dungeon = dungeon;
	}
	
	public boolean pushUp() {
    	List<Entity> moveTo = dungeon.getCurrentEntity(this.getX(), this.getY() - 1);
        if (getY() > 0 && moveTo.size() == 0) {
            y().set(getY() - 1);  
        } else if (moveTo.size() != 0) {
        	for (Entity entity : moveTo) {
        		//System.out.println(entity.checkMovement(this));
        		if (entity.canMove(this, entity, "UP") == false) {
        			return false;
        		}
        	}
        	y().set(getY() - 1);  
        }
        return true;
    }

    public boolean pushDown() {
    	List<Entity> moveTo = dungeon.getCurrentEntity(this.getX(), this.getY() + 1);
        if (getY() < dungeon.getHeight() - 1 && moveTo.size() == 0) {
            y().set(getY() + 1);
        } else if (moveTo.size() != 0) {
        	for (Entity entity : moveTo) {
        		if (entity.canMove(this, entity, "DOWN") == false) {
        			return false;
        		}
        	}
        	y().set(getY() + 1);  
        } 
        return true;
    }

    public boolean pushLeft() {
    	List<Entity> moveTo = dungeon.getCurrentEntity(this.getX() - 1, this.getY());
        if (getX() > 0 && moveTo.size() == 0) {
            x().set(getX() - 1);
        } else if (moveTo.size() != 0) {
        	for (Entity entity : moveTo) {
        		if (entity.canMove(this, entity, "LEFT") == false) {
        			return false;
        		}
        	}
        	x().set(getX() - 1);  
        } 
        return true;
    }

    public boolean pushRight() {
    	List<Entity> moveTo = dungeon.getCurrentEntity(this.getX() + 1, this.getY());
        if (getX() < dungeon.getWidth() - 1 && moveTo.size() == 0) {
            x().set(getX() + 1);
        } else if (moveTo.size() != 0) {
        	for (Entity entity : moveTo) {
        		if (entity.canMove(this, entity, "RIGHT") == false) {
        			return false;
        		}
        	}
        	x().set(getX() + 1);  
        } 
        return true;
    }
	
	
}
