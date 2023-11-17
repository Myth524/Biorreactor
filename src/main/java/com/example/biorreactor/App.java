package com.example.biorreactor;

import com.example.biorreactor.Models.Biorreactor;
import com.example.biorreactor.DataBase.PersistenceHandler;
import com.example.biorreactor.Models.ViewModel;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class App extends Application {

    private final Random random = new Random();

    @Override
    public void start(Stage stage) {
        ViewModel viewModel = ViewModel.getInstance();
        viewModel.getViewFactory().showConfigurationWindow();
        Biorreactor biorreactor = Biorreactor.getInstance();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            changePvValues(biorreactor);
        }, 0, 5, TimeUnit.SECONDS);

        Thread alarmThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                biorreactor.checkAlarms();
            }
        });

        alarmThread.setDaemon(true);
        alarmThread.start();
    }

    private void changePvValues(Biorreactor biorreactor) {
        PersistenceHandler persistenceHandler = PersistenceHandler.getInstance();

        for (int i = 0; i < biorreactor.getPumps().size(); i++) {
            double pvChange = generateRandomValue(-5, 5);
            biorreactor.getPumps().get(i).setPv(biorreactor.getPumps().get(i).getPv() + pvChange);
        }

        for (int i = 0; i < biorreactor.getLoops().size(); i++) {
            double pvChange = generateRandomValue(-5, 5);
            biorreactor.getLoops().get(i).setPv(biorreactor.getLoops().get(i).getPv() + pvChange);
        }
        for (int i = 0; i< biorreactor.getLoops().size(); i++){
            persistenceHandler.insertLoop(1, biorreactor.getLoops().get(i).getName(), biorreactor.getLoops().get(i).getSt(), biorreactor.getLoops().get(i).getPv());
        }
    }

    private double generateRandomValue(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
