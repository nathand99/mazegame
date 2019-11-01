package unsw.dungeon;

public class TestSword {

	public static void main(String[] args) {
		Dungeon dungeon = new Dungeon(10,10);
		Player player = new Player(dungeon, 0, 0, new Moveable());
		
		Sword sword = new Sword(dungeon, 0, 1, 1);
		dungeon.addPickup_item(sword);
		Sword sword2 = new Sword(dungeon, 0, 2, 2);
		dungeon.addPickup_item(sword2);
		player.moveDown();
		if (player.key == null) {
			System.out.println("i have no sword");
		}
		player.pickup();
		if (player.sword != null) {
			System.out.println("got sword with ID:" + player.sword.getswordID());
		}
		player.moveDown();
		System.out.println("move down");
		player.pickup();
		if (player.sword != null) {
			System.out.println("got sword with ID:" + player.sword.getswordID());
		}
		Sword i = (Sword) dungeon.getCurrentPickup_item(player.getX(), player.getY());
		if (i == null) {
			System.out.println("why");
		}
		System.out.println("dropped sword with ID:" + i.getswordID());
		
	}

}
