package com.example.biorreactor.Controllers.Configuration;

import com.example.biorreactor.Models.Biorreactor;
import com.example.biorreactor.Models.Calibration;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class CalibrationController implements Initializable {

    public TextField setZeroPh_textField; // usuario entra valor ph conocido para ajustar el punto cero de la sonda
    public TextField setSpanPh_textField;  // usuario entra valor ph conocido para ajustar el rango de la medicion de la sonda
    public Button setZeroPh_btn; // al hundirlo cambia el valor del zero
    public Button setSpanPh_btn; // al hundirolo cambia el valor del span
    public TextField setZeroDO_textField;
    public TextField setSpanDO_textField;
    public Button setZeroDO_btn;
    public Button setSpanDO_btn;
    public Label currentPh_label; // valor actual
    public Label rawPh_label; // valor bruto
    public Label currentDO_label;
    public Label rawDO_label;

    Biorreactor biorreactor = Biorreactor.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DoubleProperty currentPhValue = new SimpleDoubleProperty(biorreactor.getCalibrations().get(0).getCurrentValue());
        currentPh_label.textProperty().bind(currentPhValue.asString());

        biorreactor.getCalibrations().get(0).currentValueProperty().addListener((observable, oldValue, newValue) -> {
            currentPhValue.set(newValue.doubleValue());
        });

        DoubleProperty rawPhValue = new SimpleDoubleProperty(biorreactor.getCalibrations().get(0).getRawValue());
        rawPh_label.textProperty().bind(rawPhValue.asString());

        biorreactor.getCalibrations().get(0).rawValueProperty().addListener((observable, oldValue, newValue) -> {
            rawPhValue.set(newValue.doubleValue());
        });

        DoubleProperty currentDOValue = new SimpleDoubleProperty(biorreactor.getCalibrations().get(1).getCurrentValue());
        currentDO_label.textProperty().bind(currentDOValue.asString());

        biorreactor.getCalibrations().get(1).currentValueProperty().addListener((observable, oldValue, newValue) -> {
            currentDOValue.set(newValue.doubleValue());
        });

        DoubleProperty rawDOValue = new SimpleDoubleProperty(biorreactor.getCalibrations().get(1).getRawValue());
        rawDO_label.textProperty().bind(rawDOValue.asString());

        biorreactor.getCalibrations().get(1).rawValueProperty().addListener((observable, oldValue, newValue) -> {
            rawDOValue.set(newValue.doubleValue());
        });

        setSpanPh_btn.setOnAction(event -> onSetSpan(setSpanPh_textField, 0));
        setZeroPh_btn.setOnAction(event -> onSetZero(setZeroPh_textField, 0));
        setSpanDO_btn.setOnAction(event -> onSetSpan(setSpanDO_textField, 1));
        setZeroDO_btn.setOnAction(event -> onSetZero(setZeroDO_textField, 1));
    }

    private void onSetSpan(TextField spanTextField, int i) {
        DoubleProperty spanValue = new SimpleDoubleProperty(0);
        String newValue = spanTextField.getText().trim();
            if (isValidDouble(newValue)) {
                spanValue.set((Double.parseDouble(newValue)));
                biorreactor.getCalibrations().get(i).setSpanValue(spanValue.get());
                System.out.println( biorreactor.getCalibrations().get(i).getProbeName() + " Span Value: " +  biorreactor.getCalibrations().get(i).getSpanValue());
            } else {
             showErrorAlert();
           }
    }

    private void onSetZero(TextField spanTextField, int i) {
        DoubleProperty zeroValue = new SimpleDoubleProperty(0);
        String newValue = spanTextField.getText().trim();
        if (isValidDouble(newValue)) {
            zeroValue.set((Double.parseDouble(newValue)));
            biorreactor.getCalibrations().get(i).setZeroValue(zeroValue.get());
            System.out.println( biorreactor.getCalibrations().get(i).getProbeName() + " Zero Value: " +  biorreactor.getCalibrations().get(i).getZeroValue());
        } else {
            showErrorAlert();
        }
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
