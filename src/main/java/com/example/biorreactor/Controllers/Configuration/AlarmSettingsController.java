package com.example.biorreactor.Controllers.Configuration;

import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AlarmSettingsController implements Initializable {
    public TextField stText;
    public TextField pvText;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
