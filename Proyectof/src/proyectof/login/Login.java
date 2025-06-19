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
//import javafx.scene.layout.Region;
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
import proyectof.menu.doctor.MenuDoctor;
import proyectof.menu.paciente.MenuPaciente;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.effect.DropShadow; // <-- ¡Importar esta clase!
import javafx.scene.effect.BlurType; // <-- ¡Importar esta clase!

public class Login {

    public void mostrar(Stage primaryStage) {
        // --- Paleta de Colores Sugerida ---
        final String BASE_COLOR = "#ECF0F3";
        final String DARK_TEXT = "#34495E";
        final String ACCENT_PRIMARY = "#5D9CEC";
        final String ACCENT_SECONDARY = "#2ECC71";
        final String ERROR_COLOR = "#E74C3C";
        final String SUCCESS_COLOR = "#27AE60";

        // --- Carga y Configuración del Logo Principal ---
        Image logoImage = null;
        try {
            logoImage = new Image(getClass().getResourceAsStream("/proyectof/images/hospital_vitali_logo.png"));
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen del logo principal: " + e.getMessage());
        }

        ImageView logoView = null;
        if (logoImage != null) {
            logoView = new ImageView(logoImage);
            logoView.setFitWidth(150);
            logoView.setPreserveRatio(true);
            logoView.setSmooth(true);
            logoView.setCache(true);
            VBox.setMargin(logoView, new Insets(0, 0, 20, 0));

            // **NUEVO CÓDIGO: Aplicar un DropShadow sutil al logo**
            DropShadow dropShadow = new DropShadow();
            dropShadow.setBlurType(BlurType.GAUSSIAN);
            dropShadow.setColor(Color.rgb(0, 0, 0, 0.15)); // Un negro muy suave, casi gris, con poca opacidad
            dropShadow.setRadius(15); // Radio de la sombra, suavidad
            dropShadow.setOffsetX(5); // Desplazamiento horizontal de la sombra
            dropShadow.setOffsetY(5); // Desplazamiento vertical de la sombra
            logoView.setEffect(dropShadow); // Aplica la sombra al ImageView del logo
        }

        // --- Carga de Iconos para campos de texto (user y lock) ---
        Image userIcon = null;
        Image lockIcon = null;
        try {
            userIcon = new Image(getClass().getResourceAsStream("/proyectof/images/user_icon.png"));
            lockIcon = new Image(getClass().getResourceAsStream("/proyectof/images/lock_icon.png"));
        } catch (Exception e) {
            System.err.println("Error al cargar iconos de usuario/contraseña: " + e.getMessage());
        }

        // --- Contenido de la Tarjeta de Login (Card) ---
        Label tituloCard = new Label("Acceso al Sistema");
        tituloCard.setFont(Font.font("Roboto", FontWeight.BOLD, 32));
        tituloCard.setTextFill(Color.web(DARK_TEXT));

        Label subtituloCard = new Label("Ingresa tus credenciales");
        subtituloCard.setFont(Font.font("Roboto", FontWeight.NORMAL, 18));
        subtituloCard.setTextFill(Color.web(DARK_TEXT));
        subtituloCard.setWrapText(true);
        subtituloCard.setMaxWidth(560);
        subtituloCard.setTextAlignment(TextAlignment.CENTER);
        subtituloCard.setAlignment(Pos.CENTER);

        Label mensaje = new Label("");
        mensaje.setTextFill(Color.web(ERROR_COLOR));
        mensaje.setFont(Font.font("Roboto", FontWeight.NORMAL, 14));
        mensaje.setAlignment(Pos.CENTER);
        mensaje.setMaxWidth(Double.MAX_VALUE);

        // Campos de entrada
        TextField campoUsuario = new TextField();
        campoUsuario.setPromptText("Usuario");
        campoUsuario.setFont(Font.font("Roboto", 16));
        campoUsuario.setMaxWidth(Double.MAX_VALUE);
        campoUsuario.setPrefHeight(35);
        applyNeumorphismInputStyle(campoUsuario, BASE_COLOR);

        PasswordField campoContrasena = new PasswordField();
        campoContrasena.setPromptText("Contraseña");
        campoContrasena.setFont(Font.font("Roboto", 16));
        campoContrasena.setMaxWidth(Double.MAX_VALUE);
        campoContrasena.setPrefHeight(35);
        applyNeumorphismInputStyle(campoContrasena, BASE_COLOR);


        // --- HBox para el campo de Usuario con icono ---
        HBox usuarioInputBox = new HBox(10);
        usuarioInputBox.setAlignment(Pos.CENTER_LEFT);
        usuarioInputBox.setMaxWidth(Double.MAX_VALUE);
        usuarioInputBox.setPrefHeight(50);

        if (userIcon != null) {
            ImageView userIconView = new ImageView(userIcon);
            userIconView.setFitWidth(24);
            userIconView.setFitHeight(24);
            userIconView.setSmooth(true);
            usuarioInputBox.getChildren().add(userIconView);
        }
        usuarioInputBox.getChildren().add(campoUsuario);
        applyNeumorphismInputBoxStyle(usuarioInputBox, BASE_COLOR);


        // --- HBox para el campo de Contraseña con icono ---
        HBox contrasenaInputBox = new HBox(10);
        contrasenaInputBox.setAlignment(Pos.CENTER_LEFT);
        contrasenaInputBox.setMaxWidth(Double.MAX_VALUE);
        contrasenaInputBox.setPrefHeight(50);

        if (lockIcon != null) {
            ImageView lockIconView = new ImageView(lockIcon);
            lockIconView.setFitWidth(24);
            lockIconView.setFitHeight(24);
            lockIconView.setSmooth(true);
            contrasenaInputBox.getChildren().add(lockIconView);
        }
        contrasenaInputBox.getChildren().add(campoContrasena);
        applyNeumorphismInputBoxStyle(contrasenaInputBox, BASE_COLOR);


        // Botones de acción
        Button btnLogin = new Button("Ingresar");
        btnLogin.setPrefHeight(50);
        btnLogin.setFont(Font.font("Roboto", FontWeight.BOLD, 18));
        applyNeumorphismButtonStyle(btnLogin, ACCENT_PRIMARY, true);
        btnLogin.setMinWidth(250);
        btnLogin.setMaxWidth(250);
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
        btnRegistrarse.setMinWidth(250);
        btnRegistrarse.setMaxWidth(250);
        btnRegistrarse.setCursor(Cursor.HAND);
        btnRegistrarse.setAlignment(Pos.CENTER);


        Button btnSalir = new Button("Salir");
        btnSalir.setPrefHeight(50);
        btnSalir.setFont(Font.font("Roboto", FontWeight.BOLD, 18));
        btnSalir.setStyle(
                "-fx-background-color: transparent; "
                + "-fx-border-color: transparent; "
                + "-fx-text-fill: " + ERROR_COLOR + "; "
                + "-fx-font-weight: bold; "
                + "-fx-font-size: 18px;"
        );
        btnSalir.setMinWidth(250);
        btnSalir.setMaxWidth(250);
        btnSalir.setCursor(Cursor.HAND);
        btnSalir.setAlignment(Pos.CENTER);


        // Contenedor para los botones de acción (Ingresar, Registrarme)
        VBox actionButtons = new VBox(15);
        actionButtons.getChildren().addAll(btnLogin, btnRegistrarse);
        actionButtons.setAlignment(Pos.CENTER);


        VBox cardContent = new VBox(20);
        if (logoView != null) {
            cardContent.getChildren().add(logoView);
        }
        cardContent.getChildren().addAll(
                tituloCard,
                subtituloCard,
                mensaje,
                usuarioInputBox,
                contrasenaInputBox,
                actionButtons
        );
        VBox.setMargin(btnSalir, new Insets(15, 0, 0, 0));
        cardContent.getChildren().add(btnSalir);

        cardContent.setAlignment(Pos.CENTER);
        cardContent.setPadding(new Insets(30, 40, 30, 40));
        cardContent.setStyle(
                "-fx-background-radius: 30px; "
                + "-fx-border-radius: 30px;"
        );
        cardContent.setMaxWidth(500);


        // Fondo de gradiente
        LinearGradient backgroundGradient = new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#F6F8F9")),
                new Stop(1, Color.web("#E6E9EB"))
        );

