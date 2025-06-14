package hospitalvitali;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginDoctor {

    public void mostrar(Stage primaryStage) {
        Label titulo = new Label("Login Doctor");
        titulo.setStyle("-fx-font-size: 22px; -fx-text-fill: darkblue;");

        TextField txtUsuario = new TextField();
        txtUsuario.setPromptText("Usuario");

        PasswordField txtContrasena = new PasswordField();
        txtContrasena.setPromptText("Contraseña");

        Button btnLogin = new Button("Iniciar Sesión");
        btnLogin.setStyle("-fx-background-color: #1565C0; -fx-text-fill: white;");
        btnLogin.setOnAction(e -> {
            String usuario = txtUsuario.getText().trim();
            String contrasena = txtContrasena.getText().trim();

            // ✅ Aquí se consulta a la Base de Datos Simulada
            Doctor doctor = BaseDatosSimulada.verificarDoctor(usuario, contrasena);

            if (doctor != null) {
                System.out.println("Inicio de sesión exitoso para doctor: " + doctor.getNombre());

                MenuDoctor menu = new MenuDoctor();
                menu.mostrar(primaryStage, doctor.getNombre());
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR, "Usuario o contraseña incorrectos", ButtonType.OK);
                alerta.showAndWait();
            }
        });

        Button btnRegistro = new Button("Registrarse");
        btnRegistro.setOnAction(e -> new RegistroDoctor().mostrar(primaryStage));

        VBox layout = new VBox(10, titulo, txtUsuario, txtContrasena, btnLogin, btnRegistro);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #E3F2FD;");

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Doctor - Hospital Vitali");
    }
}
