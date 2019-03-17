package pl.pwn.reaktor.dziekanat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.utils.HibernateUtils;

public class DziekanatMain extends Application {


    private static Stage primaryStage; // ustawiamy jako private static, wraz z metoda get, aby miesc dostep we wszystkich kontrolerach

    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginView.fxml")); // wskazujemy nasz domyslny widok FXML
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
    public static Stage getPrimaryStage() { // get jest public
        return primaryStage;
    }

    private static void setPrimaryStage(Stage primaryStage) { // set jest private
        DziekanatMain.primaryStage = primaryStage;
    }

    @Override
    public void init() throws Exception {
        super.init();
        System.out.println("Metoda init - uruchamiana tylko raz przy stracie projektu");
        HibernateUtils.initSessionFactory();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("Metoda init - uruchamiana tylko raz przy zamykaniu projektu");
        HibernateUtils.destroySessionFactory();
    }
}
