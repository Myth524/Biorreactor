package com.example.biorreactor.Controllers.Configuration;

import com.example.biorreactor.Models.*;
import com.example.biorreactor.Views.ViewFactory;
import javafx.beans.property.IntegerProperty;
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
    @FXML public TableView<AddRow> phTable;
    @FXML public TableColumn<AddRow, Double> stPhCol;
    @FXML public TableColumn<AddRow, Double> pvPhCol;

    // Temperature Table
    @FXML public TableView<AddRow> tempTable;
    @FXML public TableColumn<AddRow, Double> stTempCol;
    @FXML public TableColumn<AddRow, Double> pvTempCol;

    // DO Table
    @FXML public TableView<AddRow> DOTable;
    @FXML public TableColumn<AddRow, Double> stDOCol;
    @FXML public TableColumn<AddRow, Double> pvDOCol;

    // Stirring Rate Table
    @FXML public TableView<AddRow> stirringTable;
    @FXML public TableColumn<AddRow, Double> stStirringCol;
    @FXML public TableColumn<AddRow, Double> pvStirringCol;

    // Pump 1 Table
    @FXML public TableView<AddRow> pump1Table;
    @FXML public TableColumn<AddRow, Double> stPump1Col;
    @FXML public TableColumn<AddRow, Double> pvPump1Col;

    // Pump 2 Table
    @FXML public TableView<AddRow> pump2Table;
    @FXML public TableColumn<AddRow, Double> stPump2Col;
    @FXML public TableColumn<AddRow, Double> pvPump2Col;

    // Pump 3 Table
    @FXML public TableView<AddRow> pump3Table;
    @FXML public TableColumn<AddRow, Double> stPump3Col;
    @FXML public TableColumn<AddRow, Double> pvPump3Col;

    @FXML public TextField cropName;
    @FXML public DatePicker date;

    Biorreactor biorreactor = Biorreactor.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();

        pump1_btn.setOnAction(event -> onPumps());
        pump2_btn.setOnAction(event -> onPumps());
        pump3_btn.setOnAction(event -> onPumps());


        // Tabla ph
        AddRow phRow = new AddRow();
        Loop phLoop = biorreactor.getLoops().get(0);
        phRow.setPv(phLoop.getPv());
        pvPhCol.setCellValueFactory(new PropertyValueFactory<>("pv"));
        phRow.setSt(phLoop.getSt());
        stPhCol.setCellValueFactory(new PropertyValueFactory<>("st"));
        phTable.getItems().add(phRow);

        phLoop.pvProperty().addListener((observable, oldValue, newValue) ->
        {
            phRow.setPv(newValue.doubleValue());
        });

        phLoop.stProperty().addListener((observable, oldValue, newValue) -> {
            phRow.setSt(newValue.doubleValue());
        });

        // Tabla temperature
        AddRow tempRow = new AddRow();
        Loop tempLoop = biorreactor.getLoops().get(1);
        tempRow.setPv(tempLoop.getPv());
        pvTempCol.setCellValueFactory(new PropertyValueFactory<>("pv"));
        tempRow.setSt(tempLoop.getSt());
        stTempCol.setCellValueFactory(new PropertyValueFactory<>("st"));
        tempTable.getItems().add(tempRow);

        tempLoop.pvProperty().addListener((observable, oldValue, newValue) -> {
            tempRow.setPv(newValue.doubleValue());
        });

        tempLoop.stProperty().addListener((observable, oldValue, newValue) -> {
            tempRow.setSt(newValue.doubleValue());
        });

        // Tabla DO
        AddRow doRow = new AddRow();
        Loop doLoop = biorreactor.getLoops().get(2);
        doRow.setPv(doLoop.getPv());
        pvDOCol.setCellValueFactory(new PropertyValueFactory<>("pv"));
        doRow.setSt(doLoop.getSt());
        stDOCol.setCellValueFactory(new PropertyValueFactory<>("st"));
        DOTable.getItems().add(doRow);

        doLoop.pvProperty().addListener((observable, oldValue, newValue) -> {
            doRow.setPv(newValue.doubleValue());
        });

        doLoop.stProperty().addListener((observable, oldValue, newValue) -> {
            doRow.setSt(newValue.doubleValue());
        });

        // Tabla Stirring Rate
        AddRow stirringRow = new AddRow();
        Loop stirringLoop = biorreactor.getLoops().get(3);
        stirringRow.setPv(stirringLoop.getPv());
        pvStirringCol.setCellValueFactory(new PropertyValueFactory<>("pv"));
        stirringRow.setSt(stirringLoop.getSt());
        stStirringCol.setCellValueFactory(new PropertyValueFactory<>("st"));
        stirringTable.getItems().add(stirringRow);

        stirringLoop.pvProperty().addListener((observable, oldValue, newValue) -> {
            stirringRow.setPv(newValue.doubleValue());
        });

        stirringLoop.stProperty().addListener((observable, oldValue, newValue) -> {
            stirringRow.setSt(newValue.doubleValue());
        });

        // Tabla Pump 1
        AddRow pump1Row = new AddRow();
        Pump pump1 = biorreactor.getPumps().get(0);
        pump1Row.setPv(pump1.getPv());
        pvPump1Col.setCellValueFactory(new PropertyValueFactory<>("pv"));
        pump1Row.setSt(pump1.getSt());
        stPump1Col.setCellValueFactory(new PropertyValueFactory<>("st"));
        pump1Table.getItems().add(pump1Row);

        pump1.pvProperty().addListener((observable, oldValue, newValue) -> {
            pump1Row.setPv(newValue.doubleValue());
        });

        pump1.stProperty().addListener((observable, oldValue, newValue) -> {
            pump1Row.setSt(newValue.doubleValue());
        });

        // Tabla Pump 2
        AddRow pump2Row = new AddRow();
        Pump pump2 = biorreactor.getPumps().get(1);
        pump2Row.setPv(pump2.getPv());
        pvPump2Col.setCellValueFactory(new PropertyValueFactory<>("pv"));
        pump2Row.setSt(pump2.getSt());
        stPump2Col.setCellValueFactory(new PropertyValueFactory<>("st"));
        pump2Table.getItems().add(pump2Row);

        pump2.pvProperty().addListener((observable, oldValue, newValue) -> {
            pump2Row.setPv(newValue.doubleValue());
        });

        pump2.stProperty().addListener((observable, oldValue, newValue) -> {
            pump2Row.setSt(newValue.doubleValue());
        });

        // Tabla Pump 3
        AddRow pump3Row = new AddRow();
        Pump pump3 = biorreactor.getPumps().get(2);
        pump3Row.setPv(pump3.getPv());
        pvPump3Col.setCellValueFactory(new PropertyValueFactory<>("pv"));
        pump3Row.setSt(pump3.getSt());
        stPump3Col.setCellValueFactory(new PropertyValueFactory<>("st"));
        pump3Table.getItems().add(pump3Row);

        pump3.pvProperty().addListener((observable, oldValue, newValue) -> {
            pump3Row.setPv(newValue.doubleValue());
        });

        pump3.stProperty().addListener((observable, oldValue, newValue) -> {
            pump3Row.setSt(newValue.doubleValue());
        });
    }


    private void addListeners() {
        start_btn.setOnAction(event -> onStart());
        cropName.setOnAction(event -> fillCropName(cropName.getText()));
        date.setOnAction(event -> fillDate());
    }

    private void fillCropName(String cropName) {
        if (cropName != null && !cropName.isEmpty()) {
            biorreactor.setCropName(cropName);
            this.cropName.setDisable(true);
            System.out.println("\nCrop name: " + biorreactor.getCropName());
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

    private void onPumps() {

        ViewFactory viewFactory = ViewModel.getInstance().getViewFactory();
        ViewModel.getInstance().getViewFactory().getconfigurationSelectedMenuItem().set("Pumps");
        ConfigurationMenuController configMenuController = ViewModel.getInstance().getConfigurationMenuController();
        configMenuController.onPumps();

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

        biorreactor.getPumps().get(0).setSt(biorreactor.getPumps().get(0).getSt()+1);
        biorreactor.getPumps().get(0).setPv(biorreactor.getPumps().get(0).getPv()+2);
        biorreactor.getPumps().get(0).setControlMode(!biorreactor.getPumps().get(0).isControlMode());
        int currentPeriod = biorreactor.getPumps().get(0).getPeriod().get();
        biorreactor.getPumps().get(0).setPeriod(currentPeriod + 1);

        biorreactor.getPumps().get(1).setSt(biorreactor.getPumps().get(1).getSt()+1);
        biorreactor.getPumps().get(1).setPv(biorreactor.getPumps().get(1).getPv()+2);
        biorreactor.getPumps().get(1).setControlMode(!biorreactor.getPumps().get(1).isControlMode());
        currentPeriod = biorreactor.getPumps().get(1).getPeriod().get();
        biorreactor.getPumps().get(1).setPeriod(currentPeriod + 1);

        biorreactor.getPumps().get(2).setSt(biorreactor.getPumps().get(2).getSt()+1);
        biorreactor.getPumps().get(2).setPv(biorreactor.getPumps().get(2).getPv()+2);
        biorreactor.getPumps().get(2).setControlMode(!biorreactor.getPumps().get(2).isControlMode());
        currentPeriod = biorreactor.getPumps().get(2).getPeriod().get();
        biorreactor.getPumps().get(2).setPeriod(currentPeriod + 1);

    }

    private void setButtonStyle(ToggleButton button, String styleClass) {
        button.getStyleClass().removeIf(style -> style.equals("alt_ToggleButton_container") || style.equals("dashboard_container"));
        button.getStyleClass().add(styleClass);
        button.requestFocus();
    }
}