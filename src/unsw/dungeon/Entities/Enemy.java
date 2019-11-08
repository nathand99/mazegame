package unsw.dungeon.Entities;

import unsw.dungeon.*;
import unsw.dungeon.AStar_Algorithm.AStarSearch;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Application.DungeonApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Enemy extends Entity implements EnemyObserver {
	
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
		this.player = null;
		
		
	}
	
	@Override
	public void register() {
		this.player = dungeon.getPlayer();
		player.registerObserver((EnemyObserver) this); // when enemy dies, remove
		startMove();
	}
	
	// for testing purposes only.
	@Override
	public void registerNoMove() {
		this.player = dungeon.getPlayer();
		player.registerObserver((EnemyObserver) this); // when enemy dies, remove
	}
	
	/**
	 * startMove - starts the time for enemies to move.
	 */
	public void startMove() {
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
	
	/**
	 * singleMove - for testing, allows enemy to move a single square.
	 */
	public void singleMove() {
		// for testing, uses same logic as above.
		if (player == null) {
    		// do nothing.
    	} else if (player.isNormalState()) {
			approach();
		} else {
			escape();
		}
	}

	// while the game goes on, check for player state, and if not invincible run aggressive code.
	// if invincible, run passive code.
	
	/**
	 * approach - code to run A* to approach the player by the shortest path.
	 */
	public void approach() {
		// should loop every once in a while.
		int[] playerXY = player.getXY();
		int[] currentXY = this.getXY();
		
		AStarSearch aStar = new AStarSearch(dungeon, playerXY, currentXY);
		List<String> bestPath = aStar.search();
		String firstMove = bestPath.get(0);
		enemyMove(firstMove);
		kill(playerXY);
	}
	
	/**
	 * escape - code to move away from player in a very simplistic movement.
	 */
	public void escape() {
		Player player = dungeon.getPlayer();
		int[] playerXY = player.getXY();
		int distance = Math.abs(playerXY[0] - this.getX()) + Math.abs(playerXY[1] - this.getY());
		// can probs be its own func, currently just testing functionality.
		int[] enemyUp = this.getXY();
		enemyUp[1] = enemyUp[1] - 1;
		if (enemyUp[1] >= 0) {
			if(moveValid(distance, enemyUp, playerXY, "UP")) {
				return;
			}
		}
		
		int[] enemyDown = this.getXY();
		enemyDown[1] = enemyDown[1] + 1;
		if (enemyDown[1] <= dungeon.getHeight() - 1) {
			if(moveValid(distance, enemyDown, playerXY, "DOWN")) {
				return;
			}
		}
		int[] enemyLeft = this.getXY();
		enemyLeft[0] = enemyLeft[0] - 1;
		if (enemyLeft[0] >= 0) {
			if(moveValid(distance, enemyLeft, playerXY, "LEFT")) {
				return;
			}
		}
		int[] enemyRight = this.getXY();
		enemyRight[0] = enemyRight[0] + 1;
		if (enemyRight[0] <= dungeon.getHeight() - 1) {
			if(moveValid(distance, enemyRight, playerXY, "RIGHT")) {
				return;
			}
		}
	}
	
	/**
	 * kill - code to check if the player is on the enemy, and kill them.
	 * @param playerXY - the x y co-ordinates of the player.
	 * @return true if the player is killed, and false otherwise.
	 */
	public boolean kill(int[] playerXY) {
		DungeonApplication dApp = new DungeonApplication();
		if (playerXY[0] == this.getX() && playerXY[1] == this.getY()) {
			System.out.println("You were killed");
			dungeon.removeEntity(player);
			dungeon.setPlayer(null);
			this.player = null;
			
			return true;	
			
		}
		return false;
	}
	
	/**
	 * isFurther - code to check if the new square is further from the player than old square
	 * @param distance - previous distance from player
	 * @param enemyXY - enemy new x y co-ordinate
	 * @param playerXY - player x y co-ordinate
	 * @return true if further, false otherwise.
	 */
	public boolean isFurther(int distance, int[] enemyXY, int playerXY[]) {
		int newDist = Math.abs(playerXY[0] - enemyXY[0]) + Math.abs(playerXY[1] - enemyXY[1]);
		if (newDist > distance) {
			return true;
		}
		return false;
	}
	
	/**
	 * collision - tests the collision of the enemy entity.
	 * @param newXY - the x y co-ordinate the enemy is moving to
	 * @return true if there is no collision (can move), false otherwise.
	 */
	public boolean collision(int[] newXY) {
		List <Entity> entities = dungeon.getCurrentEntity(newXY[0], newXY[1]);
		for (Entity entity : entities) {
			if (!entity.canMove(this, entity, null)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * moveValid -tests if a move is valid for escape, and actually escapes the player.
	 * @param distance - the distance from enemy to player.
	 * @param newXY - the new square enemy is moving to.
	 * @param playerXY
	 * @param direction - direction the enemy moves.
	 * @return true if the new square satisfies requirements, false otherwise.
	 */
	public boolean moveValid(int distance, int[] newXY, int[] playerXY, String direction) {
		if (collision(newXY) && isFurther(distance, newXY, playerXY)) {
			enemyMove(direction);
			return true;
		}
		return false;
	}
	
	/**
	 * enemyMove - switch statement to determine where the enemy moves.
	 * @param move - the direction the enemy moves in (up, down, left, right).
	 */
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
	public void update(int[] playerXY, PlayerGoal goals) {
		// TODO Auto-generated method stub
		if (player.isNormalState()) {
			if (kill(playerXY)) {
				return;
			}
			//approach();
		} else {
			// System.out.println("this works");
			die(playerXY[0], playerXY[1], goals);
		}
		
	}

	@Override
	public void die(int x, int y, PlayerGoal goals) {
		// TODO Auto-generated method stub
		if (this.getX() == x && this.getY() == y) {
			goals.addComplete("enemy");
			player.removeObserver((EnemyObserver) this);
			dungeon.removeEntity(this);
			this.player = null;
		}

	}



}