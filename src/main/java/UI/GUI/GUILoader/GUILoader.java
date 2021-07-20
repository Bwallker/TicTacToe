package UI.GUI.GUILoader;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.InputMethodTextRun;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUILoader extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = new File("src/main/java/UI/GUI/GUILoader/GUILoader.fxml").toURI().toURL();
        String css = new File("src/main/java/UI/GUI/GUILoader/LoaderStyle.css").toURI().toURL().toString();
        System.out.println(css);
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Controller controller = loader.getController();
        primaryStage.setTitle("TicTacToe");

        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}