package proyectof.login;

import proyectof.base.BaseDatos;
import proyectof.entities.Sesion;
import proyectof.entities.Usuario;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.CycleMethod;
import javafx.scene.text.TextAlignment;
import proyectof.menu.MenuAdmin;
import proyectof.menu.MenuDoctor;
import proyectof.menu.MenuPaciente;

public class Login {

    public void mostrar(Stage primaryStage) {
        // --- Paleta de Colores Sugerida (puedes ajustarla) ---
        // Fondo principal: muy claro, casi blanco
        final String BASE_COLOR = "#ECF0F3"; // Un gris muy claro, cálido
        final String DARK_TEXT = "#34495E"; // Gris azulado oscuro
        final String ACCENT_PRIMARY = "#5D9CEC"; // Azul vibrante (Ingresar)
        final String ACCENT_SECONDARY = "#2ECC71"; // Verde (Registrarme)
        final String ERROR_COLOR = "#E74C3C"; // Rojo moderno
        final String SUCCESS_COLOR = "#27AE60"; // Verde éxito

        // --- Contenido de la Tarjeta de Login (Card - con efecto Neumorphism) ---
        Label tituloCard = new Label("Hospital Vitali"); // Texto más conciso
        tituloCard.setFont(Font.font("Roboto", FontWeight.BOLD, 36)); // "Roboto" o "System" si no está
        tituloCard.setTextFill(Color.web(DARK_TEXT));

        Label subtituloCard = new Label("Acceso al sistema, ingresa tus credenciales");
        subtituloCard.setFont(Font.font("Roboto", FontWeight.BOLD, 24));
        subtituloCard.setTextFill(Color.web(DARK_TEXT));
        subtituloCard.setWrapText(true);
        subtituloCard.setMaxWidth(560); // Un poco menos que el VBox padre
        subtituloCard.setTextAlignment(TextAlignment.CENTER); // Centra el texto visualmente
        subtituloCard.setAlignment(Pos.CENTER); // Centra dentro del Label

        Label mensaje = new Label("");
        mensaje.setTextFill(Color.web(ERROR_COLOR));
        mensaje.setFont(Font.font("Roboto", FontWeight.NORMAL, 14)); // Normal weight
        mensaje.setAlignment(Pos.CENTER);
        mensaje.setMaxWidth(Double.MAX_VALUE);

        // Campos de entrada con estilo Neumorphism suave
        TextField campoUsuario = new TextField();
        campoUsuario.setPromptText("Usuario");
        campoUsuario.setMaxWidth(Double.MAX_VALUE);
        campoUsuario.setFont(Font.font("Roboto", 16));

        applyNeumorphismInputStyle(campoUsuario, BASE_COLOR); // Aplicar estilo Neumorphism

        PasswordField campoContrasena = new PasswordField();
        campoContrasena.setPromptText("Contraseña");
        campoContrasena.setMaxWidth(Double.MAX_VALUE);
        campoContrasena.setFont(Font.font("Roboto", 16));
        applyNeumorphismInputStyle(campoContrasena, BASE_COLOR); // Aplicar estilo Neumorphism

        // Botones con estilo Neumorphism
        Button btnLogin = new Button("Ingresar");
        btnLogin.setPrefHeight(50); // Más alto
        btnLogin.setFont(Font.font("Roboto", FontWeight.BOLD, 18));
        applyNeumorphismButtonStyle(btnLogin, ACCENT_PRIMARY, true); // True para sombra más pronunciada
        btnLogin.setMinWidth(180); // Ajustar ancho mínimo para que quepa bien el texto
        btnLogin.setMaxWidth(Double.MAX_VALUE);
        btnLogin.setCursor(Cursor.HAND);

        Button btnRegistrarse = new Button("Registrarme");
        btnRegistrarse.setStyle(
                "-fx-background-color: transparent; "
                + "-fx-border-color: transparent; "
                + "-fx-text-fill: " + ACCENT_SECONDARY + "; "
                + "-fx-font-weight: bold; "
                + "-fx-font-size: 18px;"
        );
        btnRegistrarse.setPrefHeight(50);
        btnRegistrarse.setMinWidth(180);
        btnRegistrarse.setMaxWidth(Double.MAX_VALUE);
        btnRegistrarse.setCursor(Cursor.HAND);

        Button btnSalir = new Button("Salir"); // Texto más descriptivo
        btnSalir.setPrefHeight(50);
        btnSalir.setFont(Font.font("Roboto", FontWeight.BOLD, 18));
        btnSalir.setStyle(
                "-fx-background-color: transparent; "
                + // Sin fondo
                "-fx-border-color: transparent; "
                + // Sin borde
                "-fx-text-fill: " + ERROR_COLOR + "; "
                + // Texto rojo
                "-fx-font-weight: bold; "
                + // Texto en negrita (opcional)
                "-fx-font-size: 18px;" // Tamaño de fuente
        );
        btnSalir.setMinWidth(180);
        btnSalir.setMaxWidth(Double.MAX_VALUE);
        btnSalir.setCursor(Cursor.HAND);

        // Contenedor para los botones de acción (Ingresar, Registrarme)
        VBox actionButtons = new VBox(15); // Espaciado vertical entre botones
        actionButtons.getChildren().addAll(btnLogin, btnRegistrarse, btnSalir);
        actionButtons.setAlignment(Pos.CENTER);

        // VBox principal de la tarjeta (el "card") con estilo Neumorphism
        VBox cardContent = new VBox(25); // Más espaciado entre elementos de la tarjeta
        cardContent.getChildren().addAll(tituloCard, subtituloCard, mensaje, campoUsuario, campoContrasena, actionButtons, btnSalir);
        cardContent.setAlignment(Pos.CENTER);
        cardContent.setPadding(new Insets(40, 40, 40, 40)); // Padding cómodo sin cortar
        cardContent.setStyle(
                "-fx-background-radius: 30px; "
                + // Bordes más redondeados para el efecto suave
                "-fx-border-radius: 30px;"
        );
        cardContent.setMaxWidth(500);
        cardContent.setPrefWidth(500); // Asegura el ancho mínimo

        // --- Fondo general de la escena con un gradiente muy sutil y limpio ---
        LinearGradient backgroundGradient = new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#F6F8F9")), // Blanco-gris muy claro
                new Stop(1, Color.web("#E6E9EB")) // Otro blanco-gris sutilmente diferente
        );

