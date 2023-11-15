package com.example.biorreactor.Controllers.Configuration;

import com.example.biorreactor.DataBase.LoopData;
import com.example.biorreactor.DataBase.PersistenceHandler;
import com.example.biorreactor.Models.Biorreactor;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.paint.Color;

public class GraphsController implements Initializable {

    public LineChart<Number, Number> lineChart;
    public ColorPicker ph_colorPicker;
    public ColorPicker temp_colorPicker;
    public ColorPicker stirring_colorPicker;
    public ColorPicker do_colorPicker;
    public Label phSt_label;
    public Label phPv_label;
    public Label tempSt_label;
    public Label tempPv_label;
    public Label doSt_label;
    public Label doPv_label;
    public Label stirringSt_label;
    public Label stirringPV_label;
    public CheckBox ph_checkbox;
    public CheckBox temp_checkbox;
    public CheckBox do_checkbox;
    public CheckBox stirring_checkbox;
    public Spinner from_spinner;
    public Spinner to_spinner;

    private final XYChart.Series<Number, Number> phSeries = new XYChart.Series<>();
    private final XYChart.Series<Number, Number> tempSeries = new XYChart.Series<>();

    private final XYChart.Series<Number, Number> doSeries = new XYChart.Series<>();

    private final XYChart.Series<Number, Number> stirringSeries = new XYChart.Series<>();

    Biorreactor biorreactor = Biorreactor.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        int biorreactorId = 1;
        loadPhDataFromDatabase(biorreactorId);
        loadTempDataFromDatabase(biorreactorId);
        loadDODataFromDatabase(biorreactorId);
        loadStirringDataFromDatabase(biorreactorId);

        phSeries.setName("pH");
        tempSeries.setName("Temperature");
        doSeries.setName("DO");
        stirringSeries.setName("Stirring");

        // Obtener el rango de los datos de las series

        double minTimestamp= minAxis(phSeries, tempSeries, doSeries, stirringSeries);
        double maxTimestamp = maxAxis(phSeries, tempSeries, doSeries, stirringSeries);

        // Configurar el eje X con el rango de datos
        NumberAxis xAxis = (NumberAxis) lineChart.getXAxis();
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(minTimestamp);
        xAxis.setUpperBound(maxTimestamp);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        xAxis.setTickLabelFormatter(new DateAxisFormatter(dateFormatter));


        // Agregar las series al gráfico
        lineChart.getData().addAll(phSeries, tempSeries, doSeries, stirringSeries);

        // Asignar valores pv y st a los loops
        DoubleProperty phStLabelValue = new SimpleDoubleProperty(biorreactor.getLoops().get(0).getSt());
        phSt_label.textProperty().bind(phStLabelValue.asString());

        biorreactor.getLoops().get(0).stProperty().addListener((observable, oldValue, newValue) -> {
            phStLabelValue.set(newValue.doubleValue());
        });

        DoubleProperty phPvLabelValue = new SimpleDoubleProperty(biorreactor.getLoops().get(0).getPv());
        phPv_label.textProperty().bind(phPvLabelValue.asString());

        biorreactor.getLoops().get(0).pvProperty().addListener((observable, oldValue, newValue) -> {
            phPvLabelValue.set(newValue.doubleValue());
        });

        DoubleProperty tempStValue = new SimpleDoubleProperty(biorreactor.getLoops().get(1).getSt());
        tempSt_label.textProperty().bind(tempStValue.asString());

        biorreactor.getLoops().get(1).stProperty().addListener((observable, oldValue, newValue) -> {
            tempStValue.set(newValue.doubleValue());
        });

        DoubleProperty tempPvValue = new SimpleDoubleProperty(biorreactor.getLoops().get(1).getPv());
        tempPv_label.textProperty().bind(tempPvValue.asString());

        biorreactor.getLoops().get(1).pvProperty().addListener((observable, oldValue, newValue) -> {
            tempPvValue.set(newValue.doubleValue());
        });

        DoubleProperty doStValue = new SimpleDoubleProperty(biorreactor.getLoops().get(2).getSt());
        doSt_label.textProperty().bind(doStValue.asString());

        biorreactor.getLoops().get(2).stProperty().addListener((observable, oldValue, newValue) -> {
            doStValue.set(newValue.doubleValue());
        });

        DoubleProperty doPvValue = new SimpleDoubleProperty(biorreactor.getLoops().get(2).getPv());
        doPv_label.textProperty().bind(doPvValue.asString());

        biorreactor.getLoops().get(2).pvProperty().addListener((observable, oldValue, newValue) -> {
            doPvValue.set(newValue.doubleValue());
        });

        DoubleProperty stirringStValue = new SimpleDoubleProperty(biorreactor.getLoops().get(3).getSt());
        stirringSt_label.textProperty().bind(stirringStValue.asString());

        biorreactor.getLoops().get(3).stProperty().addListener((observable, oldValue, newValue) -> {
            stirringStValue.set(newValue.doubleValue());
        });

        DoubleProperty stirringPvValue = new SimpleDoubleProperty(biorreactor.getLoops().get(3).getPv());
        stirringPV_label.textProperty().bind(stirringPvValue.asString());

        biorreactor.getLoops().get(3).pvProperty().addListener((observable, oldValue, newValue) -> {
            stirringPvValue.set(newValue.doubleValue());
        });

