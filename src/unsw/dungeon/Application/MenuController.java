package unsw.dungeon.Application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	private TutorialScreen tutorialScreen;
	
	/**
	 * Will create buttons for the GridPane to go to specific levels.
	 */
	@FXML
	public void initalize() {
		for (int i = 0; i < levelGrid.getRowCount(); i++) {
			for (int j = 0; j < levelGrid.getColumnCount(); j++) {
				String levelNum = new String();
				levelNum = String.valueOf(j + i*5 + 1);
				Button levelButton = new Button(levelNum);
				levelButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
				        try {
							dungeonScreen.start();
						} catch (IOException error) {
							error.printStackTrace();
						}
				    }
				});
				levelGrid.add(levelButton, j, i);
			}
		}
	}
	
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}
	
	public void setTutorialScreen(TutorialScreen tutorialScreen) {
		this.tutorialScreen = tutorialScreen;
	}
	
	/**
	 * Goes to the tutorialPage.
	 * @throws IOException
	 */
	@FXML
	public void tutorialPage() throws IOException {
		tutorialScreen.start();
	}
	
	/**
	 * Quits the application.
	 */
	public void quitGame() {
		Stage stage = (Stage) quitButton.getScene().getWindow();
	    stage.close();
	}
}
