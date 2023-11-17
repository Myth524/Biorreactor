    package com.example.biorreactor.Controllers.Configuration;

    import com.example.biorreactor.Models.Biorreactor;
    import javafx.beans.property.*;
    import javafx.fxml.Initializable;
    import javafx.scene.control.*;
    import javafx.scene.control.Alert.AlertType;
    import javafx.scene.input.KeyCode;
    import javafx.util.converter.NumberStringConverter;

    import java.net.URL;
    import java.util.ResourceBundle;

    public class PumpsController implements Initializable {
        public TextField stText;
        public Label pvUnits;
        public Label stUnits;
        public Label pvLabel;
        public Button calibrate_btn;
        public Label calibratedLabel;
        public Label TotalLabel;
        public Button reset_btn;
        public TextField stText2;
        public Label pvLabel2;
        public Label pvUnits2;
        public Label stUnits2;
        public Button calibrate_btn2;
        public Label calibratedLabel2;
        public Label TotalLabel2;
        public Button reset_btn2;
        public TextField stText3;
        public Label stUnits3;
        public Label pvLabel3;
        public Label pvUnits3;
        public Button calibrate_btn3;
        public Label calibratedLabel3;
        public Label TotalLabel3;
        public Button reset_btn3;
        public Button on_btn;
        public Button off_btn;
        public TextField periodText;
        public TextField periodText3;
        public TextField periodText2;
        public Button on_btn2;
        public Button off_btn2;
        public Button on_btn3;
        public Button off_btn3;

        private DoubleProperty stValue1;
        private DoubleProperty stValue2;
        private DoubleProperty stValue3;
        private BooleanProperty controlModeProperty1;
        private BooleanProperty controlModeProperty2;
        private BooleanProperty controlModeProperty3;
        private IntegerProperty periodValue1;
        private IntegerProperty periodValue2;
        private IntegerProperty periodValue3;

        Biorreactor biorreactor = Biorreactor.getInstance();

        @Override
        public void initialize(URL location, ResourceBundle resources) {

            // Pump 1

            stValue1 = new SimpleDoubleProperty(biorreactor.getPumps().get(0).getSt());
            stText.textProperty().bindBidirectional(stValue1, new NumberStringConverter());

            stText.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    String newValue = stText.getText().trim();
                    if (isValidDouble(newValue)) {
                        stValue1.set(Double.parseDouble(newValue));
                        biorreactor.getPumps().get(0).setSt(stValue1.get());
                        System.out.println(biorreactor.getPumps().get(0).getName() + " st: " + biorreactor.getPumps().get(0).getSt());
                    } else {
                        showErrorAlert();
                    }
                }
            });

            biorreactor.getPumps().get(0).stProperty().addListener((observable, oldValue, newValue) -> {
                stValue1.set(newValue.doubleValue());
            });

            DoubleProperty pvLabel1 = new SimpleDoubleProperty(biorreactor.getPumps().get(0).getPv());
            pvLabel.textProperty().bind(pvLabel1.asString());

            biorreactor.getPumps().get(0).pvProperty().addListener((observable, oldValue, newValue) -> {
                pvLabel1.set(newValue.doubleValue());
            });

            StringProperty stUnits1 = new SimpleStringProperty(biorreactor.getPumps().get(0).getUnits());
            stUnits.textProperty().bind(stUnits1);

            biorreactor.getPumps().get(0).unitsProperty().addListener((observable, oldValue, newValue) -> {
                stUnits1.set(newValue);
            });

            StringProperty pvUnits1 = new SimpleStringProperty(biorreactor.getPumps().get(0).getUnits());
            pvUnits.textProperty().bind(pvUnits1);

            biorreactor.getPumps().get(0).unitsProperty().addListener((observable, oldValue, newValue) -> {
                stUnits1.set(newValue);
            });

            controlModeProperty1 = new SimpleBooleanProperty(biorreactor.getPumps().get(0).isControlMode());

            biorreactor.getPumps().get(0).controlModeProperty().addListener((observable, oldValue, newValue) -> {
                controlModeProperty1.set((newValue));
            });

            on_btn.setOnAction(event -> {
                controlModeProperty1.set(true);
                biorreactor.getPumps().get(0).setControlMode(controlModeProperty1.get());
                System.out.println(biorreactor.getPumps().get(0).getName() + " Control mode: " + biorreactor.getPumps().get(0).isControlMode());

                if (on_btn.getStyleClass().contains("alt2_ToggleButton_container")) {
                    return;
                }

                if (on_btn.getStyleClass().contains("alt_ToggleButton_container")) {
                    on_btn.getStyleClass().remove("alt_ToggleButton_container");
                    on_btn.getStyleClass().add("alt2_ToggleButton_container");
                } else {
                    on_btn.getStyleClass().remove("alt2_ToggleButton_container");
                    on_btn.getStyleClass().add("alt_ToggleButton_container");
                }

                // Cambiar el estilo de off_btn
                if (off_btn.getStyleClass().contains("alt_ToggleButton_container")) {
                    off_btn.getStyleClass().remove("alt_ToggleButton_container");
                    off_btn.getStyleClass().add("alt2_ToggleButton_container");
                } else {
                    off_btn.getStyleClass().remove("alt2_ToggleButton_container");
                    off_btn.getStyleClass().add("alt_ToggleButton_container");
                }
            });

            off_btn.setOnAction(event -> {
                controlModeProperty1.set(false);
                biorreactor.getPumps().get(0).setControlMode(controlModeProperty1.get());
                System.out.println(biorreactor.getPumps().get(0).getName() + " Control mode: " + biorreactor.getPumps().get(0).isControlMode());

                if (off_btn.getStyleClass().contains("alt2_ToggleButton_container")) {
                    return;
                }

                if (off_btn.getStyleClass().contains("alt_ToggleButton_container")) {
                    off_btn.getStyleClass().remove("alt_ToggleButton_container");
                    off_btn.getStyleClass().add("alt2_ToggleButton_container");
                } else {
                    off_btn.getStyleClass().remove("alt2_ToggleButton_container");
                    off_btn.getStyleClass().add("alt_ToggleButton_container");
                }

                // Cambiar el estilo de on_btn
                if (on_btn.getStyleClass().contains("alt_ToggleButton_container")) {
                    on_btn.getStyleClass().remove("alt_ToggleButton_container");
                    on_btn.getStyleClass().add("alt2_ToggleButton_container");
                } else {
                    on_btn.getStyleClass().remove("alt2_ToggleButton_container");
                    on_btn.getStyleClass().add("alt_ToggleButton_container");
                }
            });

            periodValue1 = new SimpleIntegerProperty(biorreactor.getPumps().get(0).getPeriod().get());
            periodText.textProperty().bindBidirectional(periodValue1, new NumberStringConverter());

            periodText.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    String newValue = periodText.getText().trim();
                    if (isValidInteger(newValue)) {
                        periodValue1.set(Integer.parseInt(newValue));
                        biorreactor.getPumps().get(0).setPeriod(periodValue1.get());
                        System.out.println(biorreactor.getPumps().get(0).getName() + " period: " + biorreactor.getPumps().get(0).getPeriod().get());
                    } else {
                        showErrorAlert();
                    }
                }
            });

            biorreactor.getPumps().get(0).periodProperty().addListener((observable, oldValue, newValue) -> {
                periodValue1.set((newValue.intValue()));
            });

            // Pump 2

            stValue2 = new SimpleDoubleProperty(biorreactor.getPumps().get(1).getSt());
            stText2.textProperty().bindBidirectional(stValue2, new NumberStringConverter());

            stText2.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    String newValue = stText2.getText().trim();
                    if (isValidDouble(newValue)) {
                        stValue2.set(Double.parseDouble(newValue));
                        biorreactor.getPumps().get(1).setSt(stValue2.get());
                        System.out.println(biorreactor.getPumps().get(1).getName() + " st: " + biorreactor.getPumps().get(1).getSt());
                    } else {
                        showErrorAlert();
                    }
                }
            });

            biorreactor.getPumps().get(1).stProperty().addListener((observable, oldValue, newValue) -> {
                stValue2.set(newValue.doubleValue());
            });

            DoubleProperty pvLabelValue2 = new SimpleDoubleProperty(biorreactor.getPumps().get(1).getPv());
            pvLabel2.textProperty().bind(pvLabelValue2.asString());

            biorreactor.getPumps().get(1).pvProperty().addListener((observable, oldValue, newValue) -> {
                pvLabelValue2.set(newValue.doubleValue());
            });

            StringProperty stUnitsValue2 = new SimpleStringProperty(biorreactor.getPumps().get(1).getUnits());
            stUnits2.textProperty().bind(stUnitsValue2);

            biorreactor.getPumps().get(1).unitsProperty().addListener((observable, oldValue, newValue) -> {
                stUnitsValue2.set(newValue);
            });

            StringProperty pvUnitsValue2 = new SimpleStringProperty(biorreactor.getPumps().get(1).getUnits());
            pvUnits2.textProperty().bind(pvUnitsValue2);

            biorreactor.getPumps().get(1).unitsProperty().addListener((observable, oldValue, newValue) -> {
                stUnitsValue2.set(newValue);
            });

            controlModeProperty2 = new SimpleBooleanProperty(biorreactor.getPumps().get(1).isControlMode());

            biorreactor.getPumps().get(1).controlModeProperty().addListener((observable, oldValue, newValue) -> {
                controlModeProperty2.set(newValue);
            });

            on_btn2.setOnAction(event -> {
                controlModeProperty2.set(true);
                biorreactor.getPumps().get(1).setControlMode(controlModeProperty2.get());
                System.out.println(biorreactor.getPumps().get(1).getName() + " Control mode: " + biorreactor.getPumps().get(1).isControlMode());

                if (on_btn2.getStyleClass().contains("alt2_ToggleButton_container")) {
                    return;
                }

                if (on_btn2.getStyleClass().contains("alt_ToggleButton_container")) {
                    on_btn2.getStyleClass().remove("alt_ToggleButton_container");
                    on_btn2.getStyleClass().add("alt2_ToggleButton_container");
                } else {
                    on_btn2.getStyleClass().remove("alt2_ToggleButton_container");
                    on_btn2.getStyleClass().add("alt_ToggleButton_container");
                }

                // Cambiar el estilo de off_btn
                if (off_btn2.getStyleClass().contains("alt_ToggleButton_container")) {
                    off_btn2.getStyleClass().remove("alt_ToggleButton_container");
                    off_btn2.getStyleClass().add("alt2_ToggleButton_container");
                } else {
                    off_btn2.getStyleClass().remove("alt2_ToggleButton_container");
                    off_btn2.getStyleClass().add("alt_ToggleButton_container");
                }
            });

            off_btn2.setOnAction(event -> {
                controlModeProperty2.set(false);
                biorreactor.getPumps().get(1).setControlMode(controlModeProperty2.get());
                System.out.println(biorreactor.getPumps().get(1).getName() + " Control mode: " + biorreactor.getPumps().get(1).isControlMode());

                if (off_btn2.getStyleClass().contains("alt2_ToggleButton_container")) {
                    return;
                }

                if (off_btn2.getStyleClass().contains("alt_ToggleButton_container")) {
                    off_btn2.getStyleClass().remove("alt_ToggleButton_container");
                    off_btn2.getStyleClass().add("alt2_ToggleButton_container");
                } else {
                    off_btn2.getStyleClass().remove("alt2_ToggleButton_container");
                    off_btn2.getStyleClass().add("alt_ToggleButton_container");
                }

                // Cambiar el estilo de on_btn
                if (on_btn2.getStyleClass().contains("alt_ToggleButton_container")) {
                    on_btn2.getStyleClass().remove("alt_ToggleButton_container");
                    on_btn2.getStyleClass().add("alt2_ToggleButton_container");
                } else {
                    on_btn2.getStyleClass().remove("alt2_ToggleButton_container");
                    on_btn2.getStyleClass().add("alt_ToggleButton_container");
                }
            });

            periodValue2 = new SimpleIntegerProperty(biorreactor.getPumps().get(1).getPeriod().get());
            periodText2.textProperty().bindBidirectional(periodValue2, new NumberStringConverter());

            periodText2.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    String newValue = periodText2.getText().trim();
                    if (isValidInteger(newValue)) {
                        periodValue2.set(Integer.parseInt(newValue));
                        biorreactor.getPumps().get(1).setPeriod(periodValue2.get());
                        System.out.println(biorreactor.getPumps().get(1).getName() + " period: " + biorreactor.getPumps().get(1).getPeriod().get());
                    } else {
                        showErrorAlert();
                    }
                }
            });

            biorreactor.getPumps().get(1).periodProperty().addListener((observable, oldValue, newValue) -> {
                periodValue2.set(newValue.intValue());
            });

