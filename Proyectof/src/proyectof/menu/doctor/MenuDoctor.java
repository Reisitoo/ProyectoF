package proyectof.menu.doctor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight; // Importación necesaria para FontWeight
import javafx.stage.Stage;
import proyectof.PantallaInicial;

public class MenuDoctor {

    // Necesitamos que el StackPane del contenido sea accesible para los métodos de actualización
    private StackPane areaContenido;

    public void mostrar(Stage primaryStage, String nombreDoctor) {
        // Bienvenida
        Label lblBienvenida = new Label("Bienvenido, Dr(a). " + nombreDoctor);
        lblBienvenida.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Usando FontWeight
        lblBienvenida.setTextFill(Color.DARKBLUE);

        // Botones del menú
        Button btnAgenda = crearBotonDoctor("Agenda");
        Button btnInformes = crearBotonDoctor("Informes de Pacientes");
        Button btnConsultas = crearBotonDoctor("Consultas Pendientes");
        Button btnRedes = crearBotonDoctor("Redes Médicas");
        Button btnComunicacion = crearBotonDoctor("Comunicación");
        Button btnTelemedicina = crearBotonDoctor("Telemedicina");
        Button btnConfiguracion = crearBotonDoctor("Configuración");

        // Botón de Cerrar Sesión
        Button btnCerrarSesion = new Button("Cerrar Sesión");
        btnCerrarSesion.setStyle("-fx-background-color: #C62828; -fx-text-fill: white; -fx-font-size: 14px;"); // Rojo intenso
        btnCerrarSesion.setMinWidth(200);
        btnCerrarSesion.setOnAction(e -> {
            PantallaInicial inicio = new PantallaInicial();
            inicio.start(primaryStage); // Regresa a la pantalla inicial
        });

        // Contenedor del menú lateral
        VBox menuLateral = new VBox(10, lblBienvenida, btnAgenda, btnInformes, btnConsultas, btnRedes,
                                    btnComunicacion, btnTelemedicina, btnConfiguracion, btnCerrarSesion);
        menuLateral.setPadding(new Insets(20));
        menuLateral.setStyle("-fx-background-color: #BBDEFB;"); // Azul claro para el menú lateral
        menuLateral.setAlignment(Pos.TOP_LEFT);

        // Área de contenido principal
        areaContenido = new StackPane();
        areaContenido.setPadding(new Insets(20));
        areaContenido.setStyle("-fx-background-color: #E3F2FD;"); // Azul aún más claro para el área de contenido

        // Mensaje inicial al cargar el menú
        actualizarContenido(crearPanelMensaje("Seleccione una opción del menú para gestionar el hospital.", Color.GRAY));

        // --- Lógica de los botones ---
        btnAgenda.setOnAction(e -> actualizarContenido(crearPanelAgenda()));
        btnInformes.setOnAction(e -> actualizarContenido(crearPanelInformesPacientes()));
        btnConsultas.setOnAction(e -> actualizarContenido(crearPanelConsultasPendientes()));
        btnRedes.setOnAction(e -> actualizarContenido(crearPanelRedesMedicas()));
        btnComunicacion.setOnAction(e -> actualizarContenido(crearPanelComunicacionDoctor()));
        btnTelemedicina.setOnAction(e -> actualizarContenido(crearPanelTelemedicinaDoctor()));
        btnConfiguracion.setOnAction(e -> actualizarContenido(crearPanelConfiguracionDoctor()));


        // Integrar todo con BorderPane
        BorderPane root = new BorderPane();
        root.setLeft(menuLateral);
        root.setCenter(areaContenido);

        Scene scene = new Scene(root, 850, 550); // Un tamaño similar al del paciente
        primaryStage.setScene(scene);
        primaryStage.setTitle("Panel del Doctor - Hospital Vitali");
        primaryStage.show();
    }

    // Método auxiliar para crear botones del menú del doctor con estilo personalizado
    private Button crearBotonDoctor(String texto) {
        Button btn = new Button(texto);
        btn.setMinWidth(200);
        btn.setStyle("-fx-background-color: #1565C0; -fx-text-fill: white; -fx-font-size: 14px;"); // Botones azules
        return btn;
    }

