package pl.pwn.reaktor.dziekanat.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label lblLogin;

    @FXML
    private Label lblPassword;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtPassword;

    @FXML
    private PasswordField pswFieldPassword;

    @FXML
    private Button btnShow;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnGuest;

    @FXML
    private Button btnSignIn;

    @FXML
    void ContinueAsGuestEvent(MouseEvent event) {

    }

    @FXML
    void LogInEvent(MouseEvent event) throws IOException {
        Stage primaryStage = DziekanatMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/userView.fxml")); // wskazujemy nasz widok FXML
        primaryStage.setTitle("User");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    void ShowPasswordEvent(MouseEvent event) {
        if("SHOW".equalsIgnoreCase(btnShow.getText())){
            txtPassword.setText(pswFieldPassword.getText());
            txtPassword.setVisible(true);
            pswFieldPassword.setVisible(false);
            btnShow.setText("HIDE");

        }else if("HIDE".equalsIgnoreCase(btnShow.getText())){
            pswFieldPassword.setText(txtPassword.getText());
            txtPassword.setVisible(false);
            pswFieldPassword.setVisible(true);
            btnShow.setText("SHOW");
        }

    }

    @FXML
    void SignInEvent(MouseEvent event) {

    }

    @FXML
    void loginKeyAction(KeyEvent event) {

    }

}