// Pump 3

            stValue3 = new SimpleDoubleProperty(biorreactor.getPumps().get(2).getSt());
            stText3.textProperty().bindBidirectional(stValue3, new NumberStringConverter());

            stText3.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    String newValue = stText3.getText().trim();
                    if (isValidDouble(newValue)) {
                        stValue3.set(Double.parseDouble(newValue));
                        biorreactor.getPumps().get(2).setSt(stValue3.get());
                        System.out.println(biorreactor.getPumps().get(2).getName() + " st: " + biorreactor.getPumps().get(2).getSt());
                    } else {
                        showErrorAlert();
                    }
                }
            });

            biorreactor.getPumps().get(2).stProperty().addListener((observable, oldValue, newValue) -> {
                stValue3.set(newValue.doubleValue());
            });

            DoubleProperty pvLabelValue3 = new SimpleDoubleProperty(biorreactor.getPumps().get(2).getPv());
            pvLabel3.textProperty().bind(pvLabelValue3.asString());

            biorreactor.getPumps().get(2).pvProperty().addListener((observable, oldValue, newValue) -> {
                pvLabelValue3.set(newValue.doubleValue());
            });

            StringProperty stUnitsValue3 = new SimpleStringProperty(biorreactor.getPumps().get(2).getUnits());
            stUnits3.textProperty().bind(stUnitsValue3);

            biorreactor.getPumps().get(2).unitsProperty().addListener((observable, oldValue, newValue) -> {
                stUnitsValue3.set(newValue);
            });

            StringProperty pvUnitsValue3 = new SimpleStringProperty(biorreactor.getPumps().get(2).getUnits());
            pvUnits3.textProperty().bind(pvUnitsValue3);

            biorreactor.getPumps().get(2).unitsProperty().addListener((observable, oldValue, newValue) -> {
                stUnitsValue3.set(newValue);
            });

            controlModeProperty3 = new SimpleBooleanProperty(biorreactor.getPumps().get(2).isControlMode());

            biorreactor.getPumps().get(2).controlModeProperty().addListener((observable, oldValue, newValue) -> {
                controlModeProperty3.set(newValue);
            });

            on_btn3.setOnAction(event -> {
                controlModeProperty3.set(true);
                biorreactor.getPumps().get(2).setControlMode(controlModeProperty3.get());
                System.out.println(biorreactor.getPumps().get(2).getName() + "Control mode: " + biorreactor.getPumps().get(2).isControlMode());

                if (on_btn3.getStyleClass().contains("alt2_ToggleButton_container")) {
                    return;
                }

                if (on_btn3.getStyleClass().contains("alt_ToggleButton_container")) {
                    on_btn3.getStyleClass().remove("alt_ToggleButton_container");
                    on_btn3.getStyleClass().add("alt2_ToggleButton_container");
                } else {
                    on_btn3.getStyleClass().remove("alt2_ToggleButton_container");
                    on_btn3.getStyleClass().add("alt_ToggleButton_container");
                }

                // Cambiar el estilo de off_btn
                if (off_btn3.getStyleClass().contains("alt_ToggleButton_container")) {
                    off_btn3.getStyleClass().remove("alt_ToggleButton_container");
                    off_btn3.getStyleClass().add("alt2_ToggleButton_container");
                } else {
                    off_btn3.getStyleClass().remove("alt2_ToggleButton_container");
                    off_btn3.getStyleClass().add("alt_ToggleButton_container");
                }
            });

            off_btn3.setOnAction(event -> {
                controlModeProperty3.set(false);
                biorreactor.getPumps().get(2).setControlMode(controlModeProperty3.get());
                System.out.println(biorreactor.getPumps().get(2).getName() + " Control mode: " + biorreactor.getPumps().get(2).isControlMode());

                if (off_btn3.getStyleClass().contains("alt2_ToggleButton_container")) {
                    return;
                }

                if (off_btn3.getStyleClass().contains("alt_ToggleButton_container")) {
                    off_btn3.getStyleClass().remove("alt_ToggleButton_container");
                    off_btn3.getStyleClass().add("alt2_ToggleButton_container");
                } else {
                    off_btn3.getStyleClass().remove("alt2_ToggleButton_container");
                    off_btn3.getStyleClass().add("alt_ToggleButton_container");
                }

                // Cambiar el estilo de on_btn
                if (on_btn3.getStyleClass().contains("alt_ToggleButton_container")) {
                    on_btn3.getStyleClass().remove("alt_ToggleButton_container");
                    on_btn3.getStyleClass().add("alt2_ToggleButton_container");
                } else {
                    on_btn3.getStyleClass().remove("alt2_ToggleButton_container");
                    on_btn3.getStyleClass().add("alt_ToggleButton_container");
                }
            });

            periodValue3 = new SimpleIntegerProperty(biorreactor.getPumps().get(2).getPeriod().get());
            periodText3.textProperty().bindBidirectional(periodValue3, new NumberStringConverter());

            periodText3.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    String newValue = periodText3.getText().trim();
                    if (isValidInteger(newValue)) {
                        periodValue3.set(Integer.parseInt(newValue));
                        biorreactor.getPumps().get(2).setPeriod(periodValue3.get());
                        System.out.println(biorreactor.getPumps().get(2).getName() + " period: " + biorreactor.getPumps().get(2).getPeriod().get());
                    } else {
                        showErrorAlert();
                    }
                }
            });

            biorreactor.getPumps().get(2).periodProperty().addListener((observable, oldValue, newValue) -> {
                periodValue3.set(newValue.intValue());
            });

        }

        // Agregar el cambio de estilo al boton

        private boolean isValidInteger(String input) {
            try {
                Integer.parseInt(input);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        private boolean isValidDouble(String input) {
            try {
                Double.parseDouble(input);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        private void showErrorAlert() {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Ingrese un dato válido");
            alert.showAndWait();
        }
    }
