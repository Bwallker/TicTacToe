package UI.GUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class GUIApplication extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = new File("src/main/java/UI/GUILoader.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        GameController gameController = loader.getController();
        primaryStage.setTitle("TicTacToe");

        Scene launcherScene = new Scene(root);
        primaryStage.setScene(launcherScene);
        primaryStage.show();
    }
}