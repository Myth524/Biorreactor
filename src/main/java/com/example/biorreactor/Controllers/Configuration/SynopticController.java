package com.example.biorreactor.Controllers.Configuration;

import com.example.biorreactor.Models.DataModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SynopticController implements Initializable {

    // Buttons
    public ToggleButton start_btn;
    public Button temp_btn;
    public Button stirring_btn;
    public Button ph_btn;
    public Button o2_btn;
    public Button pump1_btn;
    public Button pump2_btn;
    public Button pump4_btn;
    public Button pump3_btn;
    public Button air_btn;

    // pH Table
    @FXML public TableView<DataModel> phTable;
    @FXML public TableColumn<DataModel, Double> stPhCol;
    @FXML public TableColumn<DataModel, Double> pvPhCol;

    // Temperature Table
    @FXML public TableView<DataModel> tempTable;
    @FXML public TableColumn<DataModel, Double> stTempCol;
    @FXML public TableColumn<DataModel, Double> pvTempCol;

    // Oxygen Flow Table
    @FXML public TableView<DataModel> o2Table;
    @FXML public TableColumn<DataModel, Double> stO2Col;
    @FXML public TableColumn<DataModel, Double> pvO2Col;

    // Stirring Rate Table
    @FXML public TableView<DataModel> stirringTable;
    @FXML public TableColumn<DataModel, Double> stStirringCol;
    @FXML public TableColumn<DataModel, Double> pvStirringCol;

    // Pump 4 Table
    @FXML public TableView<DataModel> pump4Table;
    @FXML public TableColumn<DataModel, Double> stPump4Col;
    @FXML public TableColumn<DataModel, Double> pvPump4Col;

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

    // Air Table
    @FXML public TableView<DataModel> airTable;
    @FXML public TableColumn<DataModel, Double> stAirCol;
    @FXML public TableColumn<DataModel, Double> pvAirCol;
    @FXML public TextField cropName;
    @FXML public DatePicker date;

    DataModel dataModel = DataModel.getInstance();
    ObservableList<DataModel> list = FXCollections.observableArrayList(dataModel,dataModel);

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

        // Air Flow
        stAirCol.setCellValueFactory(cellData -> {
            int columnIndex = airTable.getColumns().indexOf(cellData.getTableColumn());
            if (columnIndex >= 0 && columnIndex < list.size()) {
                return list.get(columnIndex).getStProperty(3);
            }
            return new SimpleDoubleProperty(0).asObject();
        });

        pvAirCol.setCellValueFactory(cellData -> {
            int columnIndex = airTable.getColumns().indexOf(cellData.getTableColumn());
            if (columnIndex >= 0 && columnIndex < list.size()) {
                return list.get(columnIndex).getPvProperty(3);
            }
            return new SimpleDoubleProperty(0).asObject();
        });
        airTable.setItems(list);

        // Oxygen Flow
        stO2Col.setCellValueFactory(cellData -> {
            int columnIndex = o2Table.getColumns().indexOf(cellData.getTableColumn());
            if (columnIndex >= 0 && columnIndex < list.size()) {
                return list.get(columnIndex).getStProperty(4);
            }
            return new SimpleDoubleProperty(0).asObject();
        });

        pvO2Col.setCellValueFactory(cellData -> {
            int columnIndex = o2Table.getColumns().indexOf(cellData.getTableColumn());
            if (columnIndex >= 0 && columnIndex < list.size()) {
                return list.get(columnIndex).getPvProperty(4);
            }
            return new SimpleDoubleProperty(0).asObject();
        });
        o2Table.setItems(list);

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

        // Pump 4
        stPump4Col.setCellValueFactory(cellData -> {
            int columnIndex = pump4Table.getColumns().indexOf(cellData.getTableColumn());
            if (columnIndex >= 0 && columnIndex < list.size()) {
                return list.get(columnIndex).getStProperty(8);
            }
            return new SimpleDoubleProperty(0).asObject();
        });

        pvPump4Col.setCellValueFactory(cellData -> {
            int columnIndex = pump4Table.getColumns().indexOf(cellData.getTableColumn());
            if (columnIndex >= 0 && columnIndex < list.size()) {
                return list.get(columnIndex).getPvProperty(8);
            }
            return new SimpleDoubleProperty(0).asObject();
        });
        pump4Table.setItems(list);
    }
    private void addListeners() {
        start_btn.setOnAction(event -> onStart());
        cropName.setOnAction(event -> fillCropName(cropName.getText()));
        date.setOnAction(event -> fillDate());
    }

    private void fillCropName(String cropName) {
        if (cropName != null && !cropName.isEmpty()) {
            this.cropName.setEditable(false);
            this.cropName.setDisable(true);
            this.cropName.setStyle("-fx-text-fill: gray;");
        }
    }
    private void fillDate() {
        if (date !=null) {
            date.setDisable(true);
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
