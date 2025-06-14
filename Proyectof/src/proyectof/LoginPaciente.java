package hospitalvitali;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow; // Para Neumorphism
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.CycleMethod;
import javafx.scene.layout.Priority;

public class LoginPaciente {

    public void mostrar(Stage primaryStage) {
        // --- Paleta de Colores Sugerida (puedes ajustarla) ---
        // Fondo principal: muy claro, casi blanco
        final String BASE_COLOR = "#ECF0F3"; // Un gris muy claro, cálido
        // Texto y acentos oscuros
        final String DARK_TEXT = "#34495E"; // Gris azulado oscuro
        // Colores de acento para botones
        final String ACCENT_PRIMARY = "#5D9CEC"; // Azul vibrante (Ingresar)
        final String ACCENT_SECONDARY = "#2ECC71"; // Verde (Registrarme)
        // Color para advertencias/errores
        final String ERROR_COLOR = "#E74C3C"; // Rojo moderno
        // Color para éxito
        final String SUCCESS_COLOR = "#27AE60"; // Verde éxito

        // --- Contenido de la Tarjeta de Login (Card - con efecto Neumorphism) ---
        Label tituloCard = new Label("Acceso Pacientes"); // Texto más conciso
        tituloCard.setFont(Font.font("Roboto", FontWeight.BOLD, 36)); // "Roboto" o "System" si no está
        tituloCard.setTextFill(Color.web(DARK_TEXT));

        Label mensaje = new Label("");
        mensaje.setTextFill(Color.web(ERROR_COLOR));
        mensaje.setFont(Font.font("Roboto", FontWeight.NORMAL, 14)); // Normal weight
        mensaje.setAlignment(Pos.CENTER);
        mensaje.setMaxWidth(Double.MAX_VALUE);

        // Campos de entrada con estilo Neumorphism suave
        TextField campoUsuario = new TextField();
        campoUsuario.setPromptText("Usuario o Email");
        campoUsuario.setPrefHeight(48); // Un poco más alto
        campoUsuario.setMaxWidth(350); // Un poco más ancho
        campoUsuario.setFont(Font.font("Roboto", 16));
        applyNeumorphismInputStyle(campoUsuario, BASE_COLOR); // Aplicar estilo Neumorphism

        PasswordField campoContrasena = new PasswordField();
        campoContrasena.setPromptText("Contraseña");
        campoContrasena.setPrefHeight(48);
        campoContrasena.setMaxWidth(350);
        campoContrasena.setFont(Font.font("Roboto", 16));
        applyNeumorphismInputStyle(campoContrasena, BASE_COLOR); // Aplicar estilo Neumorphism

        // Botones con estilo Neumorphism
        Button btnLogin = new Button("Ingresar");
        btnLogin.setPrefHeight(50); // Más alto
        btnLogin.setFont(Font.font("Roboto", FontWeight.BOLD, 18));
        applyNeumorphismButtonStyle(btnLogin, ACCENT_PRIMARY, true); // True para sombra más pronunciada
        btnLogin.setMinWidth(180); // Ajustar ancho mínimo para que quepa bien el texto
        btnLogin.setMaxWidth(200); // Límite para que no se estiren demasiado

        Button btnRegistrarse = new Button("Registrarme");
        btnRegistrarse.setPrefHeight(50);
        btnRegistrarse.setFont(Font.font("Roboto", FontWeight.BOLD, 18));
        applyNeumorphismButtonStyle(btnRegistrarse, ACCENT_SECONDARY, false); // False para sombra más sutil
        btnRegistrarse.setMinWidth(180);
        btnRegistrarse.setMaxWidth(200);

        Button btnVolver = new Button("Volver a Inicio"); // Texto más descriptivo
        btnVolver.setPrefHeight(50);
        btnVolver.setFont(Font.font("Roboto", FontWeight.BOLD, 18));
        // Un estilo más neutro para "Volver"
        btnVolver.setStyle(
            "-fx-background-color: " + BASE_COLOR + "; " +
            "-fx-text-fill: " + DARK_TEXT + "; " +
            "-fx-background-radius: 15px; " +
            "-fx-border-radius: 15px; " +
            "-fx-padding: 10px 20px;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 5);" // Sombra para realzar
        );
        btnVolver.setMinWidth(180);
        btnVolver.setMaxWidth(200);


        // Contenedor para los botones de acción (Ingresar, Registrarme)
        HBox actionButtons = new HBox(20); // Más espaciado entre ellos
        actionButtons.getChildren().addAll(btnLogin, btnRegistrarse);
        actionButtons.setAlignment(Pos.CENTER);
        actionButtons.setMaxWidth(400); // Para que no se separen demasiado si la tarjeta es muy ancha
        HBox.setHgrow(btnLogin, Priority.ALWAYS); // Asegura que crezcan equitativamente
        HBox.setHgrow(btnRegistrarse, Priority.ALWAYS);


        // VBox principal de la tarjeta (el "card") con estilo Neumorphism
        VBox cardContent = new VBox(25); // Más espaciado entre elementos de la tarjeta
        cardContent.getChildren().addAll(tituloCard, mensaje, campoUsuario, campoContrasena, actionButtons, btnVolver);
        cardContent.setAlignment(Pos.CENTER);
        cardContent.setPadding(new Insets(50)); // Más padding interno
        cardContent.setMaxWidth(500); // Aumentar el ancho máximo de la tarjeta para que todo quepa
        cardContent.setStyle(
            "-fx-background-color: " + BASE_COLOR + "; " +
            "-fx-background-radius: 30px; " + // Bordes más redondeados para el efecto suave
            "-fx-border-radius: 30px;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 30, 0.2, 8, 8);" + // Sombra inferior/derecha
            " -fx-inner-shadow: dropshadow(gaussian, rgba(255,255,255,0.7), 15, 0.2, -8, -8);" // Luz superior/izquierda
        );


        // --- Fondo general de la escena con un gradiente muy sutil y limpio ---
        LinearGradient backgroundGradient = new LinearGradient(
            0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.web("#F6F8F9")), // Blanco-gris muy claro
            new Stop(1, Color.web("#E6E9EB"))  // Otro blanco-gris sutilmente diferente
        );

