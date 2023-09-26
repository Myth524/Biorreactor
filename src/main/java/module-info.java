module com.example.biorreactor {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;


    opens com.example.biorreactor to javafx.fxml;
    exports com.example.biorreactor;
    exports com.example.biorreactor.Controllers;
    exports com.example.biorreactor.Controllers.Configuration;
    exports com.example.biorreactor.Controllers.Historic;
    exports  com.example.biorreactor.Models;
    exports com.example.biorreactor.Views;
}