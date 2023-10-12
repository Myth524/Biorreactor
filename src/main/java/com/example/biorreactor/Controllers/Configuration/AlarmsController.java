package com.example.biorreactor.Controllers.Configuration;

import com.example.biorreactor.Models.Alarms;
import com.example.biorreactor.Models.AlarmsDataRow;
import com.example.biorreactor.Models.DataModel;
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
import java.util.Observable;
import java.util.ResourceBundle;

public class AlarmsController implements Initializable {
    public Button history_btn;
    public Button acknowAll_btn;
    public Button currentA_btn;
    @FXML public TableView<AlarmsDataRow> alarms_table;
    @FXML public TableColumn<AlarmsDataRow, StringProperty> loopNameCol;
    @FXML public TableColumn<AlarmsDataRow, DoubleProperty> absLowCol;
    @FXML public TableColumn<AlarmsDataRow, DoubleProperty> absHighCol;
    @FXML public TableColumn<AlarmsDataRow, Boolean> absEnCol;
    @FXML public TableColumn<AlarmsDataRow, DoubleProperty> devLowCol;
    @FXML public TableColumn<AlarmsDataRow, DoubleProperty> devHighCol;
    @FXML public TableColumn<AlarmsDataRow, Boolean> devEnCol;

    DataModel dataModel = DataModel.getInstance();
    ObservableList<AlarmsDataRow> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

       ArrayList <Alarms> alarms = dataModel.getAlarms();

        for (Alarms alarm : alarms) {
            AlarmsDataRow alarmDataRow = new AlarmsDataRow(
                    alarm.loopNameProperty().get(),
                    alarm.absLowProperty().get(),
                    alarm.absHighProperty().get(),
                    alarm.absEnProperty().get(),
                    alarm.devLowProperty().get(),
                    alarm.devHighProperty().get(),
                    alarm.devEnProperty().get()
            );

            loopNameCol.setCellValueFactory(new PropertyValueFactory<>("loopName"));
            absLowCol.setCellValueFactory(new PropertyValueFactory<>("absLow"));
            absHighCol.setCellValueFactory(new PropertyValueFactory<>("absHigh"));
            absEnCol.setCellValueFactory(new PropertyValueFactory<>("absEn"));
            devLowCol.setCellValueFactory(new PropertyValueFactory<>("devLow"));
            devHighCol.setCellValueFactory(new PropertyValueFactory<>("devHigh"));
            devEnCol.setCellValueFactory(new PropertyValueFactory<>("devEn"));

            list.add(alarmDataRow);
        }
        alarms_table.setItems(list);

    }
}
