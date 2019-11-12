package unsw.dungeon.Application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * A controller for the Menu.
 * 
 *
 */
public class MenuController {
	@FXML
	private GridPane levelGrid;
	@FXML
	private Button helpButton;
	@FXML
	private Button quitButton;
	
	private DungeonScreen dungeonScreen;
	
	@FXML
	public void initalize() {
		
	}
	
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}
	
	@FXML
	public void tutorialPage() throws IOException {
		dungeonScreen.start();
	}
	
	public void quitGame() {
		Stage stage = (Stage) quitButton.getScene().getWindow();
	    stage.close();
	}
}
