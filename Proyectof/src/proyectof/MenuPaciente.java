package proyectof;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight; // <--- AÑADE ESTA LÍNEA
import javafx.stage.Stage;

public class MenuPaciente {

    // Necesitamos que el StackPane del contenido sea accesible para los métodos de actualización
    private StackPane areaContenido;

    public void mostrar(Stage primaryStage, String nombrePaciente) {
        // Bienvenida
        Label lblBienvenida = new Label("Bienvenido(a), " + nombrePaciente + "!");
        lblBienvenida.setFont(Font.font("Arial", 20));
        lblBienvenida.setTextFill(Color.DARKGREEN);

        // Botones del menú lateral
        Button btnPerfil = crearBotonPaciente("Perfil del Paciente");
        Button btnCitas = crearBotonPaciente("Citas Médicas");
        Button btnResultados = crearBotonPaciente("Resultados de Exámenes");
        Button btnPrescripciones = crearBotonPaciente("Mis Prescripciones");
        Button btnComunicacion = crearBotonPaciente("Comunicación con Doctor");
        Button btnRecursosEducativos = crearBotonPaciente("Recursos Educativos");
        Button btnTelemedicina = crearBotonPaciente("Telemedicina");
        Button btnConfiguracion = crearBotonPaciente("Configuración");

        // Botón de Cerrar Sesión
        Button btnCerrarSesion = new Button("Cerrar Sesión");
        btnCerrarSesion.setStyle("-fx-background-color: #D32F2F; -fx-text-fill: white; -fx-font-size: 14px;");
        btnCerrarSesion.setMinWidth(200);
        btnCerrarSesion.setOnAction(e -> {
            PantallaInicial inicio = new PantallaInicial();
            inicio.start(primaryStage);
        });

        // Contenedor del menú lateral
        VBox menuLateral = new VBox(10, lblBienvenida, btnPerfil, btnCitas, btnResultados,
                                    btnPrescripciones, btnComunicacion, btnRecursosEducativos,
                                    btnTelemedicina, btnConfiguracion, btnCerrarSesion);
        menuLateral.setPadding(new Insets(20));
        menuLateral.setStyle("-fx-background-color: #C8E6C9;");
        menuLateral.setAlignment(Pos.TOP_LEFT);

        // Área de contenido principal
        // Inicializamos areaContenido aquí para que sea un campo de la clase y los métodos lo usen
        areaContenido = new StackPane();
        areaContenido.setPadding(new Insets(20));
        areaContenido.setStyle("-fx-background-color: #F1F8E9;");

        // Mostrar un mensaje inicial al cargar el menú
        actualizarContenido(crearPanelMensaje("Seleccione una opción del menú para comenzar.", Color.GRAY));


        // --- Lógica de los botones ---
        btnPerfil.setOnAction(e -> actualizarContenido(crearPanelPerfilPaciente()));
        btnCitas.setOnAction(e -> actualizarContenido(crearPanelCitasMedicas()));
        btnResultados.setOnAction(e -> actualizarContenido(crearPanelResultadosExamenes()));
        btnPrescripciones.setOnAction(e -> actualizarContenido(crearPanelPrescripciones()));
        btnComunicacion.setOnAction(e -> actualizarContenido(crearPanelComunicacion()));
        btnRecursosEducativos.setOnAction(e -> actualizarContenido(crearPanelRecursosEducativos()));
        btnTelemedicina.setOnAction(e -> actualizarContenido(crearPanelTelemedicina()));
        btnConfiguracion.setOnAction(e -> actualizarContenido(crearPanelConfiguracion()));


        // Integrar todo con BorderPane
        BorderPane root = new BorderPane();
        root.setLeft(menuLateral);
        root.setCenter(areaContenido);

        Scene scene = new Scene(root, 850, 550);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Panel del Paciente - Hospital Vitali");
        primaryStage.show();
    }

    // Método auxiliar para crear botones del menú del paciente
    private Button crearBotonPaciente(String texto) {
        Button btn = new Button(texto);
        btn.setMinWidth(200);
        btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
        return btn;
    }

    // Método auxiliar para cambiar el contenido en el área central
    private void actualizarContenido(Region nuevoContenido) {
        areaContenido.getChildren().clear();
        areaContenido.getChildren().add(nuevoContenido);
        StackPane.setAlignment(nuevoContenido, Pos.TOP_CENTER); // Alinea el contenido en la parte superior central
    }

