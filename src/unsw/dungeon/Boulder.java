package unsw.dungeon;

import java.util.List;

public class Boulder extends Entity{
	
	private Dungeon dungeon;
	
	public Boulder(Dungeon dungeon, int x, int y, Movement movement) {
		super(x, y, movement);
		this.dungeon = dungeon;
	}
	
	public void pushUp() {
    	List<Entity> moveTo = dungeon.getCurrentEntity(this.getX(), this.getY() - 1);
        if (getY() > 0 && moveTo.size() == 0) {
            y().set(getY() - 1);  
        } else if (moveTo.size() != 0) {
        	for (Entity entity : moveTo) {
        		//System.out.println(entity.checkMovement(this));
        		if (entity.checkMovement(this) == false) {
        			return;
        		}
        	}
        	y().set(getY() - 1);  
        }
    }

    public void pushDown() {
    	List<Entity> moveTo = dungeon.getCurrentEntity(this.getX(), this.getY() + 1);
        if (getY() < dungeon.getHeight() - 1 && moveTo.size() == 0) {
            y().set(getY() + 1);
        } else if (moveTo.size() != 0) {
        	for (Entity entity : moveTo) {
        		if (entity.checkMovement(this) == false) {
        			return;
        		}
        	}
        	y().set(getY() + 1);  
        } 
    }

    public void pushLeft() {
    	List<Entity> moveTo = dungeon.getCurrentEntity(this.getX() - 1, this.getY());
        if (getX() > 0 && moveTo.size() == 0) {
            x().set(getX() - 1);
        } else if (moveTo.size() != 0) {
        	for (Entity entity : moveTo) {
        		if (entity.checkMovement(this) == false) {
        			return;
        		}
        	}
        	x().set(getX() - 1);  
        } 
    }

    public void pushRight() {
    	List<Entity> moveTo = dungeon.getCurrentEntity(this.getX() + 1, this.getY());
        if (getX() < dungeon.getWidth() - 1 && moveTo.size() == 0) {
            x().set(getX() + 1);
        } else if (moveTo.size() != 0) {
        	for (Entity entity : moveTo) {
        		if (entity.checkMovement(this) == false) {
        			return;
        		}
        	}
        	x().set(getX() + 1);  
        } 
    }
	
	
}
