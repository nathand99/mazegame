package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * 
 * testing ES6: Key Interaction, PS5: Using Keys (testDoorUnlock() only), ES5: Unlocked Door Interaction (testDoorUnlock() only)
 *
 */
class KeyTest {

	@Test
	void testKeyPickup() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		Key key = new Key(dungeon, 0, 1, 1, new Collectable());		// create a new key (1 square below player)
		dungeon.addEntity(key);										// add key to dungeon
		
		assertEquals(player.getKey(), null);	// player initally has no key in inventory
		player.moveDown();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(player.getKey(), key);		// player picks up key from ground and puts it in inventory
		
		// now check the key is not on the ground
		List<Entity> entities = dungeon.getCurrentEntity(player.getX(), player.getY());	// get all entities on the square of the player
    	Key i = null;
    	for (Entity e : entities) {
    		if (e instanceof Key) {
    			i = (Key) e;
    			break;
    		}
    	}
    	assertEquals(i,null);	// there is no key on the square of the player 
	}
	
	@Test
	void testKeySwap() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		Key key1 = new Key(dungeon, 0, 1, 1, new Collectable());	// create a new key (1 square below player)
		Key key2 = new Key(dungeon, 0, 2, 2, new Collectable());	// create another new key (2 squares below player)
		dungeon.addEntity(key1);
		dungeon.addEntity(key2);									// add keys to dungeon
		
		assertEquals(player.getKey(), null);	// player initally has no key in inventory
		player.moveDown();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(player.getKey(), key1);	// player picks up key 1 from ground and puts it in inventory
		
		// now check key 1 is not on the ground
		List<Entity> entities = dungeon.getCurrentEntity(player.getX(), player.getY());	// get all entities on the square of the player
    	Key i = null;
    	for (Entity e : entities) {
    		if (e instanceof Key) {
    			i = (Key) e;
    			break;
    		}
    	}
    	assertEquals(i,null);	// there is no key on the square of the player 
    	
    	player.moveDown();		// player moves down 1 more space onto key 2
    	try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
    	
    	assertEquals(player.getKey(), key2); // player picks up key 2 from ground and puts it in inventory
    	// now key 1 is on the ground
		List<Entity> entities2 = dungeon.getCurrentEntity(player.getX(), player.getY());	// get all entities on the square of the player
    	Key k = null;
    	for (Entity e : entities2) {
    		if (e instanceof Key) {
    			k = (Key) e;
    			break;
    		}
    	}
    	assertEquals(k.getkeyID(),key1.getkeyID());	// the player drops key 1 on the ground
	}
	
	@Test
	void testDoorUnlock() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		Key key = new Key(dungeon, 0, 1, 1, new Collectable());		// create a new key (1 square below player)
		Door door = new Door(dungeon, 0, 2, 1, new Interactable()); // create a new door (2 squares below player) (same ID as key)
		dungeon.addEntity(key);										// add key to dungeon
		dungeon.addEntity(door);									// add door to dungeon
		
		assertEquals(player.getKey(), null);	// player initally has no key in inventory
		player.moveDown();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(player.getKey(), key);		// player picks up key from ground and puts it in inventory
		
		player.moveDown();						// player moves down 1 more space onto door
		try
		{
		    Thread.sleep(400);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(player.getX(), door.getX());	// player unlocks the door (since keyID and doorID match
		assertEquals(player.getY(), door.getY());	// player is now standing in the unlocked door
		
	}

}
