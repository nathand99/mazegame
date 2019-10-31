package unsw.dungeon;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
	private boolean ableToMove = true;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    //check if there is an obstacle, if so player can't move
    public void moveUp() {
    	dungeon.checkForcedMove(getX(), getY()-1, "up");
        if (getY() > 0 && ableToMove == true)
            y().set(getY() - 1);
        ableToMove = true;
    }

    public void moveDown() {
    	dungeon.checkForcedMove(getX(), getY()+1, "down");
        if (getY() < (dungeon.getHeight() - 1) && ableToMove == true)
            y().set(getY() + 1);
        ableToMove = true;
    }

    public void moveLeft() {
    	dungeon.checkForcedMove(getX()-1, getY(), "left");
        if (getX() > 0 &&ableToMove == true)
            x().set(getX() - 1);
        ableToMove = true;
    }

    public void moveRight() {
    	dungeon.checkForcedMove(getX()+1, getY(), "right");
        if (getX() < (dungeon.getWidth() - 1) && ableToMove == true)
            x().set(getX() + 1);
        ableToMove = true;
    }

    //does entity allow us to move
    //e.g. wall has set to false, so we cant move
	public void setAbleToMove(boolean ableToMove) {
		// TODO Auto-generated method stub
		this.ableToMove  = ableToMove; 
	}
}