        Region backgroundRegion = new Region();
        backgroundRegion.setBackground(new Background(new BackgroundFill(backgroundGradient, CornerRadii.EMPTY, Insets.EMPTY)));

        // --- Contenedor principal usando BorderPane para centrar la tarjeta ---
        BorderPane root = new BorderPane();
        root.setCenter(cardContent);
        root.setBackground(new Background(new BackgroundFill(backgroundGradient, CornerRadii.EMPTY, Insets.EMPTY))); // Aplica el gradiente al root
        BorderPane.setAlignment(cardContent, Pos.CENTER);

        Scene scene = new Scene(root, 800, 600); // Un tamaño de escena más generoso
        primaryStage.setTitle("Hospital Vitali - Acceso Paciente");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen(); // Centra la ventana en la pantalla del monitor
primaryStage.show();          // Asegúrate de que esta línea esté presente para mostrar la ventana

        // Lógica de autenticación (sin cambios)
        btnLogin.setOnAction(e -> {
            String usuario = campoUsuario.getText().trim();
            String contrasena = campoContrasena.getText();

            Paciente pacienteAutenticado = BaseDatosSimulada.verificarPaciente(usuario, contrasena);

            if (pacienteAutenticado != null) {
                System.out.println("Inicio de sesión exitoso para paciente: " + pacienteAutenticado.getUsuario());
                mensaje.setText("¡Bienvenido, " + pacienteAutenticado.getNombreCompleto() + "!");
                mensaje.setTextFill(Color.web(SUCCESS_COLOR)); // Color de éxito
                MenuPaciente menuPaciente = new MenuPaciente();
                menuPaciente.mostrar(primaryStage, pacienteAutenticado.getNombreCompleto());
            } else {
                mensaje.setText("Usuario o contraseña incorrectos.");
                mensaje.setTextFill(Color.web(ERROR_COLOR)); // Color de error
            }
        });

        btnRegistrarse.setOnAction(e -> {
            RegistroPaciente registroPaciente = new RegistroPaciente();
            registroPaciente.mostrar(primaryStage);
        });

        btnVolver.setOnAction(e -> {
            PantallaInicial pantallaInicial = new PantallaInicial();
            pantallaInicial.start(primaryStage);
        });
    }

    // --- Métodos Auxiliares para el Estilo Neumorphism ---

    private void applyNeumorphismInputStyle(TextField input, String baseColor) {
        input.setStyle(
            "-fx-background-color: " + baseColor + "; " +
            "-fx-background-radius: 10px; " +
            "-fx-border-radius: 10px; " +
            "-fx-font-size: 16px; " +
            "-fx-text-fill: #34495E; " +
            "-fx-prompt-text-fill: #6C7A89; " +
            "-fx-padding: 12px 15px; " + // Más padding vertical
            // Neumorphism effect for input fields (inner shadow)
            "-fx-effect: innershadow(gaussian, rgba(0,0,0,0.1), 10, 0, 2, 2)," +
            "            innershadow(gaussian, rgba(255,255,255,0.7), 10, 0, -2, -2);"
        );
    }

    private void applyNeumorphismInputStyle(PasswordField input, String baseColor) {
        input.setStyle(
            "-fx-background-color: " + baseColor + "; " +
            "-fx-background-radius: 10px; " +
            "-fx-border-radius: 10px; " +
            "-fx-font-size: 16px; " +
            "-fx-text-fill: #34495E; " +
            "-fx-prompt-text-fill: #6C7A89; " +
            "-fx-padding: 12px 15px; " + // Más padding vertical
            // Neumorphism effect for input fields (inner shadow)
            "-fx-effect: innershadow(gaussian, rgba(0,0,0,0.1), 10, 0, 2, 2)," +
            "            innershadow(gaussian, rgba(255,255,255,0.7), 10, 0, -2, -2);"
        );
    }

    private void applyNeumorphismButtonStyle(Button button, String accentColor, boolean primary) {
        button.setStyle(
            "-fx-background-color: " + accentColor + "; " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 15px; " + // Bordes redondeados de botón
            "-fx-border-radius: 15px; " +
            "-fx-padding: 15px 30px; " + // Más padding
            "-fx-font-size: 18px; " +
            "-fx-font-weight: bold; " +
            (primary ? // Sombra más pronunciada para el botón principal
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 20, 0.3, 5, 5);" :
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0.1, 3, 3);"
            )
        );
    }
}