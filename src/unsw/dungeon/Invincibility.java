package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

public class Invincibility extends Entity implements PickupItem{
	Timer timer = new Timer();
	private int timeLimit = 10000;
	Dungeon dungeon;
	
	/**
	 * 
	 * @param dungeon
	 * @param x
	 * @param y
	 * @param movement
	 */
	public Invincibility(Dungeon dungeon, int x, int y, Movement movement) {
		super(x, y, movement);
		this.dungeon = dungeon;
	}
	
	/**
	 * @param player
	 */
	public boolean invincible(Player player) {
		//removes potion entity 
		//.... need to remove potion picture ....
		//.... need to change sprite of player ....
		player.setNormalState(false);
		player.setNumThreads(player.getNumThreads()+1);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				//player back to normal state
				if(player.getNumThreads()==1) {
					//only goes back to normal state if one thread is active 
					//otherwise we would set state back to normal before the last 
					//potion was collected
					player.setNormalState(true);
					player.setNumThreads(player.getNumThreads()-1);
				}
				else {
					//another thread is active 
					player.setNumThreads(player.getNumThreads()-1);
				}	
			}
		}, timeLimit);
		return true;
	}

	@Override
	public Entity pickup(Player p, Dungeon d) {
		this.invincible(p);
		d.removeEntity(this);
		return null;
	}
	
	
	
}