    // --- Métodos para crear los paneles de cada funcionalidad ---

    private VBox crearPanelMensaje(String mensaje, Color color) {
        Label label = new Label(mensaje);
        label.setFont(Font.font("Arial", 16));
        label.setTextFill(color);
        VBox panel = new VBox(label);
        panel.setAlignment(Pos.CENTER);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelPerfilPaciente() {
        Label titulo = new Label("Mi Perfil");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titulo.setTextFill(Color.DARKGREEN);

        Label lblNombre = new Label("Nombre: [Nombre del Paciente]"); // Aquí se mostraría el nombre real
        Label lblUsuario = new Label("Usuario: [Usuario del Paciente]"); // Aquí se mostraría el usuario real
        Label lblEmail = new Label("Email: [Email del Paciente]"); // Aquí se mostraría el email real
        Label lblNacimiento = new Label("Fecha de Nacimiento: [DD/MM/AAAA]"); // Puedes añadir este dato al Paciente
        Label lblDireccion = new Label("Dirección: [Dirección del Paciente]"); // Puedes añadir este dato al Paciente

        Button btnEditarPerfil = new Button("Editar Perfil");
        btnEditarPerfil.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        btnEditarPerfil.setOnAction(e -> mostrarAlerta(Alert.AlertType.INFORMATION, "Simulando edición de perfil..."));

        VBox panel = new VBox(10, titulo, lblNombre, lblUsuario, lblEmail, lblNacimiento, lblDireccion, btnEditarPerfil);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelCitasMedicas() {
        Label titulo = new Label("Mis Citas Médicas");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titulo.setTextFill(Color.DARKGREEN);

        ListView<String> listaCitas = new ListView<>();
        listaCitas.getItems().addAll(
            "Cita con Dr. Juan Pérez - 15/06/2025 (10:00 AM)",
            "Cita con Dra. Ana Gómez - 20/06/2025 (03:30 PM)",
            "Cita con Dr. Carlos Ruiz - 01/07/2025 (09:00 AM)"
        );
        listaCitas.setPrefHeight(150); // Altura preferida para la lista

        Button btnAgendarNueva = new Button("Agendar Nueva Cita");
        btnAgendarNueva.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnAgendarNueva.setOnAction(e -> mostrarAlerta(Alert.AlertType.INFORMATION, "Simulando agendar nueva cita..."));

        VBox panel = new VBox(10, titulo, listaCitas, btnAgendarNueva);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelResultadosExamenes() {
        Label titulo = new Label("Resultados de Exámenes");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titulo.setTextFill(Color.DARKGREEN);

        VBox resultadosList = new VBox(5);
        resultadosList.getChildren().addAll(
            new Hyperlink("Resultados de Hemograma Completo - 01/06/2025 (PDF)"),
            new Hyperlink("Informe de Rayos X de Tórax - 25/05/2025 (PDF)"),
            new Hyperlink("Análisis de Orina - 10/05/2025 (PDF)")
        );
        // Simular descarga al hacer clic
        resultadosList.getChildren().forEach(node -> {
            if (node instanceof Hyperlink) {
                ((Hyperlink) node).setOnAction(e -> mostrarAlerta(Alert.AlertType.INFORMATION, "Simulando descarga de: " + ((Hyperlink) node).getText()));
            }
        });

        VBox panel = new VBox(10, titulo, resultadosList);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelPrescripciones() {
        Label titulo = new Label("Mis Prescripciones");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titulo.setTextFill(Color.DARKGREEN);

        VBox prescripcionesList = new VBox(5);
        prescripcionesList.getChildren().addAll(
            new Label("1. Amoxicilina 500mg - 1 cada 8 horas por 7 días (Dr. Juan Pérez)"),
            new Label("2. Ibuprofeno 400mg - según necesidad (Dr. Juan Pérez)"),
            new Label("3. Vitamina D 1000 UI - 1 diaria (Dra. Ana Gómez)")
        );

        Button btnVerDetalles = new Button("Ver Detalles Completos");
        btnVerDetalles.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        btnVerDetalles.setOnAction(e -> mostrarAlerta(Alert.AlertType.INFORMATION, "Simulando vista de detalles de prescripciones..."));


        VBox panel = new VBox(10, titulo, prescripcionesList, btnVerDetalles);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelComunicacion() {
        Label titulo = new Label("Comunicación con el Doctor");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titulo.setTextFill(Color.DARKGREEN);

        TextArea chatArea = new TextArea();
        chatArea.setPromptText("Historial de mensajes...");
        chatArea.setEditable(false);
        chatArea.setPrefHeight(150);

        TextField mensajeInput = new TextField();
        mensajeInput.setPromptText("Escriba su mensaje aquí...");

        Button btnEnviar = new Button("Enviar Mensaje");
        btnEnviar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnEnviar.setOnAction(e -> {
            String mensaje = mensajeInput.getText().trim();
            if (!mensaje.isEmpty()) {
                chatArea.appendText("Usted: " + mensaje + "\n");
                mensajeInput.clear();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Mensaje enviado a su doctor.");
                // En una app real, aquí se enviaría el mensaje al servidor
            }
        });

        HBox inputRow = new HBox(5, mensajeInput, btnEnviar);
        HBox.setHgrow(mensajeInput, Priority.ALWAYS); // Haz que el campo de texto crezca

        VBox panel = new VBox(10, titulo, chatArea, inputRow);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelRecursosEducativos() {
        Label titulo = new Label("Recursos Educativos para tu Salud");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titulo.setTextFill(Color.DARKGREEN);

        TitledPane tpEjercicios = new TitledPane("Guías de Ejercicio", new Label("Videos y rutinas para mantenerse activo."));
        tpEjercicios.setExpanded(false);
        TitledPane tpDietas = new TitledPane("Planes de Dieta Saludable", new Label("Recetas y consejos para una alimentación balanceada."));
        tpDietas.setExpanded(false);
        TitledPane tpCondiciones = new TitledPane("Información sobre Condiciones Médicas", new Label("Artículos y FAQs sobre enfermedades comunes."));
        tpCondiciones.setExpanded(false);

        Accordion accordion = new Accordion(tpEjercicios, tpDietas, tpCondiciones);

        VBox panel = new VBox(10, titulo, accordion);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelTelemedicina() {
        Label titulo = new Label("Telemedicina: Videollamada con tu Doctor");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titulo.setTextFill(Color.DARKGREEN);

        Label lblInstrucciones = new Label(
            "Conéctate con tu doctor desde casa. Asegúrate de tener una buena conexión a internet."
        );
        lblInstrucciones.setWrapText(true);

        Button btnIniciarLlamada = new Button("Iniciar Videollamada Ahora");
        btnIniciarLlamada.setStyle("-fx-background-color: #E64A19; -fx-text-fill: white; -fx-font-size: 16px;"); // Naranja
        btnIniciarLlamada.setOnAction(e -> mostrarAlerta(Alert.AlertType.INFORMATION, "Simulando inicio de videollamada con el doctor..."));

        Button btnAgendarLlamada = new Button("Agendar Videollamada");
        btnAgendarLlamada.setStyle("-fx-background-color: #FFA000; -fx-text-fill: white;"); // Naranja más claro
        btnAgendarLlamada.setOnAction(e -> mostrarAlerta(Alert.AlertType.INFORMATION, "Simulando agendar videollamada..."));

        VBox panel = new VBox(15, titulo, lblInstrucciones, btnIniciarLlamada, btnAgendarLlamada);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelConfiguracion() {
        Label titulo = new Label("Configuración de la Cuenta");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titulo.setTextFill(Color.DARKGREEN);

        CheckBox chkNotificaciones = new CheckBox("Recibir notificaciones por correo");
        TextField txtCambiarContrasena = new PasswordField();
        txtCambiarContrasena.setPromptText("Nueva Contraseña");
        TextField txtConfirmarContrasena = new PasswordField();
        txtConfirmarContrasena.setPromptText("Confirmar Nueva Contraseña");

        Button btnGuardarCambios = new Button("Guardar Cambios");
        btnGuardarCambios.setStyle("-fx-background-color: #2E7D32; -fx-text-fill: white;");
        btnGuardarCambios.setOnAction(e -> mostrarAlerta(Alert.AlertType.INFORMATION, "Simulando guardar cambios de configuración..."));

        VBox panel = new VBox(10, titulo, chkNotificaciones, txtCambiarContrasena, txtConfirmarContrasena, btnGuardarCambios);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    // Método auxiliar para mostrar alertas, reutilizado de RegistroPaciente
    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo, mensaje, ButtonType.OK);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
}