        Region backgroundRegion = new Region();
        backgroundRegion.setBackground(new Background(new BackgroundFill(backgroundGradient, CornerRadii.EMPTY, Insets.EMPTY)));

        // --- Contenedor principal usando BorderPane para centrar la tarjeta ---
        BorderPane root = new BorderPane();
        root.setCenter(cardContent);
        root.setBackground(new Background(new BackgroundFill(backgroundGradient, CornerRadii.EMPTY, Insets.EMPTY))); // Aplica el gradiente al root
        BorderPane.setAlignment(cardContent, Pos.CENTER);

        Scene scene = new Scene(root, 900, 650); // Un tamaño de escena más generoso
        primaryStage.setTitle("Hospital Vitali - Acceso Paciente");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen(); // Centra la ventana en la pantalla del monitor
        primaryStage.show();          // Asegúrate de que esta línea esté presente para mostrar la ventana

        // Lógica de autenticación (sin cambios)
        btnLogin.setOnAction(e -> {
            String usuario = campoUsuario.getText().trim();
            String contrasena = campoContrasena.getText();

            Usuario Response = BaseDatos.verificarCredenciales(usuario, contrasena);

            if (Response == null) {
                mensaje.setText("Usuario o contraseña incorrectos.");
                mensaje.setTextFill(Color.web(ERROR_COLOR)); // Color de error 
                return;
            }
            
            Sesion.setUsuarioActual(Response); //guardar el inicio de sesion

            if (Response.getEsAdmin()) {
                System.out.println("Inicio de sesión exitoso para paciente: " + Response.getUsuario());
                mensaje.setText("¡Bienvenido, " + Response.getNombreCompleto() + "!");
                mensaje.setTextFill(Color.web(SUCCESS_COLOR)); // Color de éxito
                MenuAdmin menu = new MenuAdmin();
                menu.mostrar(primaryStage);
                return;
            }

            if (Response.getEsDoctor()) {
                System.out.println("Inicio de sesión exitoso para paciente: " + Response.getUsuario());
                mensaje.setText("¡Bienvenido, " + Response.getNombreCompleto() + "!");
                mensaje.setTextFill(Color.web(SUCCESS_COLOR)); // Color de éxito
                MenuDoctor menu = new MenuDoctor();
                menu.mostrar(primaryStage, Response.getNombreCompleto());
                return;
            }

            System.out.println("Inicio de sesión exitoso para paciente: " + Response.getUsuario());
            mensaje.setText("¡Bienvenido, " + Response.getNombreCompleto() + "!");
            mensaje.setTextFill(Color.web(SUCCESS_COLOR)); // Color de éxito
            MenuPaciente menu = new MenuPaciente();
            menu.mostrar(primaryStage, Response.getNombreCompleto());
        });

