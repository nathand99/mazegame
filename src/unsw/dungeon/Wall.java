package unsw.dungeon;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
	public void walk(Player p, String direction) {
		p.setAbleToMove(false);
	}
}
