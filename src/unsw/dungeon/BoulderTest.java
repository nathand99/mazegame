package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests boulders and floor switches
 * 
 * testing: PS3: Pushing Boulders and ES7: Boulder Interaction
 *
 */
class BoulderTest {
	
	/**
	 * If a player moves onto a square with a boulder, 
	 * the boulder is pushed 1 square in the corresponding direction of the player's movement onto it. 
	 * The player takes its previous square.
	 */
	@Test
	void testPushBoulder() {
		Dungeon dungeon = new Dungeon(10,10);								// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());			// initialise player in the dungeon (at 0,0)
		Boulder boulder = new Boulder(dungeon, 0, 1, new Interactable());
		dungeon.addEntity(boulder);											// add boulder to dungeon (at 0,1)
		
		player.moveDown();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(boulder.getX(), 0);	
		assertEquals(boulder.getY(), 2);	// boulder moves down 1 spot to 0,2 
		assertEquals(player.getX(), 0);	
		assertEquals(player.getY(), 1);		// player moves down one spot to 0,2 to where the boulder was
	}
	
	/**
	 * If a boulder is blocked in that direction then the boulder does not move, and the player does not move either.
	 */
	@Test
	void testBlockedBoulder() {
		Dungeon dungeon = new Dungeon(10,10);								// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());			// initialise player in the dungeon (at 0,0)		
		Boulder boulder = new Boulder(dungeon, 0, 1, new Interactable());	// create new boulder (at 0,1)
		Wall wall = new Wall(0, 2, new Immovable());						// create a new wall (at 0,2)
		dungeon.addEntity(wall);											// add wall to dungeon
		dungeon.addEntity(boulder);											// add boulder to dungeon
		
		player.moveDown();						// player attemps moves down 1 space to push the boulder
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(boulder.getX(), 0);	
		assertEquals(boulder.getY(), 1);	// boulder does not move since the wall blocks it (boulder is still at 0,1)
		assertEquals(player.getX(), 0);	
		assertEquals(player.getY(), 0);		// player does not move since the wall blocked the boulder (player is still at 0,0)
	}
	
	/**
	 * If the boulder is pushed onto a floor switch, it triggers the floor switch, and moves into the same square as the floor switch
	 */
	@Test
	void testPushBoulderOntoFloorSwitch() {
		Dungeon dungeon = new Dungeon(10,10);								// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());			// initialise player in the dungeon (at 0,0)
		Boulder boulder = new Boulder(dungeon, 0, 1, new Interactable());	// add boulder to dungeon (at 0,1)
		FloorSwitch fs = new FloorSwitch(dungeon, 0, 2, new Moveable());	// add FloorSwitch (at 0,2)
		dungeon.addEntity(boulder);	
		dungeon.addEntity(fs);	
		
		dungeon.setPlayer(player);
		dungeon.registerNoMover();
		
		player.moveDown();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(boulder.getX(), 0);	
		assertEquals(boulder.getY(), 2);	// boulder moves down 1 spot to 0,2 on top of the floor switch
		assertEquals(fs.getX(), 0);	
		assertEquals(fs.getY(), 2);			// floor switch is at 0,2
	}

}
