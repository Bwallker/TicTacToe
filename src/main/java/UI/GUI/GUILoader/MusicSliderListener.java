package UI.GUI.GUILoader;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class MusicSliderListener implements ChangeListener<Number> {
    private LauncherController launcherController;
    public MusicSliderListener(LauncherController launcherController) {
        this.launcherController = launcherController;
    }
    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
        this.launcherController.musicSliderChange();
    }
}
