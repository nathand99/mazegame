package unsw.dungeon.Application;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import unsw.dungeon.Entity;
import unsw.dungeon.GoalReader;
import unsw.dungeon.PlayerGoal;
import unsw.dungeon.Entities.*;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

	
	// this stuff comes first in the loading sequence.
    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    
    private boolean registered = false;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

    }
    
    /**
     * readGoal - runs the GoalReader
     * @param map
     * @return
     * @throws FileNotFoundException
     */
    // move to dungeonApplication maybe.
    public GoalReader readGoal(String map) throws FileNotFoundException {
    	return new GoalReader(new PlayerGoal(), dungeon.getPlayer(), map, dungeon.getEntities());
    } 

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            register();
            break;
        case DOWN:
            player.moveDown();
            register();
            break;
        case LEFT:
            player.moveLeft();
            register();
            break;
        case RIGHT:
            player.moveRight();
            register();
            break;
        case W:
        	player.attackW();
        case A:
        	player.attackA();
        case S:
        	player.attackS();
        case D:
        	player.attackD();
        default:
            break;
        }
        
        if(!player.isNormalState()) {
        	DropShadow ds= new DropShadow();
			ds.setRadius(17.0);
			ds.setColor(Color.color(0, 0.3, 1));
			player.getEntityView().setEffect(ds);
        }else {
        	player.getEntityView().setEffect(null);
        }
        
    }
    
    public void register() {
    	if (registered == false) {
    		dungeon.registerAll();
    		registered = true;
    	}
    }


}

