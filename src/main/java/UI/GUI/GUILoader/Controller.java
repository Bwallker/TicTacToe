package UI.GUI.GUILoader;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.*;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    public static ComboBox<Resolution> resultions;
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

    private ObservableList<AspectRatio> validAspectRatios = FXCollections.observableArrayList();


    private Map<AspectRatio, List<Resolution>> aspectRatiosWithResolutions = new HashMap<>();

    private boolean validCustomWidth;
    private boolean validCustomHeight;

    public void resolutionChange(ActionEvent event) {
        this.startGame.setDisable(false);
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
        this.resolutions.getItems().removeAll(this.resolutions.getItems());
        this.resolutions.getItems().addAll(resolutionsForAspectRatio);
        this.startGame.setDisable(true);
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
            this.startGame.setDisable(false);
        } else {
            this.aspectRatios.getItems().addAll(this.validAspectRatios);
            this.startGame.setDisable(true);
        }
    }

    public void widthChange(KeyEvent event) {
        String text = this.widthField.getText();
        this.resolutions.setDisable(true);
        this.aspectRatios.setDisable(true);
        this.resolutions.getItems().clear();
        this.aspectRatios.getItems().clear();
        this.aspectRatios.getItems().addAll(this.validAspectRatios);
        this.aspectRatios.setDisable(false);
        if (text.equals("")) {
            this.widthError.setVisible(false);
            this.validCustomWidth = false;
            this.startGame.setDisable(true);
            return;
        }
        try {
            int number = Integer.parseInt(text);
            if (number < 1) throw new NumberFormatException();
            this.widthError.setVisible(false);
            this.validCustomWidth = true;
            this.startGame.setDisable(!this.validCustomHeight);
        } catch (NumberFormatException e) {
            this.widthError.setVisible(true);
            this.validCustomWidth = false;
            this.startGame.setDisable(true);
        }
    }

    public void heightChange(KeyEvent event) {
        String text = this.heightField.getText();
        if (text.equals("")) {
            this.heightError.setVisible(false);
            this.validCustomHeight = false;
            this.startGame.setDisable(true);
            return;
        }
        try {
            int number = Integer.parseInt(text);
            if (number < 1) throw new NumberFormatException();
            this.heightError.setVisible(false);
            this.validCustomHeight = true;
            this.startGame.setDisable(!this.validCustomWidth);
        } catch (NumberFormatException e) {
            this.heightError.setVisible(true);
            this.validCustomHeight = false;
            this.startGame.setDisable(true);
        }
    }

    public void musicSliderChange() {
        double value = this.musicSlider.getValue();
        if (value == 1) {
            this.musicSliderPercentage.setText("100%");
            return;
        }
        if (value == 0) {
            this.musicSliderPercentage.setText("0%");
            return;
        }
        value *= 100;
        String asString = Double.toString(value);
        if (value < 10) {
            asString = asString.substring(0, 1);
        } else {
            asString = asString.substring(0, 2);
        }
        this.musicSliderPercentage.setText(asString + "%");
    }

    public void sfxSliderChange() {
        double value = this.sfxSlider.getValue();
        if (value == 1) {
            this.sfxSliderPercentage.setText("100%");
            return;
        }
        if (value == 0) {
            this.sfxSliderPercentage.setText("0%");
            return;
        }
        value *= 100;
        String asString = Double.toString(value);
        if (value < 10) {
            asString = asString.substring(0, 1);
        } else {
            asString = asString.substring(0, 2);
        }
        this.sfxSliderPercentage.setText(asString + "%");
    }

    public void sfxCheckboxChange(ActionEvent event) {
        final boolean isSelected = this.sfxCheckbox.isSelected();
        this.sfxSlider.setDisable(!isSelected);
        this.sfxSlider.setValue(0);
        this.sfxSliderPercentage.setText("0%");
    }

    public void musicCheckboxChange(ActionEvent event) {
        final boolean isSelected = this.musicCheckbox.isSelected();
        this.musicSlider.setDisable(!isSelected);
        this.musicSlider.setValue(0);
        this.musicSliderPercentage.setText("0%");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.startGame.setDisable(true);
        this.musicCheckbox.setSelected(true);
        this.sfxCheckbox.setSelected(true);

        this.widthError.setVisible(false);
        this.heightError.setVisible(false);

        this.musicSlider.setMax(1);
        this.musicSlider.setMin(0);
        this.musicSlider.setValue(0);

        this.sfxSlider.setMax(1);
        this.sfxSlider.setMin(0);
        this.sfxSlider.setValue(0);


        this.resolutions.getItems().removeAll(resolutions.getItems());
        this.resolutions.setDisable(true);
        this.resolutions.setOnAction(this::resolutionChange);
        this.resolutionsText.setDisable(true);
        int validAspectRatiosSize = this.validAspectRatios.size();
        while (validAspectRatiosSize > 0) {
            this.validAspectRatios.remove(0);
            validAspectRatiosSize--;
        }


        AspectRatio fourByThree = new AspectRatio(4, 3);
        List<Resolution> fourByThreeResolutions = new ArrayList<>();

        this.addResolutions(fourByThreeResolutions,
                640, 480,
                800, 600,
                960, 720,
                1024, 768,
                1280, 960,
                1400, 1050,
                1440, 1080 ,
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

        this.aspectRatios.getItems().removeAll(this.aspectRatios.getItems());
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

    }

    private void addResolutions(List<Resolution> list, int... items) {
        for (int i = 0; i < items.length; i++) {
            if (i % 2 == 0) {
                list.add(new Resolution(items[i], items[i+1]));
            }
        }

    }
}
