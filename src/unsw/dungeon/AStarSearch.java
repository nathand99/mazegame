package unsw.dungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AStarSearch {
	private List<AStarNode> open;
	private List<AStarNode> closed;
	private int[] goal;
	private int[] start;
	private Dungeon dungeon;
	
	
	public AStarSearch(Dungeon dungeon, int[] goal, int[] start) {
		this.open = new ArrayList<AStarNode>();
		this.closed = new ArrayList<AStarNode>();
		this.goal = goal;
		this.start = start;
		this.dungeon = dungeon;
	}
	
	public List<String> search() {  // not supposed to be void, must return path.
		initialise();
		
		while(!open.isEmpty()) {
			AStarNode next = open.get(0);
			
			if (checkGoal(next)) {
				return next.getPath();
			}
			open.remove(next);
			closed.add(next);
			createChildren(next);
		}
		
		// return failure
		return null;
	}
	
	public void initialise() {
		AStarNode start = new AStarNode(this.start, 0);
		start.calcHeuristic(goal);
		open.add(start);
	}
	
	public boolean checkGoal(AStarNode node) {
		if (node.getX() == this.goal[0] && node.getY() == this.goal[1]) {
			return true;
		}
		return false;
	}
	
	public void createChildren(AStarNode parent) {
		int prevX = parent.getX();
		int prevY = parent.getY();
		int newDistance = parent.getDistance() + 1;
		List<String> parentPath = parent.getPath();
		
		List<String> upPath = createPath(parentPath, "UP");
		List<String> downPath = createPath(parentPath, "DOWN");
		List<String> leftPath = createPath(parentPath, "LEFT");
		List<String> rightPath = createPath(parentPath, "RIGHT");

		
		// dont forget enemies have to check collision!
		AStarNode up = new AStarNode(prevX, prevY - 1, newDistance, upPath);
		up.calcHeuristic(goal);
		AStarNode down = new AStarNode(prevX, prevY + 1, newDistance, downPath);
		down.calcHeuristic(goal);
		AStarNode left = new AStarNode(prevX - 1, prevY, newDistance, leftPath);
		left.calcHeuristic(goal);
		AStarNode right = new AStarNode(prevX + 1, prevY, newDistance, rightPath);
		right.calcHeuristic(goal);
		
		addChild(up);
		addChild(down);
		addChild(left);
		addChild(right);
	}
	
	public List<String> createPath(List<String> parentPath, String direction) {
		List<String> childPath = new ArrayList<String>(parentPath);
		childPath.add(direction);
		return childPath;
	}
	
	public void addChild(AStarNode child) { // best if collision is checked here.
		// collision check
		List <Entity> entities = dungeon.getCurrentEntity(child.getX(), child.getY());
		for (Entity entity : entities) {
			if (entity instanceof Wall) {
				return;
			}
		}
		// collision check end.
		if (!alreadyChecked(child, this.open) && !alreadyChecked(child, this.closed)) {
			addAndSort(child);
		}
		
	}
	
	public boolean alreadyChecked(AStarNode child, List<AStarNode> list) {
		for (AStarNode node : list) {
			if (node.getX() == child.getX() && node.getY() == child.getY()) {
				// when we have to rerun because there is a new better path
				if (child.getDistance() < node.getDistance()) {
					list.remove(node);
					return false;
				}
				return true;
			}
		}
		return false;
	}
	
	public void addAndSort(AStarNode node) {
		open.add(node);
		Collections.sort(open);
	}
	
}
