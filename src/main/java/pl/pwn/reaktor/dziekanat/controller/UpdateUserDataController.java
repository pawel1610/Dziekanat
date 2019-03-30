package pl.pwn.reaktor.dziekanat.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.bytebuddy.dynamic.DynamicType;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.Address;
import pl.pwn.reaktor.dziekanat.model.Student;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.model.utiils.CurrentUser;
import pl.pwn.reaktor.dziekanat.service.StudentService;
import pl.pwn.reaktor.dziekanat.service.UserService;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class UpdateUserDataController {

    @FXML
    private Label lblID;

    @FXML
    private Label lblFirstName;

    @FXML
    private Label lblLastName;

    @FXML
    private Label lblStreet;

    @FXML
    private Label lblCity;

    @FXML
    private TextField tfID;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfStreet;

    @FXML
    private TextField tfCity;

    @FXML
    private Button btnConfirm;

    @FXML
    void returnToUserPanel(MouseEvent event) throws IOException {
        Stage primaryStage = DziekanatMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/userView.fxml")); // wskazujemy nasz widok FXML
        primaryStage.setTitle("User");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    void save(MouseEvent event) {
        StudentService studentService = new StudentService();
        UserService userService = new UserService();
        User user = CurrentUser.getCurrentUser();
        Student student = user.getStudent();
        if(Objects.isNull(student)){
            student = new Student();
            studentService.save(student);
            user.setStudent(student);
            userService.update(user);

        }
        student.setFirstName(tfFirstName.getText());
        student.setLastName(tfLastName.getText());
        Address address = new Address(tfStreet.getText(), tfCity.getText());
        student.setAddress(address);

        try{
            studentService.update(student);
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setContentText("UpdateStudentSucces");
            info.setTitle("Student update");
            info.show();
        } catch (Exception e){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("UpdateStudentError\n" + e );
            error.setTitle("Error student update");
            error.show();
        }

    }

    public void initialize(){
        User currentUser = CurrentUser.getCurrentUser();
        Optional.ofNullable(currentUser.getStudent())
                .ifPresent(this::mapToForm);
    }

    private void mapToForm(Student s) {

        tfID.setText(String.valueOf(s.getId()));
        tfFirstName.setText(Objects.nonNull(s.getFirstName())?s.getFirstName(): ""); // spradzamy czy firstname jest nullem
        tfLastName.setText(Optional.ofNullable(s.getLastName()).orElse(""));// spradzamy czy lasttname jest nullem

        if (Objects.nonNull(s.getAddress())){
            tfStreet.setText(Objects.isNull(s.getAddress().getStreet())?"": s.getAddress().getStreet());
            tfCity.setText(Optional.ofNullable(s.getAddress().getCity()).orElse("")); // orElse jest leniwe - stworzy stringa jezeli wysawpi null, orElseGet jest ahlana, niewazne czy null wystawpi, storzy Stringa(przymjue lambde)
        }
    }

}
