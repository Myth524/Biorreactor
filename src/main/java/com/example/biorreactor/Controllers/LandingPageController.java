package com.example.biorreactor.Controllers;

import com.example.biorreactor.Models.ViewModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LandingPageController implements Initializable {
    public Button configuration_btn;
    public Button historic_btn;
    public Button quit_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners(){
        configuration_btn.setOnAction(event -> onConfiguration());
        historic_btn.setOnAction(event -> onHistoric());
        quit_btn.setOnAction(event -> onQuit());
    }

    private void onConfiguration(){
        Stage stage = (Stage) configuration_btn.getScene().getWindow();
        ViewModel.getInstance().getViewFactory().closeStage(stage);
        ViewModel.getInstance().getViewFactory().showConfigurationWindow();
    }

    private void onHistoric(){
        Stage stage = (Stage) historic_btn.getScene().getWindow();
        ViewModel.getInstance().getViewFactory().closeStage(stage);
        ViewModel.getInstance().getViewFactory().showHistoricView();

    }

    private void onQuit(){
        Stage stage = (Stage) quit_btn.getScene().getWindow();
        ViewModel.getInstance().getViewFactory().closeStage(stage);
    }
}
