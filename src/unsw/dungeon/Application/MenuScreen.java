package unsw.dungeon.Application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * An Application for the Menu
 * @author z5207990
 *
 */

public class MenuScreen {
	
	private Stage stage;
	private Scene scene;
	private MenuController controller;
	
	public MenuScreen(Stage primaryStage) throws IOException {
		this.stage = primaryStage;
	    //stage.setTitle("Game Menu");
	    
	    controller = new MenuController();
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
	    loader.setController(controller);

	    Parent root = loader.load();
	    this.scene = new Scene(root);

	    //stage.setScene(scene);
	    //stage.show();
	}
	
	public void start() {
		stage.setTitle("Game Menu");
		stage.setScene(scene);
		stage.show();
		
	}
	
	public MenuController getController() {
		return this.controller;
	}
	
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		controller.setDungeonScreen(dungeonScreen);
	}

}
