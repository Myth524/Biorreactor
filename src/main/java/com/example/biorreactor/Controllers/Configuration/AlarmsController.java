package com.example.biorreactor.Controllers.Configuration;

import com.example.biorreactor.Models.AddRow;
import com.example.biorreactor.Models.Alarm;
import com.example.biorreactor.Models.Biorreactor;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AlarmsController implements Initializable {
    public Button history_btn;
    public Button acknowAll_btn;
    public Button currentA_btn;
    @FXML public TableView<AddRow> alarms_table;
    @FXML public TableColumn<AddRow, StringProperty> loopNameCol;
    @FXML public TableColumn<AddRow, DoubleProperty> absLowCol;
    @FXML public TableColumn<AddRow, DoubleProperty> absHighCol;
    @FXML public TableColumn<AddRow, Boolean> absEnCol;
    @FXML public TableColumn<AddRow, DoubleProperty> devLowCol;
    @FXML public TableColumn<AddRow, DoubleProperty> devHighCol;
    @FXML public TableColumn<AddRow, Boolean> devEnCol;

    Biorreactor biorreactor = Biorreactor.getInstance();
    ObservableList<AddRow> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < biorreactor.getLoops().size(); i++) {
            ArrayList<Alarm> alarms = biorreactor.getLoops().get(i).getAlarms();

            for (Alarm alarm : alarms) {
                AddRow row = new AddRow();
                row.setLoopName(biorreactor.getLoops().get(i).getName());
                row.setAbsLow(alarm.getAbsLow());
                row.setAbsHigh(alarm.getAbsHigh());
                row.setAbsEn(alarm.isAbsEn());
                row.setDevLow(alarm.getDevLow());
                row.setDevHigh(alarm.getDevHigh());
                row.setDevEn(alarm.isDevEn());
                list.add(row);
            }
        }

        loopNameCol.setCellValueFactory(new PropertyValueFactory<>("loopName"));
        absLowCol.setCellValueFactory(new PropertyValueFactory<>("absLow"));
        absHighCol.setCellValueFactory(new PropertyValueFactory<>("absHigh"));
        absEnCol.setCellValueFactory(new PropertyValueFactory<>("absEn"));
        devLowCol.setCellValueFactory(new PropertyValueFactory<>("devLow"));
        devHighCol.setCellValueFactory(new PropertyValueFactory<>("devHigh"));
        devEnCol.setCellValueFactory(new PropertyValueFactory<>("devEn"));

        alarms_table.setItems(list);

    }

}