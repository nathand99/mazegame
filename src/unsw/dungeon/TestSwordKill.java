package unsw.dungeon;

import java.util.List;

public class TestSwordKill {

	
	public static void main(String[] args) {
		Dungeon dungeon = new Dungeon(10,10);
		Player player = new Player(dungeon, 1, 1, new Moveable());
		dungeon.setPlayer(player);
		PlayerGoal gS = new PlayerGoal(player);
		SingleGoal s1 = new SingleGoal("enemy", 5);
		gS.addGoal(s1);
		player.addGoals(gS);
		
		Sword sword = new Sword(dungeon, 1, 2, 1, new Collectable());
		dungeon.addEntity(sword);
		
		Enemy enemy1 = new Enemy(dungeon, 1, 0, new Interactable() );
		dungeon.addEntity(enemy1);
		Enemy enemy2 = new Enemy(dungeon, 0, 2, new Interactable());
		dungeon.addEntity(enemy2);
		Enemy enemy3 = new Enemy(dungeon, 2, 2, new Interactable());
		dungeon.addEntity(enemy3);
		Enemy enemy4 = new Enemy(dungeon, 1, 3, new Interactable());
		dungeon.addEntity(enemy4);
		dungeon.registerNoMove();
		//player starts at 1,1
		//attempt to kill enemy1 so above player
		player.attackW();
		//check if enemies are still there
		
		if (dungeon.isEnemy(1, 0)) {
			System.out.println("shouldnt kill enemy1");
		}
		else System.out.println("FAIL");
		if (dungeon.isEnemy(0, 2)) {
			System.out.println("shouldnt kill enemy2");
		}
		else System.out.println("FAIL");
		if (dungeon.isEnemy(2, 2)) {
			System.out.println("shouldnt kill enemy3");
		}
		else System.out.println("FAIL");
		if (dungeon.isEnemy(1, 3)) {
			System.out.println("shouldnt kill enemy4");
		}
		else System.out.println("FAIL");
		
		
		
		//move down to 1,2 but doesnt pick up sword yet
		player.moveDown();
		if (player.getSword() == null) {
			System.out.println("i have no sword");
		}
		

		if (player.getSword() != null) {
			System.out.println("i have sword now");
		}
		//attempt to kill the enemy with sword should pass
		//kill enemy2 swing left 
		player.attackA();
		//uncomment to test sword hits 
		//should fail all tests 
		//will fix this later just to see that it works 
		/*
		player.attackA();
		player.attackA();
		player.attackA();
		player.attackA();
		*/
		if (dungeon.isEnemy(1, 0)) {
			System.out.println("shouldnt kill enemy1");
		}
		else System.out.println("FAIL");
		if (!dungeon.isEnemy(0, 2)) {
			System.out.println("should kill enemy2");
		}
		else System.out.println("FAIL");
		if (dungeon.isEnemy(2, 2)) {
			System.out.println("shouldnt kill enemy3");
		}
		else System.out.println("FAIL");
		if (dungeon.isEnemy(1, 3)) {
			System.out.println("shouldnt kill enemy4");
		}
		else System.out.println("FAIL");
		//kill enemy3 swing right
		try
		{
		    Thread.sleep(400);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.attackD();
		if (dungeon.isEnemy(1, 0)) {
			System.out.println("shouldnt kill enemy1");
		}
		else System.out.println("FAIL");
		if (!dungeon.isEnemy(0, 2)) {
			System.out.println("enemy2 should stay dead");
		}
		else System.out.println("FAIL");
		if (!dungeon.isEnemy(2, 2)) {
			System.out.println("should kill enemy3");
		}
		else System.out.println("FAIL");
		if (dungeon.isEnemy(1, 3)) {
			System.out.println("shouldnt kill enemy4");
		}
		else System.out.println("FAIL");
		//kill enemy4 swing down
		try
		{
		    Thread.sleep(400);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.attackS();
		if (dungeon.isEnemy(1, 0)) {
			System.out.println("shouldnt kill enemy1");
		}
		else System.out.println("FAIL");
		if (!dungeon.isEnemy(0, 2)) {
			System.out.println("enemy2 should stay dead");
		}
		else System.out.println("FAIL");
		if (!dungeon.isEnemy(2, 2)) {
			System.out.println("enemy3 should stay dead");
		}
		else System.out.println("FAIL");
		if (!dungeon.isEnemy(1, 3)) {
			System.out.println("should kill enemy4");
		}
		else System.out.println("FAIL");
		//move up to 1,1
		try
		{
		    Thread.sleep(400);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.moveUp();
		//attempt to kill enemy1
		player.attackW();
		//check if enemies are still there
		if (!dungeon.isEnemy(1, 0)) {
			System.out.println("should kill enemy1");
		}
		else System.out.println("FAIL");
		if (!dungeon.isEnemy(0, 2)) {
			System.out.println("enemy2 should stay dead");
		}
		else System.out.println("FAIL");
		if (!dungeon.isEnemy(2, 2)) {
			System.out.println("enemy3 should stay dead");
		}
		else System.out.println("FAIL");
		if (!dungeon.isEnemy(1, 3)) {
			System.out.println("enemy4 should stay dead");
		}
		else System.out.println("FAIL");
		
		//then add new enemy 5 and 6
		Enemy enemy5 = new Enemy(dungeon, 2, 1, new Immovable());
		dungeon.addEntity(enemy5);
		Enemy enemy6 = new Enemy(dungeon, 3, 1, new Immovable());
		dungeon.addEntity(enemy6);
		dungeon.registerNoMove();
		// should kill enemy 5 but not enemy 6 as no sword.
		try
		{
		    Thread.sleep(400);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.attackD();
		if (!dungeon.isEnemy(2, 2)) {
			System.out.println("should kill enemy5");
		}
		else System.out.println("FAIL");
		if (dungeon.isEnemy(3, 1)) {
			System.out.println("shouldnt kill enemy6");
		}
		else System.out.println("FAIL");
		//move right
		try
		{
		    Thread.sleep(400);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.moveRight();
		player.attackD();
		//get current entity in dun should help to see if entity exists
		if (!dungeon.isEnemy(2, 1)) {
			System.out.println("enemy5 should stay dead");
		}
		else System.out.println("FAIL");
		if (dungeon.isEnemy(3, 1)) {
			System.out.println("shouldnt kill enemy6 used up all sword attacks ");
		}
		else System.out.println("FAIL");
			
		// player.getGoals().printGoals();
	}
}
