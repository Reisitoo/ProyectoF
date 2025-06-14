package hospitalvitali;

import javafx.application.Application;
import javafx.geometry.Insets; // Importar Insets para el padding
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient; // Para gradientes
import javafx.scene.paint.Stop;          // Para gradientes
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight; // Para el peso de la fuente
import javafx.stage.Stage;

public class PantallaInicial extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Título mejorado
        Label titulo = new Label("Hospital Vitali");
        // Puedes intentar con "Roboto", "Open Sans" si sabes que estarán disponibles.
        // Si no, "System" o "Arial" son buenas opciones por defecto.
        titulo.setFont(Font.font("System", FontWeight.BOLD, 40)); // Fuente más grande y negrita
        titulo.setTextFill(Color.web("#2C3E50")); // Color oscuro casi negro, profesional

        // Descripción o eslogan (opcional, para un toque más profesional)
        Label eslogan = new Label("Cuidando tu salud, transformando vidas.");
        eslogan.setFont(Font.font("System", FontWeight.LIGHT, 16));
        eslogan.setTextFill(Color.web("#34495E"));
        eslogan.setPadding(new Insets(0, 0, 30, 0)); // Espacio debajo del eslogan

        // Botón Doctor
        Button btnDoctor = new Button("Soy Doctor");
        btnDoctor.setStyle(
            "-fx-background-color: #2980B9; " + // Azul más vibrante
            "-fx-text-fill: white; " +
            "-fx-font-size: 16px; " +
            "-fx-padding: 15px 30px; " + // Más padding
            "-fx-border-radius: 5px; " + // Bordes redondeados
            "-fx-background-radius: 5px; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);" // Sombra
        );
        btnDoctor.setMinWidth(250); // Un poco más anchos
        btnDoctor.setOnAction(e -> {
            LoginDoctor loginDoctor = new LoginDoctor();
            loginDoctor.mostrar(primaryStage); // Navega al login del doctor
        });

        // Botón Paciente
        Button btnPaciente = new Button("Soy Paciente");
        btnPaciente.setStyle(
            "-fx-background-color: #27AE60; " + // Verde más vibrante
            "-fx-text-fill: white; " +
            "-fx-font-size: 16px; " +
            "-fx-padding: 15px 30px; " +
            "-fx-border-radius: 5px; " +
            "-fx-background-radius: 5px; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);"
        );
        btnPaciente.setMinWidth(250);
        btnPaciente.setOnAction(e -> {
            LoginPaciente loginPaciente = new LoginPaciente();
            loginPaciente.mostrar(primaryStage); // Navega al login del paciente
        });

        // Contenedor vertical
        VBox layout = new VBox(25, titulo, eslogan, btnDoctor, btnPaciente); // Espacio entre elementos, añade eslogan
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50)); // Más padding general

        // Fondo con un gradiente suave
LinearGradient gradient = new LinearGradient(
    0, 0, 0, 1, // startX, startY, endX, endY
    true,       // proportional
    CycleMethod.NO_CYCLE, // <--- AÑADE ESTA LÍNEA
    new Stop(0, Color.web("#ECF0F1")), // Color claro en la parte superior
    new Stop(1, Color.web("#BDC3C7"))  // Color ligeramente más oscuro en la parte inferior
);
        
        layout.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(layout, 800, 600); // Un poco más grande para el nuevo diseño
        primaryStage.setTitle("Hospital Vitali - Bienvenido");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}