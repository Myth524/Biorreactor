package com.example.biorreactor;

import com.example.biorreactor.Models.Biorreactor;
import com.example.biorreactor.Models.ViewModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        ViewModel viewModel = ViewModel.getInstance();
        viewModel.getViewFactory().showLoginWindow();

        // Crear y ejecutar un hilo para revisar las alarmas constantemente
        Thread alarmThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Obtener el biorreactor y verificar las alarmas
                Biorreactor.getInstance().checkAlarms();
            }
        });

        alarmThread.setDaemon(true);
        alarmThread.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
