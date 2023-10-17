package com.example.biorreactor.Controllers.Configuration;

import com.example.biorreactor.Models.ViewModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfigurationMenuController implements Initializable {
    public Button alarms_btn;
    public Button graphs_btn;
    public Button return_btn;
    public Button synoptic_btn;
    public Button summary_btn;
    public Button gauge_btn;
    public Button pumps_btn;
    public Button calibration_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        ViewModel.getInstance().setConfigurationMenuController(this);
        summary_btn.setOnAction(event -> onSummary());
        alarms_btn.setOnAction(event -> onAlarms());
        graphs_btn.setOnAction(event -> onGraphs());
        synoptic_btn.setOnAction(event -> onSynoptic());
        gauge_btn.setOnAction(event -> onGauge());
        pumps_btn.setOnAction(event -> onPumps());
        calibration_btn.setOnAction(event -> onCalibration());

        return_btn.setOnAction(event -> onReturn());
    }

    private void onSynoptic(){
        handleButtonClick("Synoptic", synoptic_btn);
    }

    private void onSummary() {
        handleButtonClick("Summary", summary_btn);
    }

    private void onAlarms() {
        handleButtonClick("Alarms", alarms_btn);
    }

    private void onGraphs() {
        handleButtonClick("Graphs", graphs_btn);
    }

    private void onGauge() {
        handleButtonClick("Gauge", gauge_btn);
    }

    void onPumps() {
        handleButtonClick("Pumps", pumps_btn);
    }

    private void onCalibration() {
        handleButtonClick("Calibration", calibration_btn);
    }

    private void onReturn() {
        Stage stage = (Stage) return_btn.getScene().getWindow();
        ViewModel.getInstance().getViewFactory().closeStage(stage);
        ViewModel.getInstance().getViewFactory().showLandingPageWindow();
    }

    // Metodos auxiliares
    private void handleButtonClick(String menuItem, Button button) {
        ViewModel viewModel = ViewModel.getInstance();

        viewModel.getViewFactory().getconfigurationSelectedMenuItem().set(menuItem);

        setButtonStyle(button, "alt_menu_btn");

        Button[] allButtons = {synoptic_btn, summary_btn, alarms_btn, graphs_btn, gauge_btn, pumps_btn, calibration_btn};
        for (Button b : allButtons) {
            if (b != button) {
                setButtonStyle(b, "menu_container Button");
            }
        }
    }

    private void setButtonStyle(Button button, String styleClass) {
        button.getStyleClass().removeIf(style -> style.equals("alt_menu_btn") || style.equals("menu_container Button"));
        button.getStyleClass().add(styleClass);
        button.requestFocus();
    }
}