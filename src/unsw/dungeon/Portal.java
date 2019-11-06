package unsw.dungeon;


public class Portal extends Entity{
	private int id; 
	private Dungeon dungeon;
	
	/**
	 * Portal to teleport player
	 * @param dungeon
	 * @param x
	 * @param y
	 * @param id
	 * @param movement
	 */
	public Portal(Dungeon dungeon,int x, int y, int id, Movement movement) {
		super(x, y, movement);
		// TODO Auto-generated constructor stub
		this.id = id;
		this.dungeon = dungeon;
	}
	
	public int getId() {
		return id;
	}
	
	/**
	 * bestTele - teleports the player onto the portal.
	 * @param player
	 * @return always returns false, just sets the player square or not.
	 */
	public boolean bestTele(Player player) {
		// TODO Auto-generated method stub
		
		for(Entity e : dungeon.getEntities()) {
			if (e == null) continue;
			if(e instanceof Portal && ((Portal) e).getId() == getId() && e != this) {
				// this code can be used to just set the player x-y to the portal.
				player.x().set(e.getX());
				player.y().set(e.getY());
				
				/*if(e.getY() > 0 && dungeon.getCurrentEntity(e.getX(), e.getY()-1).isEmpty()) {
					//System.out.println("we can move up");
					player.x().set(e.getX());
					player.y().set(e.getY()-1);
					return false;
				}
				else if (e.getY() < dungeon.getHeight() - 1 && dungeon.getCurrentEntity(e.getX(), e.getY()+1).isEmpty()) {
					//System.out.println("we can move down");
					player.x().set(e.getX());
					player.y().set(e.getY()+1);
					return false;
				}
				else if (e.getX() > 0 && dungeon.getCurrentEntity(e.getX()-1, e.getY()).isEmpty()) {
					//System.out.println("we can move left");
					player.x().set(e.getX()-1);
					player.y().set(e.getY());
					return false;
				}
				else if (e.getX() < dungeon.getWidth() - 1 && dungeon.getCurrentEntity(e.getX()+1, e.getY()).isEmpty()) {
					//System.out.println("we can move right");
					player.x().set(e.getX()+1);
					player.y().set(e.getY());
					return false;
				}
				else {
					//portal is blocked so don't do anything
					return false;
				}*/
			}
		}
		return false;
	}
	
	
	

}
