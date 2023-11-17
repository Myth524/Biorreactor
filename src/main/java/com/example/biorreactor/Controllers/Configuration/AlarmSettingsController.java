package com.example.biorreactor.Controllers.Configuration;

import com.example.biorreactor.Models.Alarm;
import com.example.biorreactor.Models.Biorreactor;
import com.example.biorreactor.Models.Loop;
import com.example.biorreactor.Models.Pump;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class AlarmSettingsController implements Initializable {
    public Text selectedLoop_text;
    public Label st_label;
    public Label pv_label;
    public CheckBox absAu_checkbox;
    public CheckBox absEn_checkbox;
    public TextField absLow_text;
    public TextField absHigh_text;
    public TextField devLow_text;
    public TextField devHigh_text;
    public CheckBox devEn_checkbox;
    public CheckBox devAu_checkbox;
    public CheckBox temp_checkbox;
    public CheckBox ph_checkbox;
    public CheckBox do_checkbox;
    public CheckBox stirring_checkbox;
    public CheckBox pump1_checkbox;
    public CheckBox pump3_checkbox;
    public CheckBox pump2_checkbox;
    Biorreactor biorreactor = Biorreactor.getInstance();
    private int pos;
    private  Loop loop;
    private int posA;
    private DoubleProperty absLowValue;
    private DoubleProperty absHighValue;
    private DoubleProperty devLowValue;
    private DoubleProperty devHighValue;
    private BooleanProperty absEnValue;
    private BooleanProperty devEnValue;
    private BooleanProperty absAuValue;
    private BooleanProperty devAuValue;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(loop == null){
            loop = biorreactor.getLoops().get(pos);
            posA = loop.getAlarms().size()-1;
        }

        if(loop.getAlarms().get(posA).isOn() && !loop.getAlarms().get(posA).isActive() && loop.getAlarms().get(posA).isTriggered()){
            loop.getAlarms().add(new Alarm());
            posA++;
        }

        absLowValue = new SimpleDoubleProperty(biorreactor.getLoops().get(pos).getSt());
        absLow_text.textProperty().bindBidirectional(absLowValue, new NumberStringConverter());

        absLow_text.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String newValue = absLow_text.getText().trim();
                if (isValidDouble(newValue)) {
                    absLowValue.set(Double.parseDouble(newValue));
                    biorreactor.getLoops().get(pos).getAlarms().get(posA).setAbsLow(absLowValue.get());
                    System.out.println(biorreactor.getLoops().get(pos).getName() + " abs low: " + biorreactor.getLoops().get(pos).getAlarms().get(posA).getAbsLow());
                } else {
                    showErrorAlert();
                }
            }
        });

        biorreactor.getLoops().get(pos).getAlarms().get(posA).absLowProperty().addListener((observable, oldValue, newValue) -> {
            absLowValue.set(newValue.doubleValue());
            loop.getAlarms().get(posA).setActive(true);
            loop.getAlarms().get(posA).setOn(true);
        });

        absHighValue = new SimpleDoubleProperty(biorreactor.getLoops().get(pos).getSt());
        absHigh_text.textProperty().bindBidirectional(absHighValue, new NumberStringConverter());

        absHigh_text.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String newValue = absHigh_text.getText().trim();
                if (isValidDouble(newValue)) {
                    absHighValue.set(Double.parseDouble(newValue));
                    biorreactor.getLoops().get(pos).getAlarms().get(posA).setAbsHigh(absHighValue.get());
                    System.out.println(biorreactor.getLoops().get(pos).getName() + " abs high: " + biorreactor.getLoops().get(pos).getAlarms().get(posA).getAbsHigh());
                } else {
                    showErrorAlert();
                }
            }
        });

        biorreactor.getLoops().get(pos).getAlarms().get(posA).absHighProperty().addListener((observable, oldValue, newValue) -> {
            absHighValue.set(newValue.doubleValue());
            loop.getAlarms().get(posA).setActive(true);
            loop.getAlarms().get(posA).setOn(true);
        });

        devLowValue = new SimpleDoubleProperty(biorreactor.getLoops().get(pos).getSt());
        devLow_text.textProperty().bindBidirectional(devLowValue, new NumberStringConverter());

        devLow_text.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String newValue = devLow_text.getText().trim();
                if (isValidDouble(newValue)) {
                    devLowValue.set(Double.parseDouble(newValue));
                    biorreactor.getLoops().get(pos).getAlarms().get(posA).setDevLow(devLowValue.get());
                    System.out.println(biorreactor.getLoops().get(pos).getName() + " dev low: " + biorreactor.getLoops().get(pos).getAlarms().get(posA).getDevLow());
                } else {
                    showErrorAlert();
                }
            }
        });

        biorreactor.getLoops().get(pos).getAlarms().get(posA).devLowProperty().addListener((observable, oldValue, newValue) -> {
            devLowValue.set(newValue.doubleValue());
            loop.getAlarms().get(posA).setActive(true);
            loop.getAlarms().get(posA).setOn(true);
        });

        devHighValue = new SimpleDoubleProperty(biorreactor.getLoops().get(pos).getSt());
        devHigh_text.textProperty().bindBidirectional(devHighValue, new NumberStringConverter());

        devHigh_text.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String newValue = devHigh_text.getText().trim();
                if (isValidDouble(newValue)) {
                    devHighValue.set(Double.parseDouble(newValue));
                    biorreactor.getLoops().get(pos).getAlarms().get(posA).setDevHigh(devHighValue.get());
                    System.out.println(biorreactor.getLoops().get(pos).getName() + " dev high: " + biorreactor.getLoops().get(pos).getAlarms().get(posA).getDevHigh());
                } else {
                    showErrorAlert();
                }
            }
        });

        biorreactor.getLoops().get(pos).getAlarms().get(posA).devHighProperty().addListener((observable, oldValue, newValue) -> {
            devHighValue.set(newValue.doubleValue());
            loop.getAlarms().get(posA).setActive(true);
            loop.getAlarms().get(posA).setOn(true);
        });

        absEnValue = new SimpleBooleanProperty(biorreactor.getLoops().get(pos).getAlarms().get(posA).isAbsEn());

        absEn_checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            absEnValue.set(newValue);
            biorreactor.getLoops().get(pos).getAlarms().get(posA).setAbsEn(newValue);
            loop.getAlarms().get(posA).setActive(true);
            loop.getAlarms().get(posA).setOn(true);
            System.out.println(biorreactor.getLoops().get(pos).getName() + " abs En: " + biorreactor.getLoops().get(pos).getAlarms().get(posA).isAbsEn());
        });

        absEn_checkbox.setSelected(absEnValue.get());

        absAuValue = new SimpleBooleanProperty(biorreactor.getLoops().get(pos).getAlarms().get(posA).isAbsAu());

        absAu_checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            absAuValue.set(newValue);
            biorreactor.getLoops().get(pos).getAlarms().get(posA).setAbsAu(newValue);
            loop.getAlarms().get(posA).setActive(true);
            loop.getAlarms().get(posA).setOn(true);
            System.out.println(biorreactor.getLoops().get(pos).getName() + " abs Au: " + biorreactor.getLoops().get(pos).getAlarms().get(posA).isAbsAu());
        });

        absAu_checkbox.setSelected(absAuValue.get());

        devEnValue = new SimpleBooleanProperty(biorreactor.getLoops().get(pos).getAlarms().get(posA).isDevEn());

        devEn_checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            devEnValue.set(newValue);
            biorreactor.getLoops().get(pos).getAlarms().get(posA).setDevEn(newValue);
            loop.getAlarms().get(posA).setActive(true);
            loop.getAlarms().get(posA).setOn(true);
            System.out.println(biorreactor.getLoops().get(pos).getName() + " dev En: " + biorreactor.getLoops().get(pos).getAlarms().get(posA).isDevEn());
        });

        devEn_checkbox.setSelected(devEnValue.get());

        devAuValue = new SimpleBooleanProperty(biorreactor.getLoops().get(pos).getAlarms().get(posA).isDevAu());

        devAu_checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            devAuValue.set(newValue);
            biorreactor.getLoops().get(pos).getAlarms().get(posA).setDevAu(newValue);
            loop.getAlarms().get(posA).setActive(true);
            loop.getAlarms().get(posA).setOn(true);
            System.out.println(biorreactor.getLoops().get(pos).getName() + " dev Au: " + biorreactor.getLoops().get(pos).getAlarms().get(posA).isDevAu());
        });

        devAu_checkbox.setSelected(devAuValue.get());

        temp_checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> handleCheckboxSelectionLoop(newValue, biorreactor.getLoopByName("Temperature")));
        ph_checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> handleCheckboxSelectionLoop(newValue, biorreactor.getLoopByName("pH")));
        do_checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> handleCheckboxSelectionLoop(newValue, biorreactor.getLoopByName("DO")));
        stirring_checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> handleCheckboxSelectionLoop(newValue, biorreactor.getLoopByName("Stirring Rate")));
        pump1_checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> handleCheckboxSelectionPump(newValue, biorreactor.getPumpsByName("Pump 1")));
        pump2_checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> handleCheckboxSelectionPump(newValue, biorreactor.getPumpsByName("Pump 2")));
        pump3_checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> handleCheckboxSelectionPump(newValue, biorreactor.getPumpsByName("Pump 3")));

    }

    private void handleCheckboxSelectionLoop(boolean isSelected, Loop cloop) {
        if (isSelected && !loop.getAlarms().get(posA).getLoopsSD().contains(cloop)) {
            loop.getAlarms().get(posA).getLoopsSD().add(cloop);
        } else if (!isSelected && loop.getAlarms().get(posA).getLoopsSD().contains(cloop)) {
            loop.getAlarms().get(posA).getLoopsSD().remove(cloop);
        }
    }

    private void handleCheckboxSelectionPump(boolean isSelected, Pump pump) {
        if (isSelected && !loop.getAlarms().get(posA).getPumpsSD().contains(pump)) {
            loop.getAlarms().get(posA).getPumpsSD().add(pump);
        } else if (!isSelected && loop.getAlarms().get(posA).getPumpsSD().contains(pump)) {
            loop.getAlarms().get(posA).getPumpsSD().remove(pump);
        }
    }

    public void initData(Loop selectedLoop) {
        try {
            ArrayList<Alarm> alarms = selectedLoop.getAlarms();
            selectedLoop_text.setText(String.valueOf(selectedLoop.getName()));
            st_label.setText(String.valueOf(selectedLoop.getSt()));
            pv_label.setText(String.valueOf(selectedLoop.getPv()));
            pos = biorreactor.getLoopPos(selectedLoop.getName());
            loop = selectedLoop;
            posA = alarms.size()-1;
            absLow_text.setText(String.valueOf(alarms.get(posA).getAbsLow()));
            absHigh_text.setText(String.valueOf(alarms.get(posA).getAbsHigh()));
            devLow_text.setText(String.valueOf(alarms.get(posA).getDevLow()));
            devHigh_text.setText(String.valueOf(alarms.get(posA).getDevHigh()));
            absEn_checkbox.setSelected(alarms.get(posA).isAbsEn());
            devEn_checkbox.setSelected(alarms.get(posA).isDevEn());
            temp_checkbox.setSelected(loop.getAlarms().get(posA).getLoopsSD().contains(biorreactor.getLoopByName("Temperature")));
            ph_checkbox.setSelected(loop.getAlarms().get(posA).getLoopsSD().contains(biorreactor.getLoopByName("pH")));
            do_checkbox.setSelected(loop.getAlarms().get(posA).getLoopsSD().contains(biorreactor.getLoopByName("DO")));
            stirring_checkbox.setSelected(loop.getAlarms().get(posA).getLoopsSD().contains(biorreactor.getLoopByName("Stirring Rate")));
            pump1_checkbox.setSelected(loop.getAlarms().get(posA).getPumpsSD().contains(biorreactor.getPumpsByName("Pump 1")));
            pump2_checkbox.setSelected(loop.getAlarms().get(posA).getPumpsSD().contains(biorreactor.getPumpsByName("Pump 2")));
            pump3_checkbox.setSelected(loop.getAlarms().get(posA).getPumpsSD().contains(biorreactor.getPumpsByName("Pump 3")));
        } catch (Exception e) {
            e.printStackTrace();
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
