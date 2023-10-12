package com.example.biorreactor.Controllers.Configuration;

import com.example.biorreactor.Models.DataModel;
import com.example.biorreactor.Models.SummaryDataRow;
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
    public TableView<SummaryDataRow> summary_table;
    @FXML
    public TableColumn<SummaryDataRow, String> loopNameCol;
    @FXML
    public TableColumn<SummaryDataRow, Double> pvCol;
    @FXML
    public TableColumn<SummaryDataRow, Double> stCol;
    @FXML
    public TableColumn<SummaryDataRow, Boolean> controlCol;
    @FXML
    public TableColumn<SummaryDataRow, String> unitsCol;

    DataModel dataModel = DataModel.getInstance();

    ObservableList<SummaryDataRow> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < dataModel.getUnits().size(); i++) {
            SummaryDataRow row = new SummaryDataRow();
            row.setLoopName(dataModel.getLoopNames().get(i).get());
            row.setPv(dataModel.getPvProperty(i).get());
            row.setSt(dataModel.getStProperty(i).get());
            row.setControl(dataModel.getControlProperty(i).get());
            row.setUnits(dataModel.getUnitsProperty().get(i).get());
            list.add(row);
        }

        loopNameCol.setCellValueFactory(new PropertyValueFactory<>("loopName"));
        pvCol.setCellValueFactory(new PropertyValueFactory<>("pv"));
        stCol.setCellValueFactory(new PropertyValueFactory<>("st"));
        controlCol.setCellValueFactory(new PropertyValueFactory<>("control"));
        unitsCol.setCellValueFactory(new PropertyValueFactory<>("units"));

        summary_table.setItems(list);
    }
}
