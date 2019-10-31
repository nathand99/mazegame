package unsw.dungeon;


public class Portal extends Entity{
	private int id; 
	private Dungeon dungeon;
	
	public Portal(Dungeon dungeon,int x, int y, int id, Movement movement) {
		super(x, y, movement);
		// TODO Auto-generated constructor stub
		this.id = id;
		this.dungeon = dungeon;
	}
	
	public int getId() {
		return id;
	}

	//find the other portal that is related to the one found 
	public Portal getRelatedPortal() {	
		for (Entity e : dungeon.getEntities()) {
			if (e == null) continue;
			if(e instanceof Portal && ((Portal) e).getId() == getId() && e != this) {
				return new Portal(dungeon, e.getX(), e.getY(), getId(), null);
			}
		}
		return null;
	}
}
