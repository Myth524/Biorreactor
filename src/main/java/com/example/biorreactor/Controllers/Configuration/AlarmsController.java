package com.example.biorreactor.Controllers.Configuration;

import com.example.biorreactor.Models.AddRow;
import com.example.biorreactor.Models.Alarm;
import com.example.biorreactor.Models.Biorreactor;
import com.example.biorreactor.Models.Loop;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AlarmsController implements Initializable {
    public Button history_btn;
    public Button acknowAll_btn;
    public Button currentA_btn;
    @FXML
    public TableView<AddRow> alarms_table;
    @FXML
    public TableColumn<AddRow, String> loopNameCol;
    @FXML
    public TableColumn<AddRow, Double> absLowCol;
    @FXML
    public TableColumn<AddRow, Double> absHighCol;
    @FXML
    public TableColumn<AddRow, Boolean> absEnCol;
    @FXML
    public TableColumn<AddRow, Double> devLowCol;
    @FXML
    public TableColumn<AddRow, Double> devHighCol;
    @FXML
    public TableColumn<AddRow, Boolean> devEnCol;

    Biorreactor biorreactor = Biorreactor.getInstance();
    ObservableList<AddRow> listAlarms = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableColumns();
        showAlarmsForActiveLoops();
        addObservers();

        acknowAll_btn.setOnAction(event -> showAllAlarms());
        currentA_btn.setOnAction(event -> showAlarmsForActiveLoops());
        history_btn.setOnAction(event -> showHistoryAlarms());
    }

    private void initializeTableColumns() {
        loopNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLoopName()));
        absLowCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getAbsLow()).asObject());
        absHighCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getAbsHigh()).asObject());
        absEnCol.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isAbsEn()));
        devLowCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getDevLow()).asObject());
        devHighCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getDevHigh()).asObject());
        devEnCol.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isDevEn()));
    }

    private void addObservers() {
        biorreactor.getLoops().forEach(loop -> {
            loop.getAlarms().forEach(alarm -> {
                alarm.absLowProperty().addListener((observable, oldValue, newValue) -> updateTable());
                alarm.absHighProperty().addListener((observable, oldValue, newValue) -> updateTable());
                alarm.absEnProperty().addListener((observable, oldValue, newValue) -> updateTable());
                alarm.devLowProperty().addListener((observable, oldValue, newValue) -> updateTable());
                alarm.devHighProperty().addListener((observable, oldValue, newValue) -> updateTable());
                alarm.devEnProperty().addListener((observable, oldValue, newValue) -> updateTable());
            });
        });
    }

    private void updateTable() {
        showAllAlarms();
    }

    private void showHistoryAlarms() {
        listAlarms.clear();
        biorreactor.getLoops().forEach(loop -> {
            List<Alarm> historyAlarms = loop.getAlarms().stream()
                    .filter(alarm -> alarm.isOn() && !alarm.isActive() && alarm.isTriggered())
                    .toList();

            historyAlarms.forEach(alarm -> listAlarms.add(createAddRow(loop, alarm)));
        });
        alarms_table.setItems(listAlarms);
    }

    private void showAlarmsForActiveLoops() {
        listAlarms.clear();
        biorreactor.getLoops().forEach(loop -> {
            List<Alarm> activeAlarms = loop.getAlarms().stream()
                    .filter(alarm -> alarm.isActive() && alarm.isOn() && !alarm.isTriggered())
                    .toList();

            activeAlarms.forEach(alarm -> listAlarms.add(createAddRow(loop, alarm)));
        });
        alarms_table.setItems(listAlarms);
    }


    private void showAllAlarms() {
            listAlarms.clear();
            biorreactor.getLoops().forEach(loop -> {
                List<Alarm> allAlarms = loop.getAlarms().stream()
                        .filter(Alarm::isOn)
                        .toList();

                allAlarms.forEach(alarm -> listAlarms.add(createAddRow(loop, alarm)));
            });
            alarms_table.setItems(listAlarms);
    }

    private AddRow createAddRow(Loop loop, Alarm alarm) {
        AddRow row = new AddRow();
        row.setLoopName(loop.getName());
        row.setAbsLow(alarm.getAbsLow());
        row.setAbsHigh(alarm.getAbsHigh());
        row.setAbsEn(alarm.isAbsEn());
        row.setDevLow(alarm.getDevLow());
        row.setDevHigh(alarm.getDevHigh());
        row.setDevEn(alarm.isDevEn());
        return row;
    }
}
