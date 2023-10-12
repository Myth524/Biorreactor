package com.example.biorreactor.Controllers.Configuration;

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

import java.net.URL;
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
        for (int i = 0; i < dataModel.getLoopNames().size(); i++) {
            AlarmsDataRow row = new AlarmsDataRow(
                    dataModel.getLoopNames().get(i).get(),
                    dataModel.getAlarms().get(i).get(0).get(),
                    dataModel.getAlarms().get(i).get(1).get(),
                    dataModel.getAlarms().get(i).get(2).get(),
                    dataModel.getAlarms().get(i).get(3).get(),
                    dataModel.getAlarms().get(i).get(4).get(),
                    dataModel.getAlarms().get(i).get(5).get()
            );
            list.add(row);
        }

        alarms_table.setItems(list);

    }
}
