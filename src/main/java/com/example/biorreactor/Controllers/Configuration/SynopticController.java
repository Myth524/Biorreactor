package com.example.biorreactor.Controllers.Configuration;

import com.example.biorreactor.Models.DataModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    @FXML public TableView<DataModel> phTable;
    @FXML public TableColumn<DataModel, Double> stPhCol;
    @FXML public TableColumn<DataModel, Double> pvPhCol;

    // Temperature Table
    @FXML public TableView<DataModel> tempTable;
    @FXML public TableColumn<DataModel, Double> stTempCol;
    @FXML public TableColumn<DataModel, Double> pvTempCol;

    // DO Table
    @FXML public TableView<DataModel> DOTable;
    @FXML public TableColumn<DataModel, Double> stDOCol;
    @FXML public TableColumn<DataModel, Double> pvDOCol;

    // Stirring Rate Table
    @FXML public TableView<DataModel> stirringTable;
    @FXML public TableColumn<DataModel, Double> stStirringCol;
    @FXML public TableColumn<DataModel, Double> pvStirringCol;

    // Pump 1 Table
    @FXML public TableView<DataModel> pump1Table;
    @FXML public TableColumn<DataModel, Double> stPump1Col;
    @FXML public TableColumn<DataModel, Double> pvPump1Col;

    // Pump 2 Table
    @FXML public TableView<DataModel> pump2Table;
    @FXML public TableColumn<DataModel, Double> stPump2Col;
    @FXML public TableColumn<DataModel, Double> pvPump2Col;

    // Pump 3 Table
    @FXML public TableView<DataModel> pump3Table;
    @FXML public TableColumn<DataModel, Double> stPump3Col;
    @FXML public TableColumn<DataModel, Double> pvPump3Col;


    @FXML public TextField cropName;
    @FXML public DatePicker date;

    DataModel dataModel = DataModel.getInstance();
    ObservableList<DataModel> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();

        // pH
        stPhCol.setCellValueFactory(cellData -> {
            int columnIndex = phTable.getColumns().indexOf(cellData.getTableColumn());
            if (columnIndex >= 0 && columnIndex < list.size()) {
                return list.get(columnIndex).getStProperty(0);
            }
            return new SimpleDoubleProperty(0).asObject();
        });

        pvPhCol.setCellValueFactory(cellData -> {
            int columnIndex = phTable.getColumns().indexOf(cellData.getTableColumn());
            if (columnIndex >= 0 && columnIndex < list.size()) {
                return list.get(columnIndex).getPvProperty(0);
            }
            return new SimpleDoubleProperty(0).asObject();
        });
        phTable.setItems(list);

        // Temperature
        stTempCol.setCellValueFactory(cellData -> {
            int columnIndex = tempTable.getColumns().indexOf(cellData.getTableColumn());
            if (columnIndex >= 0 && columnIndex < list.size()) {
                return list.get(columnIndex).getStProperty(1);
            }
            return new SimpleDoubleProperty(0).asObject();
        });

        pvTempCol.setCellValueFactory(cellData -> {
            int columnIndex = tempTable.getColumns().indexOf(cellData.getTableColumn());
            if (columnIndex >= 0 && columnIndex < list.size()) {
                return list.get(columnIndex).getPvProperty(1);
            }
            return new SimpleDoubleProperty(0).asObject();
        });
        tempTable.setItems(list);

        // Stirring Rate
        stStirringCol.setCellValueFactory(cellData -> {
            int columnIndex = stirringTable.getColumns().indexOf(cellData.getTableColumn());
            if (columnIndex >= 0 && columnIndex < list.size()) {
                return list.get(columnIndex).getStProperty(2);
            }
            return new SimpleDoubleProperty(0).asObject();
        });

        pvStirringCol.setCellValueFactory(cellData -> {
            int columnIndex = stirringTable.getColumns().indexOf(cellData.getTableColumn());
            if (columnIndex >= 0 && columnIndex < list.size()) {
                return list.get(columnIndex).getPvProperty(2);
            }
            return new SimpleDoubleProperty(0).asObject();
        });
        stirringTable.setItems(list);

        // DO
        stDOCol.setCellValueFactory(cellData -> {
            int columnIndex = DOTable.getColumns().indexOf(cellData.getTableColumn());
            if (columnIndex >= 0 && columnIndex < list.size()) {
                return list.get(columnIndex).getStProperty(4);
            }
            return new SimpleDoubleProperty(0).asObject();
        });

        pvDOCol.setCellValueFactory(cellData -> {
            int columnIndex = DOTable.getColumns().indexOf(cellData.getTableColumn());
            if (columnIndex >= 0 && columnIndex < list.size()) {
                return list.get(columnIndex).getPvProperty(4);
            }
            return new SimpleDoubleProperty(0).asObject();
        });
        DOTable.setItems(list);

        // Pump 1
        stPump1Col.setCellValueFactory(cellData -> {
            int columnIndex = pump1Table.getColumns().indexOf(cellData.getTableColumn());
            if (columnIndex >= 0 && columnIndex < list.size()) {
                return list.get(columnIndex).getStProperty(5);
            }
            return new SimpleDoubleProperty(0).asObject();
        });

        pvPump1Col.setCellValueFactory(cellData -> {
            int columnIndex = pump1Table.getColumns().indexOf(cellData.getTableColumn());
            if (columnIndex >= 0 && columnIndex < list.size()) {
                return list.get(columnIndex).getPvProperty(5);
            }
            return new SimpleDoubleProperty(0).asObject();
        });
        pump1Table.setItems(list);

        // Pump 2
        stPump2Col.setCellValueFactory(cellData -> {
            int columnIndex = pump2Table.getColumns().indexOf(cellData.getTableColumn());
            if (columnIndex >= 0 && columnIndex < list.size()) {
                return list.get(columnIndex).getStProperty(6);
            }
            return new SimpleDoubleProperty(0).asObject();
        });

        pvPump2Col.setCellValueFactory(cellData -> {
            int columnIndex = pump2Table.getColumns().indexOf(cellData.getTableColumn());
            if (columnIndex >= 0 && columnIndex < list.size()) {
                return list.get(columnIndex).getPvProperty(6);
            }
            return new SimpleDoubleProperty(0).asObject();
        });
        pump2Table.setItems(list);

        // Pump 3
        stPump3Col.setCellValueFactory(cellData -> {
            int columnIndex = pump3Table.getColumns().indexOf(cellData.getTableColumn());
            if (columnIndex >= 0 && columnIndex < list.size()) {
                return list.get(columnIndex).getStProperty(7);
            }
            return new SimpleDoubleProperty(0).asObject();
        });

        pvPump3Col.setCellValueFactory(cellData -> {
            int columnIndex = pump3Table.getColumns().indexOf(cellData.getTableColumn());
            if (columnIndex >= 0 && columnIndex < list.size()) {
                return list.get(columnIndex).getPvProperty(7);
            }
            return new SimpleDoubleProperty(0).asObject();
        });
        pump3Table.setItems(list);
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
