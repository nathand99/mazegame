package unsw.dungeon;

import java.util.List;

public class TestSword {

	public static void main(String[] args) {
		Dungeon dungeon = new Dungeon(10,10);
		Player player = new Player(dungeon, 0, 0, new Moveable());
		
		Sword sword = new Sword(dungeon, 0, 1, 1, new Collectable());
		dungeon.addEntity(sword);
		Sword sword2 = new Sword(dungeon, 0, 2, 2, new Collectable());
		dungeon.addEntity(sword2);
		//System.out.println(dungeon.getEntities().toString());
		// dungeon.printEntities();
		player.moveDown();
		if (player.getSword() == null) {
			System.out.println("i have no sword");
		}
		if (player.getSword() != null) {
			System.out.println("got sword with ID:" + player.getSword().getswordID());
		}
		//System.out.println(dungeon.getEntities().toString());
		try
		{
		    Thread.sleep(400);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.moveDown();
		System.out.println("move down");
		System.out.println(player.getX() + ", " + player.getY()); // is not mogivn down?
		if (player.getSword() != null) {
			System.out.println("got sword with ID:" + player.getSword().getswordID());
		}
		List<Entity> entities = dungeon.getCurrentEntity(player.getX(), player.getY());
    	Sword i = null;
    	// check if there is a Pickup_item on players location
    	
    	for (Entity e : entities) {
    		if (e instanceof Sword) {
    			i = (Sword) e;
    			break;
    		}
    	}
		if (i == null) {
			System.out.println("why");
		}
		System.out.println("dropped sword with ID:" + i.getswordID());
		
	}

}
