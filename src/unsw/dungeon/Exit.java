package unsw.dungeon;

public class Exit extends Entity implements GoalObserver {
	
	private Dungeon dungeon;
	private Player player;
	
	public Exit(Dungeon dungeon, int x, int y, Movement movement) {
		super(x, y, movement);
		this.dungeon = dungeon;
		this.player = null;
		
	}

	@Override
	public void update(PlayerGoal goals, int[] playerXY) {
		if (this.getX() == playerXY[0] && this.getY() == playerXY[1]) {
			if (player.canExit()) {
				goals.addComplete("exit");
			}
		}
	}

	@Override
	public void register() {
		// TODO Auto-generated method stub
		this.player = dungeon.getPlayer();
		player.registerObserver(this);
		
	}
	
}
