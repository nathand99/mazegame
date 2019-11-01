package unsw.dungeon;

public class Door extends Entity {
	
	Dungeon dungeon;
	
	public Door(Dungeon dungeon, int x, int y, Movement movement) {
		super(x, y, movement);
		this.dungeon = dungeon;
	}

}
