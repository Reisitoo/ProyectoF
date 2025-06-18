package proyectof.login;

import proyectof.login.Login;
import proyectof.base.BaseDatos;
import proyectof.entities.Usuario;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
public class Registro {

    public void mostrar(Stage primaryStage) {
        final String BASE_COLOR = "#ECF0F3";
        final String DARK_TEXT = "#34495E";
        final String ACCENT_PRIMARY = "#5D9CEC";
        final String ERROR_COLOR = "#E74C3C";
        final String SUCCESS_COLOR = "#27AE60";

        Label titulo = new Label("Registro de Paciente");
        titulo.setFont(Font.font("Roboto", FontWeight.BOLD, 30));
        titulo.setTextFill(Color.web(DARK_TEXT));

        Label subtitulo = new Label("Crea una cuenta para acceder al sistema");
        subtitulo.setFont(Font.font("Roboto", FontWeight.BOLD, 18));
        subtitulo.setTextFill(Color.web(DARK_TEXT));
        subtitulo.setWrapText(true);
        subtitulo.setTextAlignment(TextAlignment.CENTER);
        subtitulo.setAlignment(Pos.CENTER);
        subtitulo.setMaxWidth(500);

        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre completo");
        txtNombre.setFont(Font.font("Roboto", 16));
        applyNeumorphismInputStyle(txtNombre, BASE_COLOR);

        TextField txtUsuario = new TextField();
        txtUsuario.setPromptText("Usuario");
        txtUsuario.setFont(Font.font("Roboto", 16));
        applyNeumorphismInputStyle(txtUsuario, BASE_COLOR);

        PasswordField txtContrasena = new PasswordField();
        txtContrasena.setPromptText("Contraseña");
        txtContrasena.setFont(Font.font("Roboto", 16));
        applyNeumorphismInputStyle(txtContrasena, BASE_COLOR);

        PasswordField txtConfirmar = new PasswordField();
        txtConfirmar.setPromptText("Confirmar contraseña");
        txtConfirmar.setFont(Font.font("Roboto", 16));
        applyNeumorphismInputStyle(txtConfirmar, BASE_COLOR);

        TextField txtEmailOpcional = new TextField();
        txtEmailOpcional.setPromptText("Correo Electrónico (Opcional)");
        txtEmailOpcional.setFont(Font.font("Roboto", 16));
        applyNeumorphismInputStyle(txtEmailOpcional, BASE_COLOR);

        TextField txtTelefonoOpcional = new TextField();
        txtTelefonoOpcional.setPromptText("Teléfono (Opcional)");
        txtTelefonoOpcional.setFont(Font.font("Roboto", 16));
        applyNeumorphismInputStyle(txtTelefonoOpcional, BASE_COLOR);

        Button btnRegistrar = new Button("Registrar Cuenta");
        btnRegistrar.setFont(Font.font("Roboto", FontWeight.BOLD, 18));
        btnRegistrar.setPrefHeight(50);
        btnRegistrar.setMinWidth(200);
        applyNeumorphismButtonStyle(btnRegistrar, SUCCESS_COLOR, true);
        btnRegistrar.setOnAction(e -> {
            String nombre = txtNombre.getText().trim();
            String usuario = txtUsuario.getText().trim();
            String contrasena = txtContrasena.getText();
            String confirmar = txtConfirmar.getText();
            String email = txtEmailOpcional.getText().trim();
            String telefono = txtTelefonoOpcional.getText().trim();

            if (nombre.isEmpty() || usuario.isEmpty() || contrasena.isEmpty() || confirmar.isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Por favor, complete todos los campos obligatorios.");
                return;
            }

            if (!contrasena.equals(confirmar)) {
                mostrarAlerta(Alert.AlertType.ERROR, "Las contraseñas no coinciden.");
                return;
            }

            if (BaseDatos.existeUsuario(usuario)) {
                mostrarAlerta(Alert.AlertType.ERROR, "El usuario '" + usuario + "' ya existe. Por favor, elija otro.");
                return;
            }

            Usuario nuevoUsuario = new Usuario(nombre, usuario, contrasena, email, telefono, false, false);
            BaseDatos.registrarUsuario(nuevoUsuario);

            mostrarAlerta(Alert.AlertType.INFORMATION, "¡Registro exitoso! Ahora puede iniciar sesión con su nueva cuenta.");
            new Login().mostrar(primaryStage);
        });


        Button btnVolver = new Button("Volver al Login");
        btnVolver.setStyle(
                "-fx-background-color: transparent; "
                + "-fx-border-color: transparent; "
                + "-fx-text-fill: " + ACCENT_PRIMARY + "; "
                + "-fx-font-weight: bold; "
                + "-fx-font-size: 18px;"
        );
        btnVolver.setFont(Font.font("Roboto", FontWeight.BOLD, 18));
        btnVolver.setPrefHeight(50);
        btnVolver.setMinWidth(200);
        btnVolver.setCursor(Cursor.HAND);
        btnVolver.setOnAction(e -> new Login().mostrar(primaryStage));

        VBox card = new VBox(15);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(40));
        card.getChildren().addAll(
                titulo, subtitulo,
                txtNombre, txtUsuario, txtContrasena, txtConfirmar,
                txtEmailOpcional, txtTelefonoOpcional,
                btnRegistrar, btnVolver
        );
        card.setMaxWidth(500);

        BorderPane root = new BorderPane(card);
        root.setBackground(new Background(new BackgroundFill(
                new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.web("#F6F8F9")),
                        new Stop(1, Color.web("#E6E9EB"))
                ), CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(root, 900, 650);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Registro de Paciente - Hospital Vitali");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo, mensaje, ButtonType.OK);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }

    private void applyNeumorphismInputStyle(TextField input, String baseColor) {
        input.setStyle("-fx-background-color: " + baseColor + "; -fx-background-radius: 10px; -fx-border-radius: 10px; " +
                "-fx-font-size: 16px; -fx-text-fill: #34495E; -fx-prompt-text-fill: #6C7A89; -fx-padding: 12px 15px; " +
                "-fx-effect: innershadow(gaussian, rgba(0,0,0,0.1), 10, 0, 2, 2), " +
                "innershadow(gaussian, rgba(255,255,255,0.7), 10, 0, -2, -2);");
    }

    private void applyNeumorphismButtonStyle(Button button, String accentColor, boolean primary) {
        button.setStyle("-fx-background-color: " + accentColor + "; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px; " +
                "-fx-font-size: 18px; -fx-font-weight: bold; " +
                (primary ? "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 20, 0.3, 5, 5);" :
                         "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0.1, 3, 3);"));
    }
}