        btnRegistrarse.setOnAction(e -> {
            Registro registro = new Registro();
            registro.mostrar(primaryStage);
        });

        btnSalir.setOnAction(e -> {
            Platform.exit();
        });
    }

    // --- Métodos Auxiliares para el Estilo Neumorphism ---
    private void applyNeumorphismInputStyle(TextField input, String baseColor) {
        input.setStyle(
                "-fx-background-color: " + baseColor + "; "
                + "-fx-background-radius: 10px; "
                + "-fx-border-radius: 10px; "
                + "-fx-font-size: 16px; "
                + "-fx-text-fill: #34495E; "
                + "-fx-prompt-text-fill: #6C7A89; "
                + "-fx-padding: 12px 15px; "
                + // Más padding vertical
                // Neumorphism effect for input fields (inner shadow)
                "-fx-effect: innershadow(gaussian, rgba(0,0,0,0.1), 10, 0, 2, 2),"
                + "            innershadow(gaussian, rgba(255,255,255,0.7), 10, 0, -2, -2);"
        );
    }

    private void applyNeumorphismInputStyle(PasswordField input, String baseColor) {
        input.setStyle(
                "-fx-background-color: " + baseColor + "; "
                + "-fx-background-radius: 10px; "
                + "-fx-border-radius: 10px; "
                + "-fx-font-size: 16px; "
                + "-fx-text-fill: #34495E; "
                + "-fx-prompt-text-fill: #6C7A89; "
                + "-fx-padding: 12px 15px; "
                + // Más padding vertical
                // Neumorphism effect for input fields (inner shadow)
                "-fx-effect: innershadow(gaussian, rgba(0,0,0,0.1), 10, 0, 2, 2),"
                + "            innershadow(gaussian, rgba(255,255,255,0.7), 10, 0, -2, -2);"
        );
    }

    private void applyNeumorphismButtonStyle(Button button, String accentColor, boolean primary) {
        button.setStyle(
                "-fx-background-color: " + accentColor + "; "
                + "-fx-text-fill: white; "
                + "-fx-background-radius: 15px; "
                + // Bordes redondeados de botón
                "-fx-border-radius: 15px; "
                + // Más padding
                "-fx-font-size: 18px; "
                + "-fx-font-weight: bold; "
                + (primary
                        ? // Sombra más pronunciada para el botón principal
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 20, 0.3, 5, 5);"
                        : "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0.1, 3, 3);")
        );
    }
}
