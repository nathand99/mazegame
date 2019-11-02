package unsw.dungeon;

public class TestTreasure {
	
	public static void main(String[] args) {
		Dungeon dungeon = new Dungeon(10,10);
		Player player = new Player(dungeon, 0, 0, new Moveable());
		Treasure treasure = new Treasure(dungeon, 0, 1, 1, new Collectable());
		dungeon.addEntity(treasure);
		Treasure treasure2 = new Treasure(dungeon, 0, 2, 2, new Collectable());
		dungeon.addEntity(treasure2);
		player.moveDown();
		if (player.getTreasure() == 0) {
			System.out.println("i have no treasure");
		}
		player.pickup();
		if (player.getTreasure() == 1) {
			System.out.println("got 1 treasure");
		}
		player.moveDown();
		System.out.println("move down");
		player.pickup();
		if (player.getTreasure() == 2) {
			System.out.println("got 2 treasure");
		}
	}
}
