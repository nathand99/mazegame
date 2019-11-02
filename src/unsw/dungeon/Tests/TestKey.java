package unsw.dungeon;

public class TestKey {
	
	
	public static void main(String[] args) {
		Dungeon dungeon = new Dungeon(10,10);
		Player player = new Player(dungeon, 0, 0, new Moveable());
		
		Key key = new Key(dungeon, 0, 1, 1, null);
		dungeon.addPickup_item(key);
		Key key2 = new Key(dungeon, 0, 2, 2, null);
		dungeon.addPickup_item(key2);
		player.moveDown();
		if (player.getKey() == null) {
			System.out.println("i have no key");
		}
		player.pickup();
		if (player.getKey() != null) {
			System.out.println("got key with ID:" + player.getKey().getkeyID());
		}
		player.moveDown();
		System.out.println("move down");
		player.pickup();
		if (player.getKey() != null) {
			System.out.println("got key with ID:" + player.getKey().getkeyID());
		}
		Key i = (Key) dungeon.getCurrentPickup_item(player.getX(), player.getY());
		if (i == null) {
			System.out.println("why");
		}
		System.out.println("dropped key with ID:" + i.getkeyID());
		
	}
	
}
