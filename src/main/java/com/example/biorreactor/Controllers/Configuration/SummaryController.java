package com.example.biorreactor.Controllers.Configuration;

import com.example.biorreactor.Models.DataModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
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
    public TableView<DataModel> summary_table;
    @FXML
    public TableColumn<DataModel, String> loopNameCol;
    @FXML
    public TableColumn<DataModel, Double> pvCol;
    @FXML
    public TableColumn<DataModel, Double> stCol;
    @FXML
    public TableColumn<DataModel, Boolean> controlCol;
    @FXML
    public TableColumn<DataModel, String> unitsCol;

    ObservableList<DataModel> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Loopnames
        loopNameCol.setCellValueFactory(cellData -> {
            int rowIndex = cellData.getTableView().getItems().indexOf(cellData.getValue());
            if (rowIndex >= 0 && rowIndex < cellData.getValue().getLoopNames().size()) {
                return cellData.getValue().getLoopNames().get(rowIndex);
            }
            return new SimpleStringProperty("");
        });

        // SetPoints
        stCol.setCellValueFactory(cellData -> {
            int rowIndex = cellData.getTableView().getItems().indexOf(cellData.getValue());
            if (rowIndex >= 0 && rowIndex < cellData.getValue().getSt().size()) {
                return cellData.getValue().getStProperty(rowIndex);
            }
            return new SimpleDoubleProperty(0).asObject();
        });

        // PV
        pvCol.setCellValueFactory(cellData -> {
            int rowIndex = cellData.getTableView().getItems().indexOf(cellData.getValue());
            if (rowIndex >= 0 && rowIndex < cellData.getValue().getPv().size()) {
                return cellData.getValue().getPvProperty(rowIndex);
            }
            return new SimpleDoubleProperty(0).asObject();
        });

        // Control Mode
        controlCol.setCellValueFactory(cellData -> {
            int rowIndex = cellData.getTableView().getItems().indexOf(cellData.getValue());
            if (rowIndex >= 0 && rowIndex < cellData.getValue().getControl().size()) {
                return cellData.getValue().getControlProperty(rowIndex);
            }
            return new SimpleBooleanProperty(false).asObject();
        });

        // Units
        unitsCol.setCellValueFactory(cellData -> {
            int rowIndex = cellData.getTableView().getItems().indexOf(cellData.getValue());
            if (rowIndex >= 0 && rowIndex < cellData.getValue().getUnits().size()) {
                return cellData.getValue().getUnitsProperty().get(rowIndex);
            }
            return new SimpleStringProperty("");
        });

        // Agregar filas a la tabla
        for (int i = 0; i < 6; i++) {
            list.add(new DataModel());
        }

        summary_table.setItems(list);
    }


}
