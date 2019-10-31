package unsw.dungeon;

public class Boulder extends Entity{
	
	private Dungeon dungeon;
	
	public Boulder(Dungeon dungeon, int x, int y, Movement movement) {
		super(x, y, movement);
		this.dungeon = dungeon;
	}
	
	
	
	
}