    // Método auxiliar para cambiar el contenido en el área central
    private void actualizarContenido(Region nuevoContenido) {
        areaContenido.getChildren().clear();
        areaContenido.getChildren().add(nuevoContenido);
        StackPane.setAlignment(nuevoContenido, Pos.TOP_CENTER); // Alinea el contenido en la parte superior central
    }

    // Método auxiliar para mostrar alertas, reutilizado
    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo, mensaje, ButtonType.OK);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }

    private VBox crearPanelMensaje(String mensaje, Color color) {
        Label label = new Label(mensaje);
        label.setFont(Font.font("Arial", 16));
        label.setTextFill(color);
        VBox panel = new VBox(label);
        panel.setAlignment(Pos.CENTER);
        panel.setPadding(new Insets(20));
        return panel;
    }

    // --- Métodos para crear los paneles de cada funcionalidad del Doctor ---

    private VBox crearPanelAgenda() {
        Label titulo = new Label("Mi Agenda de Citas");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Corregido el orden
        titulo.setTextFill(Color.DARKBLUE);

        DatePicker datePicker = new DatePicker();
        ListView<String> listaAgenda = new ListView<>();
        listaAgenda.getItems().addAll(
            "10:00 AM - Paciente Ana Garcia (Consulta de seguimiento)",
            "11:00 AM - Paciente Carlos López (Nueva consulta)",
            "02:00 PM - Videollamada - Paciente María Díaz"
        );
        listaAgenda.setPrefHeight(150);

        Button btnVerDia = new Button("Ver Citas del Día");
        btnVerDia.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        btnVerDia.setOnAction(e -> mostrarAlerta(Alert.AlertType.INFORMATION, "Simulando ver agenda para " + datePicker.getValue()));

        VBox panel = new VBox(10, titulo, datePicker, btnVerDia, listaAgenda);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelInformesPacientes() {
        Label titulo = new Label("Informes y Expedientes de Pacientes");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Corregido el orden
        titulo.setTextFill(Color.DARKBLUE);

        TextField searchField = new TextField();
        searchField.setPromptText("Buscar paciente por nombre o ID...");
        Button btnBuscar = new Button("Buscar");
        btnBuscar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnBuscar.setOnAction(e -> mostrarAlerta(Alert.AlertType.INFORMATION, "Simulando búsqueda de: " + searchField.getText()));

        ListView<String> listaPacientes = new ListView<>();
        listaPacientes.getItems().addAll(
            "Paciente: Ana Garcia (ID: 001) - Ver Expediente",
            "Paciente: Carlos López (ID: 002) - Ver Expediente",
            "Paciente: María Díaz (ID: 003) - Ver Expediente"
        );
        listaPacientes.setPrefHeight(200);

        HBox searchBar = new HBox(5, searchField, btnBuscar);
        HBox.setHgrow(searchField, Priority.ALWAYS);

        VBox panel = new VBox(10, titulo, searchBar, listaPacientes);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelConsultasPendientes() {
        Label titulo = new Label("Consultas y Mensajes Pendientes");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Corregido el orden
        titulo.setTextFill(Color.DARKBLUE);

        ListView<String> listaConsultas = new ListView<>();
        listaConsultas.getItems().addAll(
            "Mensaje de Ana Garcia: 'Tengo una pregunta sobre mi medicación.'",
            "Consulta de Carlos López: 'Solicitud de renovación de receta.'",
            "Nuevo mensaje de María Díaz: 'Dudas sobre resultados de laboratorio.'"
        );
        listaConsultas.setPrefHeight(180);

        Button btnResponder = new Button("Responder Seleccionado");
        btnResponder.setStyle("-fx-background-color: #FFC107; -fx-text-fill: black;"); // Naranja
        btnResponder.setOnAction(e -> {
            String selected = listaConsultas.getSelectionModel().getSelectedItem();
            if (selected != null) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Simulando responder a: " + selected);
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Seleccione una consulta para responder.");
            }
        });

        VBox panel = new VBox(10, titulo, listaConsultas, btnResponder);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelRedesMedicas() {
        Label titulo = new Label("Red de Referencias Médicas");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Corregido el orden
        titulo.setTextFill(Color.DARKBLUE);

        Label lblDescripcion = new Label(
            "Encuentre especialistas y centros de referencia para sus pacientes. " +
            "Conéctese con otros profesionales de la salud."
        );
        lblDescripcion.setWrapText(true);

        Button btnBuscarEspecialista = new Button("Buscar Especialista");
        btnBuscarEspecialista.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        btnBuscarEspecialista.setOnAction(e -> mostrarAlerta(Alert.AlertType.INFORMATION, "Simulando búsqueda de especialista..."));

        Button btnVerColaboradores = new Button("Ver Red de Colaboradores");
        btnVerColaboradores.setStyle("-fx-background-color: #1976D2; -fx-text-fill: white;");
        btnVerColaboradores.setOnAction(e -> mostrarAlerta(Alert.AlertType.INFORMATION, "Simulando vista de red de colaboradores..."));

        VBox panel = new VBox(15, titulo, lblDescripcion, btnBuscarEspecialista, btnVerColaboradores);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelComunicacionDoctor() {
        Label titulo = new Label("Comunicación Interna y Mensajes");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Corregido el orden
        titulo.setTextFill(Color.DARKBLUE);

        TextArea chatArea = new TextArea();
        chatArea.setPromptText("Historial de mensajes con otros doctores o personal...");
        chatArea.setEditable(false);
        chatArea.setPrefHeight(150);

        TextField mensajeInput = new TextField();
        mensajeInput.setPromptText("Escriba su mensaje aquí...");

        Button btnEnviar = new Button("Enviar Mensaje");
        btnEnviar.setStyle("-fx-background-color: #1565C0; -fx-text-fill: white;");
        btnEnviar.setOnAction(e -> {
            String mensaje = mensajeInput.getText().trim();
            if (!mensaje.isEmpty()) {
                chatArea.appendText("Usted: " + mensaje + "\n");
                mensajeInput.clear();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Mensaje interno enviado.");
            }
        });

        HBox inputRow = new HBox(5, mensajeInput, btnEnviar);
        HBox.setHgrow(mensajeInput, Priority.ALWAYS);

        VBox panel = new VBox(10, titulo, chatArea, inputRow);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelTelemedicinaDoctor() {
        Label titulo = new Label("Gestión de Telemedicina");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Corregido el orden
        titulo.setTextFill(Color.DARKBLUE);

        Label lblInstrucciones = new Label(
            "Acceda a sus citas de telemedicina, inicie videollamadas con pacientes, o revise el historial."
        );
        lblInstrucciones.setWrapText(true);

        Button btnVerCitasTelemedicina = new Button("Ver Citas de Telemedicina");
        btnVerCitasTelemedicina.setStyle("-fx-background-color: #E64A19; -fx-text-fill: white;");
        btnVerCitasTelemedicina.setOnAction(e -> mostrarAlerta(Alert.AlertType.INFORMATION, "Simulando ver citas de telemedicina..."));

        Button btnIniciarLlamadaPaciente = new Button("Iniciar Llamada con Paciente");
        btnIniciarLlamadaPaciente.setStyle("-fx-background-color: #FFA000; -fx-text-fill: white;");
        btnIniciarLlamadaPaciente.setOnAction(e -> mostrarAlerta(Alert.AlertType.INFORMATION, "Simulando inicio de llamada con paciente..."));

        VBox panel = new VBox(15, titulo, lblInstrucciones, btnVerCitasTelemedicina, btnIniciarLlamadaPaciente);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelConfiguracionDoctor() {
        Label titulo = new Label("Configuración de la Cuenta de Doctor");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Corregido el orden
        titulo.setTextFill(Color.DARKBLUE);

        CheckBox chkNotificacionesEmail = new CheckBox("Recibir notificaciones por email");
        CheckBox chkRecordatoriosCitas = new CheckBox("Recordatorios de citas");

        TextField txtCambiarContrasena = new PasswordField();
        txtCambiarContrasena.setPromptText("Nueva Contraseña");
        TextField txtConfirmarContrasena = new PasswordField();
        txtConfirmarContrasena.setPromptText("Confirmar Nueva Contraseña");

        Button btnGuardarConfig = new Button("Guardar Configuración");
        btnGuardarConfig.setStyle("-fx-background-color: #2E7D32; -fx-text-fill: white;");
        btnGuardarConfig.setOnAction(e -> mostrarAlerta(Alert.AlertType.INFORMATION, "Simulando guardar configuración del doctor..."));

        VBox panel = new VBox(10, titulo, chkNotificacionesEmail, chkRecordatoriosCitas, txtCambiarContrasena, txtConfirmarContrasena, btnGuardarConfig);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }
}