package unsw.dungeon.Application;

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
public class MenuApplication extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
	    primaryStage.setTitle("Game Menu");

	    FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));

	    Parent root = loader.load();
	    Scene scene = new Scene(root);

	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

	public static void main(String[] args) {
	    launch(args);
	}
}
