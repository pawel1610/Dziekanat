package pl.pwn.reaktor.dziekanat.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.service.SignInService;

import java.io.IOException;

public class SignInController {

    @FXML
    private Label lblSignInLogin;

    @FXML
    private Label lblSignInPassword;

    @FXML
    private Label lblSignInComparePassword;

    @FXML
    private Button btnShowPsw;

    @FXML
    private Button btnShowConfPsw;

    @FXML
    private Button btnSignIn;

    @FXML
    private Button btnCancell;

    @FXML
    private TextField tfLogin;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfConfPassword;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private PasswordField pfConfPassword;

    @FXML
    void cancelSignIn(MouseEvent event) throws IOException {

        Stage primaryStage = DziekanatMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    void showConfPsw(MouseEvent event) {
        if("show".equalsIgnoreCase(btnShowConfPsw.getText())){
            tfConfPassword.setText(pfConfPassword.getText());
            tfConfPassword.setVisible(true);
            pfConfPassword.setVisible(false);
            btnShowConfPsw.setText("hide");

        }else if("hide".equalsIgnoreCase(btnShowConfPsw.getText())){
            pfConfPassword.setText(pfConfPassword.getText());
            pfConfPassword.setVisible(true);
            tfConfPassword.setVisible(false);
            btnShowConfPsw.setText("show");
        }
    }

    @FXML
    void showPsw(MouseEvent event) {
        if("show".equalsIgnoreCase(btnShowPsw.getText())){
            tfPassword.setText(pfPassword.getText());
            tfPassword.setVisible(true);
            pfPassword.setVisible(false);
            btnShowPsw.setText("hide");

        }else if("hide".equalsIgnoreCase(btnShowPsw.getText())){
            pfPassword.setText(pfPassword.getText());
            pfPassword.setVisible(true);
            tfPassword.setVisible(false);
            btnShowPsw.setText("show");
        }

    }

    @FXML
    void signIn(MouseEvent event) throws IOException {

        lblSignInComparePassword.setText("Password");
        lblSignInComparePassword.setTextFill(Paint.valueOf("BLACK"));

        lblSignInLogin.setText("Login");
        lblSignInLogin.setTextFill(Paint.valueOf("BLACK"));

        SignInService signInService = new SignInService();

        String password = pfPassword.isVisible() ? pfPassword.getText() : tfPassword.getText();
        String confPassword = pfConfPassword.isVisible() ? pfConfPassword.getText() : tfConfPassword.getText();

        Boolean isPasswordmatch = signInService.isPasswordMatch(password,confPassword);

        if (isPasswordmatch){

            User user =signInService.isLoginExist(tfLogin.getText());
            if (user == null){
                signInService.signIn(tfLogin.getText(),pfPassword.isVisible() ? pfPassword.getText() : tfPassword.getText());
                Stage primaryStage = DziekanatMain.getPrimaryStage();
                Parent root = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
                primaryStage.setTitle("Login");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            }else {
                lblSignInLogin.setText("Login already exist");
                lblSignInLogin.setTextFill(Paint.valueOf("RED"));
            }

        }else {
            lblSignInComparePassword.setText("Passwords don't match");
            lblSignInComparePassword.setTextFill(Paint.valueOf("RED"));
        }
    }

}
