package pl.pwn.reaktor.dziekanat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class DziekanatController {

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
    void LogInEvent(MouseEvent event) {

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
