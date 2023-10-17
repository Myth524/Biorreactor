package com.example.biorreactor.Controllers.Configuration;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PerGaugeController implements Initializable {
    public TextField st_text;
    public Label pv_label;
    public Button on_btn;
    public Button off_btn;
    public Button alarmSettings_btn;
    public TextField low_text;
    public TextField high_text;
    public TextField integral_text;
    public TextField proportional_text;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
