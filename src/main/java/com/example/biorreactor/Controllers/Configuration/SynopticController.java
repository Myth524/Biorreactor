package com.example.biorreactor.Controllers.Configuration;

import com.example.biorreactor.Models.DataModel;
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
    public ToggleButton start_tbtn;
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

    ObservableList<DataModel> list = FXCollections.observableArrayList(
            new DataModel()
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();

        // Ph
        stPhCol.setCellValueFactory(new PropertyValueFactory<>("stPh"));
        pvPhCol.setCellValueFactory(new PropertyValueFactory<>("pvPh"));
        phTable.setItems(list);

        // Temperature
        stTempCol.setCellValueFactory(new PropertyValueFactory<>("stTemp"));
        pvTempCol.setCellValueFactory(new PropertyValueFactory<>("pvTemp"));
        tempTable.setItems(list);

        // Stirring Rate
        stStirringCol.setCellValueFactory(new PropertyValueFactory<>("stStirring"));
        pvStirringCol.setCellValueFactory(new PropertyValueFactory<>("pvStirring"));
        stirringTable.setItems(list);

        // Air Flow
        stAirCol.setCellValueFactory(new PropertyValueFactory<>("stAir"));
        pvAirCol.setCellValueFactory(new PropertyValueFactory<>("pvAir"));
        airTable.setItems(list);

        // Oxygen Flow
        stO2Col.setCellValueFactory(new PropertyValueFactory<>("stO2"));
        pvO2Col.setCellValueFactory(new PropertyValueFactory<>("pvO2"));
        o2Table.setItems(list);

        // Pump 1
        stPump1Col.setCellValueFactory(new PropertyValueFactory<>("stPump1"));
        pvPump1Col.setCellValueFactory(new PropertyValueFactory<>("pvPump1"));
        pump1Table.setItems(list);

        // Pump 2
        stPump2Col.setCellValueFactory(new PropertyValueFactory<>("stPump2"));
        pvPump2Col.setCellValueFactory(new PropertyValueFactory<>("pvPump2"));
        pump2Table.setItems(list);

        // Pump 3
        stPump3Col.setCellValueFactory(new PropertyValueFactory<>("stPump3"));
        pvPump3Col.setCellValueFactory(new PropertyValueFactory<>("pvPump3"));
        pump3Table.setItems(list);

        // Pump 4
        stPump4Col.setCellValueFactory(new PropertyValueFactory<>("stPump1"));
        pvPump4Col.setCellValueFactory(new PropertyValueFactory<>("pvPump1"));
        pump4Table.setItems(list);

    }

    private void addListeners() {
        start_tbtn.setOnAction(event -> onStart());
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
        start_tbtn.setOnMouseClicked(event -> {
            if (start_tbtn.isSelected()) {
                start_tbtn.setText("Stop");
                setButtonStyle(start_tbtn, "alt_ToggleButton_container");
            } else {
                start_tbtn.setText("Start");
                setButtonStyle(start_tbtn, "dashboard_container");
            }
        });
    }

    private void setButtonStyle(ToggleButton button, String styleClass) {
        button.getStyleClass().removeIf(style -> style.equals("alt_ToggleButton_container") || style.equals("dashboard_container"));
        button.getStyleClass().add(styleClass);
        button.requestFocus();
    }
}
