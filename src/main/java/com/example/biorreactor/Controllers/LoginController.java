package com.example.biorreactor.Controllers;

import com.example.biorreactor.DataBase.PersistenceHandler;
import com.example.biorreactor.Models.ViewModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField username_textField;
    public Label error_label;
    public PasswordField password;
    public Label error_label2;
    public Button login_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        error_label.setVisible(false);
        error_label2.setVisible(false);
        login_btn.setOnAction(event -> onLogin());
    }

    private void onLogin() {
        String username = username_textField.getText();
        String enteredPassword = password.getText();

        boolean isAuthenticated = PersistenceHandler.getInstance().authenticateUser(username, enteredPassword);

        if (isAuthenticated) {
            Stage stage = (Stage) error_label.getScene().getWindow();
            ViewModel.getInstance().getViewFactory().closeStage(stage);
            ViewModel.getInstance().getViewFactory().showLandingPageWindow();
        } else {
            error_label.setVisible(true);
            error_label2.setVisible(true);
        }
    }

}
