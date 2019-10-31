package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends Entity{
	
	private Dungeon dungeon;
	private String state = "Aggressive"; // 2 states: aggressive and fearful.
	
	
	/**
     * Create an enemy positioned in square (x,y)
     * @param x
     * @param y
	 * @throws InterruptedException 
     */
	public Enemy(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		//approach();
		
	}
	
	// while the game goes on, check for player state, and if not invincible run aggressive code.
	// if invincible, run passive code.
	
	public void approach() {
		// should loop every once in a while.
		Player player = dungeon.getPlayer();
		int[] playerXY = player.getXY();
		int[] currentXY = this.getXY();
		if (kill(playerXY)) {
			return;
		}
		
		AStarSearch aStar = new AStarSearch(dungeon, playerXY, currentXY);
		List<String> bestPath = aStar.search();
		String firstMove = bestPath.get(0);
		enemyMove(firstMove);
		kill(playerXY);
	}
	
	public boolean kill(int[] playerXY) {
		if (playerXY[0] == this.getX() && playerXY[1] == this.getY()) {
			System.out.println("You were killed");
			return true;
		}
		return false;
	}
	
	public void enemyMove(String move) {
		
		switch (move) {
        case "UP":
        	y().set(getY() - 1);
            break;
        case "DOWN":
        	y().set(getY() + 1);
            break;
        case "LEFT":
        	x().set(getX() - 1);
            break;
        case "RIGHT":
        	x().set(getX() + 1);
            break;
        default:
            break;
        }
	}



}
