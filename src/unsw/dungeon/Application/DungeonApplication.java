package unsw.dungeon.Application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unsw.dungeon.GoalReader;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Dungeon");
        String map = "dungeons/maze.json"; // need to add file string and the .json
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(map);
        DungeonController controller = dungeonLoader.loadController();
        
        // new
        GoalReader goal = controller.readGoal(map);
        goal.loadGoal();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
