package com.example.biorreactor.Controllers.Configuration;

import com.example.biorreactor.Models.AddRow;
import com.example.biorreactor.Models.Biorreactor;
import com.example.biorreactor.Models.Loop;
import com.example.biorreactor.Models.Pump;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SummaryController implements Initializable {

    @FXML
    public TableView<AddRow> summary_table;
    @FXML
    public TableColumn<AddRow, String> loopNameCol;
    @FXML
    public TableColumn<AddRow, Double> pvCol;
    @FXML
    public TableColumn<AddRow, Double> stCol;
    @FXML
    public TableColumn<AddRow, Boolean> controlCol;
    @FXML
    public TableColumn<AddRow, String> unitsCol;

    Biorreactor biorreactor = Biorreactor.getInstance();
    ObservableList<AddRow> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Loops
        for (int i = 0; i < biorreactor.getLoops().size(); i++) {
            AddRow row = new AddRow();
            Loop loop = biorreactor.getLoops().get(i);
            row.setLoopName(loop.getName());
            row.setPv(loop.pvProperty().get());
            row.setSt(loop.stProperty().get());
            row.setControlMode(loop.isControlMode());
            row.setUnits(loop.getUnits());

            list.add(row);

            loop.pvProperty().addListener((observable, oldValue, newValue) -> {
                row.setPv((Double) newValue);
            });

            loop.stProperty().addListener((observable, oldValue, newValue) -> {
                row.setSt((Double) newValue);
            });

            loop.controlModeProperty().addListener((observable, oldValue, newValue) -> {
                row.setControlMode(newValue);
            });
        }

        // Pumps
        for (int i = 0; i < biorreactor.getPumps().size(); i++) {
            AddRow row = new AddRow();
            Pump pump = biorreactor.getPumps().get(i);
            row.setLoopName(pump.getName() + " (" + pump.getMode() + ")");
            row.setPv(pump.pvProperty().get());
            row.setSt(pump.stProperty().get());
            row.setControlMode(pump.isControlMode());
            row.setUnits(pump.getUnits());

            list.add(row);

            pump.pvProperty().addListener((observable, oldValue, newValue) -> {
                row.setPv((Double) newValue);
            });

            pump.stProperty().addListener((observable, oldValue, newValue) -> {
                row.setSt((Double) newValue);
            });

            pump.controlModeProperty().addListener((observable, oldValue, newValue) -> {
               row.setControlMode(newValue);
            });
        }

        loopNameCol.setCellValueFactory(new PropertyValueFactory<>("loopName"));
        pvCol.setCellValueFactory(new PropertyValueFactory<>("pv"));
        stCol.setCellValueFactory(new PropertyValueFactory<>("st"));
        controlCol.setCellValueFactory(new PropertyValueFactory<>("controlMode"));
        unitsCol.setCellValueFactory(new PropertyValueFactory<>("units"));

        summary_table.setItems(list);
    }
}
