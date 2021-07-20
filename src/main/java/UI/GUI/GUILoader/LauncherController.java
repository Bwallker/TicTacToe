package UI.GUI.GUILoader;

import UI.GUI.GameController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class LauncherController implements Initializable {

    private GUILoader loader;


    @FXML
    private ChoiceBox<AspectRatio> aspectRatios;

    @FXML
    private ChoiceBox<Resolution> resolutions;

    @FXML
    private CheckBox fullscreen;

    @FXML
    private Label resolutionsText;

    @FXML
    private Label aspectRatiosText;

    @FXML
    private Label widthError;

    @FXML
    private Label heightError;

    @FXML
    private Button startGame;

    @FXML
    private TextField widthField;

    @FXML
    private TextField heightField;

    @FXML
    private CheckBox musicCheckbox;

    @FXML
    private Slider musicSlider;

    @FXML
    private CheckBox sfxCheckbox;

    @FXML
    private Slider sfxSlider;

    @FXML
    private Label customResolutionLabel;

    @FXML
    private Label customWidthLabel;

    @FXML
    private Label customHeightLabel;

    @FXML
    private Label musicSliderPercentage;

    @FXML
    private Label sfxSliderPercentage;

    @FXML
    private Label startGameError;

    private ObservableList<AspectRatio> validAspectRatios = FXCollections.observableArrayList();


    private Map<AspectRatio, List<Resolution>> aspectRatiosWithResolutions = new HashMap<>();
    private boolean validCustomWidth;
    private boolean validCustomHeight;


    public void resolutionChange(ActionEvent event) {
        this.toggleReadyToStart(true);
    }

    public void aspectRatioChange(ActionEvent event) {
        if (this.aspectRatios.isDisable()) return;
        this.widthField.clear();
        this.heightField.clear();
        this.widthError.setVisible(false);
        this.heightError.setVisible(false);
        AspectRatio currentValue = this.aspectRatios.getValue();
        List<Resolution> resolutionsForAspectRatio = this.aspectRatiosWithResolutions.get(currentValue);
        this.resolutions.setDisable(false);
        this.resolutionsText.setDisable(false);
        this.resolutions.getItems().clear();
        this.resolutions.getItems().addAll(resolutionsForAspectRatio);
        this.toggleReadyToStart(false);
    }

    public void fullScreenChange(ActionEvent event) {
        final boolean fullscreenSelected = this.fullscreen.isSelected();
        this.aspectRatios.setDisable(fullscreenSelected);
        this.aspectRatiosText.setDisable(fullscreenSelected);
        this.widthError.setVisible(false);
        this.heightError.setVisible(false);
        this.widthField.clear();
        this.heightField.clear();
        this.widthField.setDisable(fullscreenSelected);
        this.heightField.setDisable(fullscreenSelected);
        this.customWidthLabel.setDisable(fullscreenSelected);
        this.customHeightLabel.setDisable(fullscreenSelected);
        this.customResolutionLabel.setDisable(fullscreenSelected);
        this.validCustomWidth = false;
        this.validCustomHeight = false;
        if (fullscreenSelected) {
            this.aspectRatios.getItems().clear();
            this.resolutions.getItems().clear();
            this.resolutions.setDisable(true);
            this.resolutionsText.setDisable(true);
            this.toggleReadyToStart(true);
        } else {
            this.aspectRatios.getItems().addAll(this.validAspectRatios);
            this.toggleReadyToStart(false);
        }
    }

    private boolean widthOrHeightChange(String text, Label errorLabel, boolean otherIsValid) {
        //The return value represents whether this.validCustomWidth or this.validCustomHeight should be true or false after the event

        this.resolutions.setDisable(true);
        this.aspectRatios.setDisable(true);
        this.resolutions.getItems().clear();
        this.aspectRatios.getItems().clear();
        this.aspectRatios.getItems().addAll(this.validAspectRatios);
        this.aspectRatios.setDisable(false);


        if(text.equals("")) {
            errorLabel.setVisible(false);
            this.toggleReadyToStart(false);
            return false;
        }

        try {
            int number = Integer.parseInt(text);
            if (number <= 0) throw new NumberFormatException();

            errorLabel.setVisible(false);
            this.toggleReadyToStart(otherIsValid);
            return true;
        } catch (NumberFormatException e) {
            errorLabel.setVisible(true);
            this.toggleReadyToStart(false);
            return false;
        }
    }

    public void toggleReadyToStart(boolean ready) {
        this.startGame.setDisable(!ready);
    }

    public void widthChange(KeyEvent event) {
        this.validCustomWidth = this.widthOrHeightChange(this.widthField.getText(), this.widthError, this.validCustomHeight);
    }

    public void heightChange(KeyEvent event) {
        this.validCustomHeight = this.widthOrHeightChange(this.heightField.getText(), this.heightError, this.validCustomWidth);
    }

    private String sliderValueToPercentage(double value) {
        if (value == 1) {
            return "100%";
        }
        value *= 100;
        String asString = Double.toString(value);
        if (value < 10) {
            asString = asString.substring(0, 1);
        } else {
            asString = asString.substring(0, 2);
        }
        return asString + "%";
    }

    public void musicSliderChange() {
        this.musicSliderPercentage.setText(this.sliderValueToPercentage(this.musicSlider.getValue()));
    }

    public void sfxSliderChange() {
        this.sfxSliderPercentage.setText(this.sliderValueToPercentage(this.sfxSlider.getValue()));
    }

    public void sfxCheckboxChange(ActionEvent event) {
        final boolean isSelected = this.sfxCheckbox.isSelected();
        this.sfxSlider.setDisable(!isSelected);
    }

    public void musicCheckboxChange(ActionEvent event) {
        final boolean isSelected = this.musicCheckbox.isSelected();
        this.musicSlider.setDisable(!isSelected);
    }

    public void setLoader(GUILoader loader) {
        this.loader = loader;
    }

    public void startGameClicked(ActionEvent event) {
        System.out.println("Start game button clicked!");
        //Lock everything so it doesn't get changed while we are starting the game, since that could be bad
        this.musicSlider.setDisable(true);
        this.musicCheckbox.setDisable(true);
        this.musicSliderPercentage.setDisable(true);

        this.sfxSlider.setDisable(true);
        this.sfxCheckbox.setDisable(true);
        this.sfxSliderPercentage.setDisable(true);

        this.resolutions.setDisable(true);
        this.aspectRatios.setDisable(true);
        this.resolutionsText.setDisable(true);
        this.aspectRatiosText.setDisable(true);

        this.fullscreen.setDisable(true);

        this.customResolutionLabel.setDisable(true);
        this.customHeightLabel.setDisable(true);
        this.customWidthLabel.setDisable(true);

        this.heightError.setDisable(true);
        this.heightField.setDisable(true);
        this.widthError.setDisable(true);
        this.widthField.setDisable(true);

        this.toggleReadyToStart(false);

        double musicVolume = this.musicSlider.getValue();
        double sfxVolume = this.sfxSlider.getValue();
        if (!this.musicCheckbox.isSelected()) musicVolume = 0;
        if (!this.sfxCheckbox.isSelected()) sfxVolume = 0;


        boolean fullscreen = this.fullscreen.isSelected();
        Resolution resolution;

        if (fullscreen) {
            resolution = null;
        } else {
            resolution = this.setResolution();
        }

        try {
            Stage primaryStage = this.loader.getPrimaryStage();
            URL url = new File("src/main/resources/Game.fxml").toURI().toURL();
            String css = new File("src/main/resources/GameStyle.css").toURI().toURL().toString();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            GameController gameController = loader.getController();
            gameController.setSettings(fullscreen, resolution, musicVolume, sfxVolume);
            Scene mainMenu;
            if (fullscreen) {
                mainMenu = new Scene(root);

            } else {
                mainMenu = new Scene(root, resolution.width(), resolution.height());
            }
            mainMenu.getStylesheets().add(css);
            primaryStage.resizableProperty().setValue(true);
            primaryStage.setScene(mainMenu);
            primaryStage.setFullScreen(fullscreen);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e);
        }





    }



    private Resolution setResolution() {

        if(!this.resolutions.getItems().isEmpty()) {
            return this.resolutions.getValue();
        }
        String width = this.widthField.getText();
        String height = this.heightField.getText();

        return new Resolution(Integer.parseInt(width), Integer.parseInt(height));
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.startGameError.setVisible(false);

        this.toggleReadyToStart(false);
        this.musicCheckbox.setSelected(true);
        this.sfxCheckbox.setSelected(true);

        this.widthError.setVisible(false);
        this.heightError.setVisible(false);

        this.musicSlider.setMax(1);
        this.musicSlider.setMin(0);
        this.musicSlider.setValue(0.5);

        this.sfxSlider.setMax(1);
        this.sfxSlider.setMin(0);
        this.sfxSlider.setValue(0.5);


        this.resolutions.getItems().clear();
        this.resolutions.setDisable(true);
        this.resolutions.setOnAction(this::resolutionChange);
        this.resolutionsText.setDisable(true);

        this.aspectRatios.getItems().clear();


        AspectRatio fourByThree = new AspectRatio(4, 3);
        List<Resolution> fourByThreeResolutions = new ArrayList<>();

        this.addResolutions(fourByThreeResolutions,
                640, 480,
                800, 600,
                960, 720,
                1024, 768,
                1280, 960,
                1400, 1050,
                1440, 1080,
                1600, 1200,
                1856, 1392,
                1920, 1440,
                2048, 1536
        );
        this.aspectRatiosWithResolutions.put(fourByThree, fourByThreeResolutions);
        this.validAspectRatios.add(fourByThree);



        AspectRatio sixteenByNine = new AspectRatio(16, 9);
        List<Resolution> sixteenByNineResolutions = new ArrayList<>();
        this.addResolutions(sixteenByNineResolutions,
                960, 540,
                1280, 720,
                1366, 768,
                1600, 900,
                1920, 1080,
                2560, 1440,
                3200, 1800,
                3840, 2160,
                5120, 2880,
                7680, 4320,
                15360, 8640
        );

        this.aspectRatiosWithResolutions.put(sixteenByNine, sixteenByNineResolutions);
        this.validAspectRatios.add(sixteenByNine);


        AspectRatio sixteenByTen = new AspectRatio(16, 10);
        List<Resolution> sixteenByTenResolutions = new ArrayList<>();
        this.addResolutions(sixteenByTenResolutions,
                640, 400,
                960, 600,
                1280, 800,
                1440, 900,
                1680, 1050,
                1920, 1200,
                2560, 1600,
                3840, 2400
        );

        this.aspectRatiosWithResolutions.put(sixteenByTen, sixteenByTenResolutions);
        this.validAspectRatios.add(sixteenByTen);


        AspectRatio twentyOneByNine = new AspectRatio(21, 9);
        List<Resolution> twentyOneByNineResolutions = new ArrayList<>();
        this.addResolutions(twentyOneByNineResolutions,
                2560, 1080,
                3440, 1440,
                3840, 1600,
                5120, 2160,
                10240, 4320
        );

        this.aspectRatiosWithResolutions.put(twentyOneByNine, twentyOneByNineResolutions);
        this.validAspectRatios.add(twentyOneByNine);

        AspectRatio thirtyTwoByNine = new AspectRatio(32, 9);
        List<Resolution> thirtyTwoByNineResolutions = new ArrayList<>();
        this.addResolutions(thirtyTwoByNineResolutions,
                2560, 720,
                3840, 1080,
                5120, 1440,
                7680, 2160,
                15360, 4320
        );

        this.aspectRatiosWithResolutions.put(thirtyTwoByNine, thirtyTwoByNineResolutions);
        this.validAspectRatios.add(thirtyTwoByNine);

        this.aspectRatios.getItems().clear();
        this.aspectRatios.getItems().addAll(this.validAspectRatios);


        this.aspectRatios.setOnAction(this::aspectRatioChange);
        this.fullscreen.setDisable(false);
        this.fullscreen.setOnAction(this::fullScreenChange);


        this.widthField.setOnKeyTyped(this::widthChange);
        this.heightField.setOnKeyTyped(this::heightChange);

        this.musicCheckbox.setOnAction(this::musicCheckboxChange);
        this.musicSlider.valueProperty().addListener(new MusicSliderListener(this));

        this.sfxCheckbox.setOnAction(this::sfxCheckboxChange);
        this.sfxSlider.valueProperty().addListener(new SFXSliderListener(this));

        this.startGame.setOnAction(this::startGameClicked);

    }

    private void addResolutions(List<Resolution> list, int... items) {
        for (int i = 0; i < items.length; i++) {
            if (i % 2 == 0) {
                list.add(new Resolution(items[i], items[i+1]));
            }
        }

    }
}
