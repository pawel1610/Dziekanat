package pl.pwn.reaktor.dziekanat.controller;

import com.google.protobuf.Enum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.pwn.reaktor.dziekanat.model.DTO.StudentDTO;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.service.UserService;

import java.util.List;

public class AdminController {

    @FXML
    private TableView<StudentDTO> tvStudent;

    @FXML
    private TableColumn<StudentDTO, Long> colIDS;

    @FXML
    private TableColumn<StudentDTO, String> colLoginS;

    @FXML
    private TableColumn<StudentDTO, Boolean> colActiveS;

    @FXML
    private TableColumn<StudentDTO, String> colFirstNameS;

    @FXML
    private TableColumn<StudentDTO, String> colLastNameS;

    @FXML
    private TableColumn<StudentDTO, String> colStreetS;

    @FXML
    private TableColumn<StudentDTO, String> colCityS;

    @FXML
    private Label lblStudent;

    @FXML
    private Label lblAdmin;

    @FXML
    private TableView<User> tvAdmin;

    @FXML
    private TableColumn<User, Long> colIDA;

    @FXML
    private TableColumn<User, String> colLoginA;

    @FXML
    private TableColumn<User, Enum> colRoleA;

    @FXML
    private TableColumn<User, Boolean> colActiveA;

    @FXML
    private MenuItem mClose;

    @FXML
    private MenuItem mLogout;

    @FXML
    private MenuItem mAbout;

    private UserService userService = new UserService();

    @FXML
    void closeAction(ActionEvent event) {

    }

    @FXML
    void logoutAction(ActionEvent event) {

    }

    public void initialize(){
        initAdminTabel();
        initStudentTable();
    }

    private void initStudentTable() {
        //pobrac dane adminow z bazy
        List<StudentDTO> allStudents = userService.getAllStudents();

        //konwersja na liste zrozumiala przez tableView z JavaFX
        ObservableList<StudentDTO> studentsObservableList = FXCollections.observableArrayList(allStudents);
        tvStudent.setItems(null);
        tvStudent.setItems(studentsObservableList);
        //ustawienie kolumn
        colIDS.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLoginS.setCellValueFactory(new PropertyValueFactory<>("login"));
        colFirstNameS.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastNameS.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colStreetS.setCellValueFactory(new PropertyValueFactory<>("street"));
        colCityS.setCellValueFactory(new PropertyValueFactory<>("city"));
        colActiveS.setCellValueFactory(new PropertyValueFactory<>("active"));
    }


    private void initAdminTabel() {

        //pobrac dane adminow z bazy
        List<User> admins = userService.getAllAdmin();

        //konwersja na liste zrozumiala przez tableView z JavaFX
        ObservableList<User> adminsObservableList = FXCollections.observableArrayList(admins);
        tvAdmin.setItems(null);
        tvAdmin.setItems(adminsObservableList);

        //ustawienie kolumn
        colIDA.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLoginA.setCellValueFactory(new PropertyValueFactory<>("login"));
        colRoleA.setCellValueFactory(new PropertyValueFactory<>("role"));
        colActiveA.setCellValueFactory(new PropertyValueFactory<>("active"));
    }

}


