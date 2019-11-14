package unsw.dungeon.Entities;

import java.util.List;

import unsw.dungeon.Entity;
import unsw.dungeon.Interactable;
import unsw.dungeon.Moveable;
import unsw.dungeon.Movement;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Entities.Player;

public class Fire extends Entity {
	
	private Dungeon dungeon;
	private Player player;
	
	public Fire(Dungeon dungeon, int x, int y, Movement movement) {
		super(x, y, movement);
		this.dungeon = dungeon;
		//this.player = dungeon.getPlayer();
		
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void deactivate() {
		setMovement(new Moveable());
		this.getEntityView().setVisible(false);
	}
	
	
	public void activate() {
		checkKill();
		
		setMovement(new Interactable());
		this.getEntityView().setVisible(true);
		
		try {Thread.sleep(300);}					// lasts 0.3s
		catch(InterruptedException ex) { Thread.currentThread().interrupt();}
		deactivate();
	}
	
	public void checkKill() {
		List<Entity> onSquare = dungeon.getCurrentEntity(getX(), getY());
		// System.out.println(onSquare.size());
		for (Entity entity : onSquare) {
			if (entity instanceof Enemy) {
				((Enemy) entity).death(player.getGoals());
			} else if (entity instanceof Player) {
				this.player.die();
			}
		}
		/*System.out.println("Player X " + player.getX());
		if (player.getX() == this.getX() && player.getY() == this.getY()) {
			player.die();
		}*/
	}
}
