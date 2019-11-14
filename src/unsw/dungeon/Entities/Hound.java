package unsw.dungeon.Entities;

import java.util.Timer;
import java.util.TimerTask;

import unsw.dungeon.Movement;
import unsw.dungeon.Application.Dungeon;

/**
 * Hound - an enemy with a very erratic movement pattern, that does not pay attention
 * to where the player is.
 *
 */
public class Hound extends Enemy {
	
	private String direction;
	
	public Hound(Dungeon dungeon, int x, int y, String direction, Movement movement) {
		super(dungeon, x, y, movement);
		this.direction = direction;
	}

	@Override
	public void startMove() {
		// TODO Auto-generated method stub
		t = new Timer();
		t.schedule(new TimerTask() {

            @Override
            public void run() {
            	if (player == null) {
            		t.cancel();
            	} else {
            		hunt();
            	}

            }
        }, 500, 500);
	}

	@Override
	public void singleMove() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Hunt - a very basic movement pattern for the hound.
	 */
	public void hunt() {
		int random = (int) Math.round(Math.random()*100) % 8;
		// System.out.println(random);
		int[] move = getXY();
		if (direction.equals("UP")) { // up
			move[1] = move[1] - 1;
			if (!possibleMove(move)) {
				if (random == 0) {
					direction = "LEFT";
				} else if (random == 1) {
					direction = "DOWN";
				} else {
					direction = "RIGHT";
				}
				hunt();
			} else if (random == 0) {
				direction = "RIGHT";
			}
			
		} else if (direction.equals("DOWN")) { // down
			move[1] += 1;
			if (!possibleMove(move)) {
				if (random == 0) {
					direction = "UP";
				} else if (random == 1) {
					direction = "RIGHT";
				} else {
					direction = "LEFT";
				}
				hunt();
			} else if (random == 0) {
				direction = "LEFT";
			}
		} else if (direction.equals("LEFT")) { // left
			move[0] -= 1;
			if (!possibleMove(move)) {
				if (random == 0) {
					direction = "RIGHT";
				} else if (random == 1) {
					direction = "DOWN";
				} else {
					direction = "UP";
				}
				hunt();
			} else if (random == 0) {
				direction = "UP";
			}
		} else { // right
			move[0] += 1;	
			if (!possibleMove(move)) {
				if (random == 0) {
					direction = "UP";
				} else if (random == 1) {
					direction = "LEFT";
				} else {
					direction = "DOWN";
				}
				hunt();
			} else if (random == 0) {
				direction = "DOWN";
			}
		}
		kill();
	}
	
	/**
	 * PossibleMove - determines if the move is possible or not.
	 * @param move - the move (XY)
	 * @return
	 */
	public boolean possibleMove(int[] move) {
		if (
			move[0] >= 0 && 
			move[1] >= 0 && 
			move[1] <= dungeon.getHeight() - 1 && 
			move[0] <= dungeon.getWidth() - 1
		) {
			if (collision(move)) {
				enemyMove(direction);
				return true;
			}
		} 
		return false;
	}
}
