package UI.GUI.GUILoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class GUILoader extends Application{
    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        URL url = new File("src/main/resources/GUILoader.fxml").toURI().toURL();
        String css = new File("src/main/resources/LoaderStyle.css").toURI().toURL().toString();

        Image icon = new Image(new File("src/main/resources/TicTacToe.png").toURI().toURL().toString());
        primaryStage.getIcons().add(icon);
        System.out.println(css);
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        LauncherController launcherController = loader.getController();
        launcherController.setLoader(this);
        primaryStage.setTitle("TicTacToe");

        Scene scene = new Scene(root);
        primaryStage.resizableProperty().setValue(false);
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

}