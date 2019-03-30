package pl.pwn.reaktor.dziekanat.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.utiils.CurrentUser;

import java.io.IOException;

public class UserController {

    @FXML
    private Button btnUpdateData;

    @FXML
    private Label lblHeadText;

    @FXML
    void eventUpdateData(MouseEvent event) throws IOException {

        Stage primaryStage = DziekanatMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/updateDataView.fxml")); // wskazujemy nasz widok FXML
        primaryStage.setTitle("Update data");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void initialize(){
        lblHeadText.setText(lblHeadText.getText() + CurrentUser.getCurrentUser().getLogin());
    }

}