        BorderPane root = new BorderPane();
        root.setCenter(cardContent);
        root.setBackground(new Background(new BackgroundFill(backgroundGradient, CornerRadii.EMPTY, Insets.EMPTY)));
        BorderPane.setAlignment(cardContent, Pos.CENTER);

        Scene scene = new Scene(root, 900, 650);
        primaryStage.setTitle("Hospital Vitali - Acceso");
        primaryStage.setScene(scene);

        if (logoImage != null) {
            primaryStage.getIcons().add(logoImage);
        }

        primaryStage.centerOnScreen();
        primaryStage.show();

        // Lógica de autenticación (sin cambios)
        btnLogin.setOnAction(e -> {
            String usuario = campoUsuario.getText().trim();
            String contrasena = campoContrasena.getText();

            Usuario Response = BaseDatos.verificarCredenciales(usuario, contrasena);

            if (Response == null) {
                mensaje.setText("Usuario o contraseña incorrectos.");
                mensaje.setTextFill(Color.web(ERROR_COLOR));
                return;
            }

            Sesion.setUsuarioActual(Response);

            if (Response.getEsAdmin()) {
                System.out.println("Inicio de sesión exitoso para administrador: " + Response.getUsuario());
                mensaje.setText("¡Bienvenido, " + Response.getNombreCompleto() + "!");
                mensaje.setTextFill(Color.web(SUCCESS_COLOR));
                MenuAdmin menu = new MenuAdmin();
                menu.mostrar(primaryStage);
                return;
            }

            if (Response.getEsDoctor()) {
                System.out.println("Inicio de sesión exitoso para doctor: " + Response.getUsuario());
                mensaje.setText("¡Bienvenido, " + Response.getNombreCompleto() + "!");
                mensaje.setTextFill(Color.web(SUCCESS_COLOR));
                MenuDoctor menu = new MenuDoctor();
                menu.mostrar(primaryStage, Response.getNombreCompleto());
                return;
            }

            System.out.println("Inicio de sesión exitoso para paciente: " + Response.getUsuario());
            mensaje.setText("¡Bienvenido, " + Response.getNombreCompleto() + "!");
            mensaje.setTextFill(Color.web(SUCCESS_COLOR));
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

    private void applyNeumorphismInputBoxStyle(HBox hbox, String baseColor) {
        hbox.setStyle(
                "-fx-background-color: " + baseColor + "; "
                + "-fx-background-radius: 10px; "
                + "-fx-border-radius: 10px; "
                + "-fx-padding: 12px 15px; " // 12px arriba/abajo, 15px izq/der
                + "-fx-effect: innershadow(gaussian, rgba(0,0,0,0.1), 10, 0, 2, 2),"
                + "innershadow(gaussian, rgba(255,255,255,0.7), 10, 0, -2, -2);"
        );
    }

    private void applyNeumorphismInputStyle(TextField input, String baseColor) {
        input.setStyle(
                "-fx-background-color: transparent; "
                + "-fx-background-radius: 0px; "
                + "-fx-border-radius: 0px; "
                + "-fx-font-size: 16px; "
                + "-fx-text-fill: #34495E; "
                + "-fx-prompt-text-fill: #6C7A89; "
                + "-fx-padding: 0 0 0 0;"
                + "-fx-effect: null;"
                + "-fx-alignment: center-left;"
        );
    }

    private void applyNeumorphismInputStyle(PasswordField input, String baseColor) {
        input.setStyle(
                "-fx-background-color: transparent; "
                + "-fx-background-radius: 0px; "
                + "-fx-border-radius: 0px; "
                + "-fx-font-size: 16px; "
                + "-fx-text-fill: #34495E; "
                + "-fx-prompt-text-fill: #6C7A89; "
                + "-fx-padding: 0 0 0 0;"
                + "-fx-effect: null;"
                + "-fx-alignment: center-left;"
        );
    }

    private void applyNeumorphismButtonStyle(Button button, String accentColor, boolean primary) {
        button.setStyle(
                "-fx-background-color: " + accentColor + "; "
                + "-fx-text-fill: white; "
                + "-fx-background-radius: 15px; "
                + "-fx-border-radius: 15px; "
                + "-fx-font-size: 18px; "
                + "-fx-font-weight: bold; "
                + (primary
                ? "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 20, 0.3, 5, 5);"
                : "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0.1, 3, 3);")
        );
    }
}