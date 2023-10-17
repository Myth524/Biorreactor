package com.example.biorreactor.Models;

import com.example.biorreactor.Controllers.Configuration.ConfigurationMenuController;
import com.example.biorreactor.Views.ViewFactory;

public class ViewModel {

    private static ViewModel viewModel;
    private final ViewFactory viewFactory;
    private  ConfigurationMenuController configurationMenuController;

    private ViewModel() {
        this.viewFactory = new ViewFactory();
    }

    public static synchronized ViewModel getInstance(){
        if(viewModel==null){
            viewModel = new ViewModel();
        }
        return viewModel;
    }

    public ViewFactory getViewFactory(){
        return viewFactory;
    }

    public void setConfigurationMenuController(ConfigurationMenuController controller) {
        this.configurationMenuController = controller;
    }

    public ConfigurationMenuController getConfigurationMenuController() {
        return configurationMenuController;
    }

}
