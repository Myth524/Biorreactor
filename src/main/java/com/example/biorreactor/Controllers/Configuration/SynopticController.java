package com.example.biorreactor.Controllers.Configuration;

import com.example.biorreactor.Models.DataModel;
import com.example.biorreactor.Models.SummaryDataRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.net.URL;
import java.util.ResourceBundle;

public class SynopticController implements Initializable {

    // Buttons
    public ToggleButton start_btn;
    public Button temp_btn;
    public Button stirring_btn;
    public Button ph_btn;
    public Button DO_btn;
    public Button pump1_btn;
    public Button pump2_btn;
    public Button pump3_btn;

    // pH Table
    @FXML public TableView<SummaryDataRow> phTable;
    @FXML public TableColumn<SummaryDataRow, Double> stPhCol;
    @FXML public TableColumn<SummaryDataRow, Double> pvPhCol;

    // Temperature Table
    @FXML public TableView<SummaryDataRow> tempTable;
    @FXML public TableColumn<SummaryDataRow, Double> stTempCol;
    @FXML public TableColumn<SummaryDataRow, Double> pvTempCol;

    // DO Table
    @FXML public TableView<SummaryDataRow> DOTable;
    @FXML public TableColumn<SummaryDataRow, Double> stDOCol;
    @FXML public TableColumn<SummaryDataRow, Double> pvDOCol;

    // Stirring Rate Table
    @FXML public TableView<SummaryDataRow> stirringTable;
    @FXML public TableColumn<SummaryDataRow, Double> stStirringCol;
    @FXML public TableColumn<SummaryDataRow, Double> pvStirringCol;

    // Pump 1 Table
    @FXML public TableView<SummaryDataRow> pump1Table;
    @FXML public TableColumn<SummaryDataRow, Double> stPump1Col;
    @FXML public TableColumn<SummaryDataRow, Double> pvPump1Col;

    // Pump 2 Table
    @FXML public TableView<SummaryDataRow> pump2Table;
    @FXML public TableColumn<SummaryDataRow, Double> stPump2Col;
    @FXML public TableColumn<SummaryDataRow, Double> pvPump2Col;

    // Pump 3 Table
    @FXML public TableView<SummaryDataRow> pump3Table;
    @FXML public TableColumn<SummaryDataRow, Double> stPump3Col;
    @FXML public TableColumn<SummaryDataRow, Double> pvPump3Col;


    @FXML public TextField cropName;
    @FXML public DatePicker date;

    DataModel dataModel = DataModel.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();

        // Asignar valores a la tabla de pH
        SummaryDataRow pHRow = new SummaryDataRow();
        pHRow.setPv(dataModel.getPv().get(0).get());
        pvPhCol.setCellValueFactory(new PropertyValueFactory<>("pv"));
        pHRow.setSt(dataModel.getSt().get(0).get());
        stPhCol.setCellValueFactory(new PropertyValueFactory<>("st"));
        phTable.getItems().add(pHRow);

        // Asignar valores a la tabla de Temperature
        SummaryDataRow tempRow = new SummaryDataRow();
        tempRow.setPv(dataModel.getPv().get(1).get());
        pvTempCol.setCellValueFactory(new PropertyValueFactory<>("pv"));
        tempRow.setSt(dataModel.getSt().get(1).get());
        stTempCol.setCellValueFactory(new PropertyValueFactory<>("st"));
        tempTable.getItems().add(tempRow);

        // Asignar valores a la tabla de DO
        SummaryDataRow DORow = new SummaryDataRow();
        DORow.setPv(dataModel.getPv().get(2).get());
        pvDOCol.setCellValueFactory(new PropertyValueFactory<>("pv"));
        DORow.setSt(dataModel.getSt().get(2).get());
        stDOCol.setCellValueFactory(new PropertyValueFactory<>("st"));
        DOTable.getItems().add(DORow);

        // Asignar valores a la tabla de Stirring Rate
        SummaryDataRow stirringRow = new SummaryDataRow();
        stirringRow.setPv(dataModel.getPv().get(3).get());
        pvStirringCol.setCellValueFactory(new PropertyValueFactory<>("pv"));
        stirringRow.setSt(dataModel.getSt().get(3).get());
        stStirringCol.setCellValueFactory(new PropertyValueFactory<>("st"));
        stirringTable.getItems().add(stirringRow);

        // Asignar valores a la tabla de Pump 1
        SummaryDataRow pump1Row = new SummaryDataRow();
        pump1Row.setPv(dataModel.getPv().get(4).get());
        pvPump1Col.setCellValueFactory(new PropertyValueFactory<>("pv"));
        pump1Row.setSt(dataModel.getSt().get(4).get());
        stPump1Col.setCellValueFactory(new PropertyValueFactory<>("st"));
        pump1Table.getItems().add(pump1Row);

        // Asignar valores a la tabla de Pump 2
        SummaryDataRow pump2Row = new SummaryDataRow();
        pump2Row.setPv(dataModel.getPv().get(5).get());
        pvPump2Col.setCellValueFactory(new PropertyValueFactory<>("pv"));
        pump2Row.setSt(dataModel.getSt().get(5).get());
        stPump2Col.setCellValueFactory(new PropertyValueFactory<>("st"));
        pump2Table.getItems().add(pump2Row);

        // Asignar valores a la tabla de Pump 3
        SummaryDataRow pump3Row = new SummaryDataRow();
        pump3Row.setPv(dataModel.getPv().get(6).get());
        pvPump3Col.setCellValueFactory(new PropertyValueFactory<>("pv"));
        pump3Row.setSt(dataModel.getSt().get(6).get());
        stPump3Col.setCellValueFactory(new PropertyValueFactory<>("st"));
        pump3Table.getItems().add(pump3Row);

    }

    private void addListeners() {
        start_btn.setOnAction(event -> onStart());
        cropName.setOnAction(event -> fillCropName(cropName.getText()));
        date.setOnAction(event -> fillDate());
    }

    private void fillCropName(String cropName) {
        if (cropName != null && !cropName.isEmpty()) {
            dataModel.setCropName(cropName);
            this.cropName.setDisable(true);
            System.out.println("\nCrop name: " + dataModel.getCropName());
        }
    }
    private void fillDate() {
        if (date != null && date.getValue() != null) {
            date.setDisable(true);
            LocalDate selectedDate = date.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            String formattedDate = selectedDate.format(formatter);
            System.out.println("\nDate: " + formattedDate);
        }
    }

    private void onStart() {
        start_btn.setOnMouseClicked(event -> {
            if (start_btn.isSelected()) {
                start_btn.setText("Stop");
                setButtonStyle(start_btn, "alt_ToggleButton_container");
            } else {
                start_btn.setText("Start");
                setButtonStyle(start_btn, "dashboard_container");
            }
        });
    }

    private void setButtonStyle(ToggleButton button, String styleClass) {
        button.getStyleClass().removeIf(style -> style.equals("alt_ToggleButton_container") || style.equals("dashboard_container"));
        button.getStyleClass().add(styleClass);
        button.requestFocus();
    }
}
