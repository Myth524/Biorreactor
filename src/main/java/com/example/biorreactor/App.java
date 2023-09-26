package com.example.biorreactor;

import com.example.biorreactor.Models.ViewModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        ViewModel.getInstance().getViewFactory().showLandingPageWindow();
    }

}
