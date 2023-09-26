package com.example.biorreactor.Controllers.Configuration;

import com.example.biorreactor.Models.ViewModel;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfigurationController implements Initializable {

    public BorderPane configuration_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewModel.getInstance().getViewFactory().getconfigurationSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal){

                case "Alarms" -> configuration_parent.setCenter(ViewModel.getInstance().getViewFactory().getAlarmsView());
                case "Graphs" -> configuration_parent.setCenter(ViewModel.getInstance().getViewFactory().getGraphsView());

                default -> configuration_parent.setCenter(ViewModel.getInstance().getViewFactory().getDashboardView());
            }
        } );

    }
}
