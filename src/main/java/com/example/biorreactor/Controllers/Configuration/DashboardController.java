package com.example.biorreactor.Controllers.Configuration;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    public ToggleButton start_tbtn;
    public Button temp_btn;
    public Button pressure_btn;
    public Button stirring_btn;
    public Button crop_btn;
    public Button oxygen_btn;
    public Button foam_btn;
    public Button ph_btn;
    public Button nutrients_btn;
    public Button liquid_btn;
    public Button time_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();

    }

    private void addListeners() {
        start_tbtn.setOnAction(event -> onStart());
    }

    private void onStart() {
        start_tbtn.setOnMouseClicked(event -> {
            if (start_tbtn.isSelected()) {
                start_tbtn.setText("Stop");
                setButtonStyle(start_tbtn, "alt_ToggleButton_container");
            } else {
                start_tbtn.setText("Start");
                setButtonStyle(start_tbtn, "dashboard_container");
            }
        });
    }

    private void setButtonStyle(ToggleButton button, String styleClass) {
        button.getStyleClass().removeIf(style -> style.equals("alt_ToggleButton_container") || style.equals("dashboard_container"));
        button.getStyleClass().add(styleClass);
        button.requestFocus();
    }
}

