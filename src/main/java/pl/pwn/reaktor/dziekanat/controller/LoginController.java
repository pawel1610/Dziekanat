package pl.pwn.reaktor.dziekanat.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.RoleEnum;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.model.utiils.CurrentUser;
import pl.pwn.reaktor.dziekanat.service.LoginService;

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
    void continueAsGuestEvent(MouseEvent event) throws IOException {
        Stage primaryStage = DziekanatMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/guestView.fxml")); // wskazujemy nasz widok FXML
        primaryStage.setTitle("Guest");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    void logInEvent(InputEvent event) throws IOException {

        String login = txtLogin.getText();
        String pass = pswFieldPassword.isVisible() ? pswFieldPassword.getText():txtPassword.getText();
        LoginService loginService = new LoginService();
        User user =loginService.login(login,pass);

        if (user!= null) {
            RoleEnum role = user.getRole();
            System.out.println("Zalogowano użytkownika " + login + " o roli: " + role);
            CurrentUser.setCurrentUser(user);

            if (RoleEnum.ROLE_STUDENT.equals(role)) {
                Stage primaryStage = DziekanatMain.getPrimaryStage();
                Parent root = FXMLLoader.load(getClass().getResource("/view/userView.fxml")); // wskazujemy nasz widok FXML
                primaryStage.setTitle("User");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();

            } else if (RoleEnum.ROLE_ADMIN.equals(role)) {
                Stage primaryStage = DziekanatMain.getPrimaryStage();
                Parent root = FXMLLoader.load(getClass().getResource("/view/adminView.fxml")); // wskazujemy nasz widok FXML
                primaryStage.setTitle("Admin");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            }
        }

    }

    @FXML
    void showPasswordEvent(MouseEvent event) {
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
    void signInEvent(MouseEvent event) throws IOException {
        Stage primaryStage = DziekanatMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/signInView.fxml")); // wskazujemy nasz widok FXML
        primaryStage.setTitle("Sign In");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    void loginKeyAction(KeyEvent event) throws IOException {
        if (KeyCode.ENTER.equals(event.getCode())) {
            logInEvent(event);
        }
    }


}