        // Configurar listeners para cambios de color
        ph_colorPicker.setOnAction(event -> setSeriesColor(phSeries, ph_colorPicker.getValue()));
        temp_colorPicker.setOnAction(event -> setSeriesColor(tempSeries, temp_colorPicker.getValue()));
        do_colorPicker.setOnAction(event -> setSeriesColor(doSeries, do_colorPicker.getValue()));
        stirring_colorPicker.setOnAction(event -> setSeriesColor(stirringSeries, stirring_colorPicker.getValue()));

        // Configurar listener para cambios en el CheckBox de pH
        ph_checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                phSeries.getNode().setVisible(newValue); // Oculta o muestra la serie basándose en el estado del CheckBox
                for (XYChart.Data<Number, Number> data : phSeries.getData()) {
                    data.getNode().setVisible(newValue); // Oculta o muestra los nodos de la serie
                }
            }
        });

        // Configurar listener para cambios en el CheckBox de Temperature
        temp_checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                tempSeries.getNode().setVisible(newValue); // Oculta o muestra la serie basándose en el estado del CheckBox
                for (XYChart.Data<Number, Number> data : tempSeries.getData()) {
                    data.getNode().setVisible(newValue); // Oculta o muestra los nodos de la serie
                }
            }
        });

        // Configurar listener para cambios en el CheckBox de DO
        do_checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                doSeries.getNode().setVisible(newValue); // Oculta o muestra la serie basándose en el estado del CheckBox
                for (XYChart.Data<Number, Number> data : doSeries.getData()) {
                    data.getNode().setVisible(newValue); // Oculta o muestra los nodos de la serie
                }
            }
        });

        // Configurar listener para cambios en el CheckBox de Stirring Rate
        stirring_checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                stirringSeries.getNode().setVisible(newValue); // Oculta o muestra la serie basándose en el estado del CheckBox
                for (XYChart.Data<Number, Number> data : stirringSeries.getData()) {
                    data.getNode().setVisible(newValue); // Oculta o muestra los nodos de la serie
                }
            }
        });
    }

    private void loadTempDataFromDatabase(int biorreactorId) {
        tempSeries.getData().clear();
        List<LoopData> tempData = PersistenceHandler.getInstance().getLoopData(biorreactorId, biorreactor.getLoops().get(1).getName());
        for (LoopData data : tempData) {
            tempSeries.getData().add(new XYChart.Data<>(data.timestampMinutes(), data.getProcessValue()));
        }
    }

    private void loadDODataFromDatabase(int biorreactorId) {
        doSeries.getData().clear();
        List<LoopData> doData = PersistenceHandler.getInstance().getLoopData(biorreactorId, biorreactor.getLoops().get(2).getName());
        for (LoopData data : doData) {
            doSeries.getData().add(new XYChart.Data<>(data.timestampMinutes(), data.getProcessValue()));
        }
    }

    private void loadStirringDataFromDatabase(int biorreactorId) {
        stirringSeries.getData().clear();
        List<LoopData> stirringData = PersistenceHandler.getInstance().getLoopData(biorreactorId, biorreactor.getLoops().get(3).getName());
        for (LoopData data : stirringData) {
            stirringSeries.getData().add(new XYChart.Data<>(data.timestampMinutes(), data.getProcessValue()));
        }
    }

    private void loadPhDataFromDatabase(int biorreactorId) {
        phSeries.getData().clear();
        List<LoopData> phData = PersistenceHandler.getInstance().getLoopData(biorreactorId, biorreactor.getLoops().get(0).getName());
        for (LoopData data : phData) {
            phSeries.getData().add(new XYChart.Data<>(data.timestampMinutes(), data.getProcessValue()));
        }
    }

    private static class DateAxisFormatter extends javafx.util.StringConverter<Number> {
        private final DateTimeFormatter dateFormatter;

        public DateAxisFormatter(DateTimeFormatter dateFormatter) {
            this.dateFormatter = dateFormatter;
        }

        @Override
        public String toString(Number object) {
            long timestampMillis = object.longValue();
            LocalDateTime dateTime = LocalDateTime.MIN.plusSeconds(timestampMillis);
            return dateTime.format(dateFormatter);
        }

        @Override
        public Number fromString(String string) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    @SafeVarargs
    private double minAxis(XYChart.Series<Number, Number>... seriesArray) {
        List<Double> allTimestamps = new ArrayList<>();
        for (XYChart.Series<Number, Number> series : seriesArray) {
            allTimestamps.addAll(series.getData().stream().map(data -> data.getXValue().doubleValue()).toList());
        }
        return allTimestamps.stream().mapToDouble(Double::doubleValue).min().orElse(0);
    }

    @SafeVarargs
    private double maxAxis(XYChart.Series<Number, Number>... seriesArray) {
        List<Double> allTimestamps = new ArrayList<>();
        for (XYChart.Series<Number, Number> series : seriesArray) {
            allTimestamps.addAll(series.getData().stream().map(data -> data.getXValue().doubleValue()).toList());
        }
        return allTimestamps.stream().mapToDouble(Double::doubleValue).max().orElse(0);
    }

    private void setSeriesColor(XYChart.Series<Number, Number> series, Color color) {
        series.getNode().lookup(".chart-series-line").setStyle("-fx-stroke: " + toRGBCode(color) + ";");
        for (XYChart.Data<Number, Number> data : series.getData()) {
            data.getNode().lookup(".chart-line-symbol").setStyle("-fx-background-color: " + toRGBCode(color) + ", white;");
        }
    }

    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
