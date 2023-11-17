package com.example.biorreactor.Controllers.Historic;

import com.example.biorreactor.DataBase.BiorreactorData;
import com.example.biorreactor.DataBase.PersistenceHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HistoricController implements Initializable {

    public ListView<BiorreactorData> listView;
    public Button open_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Obtener la instancia de PersistenceHandler
        PersistenceHandler persistenceHandler = PersistenceHandler.getInstance();

        // Obtener la lista de biorreactores desde PersistenceHandler
        List<BiorreactorData> biorreactors = persistenceHandler.getAllBiorreactors();

        // Configurar la ListView para mostrar los objetos BiorreactorData
        listView.getItems().addAll(biorreactors);

        // Configurar cómo se muestra cada elemento en la ListView
        listView.setCellFactory(param -> new ListCell<BiorreactorData>() {
            @Override
            protected void updateItem(BiorreactorData item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText("ID: " + item.getId() + ", Crop name: " + item.getName() + ", Date: " + item.getDate());
                }
            }
        });

        // Agregar el listener para obtener el elemento seleccionado
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                BiorreactorData selected = newValue;
                int biorreactorId = selected.getId();
                System.out.println("Selected Biorreactor ID: " + biorreactorId + " Selected Biorreactor: " + selected.getName());
            }
        });
    }
}
