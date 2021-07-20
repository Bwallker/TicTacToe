package UI.GUI.GUILoader;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SFXSliderListener implements ChangeListener<Number> {
    private LauncherController launcherController;
    public SFXSliderListener(LauncherController launcherController) {
        this.launcherController = launcherController;
    }
    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
        this.launcherController.sfxSliderChange();
    }
}
