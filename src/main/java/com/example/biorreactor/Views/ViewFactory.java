package com.example.biorreactor.Views;

import com.example.biorreactor.Controllers.Configuration.AlarmSettingsController;
import com.example.biorreactor.Controllers.Configuration.ConfigurationController;
import com.example.biorreactor.Controllers.Configuration.PerGaugeController;
import com.example.biorreactor.Controllers.Historic.HistoricController;
import com.example.biorreactor.Models.Loop;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {

    private final StringProperty configurationSelectedMenuItem;
    private AnchorPane synopticView;
    private AnchorPane alarmsView;
    private AnchorPane graphsView;
    private AnchorPane pumpsView;
    private AnchorPane gaugeView;
    private AnchorPane summaryView;
    private AnchorPane calibrationView;
    private AnchorPane confLanding;

    public ViewFactory (){
        this.configurationSelectedMenuItem = new SimpleStringProperty("");

    }

    public StringProperty getconfigurationSelectedMenuItem(){
        return configurationSelectedMenuItem;
    }


    /** Configuration view **/
    public AnchorPane getConfLandingView(){
        if(confLanding==null){
            try{
                confLanding = new FXMLLoader(getClass().getResource("/Fxml/Configuration/LandingPage.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return confLanding;
    }

    public AnchorPane getSynopticView(){
        if(synopticView==null){
            try{
                synopticView = new FXMLLoader(getClass().getResource("/Fxml/Configuration/Synoptic.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return synopticView;
    }

    public AnchorPane getAlarmsView(){
        if(alarmsView==null){
            try{
                alarmsView = new FXMLLoader(getClass().getResource("/Fxml/Configuration/Alarms.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return alarmsView;
    }

    public AnchorPane getGraphsView(){
        if(graphsView==null){
            try{
                graphsView = new FXMLLoader(getClass().getResource("/Fxml/Configuration/Graphs.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return graphsView;
    }

    public AnchorPane getPumpsView(){
        if(pumpsView == null){
            try{
                pumpsView = new FXMLLoader(getClass().getResource("/Fxml/Configuration/Pumps.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return pumpsView;
    }

    public AnchorPane getGaugeView(){
        if(gaugeView == null){
            try{
                gaugeView = new FXMLLoader(getClass().getResource("/Fxml/Configuration/Gauge.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return gaugeView;
    }

    public AnchorPane getSummaryView(){
        if(summaryView == null){
            try{
                summaryView = new FXMLLoader(getClass().getResource("/Fxml/Configuration/Summary.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return summaryView;
    }

    public AnchorPane getCalibrationView(){
        if(calibrationView == null){
            try{
                calibrationView = new FXMLLoader(getClass().getResource("/Fxml/Configuration/Calibration.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return calibrationView;
    }

    /** Main Views **/
    public void showLandingPageWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/LandingPage.fxml"));
        createStage(loader);
    }
    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }
    public void showConfigurationWindow(){
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("/Fxml/Configuration/Configuration.fxml"));
        ConfigurationController configurationController =  new ConfigurationController();
        loader.setController(configurationController);
        createStage(loader);
    }
    public void showHistoricView(){
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("/Fxml/Historic/Historic.fxml"));
        HistoricController historicController =  new HistoricController();
        loader.setController(historicController);
        createStage(loader);
    }
    public void showPerGaugeWindow(Loop selectedLoop) {
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("/Fxml/Configuration/PerGauge.fxml"));
        PerGaugeController perGaugeController = new PerGaugeController();
        loader.setController(perGaugeController);
        createStage(loader);
        perGaugeController.initData(selectedLoop);
    }

    public void showAlarmSettingsWindow(Loop selectedLoop) {
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("/Fxml/Configuration/AlarmSettings.fxml"));
        AlarmSettingsController alarmSettingsController = new AlarmSettingsController();
        loader.setController(alarmSettingsController);
        createStage(loader);
        alarmSettingsController.initData(selectedLoop);
    }

    /** Aux methods **/
    private void createStage (FXMLLoader loader) {
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
        } catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Biorreactor");
        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
    }
}
