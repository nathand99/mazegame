package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
/*
 * Tests PS6
 */
public class InvincibilityTest {
	/*
	 * Test to determine potion pickup, invincibility
	 */
	@Test
	void testSingleGoal() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.setPlayer(player); 									// set the player in the dungeon
		Invincibility i = new Invincibility(dungeon, 0, 1, new Collectable()); // the potion
		dungeon.addEntity(i);
		assertEquals(player.isNormalState(), true); // normal state
		player.moveDown();
		assertEquals(player.isNormalState(), false); // potion act
		try {Thread.sleep(10000);}
		catch(InterruptedException ex) { Thread.currentThread().interrupt();}
		assertEquals(player.isNormalState(), true); // timed out potion.
	}
	
	// test for enemy dying is in enemy.
}
