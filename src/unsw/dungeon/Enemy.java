package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Enemy extends Entity implements EnemyObserver, GoalObserver {
	
	private Dungeon dungeon;
	private Player player;
	
	/**
     * Create an enemy positioned in square (x,y)
     * @param x
     * @param y
	 * @throws InterruptedException 
     */
	public Enemy(Dungeon dungeon, int x, int y, Movement movement) {
		super(x, y, movement);
		this.dungeon = dungeon;
		this.player = dungeon.getPlayer();
		player.registerObserver((EnemyObserver) this); // when enemy dies, remove
		player.registerObserver((GoalObserver) this); // when enemy dies, must remove the observer from.
		startMove();
	}
	
	private void startMove() {
		// TODO Auto-generated method stub
		Timer t = new Timer();
		t.schedule(new TimerTask() {

            @Override
            public void run() {
            	if (player == null) {
            		t.cancel();;
            	} else if (player.isNormalState()) {
        			approach();
        		} else {
        			escape();
        		}

            }
        }, 800, 800);
	}

	// while the game goes on, check for player state, and if not invincible run aggressive code.
	// if invincible, run passive code.
	
	public void approach() {
		// should loop every once in a while.
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
	
	public void escape() {
		Player player = dungeon.getPlayer();
		int[] playerXY = player.getXY();
		int distance = Math.abs(playerXY[0] - this.getX()) + Math.abs(playerXY[1] - this.getY());
		// can probs be its own func, currently just testing functionality.
		int[] enemyUp = this.getXY();
		enemyUp[1] = enemyUp[1] - 1;
		if(moveValid(distance, enemyUp, playerXY, "UP")) {
			return;
		}
		
		int[] enemyDown = this.getXY();
		enemyDown[1] = enemyDown[1] + 1;
		if(moveValid(distance, enemyDown, playerXY, "DOWN")) {
			return;
		}
		
		int[] enemyLeft = this.getXY();
		enemyLeft[0] = enemyLeft[0] - 1;
		if(moveValid(distance, enemyLeft, playerXY, "LEFT")) {
			return;
		}
		
		int[] enemyRight = this.getXY();
		enemyRight[0] = enemyRight[0] + 1;
		if(moveValid(distance, enemyRight, playerXY, "RIGHT")) {
			return;
		}
	}
	
	public boolean kill(int[] playerXY) {
		DungeonApplication dApp = new DungeonApplication();
		if (playerXY[0] == this.getX() && playerXY[1] == this.getY()) {
			System.out.println("You were killed");
			dungeon.removeEntity(player);
			dungeon.setPlayer(null);
			this.player = null;
			
				
			
		}
		return false;
	}
	
	public boolean isFurther(int distance, int[] enemyXY, int playerXY[]) {
		int newDist = Math.abs(playerXY[0] - enemyXY[0]) + Math.abs(playerXY[1] - enemyXY[1]);
		if (newDist > distance) {
			return true;
		}
		return false;
	}
	
	public boolean collision(int[] newXY) {
		List <Entity> entities = dungeon.getCurrentEntity(newXY[0], newXY[1]);
		for (Entity entity : entities) {
			if (!entity.canMove(this, entity, "")) {
				return false;
			}
		}
		return true;
	}
	
	public boolean moveValid(int distance, int[] newXY, int[] playerXY, String direction) {
		if (collision(newXY) && isFurther(distance, newXY, playerXY)) {
			enemyMove(direction);
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
	
	// from EnemyObserver
	@Override
	public void update(int[] playerXY) {
		// TODO Auto-generated method stub
		if (player.isNormalState()) {
			if (kill(playerXY)) {
				return;
			}
			//approach();
		} else {
			// if isKilled by player, run that.
			//escape();
		}
		
		
		
	}

	// from GoalObserver
	@Override
	public void update(PlayerGoal goals, int[] playerXY) {
		// TODO Auto-generated method stub
		if (this.getX() == playerXY[0] && this.getY() == playerXY[1]) {
			goals.addComplete("enemy");
			player.removeObserver((GoalObserver) this);
		}
	}



}
