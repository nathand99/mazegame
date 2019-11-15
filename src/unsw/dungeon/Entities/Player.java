package unsw.dungeon.Entities;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Application.DungeonController;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Subject {
	
	static final int minClickDelay = 400;
	
    private Dungeon dungeon;
    
    private List<EnemyObserver> enemyObservers;
    private List<GoalObserver> goalObservers;
    private DungeonController controller;
    
    private int lastClickTime = 0;
    private int lastWeaponSwing = 0;
    
    // goals
    private PlayerGoal goals;
    
    // inventory
    // weapons
    private Sword sword = null;
    private LongSword longSword = null;
    private Mace mace = null;
    
    private Key key = null;
    private int treasure = 0; 
    //game starts on normal state being true 
    //when normal state is true enemy can kill player
    //when normal state is false enemy can't kill player
    private boolean normalState = true;
    private int numThreads = 0;

	/**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y, Movement movement) {
        super(x, y, movement);
        this.dungeon = dungeon;
        enemyObservers = new ArrayList<EnemyObserver>();
        goalObservers = new ArrayList<GoalObserver>();
        this.setGoals(new PlayerGoal(this));
    }

    /**
     * moves up
     */
    public void moveUp() {
    	int now = (int) System.currentTimeMillis();
    	if (now - lastClickTime < minClickDelay) return;
    	
    	List<Entity> moveTo = dungeon.getCurrentEntity(this.getX(), this.getY() - 1);
        if (getY() > 0 && moveTo.size() == 0) {
            y().set(getY() - 1); 
            lastClickTime = now;
            notifyEnemyObservers();
        } else if (moveTo.size() != 0) {
        	for (Entity entity : moveTo) {
        		//System.out.println(entity.checkMovement(this));
        		if (entity.canMove(this, entity, "UP") == false) {
        			return;
        		}
        	}
        	y().set(getY() - 1); 
        	notifyGoalObservers();
        	pickup();
        	lastClickTime = now;
        	notifyEnemyObservers();
        	checkKey();
        }
    }
    
    /**
     * moves down
     */
    public void moveDown() {
    	int now = (int) System.currentTimeMillis();
    	if (now - lastClickTime < minClickDelay) return;
    	
    	List<Entity> moveTo = dungeon.getCurrentEntity(this.getX(), this.getY() + 1);
        if (getY() < dungeon.getHeight() - 1 && moveTo.size() == 0) {
            y().set(getY() + 1);
            lastClickTime = now;
            notifyEnemyObservers();
        } else if (moveTo.size() != 0) {
        	for (Entity entity : moveTo) {
        		if (entity.canMove(this, entity, "DOWN") == false) {
        			return;
        		}
        	}
        	y().set(getY() + 1);  
        	notifyGoalObservers();
        	pickup();
        	lastClickTime = now;
        	notifyEnemyObservers();
        	checkKey();
        } 
    }
    
    /**
     * moves left
     */
    public void moveLeft() {
    	int now = (int) System.currentTimeMillis();
    	if (now - lastClickTime < minClickDelay) return;
    	
    	List<Entity> moveTo = dungeon.getCurrentEntity(this.getX() - 1, this.getY());
        if (getX() > 0 && moveTo.size() == 0) {
            x().set(getX() - 1);
            lastClickTime = now;
            notifyEnemyObservers();
        } else if (moveTo.size() != 0) {
        	for (Entity entity : moveTo) {
        		if (entity.canMove(this, entity, "LEFT") == false) {
        			return;
        		}
        	}
        	x().set(getX() - 1);
        	notifyGoalObservers();
        	pickup();
        	lastClickTime = now;
        	notifyEnemyObservers();
        	checkKey();
        } 
    }

    /**
     * moves right
     */
    public void moveRight() {
    	int now = (int) System.currentTimeMillis();
    	if (now - lastClickTime < minClickDelay) return;
    	
    	List<Entity> moveTo = dungeon.getCurrentEntity(this.getX() + 1, this.getY());
        if (getX() < dungeon.getWidth() - 1 && moveTo.size() == 0) {
            x().set(getX() + 1);
            lastClickTime = now;
            notifyEnemyObservers();
        } else if (moveTo.size() != 0) {
        	for (Entity entity : moveTo) {
        		if (entity.canMove(this, entity, "RIGHT") == false) {
        			return;
        		}
        	}
        	x().set(getX() + 1); 
        	notifyGoalObservers();
        	pickup();
        	lastClickTime = now;
        	notifyEnemyObservers();
        	checkKey();
        } 
    }
    
    /**
     * pickups an item.
     */
    public void pickup() {
    	List<Entity> entities = dungeon.getCurrentEntity(getX(), getY());
    	PickupItem item = null;
    	// check if there is a Pickup_item on players location
    	for (Entity e : entities) {
    		if (e instanceof PickupItem) {
    			item = (PickupItem) e;
    			
    			break;
    		}
    	}
    	// there is a Pickup_item on the players location
    	if (item != null) {
    		Entity dropped = null;
			dropped = item.pickup(this);
			// if player drops a Pickup_item, add it to the dungeon
			if (dropped != null) {
				dungeon.addEntity(dropped);
			}
			// dungeonController bar editing.
			if (controller != null) {
				controller.invAdd(item);
			}
    	}
    }
    
    /**
     * checks if player has won, and updates the goal.
     */
    public void win() {
    	Platform.runLater(new Runnable() {
            @Override public void run() {
                updateGoal();
            }
        });
    	if (goals.checkCompletion()) {
    		// TODO: stub, should actually do stuff when front end is done.
    		System.out.println("You win!");
    	}
    }
    
    
    public int getMinClickDelay() {
		return minClickDelay;
	}
    
    /**
     * checks if player can exit the Exit entity.
     * @return true or false.
     */
    public boolean canExit() {
    	return goals.onlyExit();
    }
    
    /**
     * isNormalState - essentially a getter.
     * @return normalState value.
     */
	public boolean isNormalState() {
		return normalState;
	}

	public void setNormalState(boolean normalState) {
		this.normalState = normalState;
	}

	public int getNumThreads() {
		return numThreads;
	}

	public void setNumThreads(int numThreads) {
		this.numThreads = numThreads;
	}
	
	/**
	 * Returns the durability of Sword.
	 * @return
	 */
	public int getSwordDurability() {
		if (sword == null) {
			return 0;
		}
		return sword.getHitsLeft();
	}

	public Sword getSword() {
		return sword;
	}

	public void setSword(Sword sword) {
		this.sword = sword;
	}
	
	/**
	 * Returns the keyID.
	 * @return
	 */
	public int getKeyID() {
		return key.getkeyID();
	}
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public int getTreasure() {
		return treasure;
	}

	public void setTreasure(int treasure) {
		this.treasure = treasure;
	}
	
	public PlayerGoal getGoals() {
		return goals;
	}

	public void setGoals(PlayerGoal goals) {
		this.goals = goals;
	}
	
	/**
	 * adds a goal to the player's goals
	 * @param goals - a goal.
	 */
	public void addGoals(PlayerGoal goals) {
		this.setGoals(goals);
	}

	@Override
	public void registerObserver(EnemyObserver o) {
		enemyObservers.add(o);
	}

	@Override
	public void removeObserver(EnemyObserver o) {
		int i = enemyObservers.indexOf(o);
		if (i >= 0) {
			enemyObservers.remove(i);
		}
	}
	
	/**
	 * Removes all enemy Observers.
	 */
	public void removeAllEnemies() {
		for (int i = 0; i < enemyObservers.size(); i++) {
			EnemyObserver o = enemyObservers.get(i);
			Enemy enemy = (Enemy) o;
			enemy.setPlayer(null);
			removeObserver(o);
			i--;
		}
	}

	@Override
	public void notifyEnemyObservers() {
		// TODO Auto-generated method stub
		for (int i = 0; i < enemyObservers.size(); i++) {
			int prevSize = enemyObservers.size();
			EnemyObserver obs = enemyObservers.get(i);
			obs.update(this.getXY(), this.goals);
			if (prevSize > enemyObservers.size()) {
				i--;
			}
		}
		//updateGoal();
		win();
	}
	
	@Override
	public void notifyEnemyWeapon(int x, int y) {
		// TODO Auto-generated method stub
		for (int i = 0; i < enemyObservers.size(); i++) {
			int prevSize = enemyObservers.size();
			EnemyObserver obs = enemyObservers.get(i);
			obs.die(x, y, this.goals);
			if (prevSize > enemyObservers.size()) {
				i--;
			}
		}
		//updateGoal();
		win();
	}

	@Override
	public void registerObserver(GoalObserver o) {
		// TODO Auto-generated method stub
		goalObservers.add(o);
	}

	@Override
	public void removeObserver(GoalObserver o) {
		// TODO Auto-generated method stub
		int i = goalObservers.indexOf(o);
		if (i >= 0) {
			goalObservers.remove(i);
		}
	}

	@Override
	public void notifyGoalObservers() {
		for (int i = 0; i < goalObservers.size(); i++) {
			int prevSize = goalObservers.size();
			GoalObserver obs = goalObservers.get(i);
			obs.update(this.getGoals(), this.getXY());
			if (prevSize > goalObservers.size()) {
				i--;
			}
		}
		//updateGoal();
		win();
	}
	
	/**
	 * attack up
	 */
	public void attackW() {
		if (sword != null) {
			sword.attackAbove(this);
		}
		else if(longSword != null) {
			longSword.attackAbove(this);
		}
		else if(mace != null) {
			mace.attackAbove(this);
		}
		
		
	}
	
	/**
	 * attack down
	 */
	public void attackS() {
		if (sword != null) {
			sword.attackBelow(this);
		}
		else if(longSword != null) {
			longSword.attackBelow(this);
		}
		else if(mace != null) {
			mace.attackBelow(this);
		}
		
	}

	/**
	 * attack right
	 */
	public void attackD() {
		if (sword != null) {
			sword.attackRight(this);
		}
		else if(longSword != null) {
			longSword.attackRight(this);
		}
		else if(mace != null) {
			mace.attackRight(this);
		}
	}
	
	/**
	 * attack left
	 */
	public void attackA() {
		if (sword != null) {
			sword.attackLeft(this);
		}
		else if(longSword != null) {
			longSword.attackLeft(this);
		}
		else if(mace != null) {
			mace.attackLeft(this);
		}
		if (controller != null) {
			controller.setWeaponDurability();
		}
	}
	
	
	/**
	 * The player dies.
	 */
	 public void die() {
		 System.out.println("Yep, he's dead");
		 dungeon.deregisterAll();
		 dungeon.removeEntity(this);
		 dungeon.setPlayer(null);
	 }

	public int getLastWeaponSwing() {
		return lastWeaponSwing;
	}

	public void setLastWeaponSwing(int lastWeaponSwing) {
		this.lastWeaponSwing = lastWeaponSwing;
	}

	public LongSword getLongSword() {
		return longSword;
	}

	public void setLongSword(LongSword longSword) {
		this.longSword = longSword;
	}

	public Mace getMace() {
		return mace;
	}

	public void setMace(Mace mace) {
		this.mace = mace;
	}

	public DungeonController getController() {
		return controller;
	}

	public void setController(DungeonController controller) {
		this.controller = controller;
	}
	
	/**
	 * Checks if the key doesn't exist.
	 */
	public void checkKey() {
		if (key == null && controller != null) {
			controller.removeKey();
		}
	}
	
	/**
	 * Updates the goal for frontend.
	 */
	public void updateGoal() {
		if (controller != null) {
			controller.updateGoal();
		}
	}
}
