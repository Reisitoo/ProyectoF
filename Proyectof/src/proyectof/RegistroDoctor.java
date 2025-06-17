package proyectof;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RegistroDoctor {

    public void mostrar(Stage primaryStage) {
        Label titulo = new Label("Registro de Doctor");
        titulo.setFont(Font.font("Arial", 22));
        titulo.setTextFill(Color.DARKBLUE);

        // Campos de entrada
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre completo");

        TextField txtUsuario = new TextField();
        txtUsuario.setPromptText("Usuario o correo");

        PasswordField txtContrasena = new PasswordField();
        txtContrasena.setPromptText("Contraseña");

        PasswordField txtConfirmar = new PasswordField();
        txtConfirmar.setPromptText("Confirmar contraseña");

        Button btnRegistrar = new Button("Registrar");
        btnRegistrar.setStyle("-fx-background-color: #1565C0; -fx-text-fill: white;");
btnRegistrar.setOnAction(e -> {
    String nombre = txtNombre.getText();
    String usuario = txtUsuario.getText();
    String contrasena = txtContrasena.getText();
    String confirmar = txtConfirmar.getText();

    if (nombre.isEmpty() || usuario.isEmpty() || contrasena.isEmpty() || confirmar.isEmpty()) {
        mostrarAlerta(Alert.AlertType.WARNING, "Por favor, complete todos los campos.");
        return;
    }

    if (!contrasena.equals(confirmar)) {
        mostrarAlerta(Alert.AlertType.ERROR, "Las contraseñas no coinciden.");
        return;
    }

    // REGISTRAR EN "BASE DE DATOS"
    Doctor nuevoDoctor = new Doctor(nombre, usuario, contrasena);
    BaseDatosSimulada.registrarDoctor(nuevoDoctor);

    mostrarAlerta(Alert.AlertType.INFORMATION, "Registro exitoso. Ahora puede iniciar sesión.");
    LoginDoctor login = new LoginDoctor();
    login.mostrar(primaryStage);
});


        Button btnVolver = new Button("Volver");
        btnVolver.setOnAction(e -> {
            LoginDoctor login = new LoginDoctor();
            login.mostrar(primaryStage);
        });

        VBox layout = new VBox(10, titulo, txtNombre, txtUsuario, txtContrasena, txtConfirmar, btnRegistrar, btnVolver);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #E3F2FD;");

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Registro - Hospital Vitali");
        primaryStage.show();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo, mensaje, ButtonType.OK);
        alerta.showAndWait();
    }
}
