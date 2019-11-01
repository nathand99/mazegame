package unsw.dungeon;

public class TestTreasure {
	
	public static void main(String[] args) {
		Dungeon dungeon = new Dungeon(10,10);
		Player player = new Player(dungeon, 0, 0, new Moveable());
		Treasure treasure = new Treasure(dungeon, 0, 1, 1);
		dungeon.addPickup_item(treasure);
		Treasure treasure2 = new Treasure(dungeon, 0, 2, 2);
		dungeon.addPickup_item(treasure2);
		player.moveDown();
		if (player.treasure.size() == 0) {
			System.out.println("i have no treasure");
		}
		player.pickup();
		if (player.treasure.size() == 1) {
			System.out.println("got 1 treasure");
		}
		player.moveDown();
		System.out.println("move down");
		player.pickup();
		if (player.treasure.size() == 2) {
			System.out.println("got 2 treasure");
		}
	}
}
