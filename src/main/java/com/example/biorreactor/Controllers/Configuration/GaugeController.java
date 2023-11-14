package com.example.biorreactor.Controllers.Configuration;

import com.example.biorreactor.Models.Biorreactor;
import com.example.biorreactor.Models.Loop;
import com.example.biorreactor.Models.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class GaugeController implements Initializable {
    @FXML
    public ListView<String> listView;
    public Button configurate_btn;

    Biorreactor biorreactor = Biorreactor.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AtomicReference<Loop> selectedLoop = new AtomicReference<>();

        for (int i = 0; i < biorreactor.getLoops().size(); i++) {
            listView.getItems().add(biorreactor.getLoops().get(i).getName());
        }

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedLoop.set(biorreactor.getLoopByName(newValue));
            System.out.println("Selected Loop: " + selectedLoop.get().getName());
            addListeners(selectedLoop);
        });
    }

    private void addListeners(AtomicReference<Loop> selectedLoop) {
        configurate_btn.setOnAction(event -> onConfigurate(selectedLoop.get()));
    }

    private void onConfigurate(Loop selectedLoop) {
        ViewModel.getInstance().getViewFactory().showPerGaugeWindow(selectedLoop);
    }
}
