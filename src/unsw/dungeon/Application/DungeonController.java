package unsw.dungeon.Application;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
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
    
    @FXML 
    private ToolBar toolbar;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");
        squares.setLayoutX(0);
        //squares.setLayoutY(toolbar.getHeight()+1);
        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
    	
        //int y = dungeon.getHeight() + 1;
        //toolbar.setMaxHeight(dungeon.getHeight());
        //toolbar.setMaxWidth(dungeon.getWidth());
		/*
		 * ToolBar toolbar = new ToolBar(); squares.add(toolbar, 0, y); Button button1 =
		 * new Button("Quit"); button1.setMaxWidth(0.5); button1.setMaxHeight(0.5);
		 * 
		 * toolbar.getItems().add(button1);
		 */     
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
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
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
        
        //sword
        if(player.getSword()!= null && !player.getSword().isShow() && player.getX() == player.getSword().getX() && player.getY() == player.getSword().getY()) {
      	  for (ImageView entity : initialEntities) {
      		  if (entity.equals(player.getSword().getEntityView())) {
      			  entity.setVisible(false);
      		  }
            }
        }
        
        /*
        //treasure
        if(player.getSword()!= null && !player.getSword().isShow() && player.getX() == player.getSword().getX() && player.getY() == player.getSword().getY()) {
        	  for (ImageView entity : initialEntities) {
        		  if (entity.equals(player.getSword().getEntityView())) {
        			  entity.setVisible(false);
        		  }
              }
          }
        */
        //key
        if(player.getKey()!= null && !player.getKey().isShow() && player.getX() == player.getKey().getX() && player.getY() == player.getKey().getY()) {
        	  for (ImageView entity : initialEntities) {
        		  if (entity.equals(player.getKey().getEntityView())) {
        			  entity.setVisible(false);
        		  }
              }
          }
        
       
        /*
        //potion 
        if(player.getSword()!= null && !player.getSword().isShow() && player.getX() == player.getSword().getX() && player.getY() == player.getSword().getY()) {
        	  for (ImageView entity : initialEntities) {
        		  if (entity.equals(player.getSword().getEntityView())) {
        			  entity.setVisible(false);
        		  }
              }
          }
          */
    }

}

