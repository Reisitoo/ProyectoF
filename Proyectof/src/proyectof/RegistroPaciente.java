package hospitalvitali;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// --- IMPORTANTE: Ya NO NECESITAS esta importación estática aquí. ---
// import static hospitalvitali.LoginPaciente.pacientesRegistrados;
// Porque RegistroPaciente ahora interactúa directamente con BaseDatosSimulada.

public class RegistroPaciente {

    public void mostrar(Stage primaryStage) {
        Label titulo = new Label("Registro de Paciente");
        titulo.setFont(Font.font("Arial", 22));
        titulo.setTextFill(Color.DARKGREEN); // Cambiado a verde para paciente

        // Campos de entrada
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre completo");
        txtNombre.setMaxWidth(300);

        TextField txtUsuario = new TextField();
        txtUsuario.setPromptText("Usuario o email (para iniciar sesión)");
        txtUsuario.setMaxWidth(300);

        PasswordField txtContrasena = new PasswordField();
        txtContrasena.setPromptText("Contraseña");
        txtContrasena.setMaxWidth(300);

        PasswordField txtConfirmar = new PasswordField();
        txtConfirmar.setPromptText("Confirmar contraseña");
        txtConfirmar.setMaxWidth(300);

        // Campos opcionales
        TextField txtEmailOpcional = new TextField();
        txtEmailOpcional.setPromptText("Correo Electrónico (Opcional)");
        txtEmailOpcional.setMaxWidth(300);

        TextField txtTelefonoOpcional = new TextField();
        txtTelefonoOpcional.setPromptText("Teléfono (Opcional)");
        txtTelefonoOpcional.setMaxWidth(300);


        Button btnRegistrar = new Button("Registrar Cuenta");
        btnRegistrar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;"); // Estilo verde
        btnRegistrar.setMinWidth(200);
        btnRegistrar.setOnAction(e -> {
            String nombre = txtNombre.getText().trim();
            String usuario = txtUsuario.getText().trim();
            String contrasena = txtContrasena.getText();
            String confirmar = txtConfirmar.getText();
            String email = txtEmailOpcional.getText().trim(); // Puedes usarlo o no
            String telefono = txtTelefonoOpcional.getText().trim(); // Puedes usarlo o no


            if (nombre.isEmpty() || usuario.isEmpty() || contrasena.isEmpty() || confirmar.isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Por favor, complete todos los campos obligatorios.");
                return;
            }

            if (!contrasena.equals(confirmar)) {
                mostrarAlerta(Alert.AlertType.ERROR, "Las contraseñas no coinciden.");
                return;
            }

            // ✅ Se consulta a la BaseDatosSimulada para verificar si el usuario ya existe
            if (BaseDatosSimulada.existePaciente(usuario)) {
                mostrarAlerta(Alert.AlertType.ERROR, "El usuario '" + usuario + "' ya existe. Por favor, elija otro.");
                return;
            }

            // REGISTRAR EN LA BASE DE DATOS SIMULADA
            Paciente nuevoPaciente = new Paciente(nombre, usuario, contrasena);
            BaseDatosSimulada.registrarPaciente(nuevoPaciente);

            mostrarAlerta(Alert.AlertType.INFORMATION, "¡Registro exitoso! Ahora puede iniciar sesión con su nueva cuenta.");

            // Navegar de vuelta a la pantalla de login del paciente
            LoginPaciente login = new LoginPaciente();
            login.mostrar(primaryStage);
        });

        Button btnVolver = new Button("Volver al Login");
        btnVolver.setStyle("-fx-background-color: #607D8B; -fx-text-fill: white;"); // Estilo gris
        btnVolver.setMinWidth(200);
        btnVolver.setOnAction(e -> {
            LoginPaciente login = new LoginPaciente();
            login.mostrar(primaryStage);
        });

        // Contenedor vertical para organizar los elementos
        VBox layout = new VBox(10); // Espaciado entre elementos
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20)); // Relleno alrededor del layout
        layout.setStyle("-fx-background-color: #E8F5E9;"); // Fondo verde muy claro para paciente

        layout.getChildren().addAll(
            titulo,
            txtNombre,
            txtUsuario,
            txtContrasena,
            txtConfirmar,
            txtEmailOpcional,
            txtTelefonoOpcional,
            btnRegistrar,
            btnVolver
        );

        Scene scene = new Scene(layout, 800, 600); // Ajustar tamaño de la escena
        primaryStage.setScene(scene);
        primaryStage.setTitle("Registro de Paciente - Hospital Vitali");
        primaryStage.show();
    }

    // Método auxiliar para mostrar alertas
    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo, mensaje, ButtonType.OK);
        alerta.setHeaderText(null); // No mostrar encabezado por defecto
        alerta.showAndWait();
    }
}