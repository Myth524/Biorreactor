package com.example.biorreactor.Controllers.Configuration;

import com.example.biorreactor.Models.Biorreactor;
import com.example.biorreactor.Models.Loop;
import com.example.biorreactor.Models.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class PerGaugeController implements Initializable {
    public Label title_label;
    public TextField st_text;
    public Label pv_label;
    public Button on_btn;
    public Button off_btn;
    public Button alarmSettings_btn;
    public TextField low_text;
    public TextField high_text;
    public TextField integral_text;
    public TextField proportional_text;
    private DoubleProperty stValue;
    private DoubleProperty lowValue;
    private DoubleProperty highValue;
    private DoubleProperty integralValue;
    private DoubleProperty proportionalValue;
    private BooleanProperty controlModeProperty;
    private int pos;
    Biorreactor biorreactor = Biorreactor.getInstance();

    public void initData(Loop selectedLoop) {
        try {
            title_label.setText(String.valueOf(selectedLoop.getName()));
            st_text.setText(String.valueOf(selectedLoop.getSt()));
            pv_label.setText(String.valueOf(selectedLoop.getPv()));
            low_text.setText(String.valueOf(selectedLoop.getPid().getLowLimit()));
            high_text.setText(String.valueOf(selectedLoop.getPid().getHighLimit()));
            integral_text.setText(String.valueOf(selectedLoop.getPid().getIntegral()));
            proportional_text.setText(String.valueOf(selectedLoop.getPid().getProportional()));
            pos = biorreactor.getLoopPos(selectedLoop.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        stValue = new SimpleDoubleProperty(biorreactor.getLoops().get(pos).getSt());
        st_text.textProperty().bindBidirectional(stValue, new NumberStringConverter());

        st_text.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String newValue = st_text.getText().trim();
                if (isValidDouble(newValue)) {
                    stValue.set(Double.parseDouble(newValue));
                    biorreactor.getLoops().get(pos).setSt(stValue.get());
                    System.out.println(biorreactor.getLoops().get(pos).getName() + " st: " + biorreactor.getLoops().get(pos).getSt());
                } else {
                    showErrorAlert();
                }
            }
        });

        biorreactor.getLoops().get(pos).stProperty().addListener((observable, oldValue, newValue) -> {
            stValue.set(newValue.doubleValue());
        });

        lowValue = new SimpleDoubleProperty(biorreactor.getLoops().get(pos).getPid().getLowLimit());
        low_text.textProperty().bindBidirectional(lowValue, new NumberStringConverter());

        low_text.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String newValue = low_text.getText().trim();
                if (isValidDouble(newValue)) {
                    lowValue.set(Double.parseDouble(newValue));
                    biorreactor.getLoops().get(pos).getPid().setLowLimit(lowValue.get());
                    System.out.println(biorreactor.getLoops().get(pos).getName() + " Low Limit: " + biorreactor.getLoops().get(pos).getPid().getLowLimit());
                } else {
                    showErrorAlert();
                }
            }
        });

        biorreactor.getLoops().get(pos).getPid().lowLimitProperty().addListener((observable, oldValue, newValue) -> {
            lowValue.set(newValue.doubleValue());
        });

        highValue = new SimpleDoubleProperty(biorreactor.getLoops().get(pos).getPid().getHighLimit());
        high_text.textProperty().bindBidirectional(highValue, new NumberStringConverter());

        high_text.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String newValue = high_text.getText().trim();
                if (isValidDouble(newValue)) {
                    highValue.set(Double.parseDouble(newValue));
                    biorreactor.getLoops().get(pos).getPid().setHighLimit(highValue.get());
                    System.out.println(biorreactor.getLoops().get(pos).getName() + " High Limit: " + biorreactor.getLoops().get(pos).getPid().getHighLimit());
                } else {
                    showErrorAlert();
                }
            }
        });

        biorreactor.getLoops().get(pos).getPid().highLimitProperty().addListener((observable, oldValue, newValue) -> {
            highValue.set(newValue.doubleValue());
        });

        integralValue = new SimpleDoubleProperty(biorreactor.getLoops().get(pos).getPid().getIntegral());
        integral_text.textProperty().bindBidirectional(integralValue, new NumberStringConverter());

        integral_text.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String newValue = integral_text.getText().trim();
                if (isValidDouble(newValue)) {
                    integralValue.set(Double.parseDouble(newValue));
                    biorreactor.getLoops().get(pos).getPid().setIntegral(integralValue.get());
                    System.out.println(biorreactor.getLoops().get(pos).getName() + " Integral: " + biorreactor.getLoops().get(pos).getPid().getIntegral());
                } else {
                    showErrorAlert();
                }
            }
        });

        biorreactor.getLoops().get(pos).getPid().integralProperty().addListener((observable, oldValue, newValue) -> {
            integralValue.set(newValue.doubleValue());
        });

        proportionalValue = new SimpleDoubleProperty(biorreactor.getLoops().get(pos).getPid().getProportional());
        proportional_text.textProperty().bindBidirectional(proportionalValue, new NumberStringConverter());

        proportional_text.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String newValue = proportional_text.getText().trim();
                if (isValidDouble(newValue)) {
                    proportionalValue.set(Double.parseDouble(newValue));
                    biorreactor.getLoops().get(pos).getPid().setProportional(proportionalValue.get());
                    System.out.println(biorreactor.getLoops().get(pos).getName() + " Proportional: " + biorreactor.getLoops().get(pos).getPid().getProportional());
                } else {
                    showErrorAlert();
                }
            }
        });

        biorreactor.getLoops().get(pos).getPid().proportionalProperty().addListener((observable, oldValue, newValue) -> {
            proportionalValue.set(newValue.doubleValue());
        });

        controlModeProperty = new SimpleBooleanProperty(biorreactor.getLoops().get(pos).isControlMode());

        biorreactor.getLoops().get(pos).controlModeProperty().addListener((observable, oldValue, newValue) -> {
            controlModeProperty.set(newValue);
        });

        on_btn.setOnAction(event -> {
            controlModeProperty.set(true);
            biorreactor.getLoops().get(pos).setControlMode(controlModeProperty.get());
            System.out.println(biorreactor.getLoops().get(pos).getName() + "Control mode: " + biorreactor.getLoops().get(pos).isControlMode());

            if (on_btn.getStyleClass().contains("alt2_ToggleButton_container")) {
                return;
            }

            if (on_btn.getStyleClass().contains("alt_ToggleButton_container")) {
                on_btn.getStyleClass().remove("alt_ToggleButton_container");
                on_btn.getStyleClass().add("alt2_ToggleButton_container");
            } else {
                on_btn.getStyleClass().remove("alt2_ToggleButton_container");
                on_btn.getStyleClass().add("alt_ToggleButton_container");
            }

            // Cambiar el estilo de off_btn
            if (off_btn.getStyleClass().contains("alt_ToggleButton_container")) {
                off_btn.getStyleClass().remove("alt_ToggleButton_container");
                off_btn.getStyleClass().add("alt2_ToggleButton_container");
            } else {
                off_btn.getStyleClass().remove("alt2_ToggleButton_container");
                off_btn.getStyleClass().add("alt_ToggleButton_container");
            }
        });

        off_btn.setOnAction(event -> {
            controlModeProperty.set(false);
            biorreactor.getLoops().get(pos).setControlMode(controlModeProperty.get());
            System.out.println(biorreactor.getLoops().get(pos).getName() + " Control mode: " + biorreactor.getLoops().get(pos).isControlMode());

            if (off_btn.getStyleClass().contains("alt2_ToggleButton_container")) {
                return;
            }

            if (off_btn.getStyleClass().contains("alt_ToggleButton_container")) {
                off_btn.getStyleClass().remove("alt_ToggleButton_container");
                off_btn.getStyleClass().add("alt2_ToggleButton_container");
            } else {
                off_btn.getStyleClass().remove("alt2_ToggleButton_container");
                off_btn.getStyleClass().add("alt_ToggleButton_container");
            }

            // Cambiar el estilo de on_btn
            if (on_btn.getStyleClass().contains("alt_ToggleButton_container")) {
                on_btn.getStyleClass().remove("alt_ToggleButton_container");
                on_btn.getStyleClass().add("alt2_ToggleButton_container");
            } else {
                on_btn.getStyleClass().remove("alt2_ToggleButton_container");
                on_btn.getStyleClass().add("alt_ToggleButton_container");
            }
        });

        alarmSettings_btn.setOnAction(event -> onAlarms(biorreactor.getLoops().get(pos)));

    }

    // Verificacion high

    // Agregar el cambio de estilo al boton control

    private void onAlarms(Loop selectedLoop){
        ViewModel.getInstance().getViewFactory().showAlarmSettingsWindow(selectedLoop);
    }

    private boolean isValidDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Ingrese un dato válido");
        alert.showAndWait();
    }
}
