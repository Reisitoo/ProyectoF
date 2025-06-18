package proyectof;

import javafx.application.Application;
import javafx.stage.Stage;

public class PantallaInicial extends Application {

    @Override
    public void start(Stage primaryStage) {
        Login loginPaciente = new Login();
        loginPaciente.mostrar(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
