package com.example.biorreactor.Controllers.Configuration;

import com.example.biorreactor.DataBase.LoopData;
import com.example.biorreactor.DataBase.PersistenceHandler;
import com.example.biorreactor.Models.Biorreactor;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
    public Button zoomIn_btn;
    public Button zoomOut_btn;

    Biorreactor biorreactor = Biorreactor.getInstance();
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        configureTimeRangeSpinners();

        zoomIn_btn.setOnAction(event -> onZoomIn());
        zoomOut_btn.setOnAction(event -> onZoomOut());

        int biorreactorId = 1;
        loadPhDataFromDatabase(biorreactorId);
        loadTempDataFromDatabase(biorreactorId);
        loadDODataFromDatabase(biorreactorId);
        loadStirringDataFromDatabase(biorreactorId);

        biorreactor.getLoops().get(0).pvProperty().addListener((observable, oldValue, newValue) -> {
            updateSeries(biorreactorId,phSeries);
            updateChart();
        });

        biorreactor.getLoops().get(1).pvProperty().addListener((observable, oldValue, newValue) -> {
            updateSeries(biorreactorId, tempSeries);
            updateChart();
        });

        biorreactor.getLoops().get(2).pvProperty().addListener((observable, oldValue, newValue) -> {
            updateSeries(biorreactorId, doSeries);
            updateChart();
        });

        biorreactor.getLoops().get(3).pvProperty().addListener((observable, oldValue, newValue) -> {
            updateSeries(biorreactorId, stirringSeries);
            updateChart();
        });

        phSeries.setName("pH");
        tempSeries.setName("Temperature");
        doSeries.setName("DO");
        stirringSeries.setName("Stirring");

        // Obtener el rango de los datos de las series

        double minTimestamp= minXAxis(phSeries, tempSeries, doSeries, stirringSeries);
        double maxTimestamp = maxXAxis(phSeries, tempSeries, doSeries, stirringSeries);

        // Configurar el eje X con el rango de datos
        NumberAxis xAxis = (NumberAxis) lineChart.getXAxis();
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(minTimestamp);
        xAxis.setUpperBound(maxTimestamp);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        xAxis.setTickLabelFormatter(new DateAxisFormatter(dateFormatter));


        NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();

// Define el rango inicial para el eje Y
        double initialLowerBound = minYAxis(phSeries, tempSeries, doSeries, stirringSeries); // Establece el límite inferior inicial
        double initialUpperBound = maxYAxis(phSeries, tempSeries, doSeries, stirringSeries); // Establece el límite superior inicial

// Configura el rango inicial para el eje Y
        yAxis.setAutoRanging(false); // Desactiva el ajuste automático del rango
        yAxis.setLowerBound(initialLowerBound);
        yAxis.setUpperBound(initialUpperBound);
// Crear un gestor de eventos para el paneo en el gráfico
        EventHandler<MouseEvent> mouseHandler = new EventHandler<>() {
            private double initX, initY;

            @Override
            public void handle(MouseEvent event) {
                if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    initX = event.getSceneX();
                    initY = event.getSceneY();
                } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                    double deltaX = event.getSceneX() - initX;
                    double deltaY = event.getSceneY() - initY;

                    NumberAxis xAxis = (NumberAxis) lineChart.getXAxis();
                    NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();

                    double xOffset = xAxis.getDisplayPosition(xAxis.getLowerBound()) - xAxis.getDisplayPosition(xAxis.getUpperBound());
                    double yOffset = yAxis.getDisplayPosition(yAxis.getLowerBound()) - yAxis.getDisplayPosition(yAxis.getUpperBound());

                    double newXLower = xAxis.getLowerBound() + deltaX * xOffset / lineChart.getWidth();
                    double newXUpper = xAxis.getUpperBound() + deltaX * xOffset / lineChart.getWidth();
                    double newYLower = yAxis.getLowerBound() - deltaY * yOffset / lineChart.getHeight();
                    double newYUpper = yAxis.getUpperBound() - deltaY * yOffset / lineChart.getHeight();

                    // Restricción de los límites basados en los datos disponibles
                    double minX = minXAxis(phSeries, tempSeries, doSeries, stirringSeries);
                    double maxX = maxXAxis(phSeries, tempSeries, doSeries, stirringSeries);
                    double minY = minYAxis(phSeries, tempSeries, doSeries, stirringSeries);
                    double maxY = maxYAxis(phSeries, tempSeries, doSeries, stirringSeries);

                    if (newXLower < minX) {
                        newXLower = minX;
                        newXUpper = minX + (xAxis.getUpperBound() - xAxis.getLowerBound());
                    }

                    if (newXUpper > maxX) {
                        newXUpper = maxX;
                        newXLower = maxX - (xAxis.getUpperBound() - xAxis.getLowerBound());
                    }

                    if (newYLower < minY) {
                        newYLower = minY;
                        newYUpper = minY + (yAxis.getUpperBound() - yAxis.getLowerBound());
                    }

                    if (newYUpper > maxY) {
                        newYUpper = maxY;
                        newYLower = maxY - (yAxis.getUpperBound() - yAxis.getLowerBound());
                    }

                    xAxis.setLowerBound(newXLower);
                    xAxis.setUpperBound(newXUpper);
                    yAxis.setLowerBound(newYLower);
                    yAxis.setUpperBound(newYUpper);

                    initX = event.getSceneX();
                    initY = event.getSceneY();
                }
            }
        };

