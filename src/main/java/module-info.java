module com.example.biorreactor {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens com.example.biorreactor to javafx.fxml;
    exports com.example.biorreactor;
    exports com.example.biorreactor.Controllers;
    exports com.example.biorreactor.DataBase;
    exports com.example.biorreactor.Controllers.Configuration;
    exports com.example.biorreactor.Controllers.Historic;
    exports  com.example.biorreactor.Models;
    exports com.example.biorreactor.Views;
    opens com.example.biorreactor.Controllers to javafx.fxml;
}