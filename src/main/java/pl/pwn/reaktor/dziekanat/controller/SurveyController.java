package pl.pwn.reaktor.dziekanat.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.utiils.CurrentUser;

import java.io.IOException;

public class SurveyController {

    @FXML
    private MenuItem mClose;

    @FXML
    private MenuItem mLogout;

    @FXML
    private MenuItem mAbout;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfMail;

    @FXML
    private TextField tfPhone;

    @FXML
    private CheckBox cbJava;

    @FXML
    private CheckBox cbPython;

    @FXML
    private CheckBox cbOther;

    @FXML
    private TextField tfLanguage;

    @FXML
    private RadioButton rbBasic;

    @FXML
    private ToggleGroup g1;

    @FXML
    private RadioButton rbIntermediate;

    @FXML
    private RadioButton rbAdvance;

    @FXML
    private Button btnPreview;

    @FXML
    private ComboBox<?> cbCourseSelection;

    @FXML
    private TextArea taPreview;

    @FXML
    void aboutAction(ActionEvent event) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("About");
        info.setHeaderText("Instruction");
        info.setContentText("Blablabla");
        info.show();
    }

    @FXML
    void closeAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void logoutAction(ActionEvent event) throws IOException {
        CurrentUser.cleanCurrentUser();
        Stage primaryStage = DziekanatMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginView.fxml")); // wskazujemy nasz widok FXML
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));

    }

    @FXML
    void otherAction(MouseEvent event) {

    }

    @FXML
    void previewAction(MouseEvent event) {

    }

}
