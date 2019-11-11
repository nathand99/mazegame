package unsw.dungeon.Application;

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
	
	@FXML
	public void initalize() {
		
	}
	
	@FXML
	public void tutorialPage() {
		
	}
	
	public void quitGame() {
		Stage stage = (Stage) quitButton.getScene().getWindow();
	    stage.close();
	}
}
