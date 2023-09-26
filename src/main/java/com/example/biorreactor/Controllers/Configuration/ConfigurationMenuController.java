package com.example.biorreactor.Controllers.Configuration;

import com.example.biorreactor.Models.ViewModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfigurationMenuController implements Initializable {
    public Button parameters_btn;
    public Button alarms_btn;
    public Button graphs_btn;
    public Button return_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    /* Return */
    private void addListeners() {
        parameters_btn.setOnAction(event -> onParameters());
        alarms_btn.setOnAction(event -> onAlarms());
        graphs_btn.setOnAction(event -> onGraphs());
        return_btn.setOnAction(event -> onReturn());
    }

    private void onParameters() {
        ViewModel.getInstance().getViewFactory().getconfigurationSelectedMenuItem().set("");
        setButtonStyle(parameters_btn, "alt_menu_btn");
        setButtonStyle(alarms_btn, "menu_container Button");
        setButtonStyle(graphs_btn, "menu_container Button");
    }

    private void onAlarms() {
        ViewModel.getInstance().getViewFactory().getconfigurationSelectedMenuItem().set("Alarms");
        setButtonStyle(parameters_btn, "menu_container Button");
        setButtonStyle(alarms_btn, "alt_menu_btn");
        setButtonStyle(graphs_btn, "menu_container Button");
    }

    private void onGraphs() {
        ViewModel.getInstance().getViewFactory().getconfigurationSelectedMenuItem().set("Graphs");
        setButtonStyle(parameters_btn, "menu_container Button");
        setButtonStyle(alarms_btn, "menu_container Button");
        setButtonStyle(graphs_btn, "alt_menu_btn");
    }

    private void onReturn() {
        Stage stage = (Stage) return_btn.getScene().getWindow();
        ViewModel.getInstance().getViewFactory().closeStage(stage);
        ViewModel.getInstance().getViewFactory().showLandingPageWindow();
    }

    private void setButtonStyle(Button button, String styleClass) {
        button.getStyleClass().removeIf(style -> style.equals("alt_menu_btn") || style.equals("menu_container Button"));
        button.getStyleClass().add(styleClass);
        button.requestFocus();
    }

}
