package UI.GUI.GUILoader;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SFXSliderListener implements ChangeListener<Number> {
    private Controller controller;
    public SFXSliderListener(Controller controller) {
        this.controller = controller;
    }
    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
        this.controller.sfxSliderChange();
    }
}