// Asignar el gestor de eventos al gráfico para el paneo
        lineChart.setOnMousePressed(mouseHandler);
        lineChart.setOnMouseDragged(mouseHandler);
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
        ph_checkbox.setSelected(true); // Marcar por defecto el CheckBox de pH
        ph_checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            phSeries.getNode().setVisible(newValue); // Oculta o muestra la serie basándose en el estado del CheckBox
            for (XYChart.Data<Number, Number> data : phSeries.getData()) {
                data.getNode().setVisible(newValue); // Oculta o muestra los nodos de la serie
            }
        });

        // Configurar listener para cambios en el CheckBox de Temperature
        temp_checkbox.setSelected(true); // Marcar por defecto el CheckBox de Temperature
        temp_checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            tempSeries.getNode().setVisible(newValue); // Oculta o muestra la serie basándose en el estado del CheckBox
            for (XYChart.Data<Number, Number> data : tempSeries.getData()) {
                data.getNode().setVisible(newValue); // Oculta o muestra los nodos de la serie
            }
        });

        // Configurar listener para cambios en el CheckBox de DO
        do_checkbox.setSelected(true); // Marcar por defecto el CheckBox de DO
        do_checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            doSeries.getNode().setVisible(newValue); // Oculta o muestra la serie basándose en el estado del CheckBox
            for (XYChart.Data<Number, Number> data : doSeries.getData()) {
                data.getNode().setVisible(newValue); // Oculta o muestra los nodos de la serie
            }
        });

        // Configurar listener para cambios en el CheckBox de Stirring Rate
        stirring_checkbox.setSelected(true); // Marcar por defecto el CheckBox de Stirring Rate
        stirring_checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            stirringSeries.getNode().setVisible(newValue); // Oculta o muestra la serie basándose en el estado del CheckBox
            for (XYChart.Data<Number, Number> data : stirringSeries.getData()) {
                data.getNode().setVisible(newValue); // Oculta o muestra los nodos de la serie
            }
        });
        executor.scheduleAtFixedRate(this::updateChart, 0, 30, TimeUnit.SECONDS);
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
            long timestampMinutes = object.longValue();
            LocalDateTime dateTime = LocalDateTime.MIN.plusMinutes(timestampMinutes);
            return dateTime.format(dateFormatter);
        }


        @Override
        public Number fromString(String string) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    @SafeVarargs
    private double minXAxis(XYChart.Series<Number, Number>... seriesArray) {
        List<Double> allTimestamps = new ArrayList<>();
        for (XYChart.Series<Number, Number> series : seriesArray) {
            allTimestamps.addAll(series.getData().stream().map(data -> data.getXValue().doubleValue()).toList());
        }
        return allTimestamps.stream().mapToDouble(Double::doubleValue).min().orElse(0);
    }

    @SafeVarargs
    private double minYAxis(XYChart.Series<Number, Number>... seriesArray) {
        List<Double> allTimestamps = new ArrayList<>();
        for (XYChart.Series<Number, Number> series : seriesArray) {
            allTimestamps.addAll(series.getData().stream().map(data -> data.getYValue().doubleValue()).toList());
        }
        return allTimestamps.stream().mapToDouble(Double::doubleValue).min().orElse(0);
    }

    @SafeVarargs
    private double maxXAxis(XYChart.Series<Number, Number>... seriesArray) {
        List<Double> allTimestamps = new ArrayList<>();
        for (XYChart.Series<Number, Number> series : seriesArray) {
            allTimestamps.addAll(series.getData().stream().map(data -> data.getXValue().doubleValue()).toList());
        }
        return allTimestamps.stream().mapToDouble(Double::doubleValue).max().orElse(0);
    }

    @SafeVarargs
    private double maxYAxis(XYChart.Series<Number, Number>... seriesArray) {
        List<Double> allTimestamps = new ArrayList<>();
        for (XYChart.Series<Number, Number> series : seriesArray) {
            allTimestamps.addAll(series.getData().stream().map(data -> data.getYValue().doubleValue()).toList());
        }
        return allTimestamps.stream().mapToDouble(Double::doubleValue).max().orElse(0);
    }

    // Método para acercar el gráfico (zoom in)
    private void onZoomIn() {
        NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();
        double currentLowerBound = yAxis.getLowerBound();
        double currentUpperBound = yAxis.getUpperBound();

        double newLowerBound = currentLowerBound + (currentUpperBound - currentLowerBound) * 0.25; // Zoom in al 25%
        double newUpperBound = currentUpperBound - (currentUpperBound - currentLowerBound) * 0.25;

        yAxis.setLowerBound(newLowerBound);
        yAxis.setUpperBound(newUpperBound);
    }

    // Método para alejar el gráfico (zoom out)
    private void onZoomOut() {
        NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();
        double currentLowerBound = yAxis.getLowerBound();
        double currentUpperBound = yAxis.getUpperBound();

        double newLowerBound = currentLowerBound - (currentUpperBound - currentLowerBound) * 0.25; // Zoom out al 25%
        double newUpperBound = currentUpperBound + (currentUpperBound - currentLowerBound) * 0.25;

        yAxis.setLowerBound(newLowerBound);
        yAxis.setUpperBound(newUpperBound);
    }

    private void setSeriesColor(XYChart.Series<Number, Number> series, Color color) {
        series.getNode().lookup(".chart-series-line").setStyle("-fx-stroke: " + toRGBCode(color) + ";");
        for (XYChart.Data<Number, Number> data : series.getData()) {
            data.getNode().lookup(".chart-line-symbol").setStyle("-fx-background-color: " + toRGBCode(color) + ", white;");
        }
        setSeriesLegendColor(series, color);
    }

    private void setSeriesLegendColor(XYChart.Series<Number, Number> series, Color color) {
        for (Node legendNode : lineChart.lookupAll(".chart-legend-item")) {
            String legendText = ((Label) legendNode).getText();
            if (legendText.equals(series.getName())) {
                Node symbol = legendNode.lookup(".chart-legend-item-symbol");
                if (symbol != null) {
                    symbol.setStyle("-fx-background-color: " + toRGBCode(color) + ";");
                }
                break;
            }
        }
    }

    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    private void updateSeries(int id, XYChart.Series<Number, Number> series ) {
        if(series.equals(phSeries)){
            loadPhDataFromDatabase(id);
        } else if(series.equals(tempSeries)){
            loadTempDataFromDatabase(id);
        } else if(series.equals(doSeries)){
            loadDODataFromDatabase(id);
        } else {
            loadStirringDataFromDatabase(id);
        }
    }

    // Método para actualizar la gráfica con las series actualizadas
    private void updateChart() {
        Platform.runLater(() -> {
            int biorreactorId = 1; // O el ID correspondiente
            loadPhDataFromDatabase(biorreactorId);
            loadTempDataFromDatabase(biorreactorId);
            loadDODataFromDatabase(biorreactorId);
            loadStirringDataFromDatabase(biorreactorId);
        });
    }


    private void updateXAxisRange() {
        // Obtener los valores seleccionados en los Spinners
        int fromValue = (int) from_spinner.getValue();
        int toValue = (int) to_spinner.getValue();

        // Obtener el rango de los datos de las series
        double minTimestamp = minXAxis(phSeries, tempSeries, doSeries, stirringSeries);
        double maxTimestamp = maxXAxis(phSeries, tempSeries, doSeries, stirringSeries);

        // Calcular los nuevos límites del eje X basados en los valores de los Spinners y los datos de las series
        double newLowerBound = minTimestamp + (maxTimestamp - minTimestamp) * (fromValue / 100.0);
        double newUpperBound = minTimestamp + (maxTimestamp - minTimestamp) * (toValue / 100.0);

        // Configurar el eje X con los nuevos límites
        NumberAxis xAxis = (NumberAxis) lineChart.getXAxis();
        xAxis.setLowerBound(newLowerBound);
        xAxis.setUpperBound(newUpperBound);
    }
    private void configureTimeRangeSpinners() {
        // Obtener el eje X
        NumberAxis xAxis = (NumberAxis) lineChart.getXAxis();

        // Definir los valores iniciales y los límites de los Spinners basados en el eje X
        double lowerBound = xAxis.getLowerBound();
        double upperBound = xAxis.getUpperBound();

        SpinnerValueFactory<Integer> fromValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory((int) lowerBound, (int) upperBound, (int) lowerBound);
        SpinnerValueFactory<Integer> toValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory((int) lowerBound, (int) upperBound, (int) upperBound);

        // Asignar los valores iniciales y límites a los Spinners
        from_spinner.setValueFactory(fromValueFactory);
        to_spinner.setValueFactory(toValueFactory);

        // Configurar un listener para actualizar el eje X cuando cambian los valores de los Spinners
        from_spinner.valueProperty().addListener((observable, oldValue, newValue) -> updateXAxisRange());
        to_spinner.valueProperty().addListener((observable, oldValue, newValue) -> updateXAxisRange());
    }

}
