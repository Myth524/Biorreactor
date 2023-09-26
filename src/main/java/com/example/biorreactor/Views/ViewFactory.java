package com.example.biorreactor.Views;

import com.example.biorreactor.Controllers.Configuration.ConfigurationController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {

    private final StringProperty configurationSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane alarmsView;
    private AnchorPane graphsView;

    public ViewFactory (){
        this.configurationSelectedMenuItem = new SimpleStringProperty("");

    }

    public StringProperty getconfigurationSelectedMenuItem(){
        return configurationSelectedMenuItem;
    }


    /** Configuration view **/
    public AnchorPane getDashboardView(){
        if(dashboardView==null){
            try{
                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/Configuration/Dashboard.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return dashboardView;
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
    /** Main Views **/
    public void showLandingPageWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/LandingPage.fxml"));
        createStage(loader);
    }
    public void showConfigurationWindow(){
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("/Fxml/Configuration/Configuration.fxml"));
        ConfigurationController configurationController =  new ConfigurationController();
        loader.setController(configurationController);
        createStage(loader);
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
