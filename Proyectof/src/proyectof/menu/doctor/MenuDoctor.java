package proyectof.menu.doctor;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import proyectof.PantallaInicial;

public class MenuDoctor {

    private StackPane areaContenido;
    // Referencias a los botones del menú para poder manipular su estilo
    private Button btnAgenda;
    private Button btnInformes;
    private Button btnConsultas;
    private Button btnRedes;
    private Button btnComunicacion;
    private Button btnTelemedicina;
    private Button btnConfiguracion;

    private Button botonActivo = null; // Para rastrear qué botón está activo actualmente

    public void mostrar(Stage primaryStage, String nombreDoctor) {
        // Bienvenida al Doctor
        Label lblBienvenida = new Label("Bienvenido(a), Dr(a). " + nombreDoctor);
        lblBienvenida.setFont(Font.font("Arial", FontWeight.BOLD, 12)); // ¡Ajustado a 22px como en el diseño original!
        lblBienvenida.setTextFill(Color.web("#2C3E50")); // Azul/gris oscuro profesional

        // Botones del Menú Principal
        btnAgenda = crearBotonDoctor("Agenda");
        btnInformes = crearBotonDoctor("Informes de Pacientes");
        btnConsultas = crearBotonDoctor("Consultas Pendientes");
        btnRedes = crearBotonDoctor("Redes Médicas");
        btnComunicacion = crearBotonDoctor("Comunicación");
        btnTelemedicina = crearBotonDoctor("Telemedicina");
        btnConfiguracion = crearBotonDoctor("Configuración");

        // Botón de Cerrar Sesión (con un estilo distintivo y más suave)
        Button btnCerrarSesion = new Button("Cerrar Sesión");
        btnCerrarSesion.setStyle(
                "-fx-background-color: #E74C3C;" + // Rojo más suave y discreto
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-background-radius: 5;" + // Esquinas ligeramente redondeadas
                "-fx-border-color: #C0392B;" + // Borde sutil para profundidad
                "-fx-border-width: 1;"
        );
        btnCerrarSesion.setMinWidth(220); // Ancho consistente con los otros botones
        btnCerrarSesion.setPrefHeight(40); // Altura consistente
        btnCerrarSesion.setOnAction(e -> {
            PantallaInicial inicio = new PantallaInicial();
            inicio.start(primaryStage); // Regresa a la pantalla inicial
        });

        // Contenedor del Menú Lateral (izquierdo)
        VBox menuLateral = new VBox(15); // Espaciado entre elementos
        menuLateral.getChildren().addAll(lblBienvenida, new Separator(), btnAgenda, btnInformes, btnConsultas, btnRedes,
                btnComunicacion, btnTelemedicina, btnConfiguracion, new Separator(), btnCerrarSesion); // Separadores para división visual
        menuLateral.setPadding(new Insets(25)); // Mayor padding alrededor
        menuLateral.setStyle(
                "-fx-background-color: #ECF0F1;" + // Gris claro para un look limpio
                "-fx-border-color: #BDC3C7;" + // Borde sutil para definir el área
                "-fx-border-width: 0 1 0 0;" // Borde solo a la derecha
        );
        menuLateral.setAlignment(Pos.TOP_CENTER); // Centra los elementos en el VBox
        menuLateral.setPrefWidth(250); // Ancho fijo para el menú

        // Área de Contenido Principal (derecha)
        areaContenido = new StackPane();
        areaContenido.setPadding(new Insets(30)); // Mayor padding interno
        areaContenido.setStyle("-fx-background-color: #FFFFFF;"); // Fondo blanco puro para el contenido

        // Mensaje inicial al cargar el menú
        actualizarContenido(crearPanelMensaje("Seleccione una opción del menú para gestionar su trabajo.", Color.web("#7F8C8D"))); // Gris apagado
        // Al iniciar, ningún botón está "activo" visualmente, pero se podría activar uno por defecto.

        // --- Lógica de los botones para actualizar el contenido y el estado activo ---
        btnAgenda.setOnAction(e -> {
            activarBoton(btnAgenda);
            actualizarContenido(crearPanelAgenda());
        });
        btnInformes.setOnAction(e -> {
            activarBoton(btnInformes);
            actualizarContenido(crearPanelInformesPacientes());
        });
        btnConsultas.setOnAction(e -> {
            activarBoton(btnConsultas);
            actualizarContenido(crearPanelConsultasPendientes());
        });
        btnRedes.setOnAction(e -> {
            activarBoton(btnRedes);
            actualizarContenido(crearPanelRedesMedicas());
        });
        btnComunicacion.setOnAction(e -> {
            activarBoton(btnComunicacion);
            actualizarContenido(crearPanelComunicacionDoctor());
        });
        btnTelemedicina.setOnAction(e -> {
            activarBoton(btnTelemedicina);
            actualizarContenido(crearPanelTelemedicinaDoctor());
        });
        btnConfiguracion.setOnAction(e -> {
            activarBoton(btnConfiguracion);
            actualizarContenido(crearPanelConfiguracionDoctor());
        });

        // Integrar todo con BorderPane
        BorderPane root = new BorderPane();
        root.setLeft(menuLateral);
        root.setCenter(areaContenido);

        Scene scene = new Scene(root, 1000, 650); // Ventana más grande para mejor espacio
        primaryStage.setScene(scene);
        primaryStage.setTitle("Panel del Doctor - Hospital Vitali");
        primaryStage.show();
    }

    // Método auxiliar para crear botones del menú del doctor con estilo personalizado
    private Button crearBotonDoctor(String texto) {
        Button btn = new Button(texto);
        btn.setMinWidth(220); // Ancho consistente
        btn.setPrefHeight(40); // Altura consistente
        String estiloNormal =
                "-fx-background-color: #3498DB;" + // Azul vibrante pero no chillón
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-background-radius: 5;" + // Esquinas redondeadas
                "-fx-border-color: #2980B9;" + // Borde más oscuro para un efecto 3D sutil
                "-fx-border-width: 1;";
        btn.setStyle(estiloNormal);

        // Efecto hover sutil
        btn.setOnMouseEntered(e -> {
            if (btn != botonActivo) { // Solo cambia el estilo si no es el botón activo
                btn.setStyle(
                        "-fx-background-color: #2980B9;" + // Azul un poco más oscuro al pasar el ratón
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-background-radius: 5;" +
                        "-fx-border-color: #2980B9;" +
                        "-fx-border-width: 1;"
                );
            }
        });
        btn.setOnMouseExited(e -> {
            if (btn != botonActivo) { // Solo vuelve al estilo normal si no es el botón activo
                btn.setStyle(estiloNormal);
            }
        });
        return btn;
    }

    // Nuevo método para manejar el estado activo de los botones del menú
    private void activarBoton(Button nuevoBotonActivo) {
        // Si hay un botón activo previamente, resetear su estilo
        if (botonActivo != null) {
            botonActivo.setStyle(
                    "-fx-background-color: #3498DB;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 15px;" +
                    "-fx-background-radius: 5;" +
                    "-fx-border-color: #2980B9;" +
                    "-fx-border-width: 1;"
            );
        }
        // Establecer el nuevo botón como activo y aplicar su estilo de "activo"
        botonActivo = nuevoBotonActivo;
        botonActivo.setStyle(
                "-fx-background-color: #2C3E50;" + // Azul/gris muy oscuro para el estado activo
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" + // Negrita para el texto del botón activo
                "-fx-background-radius: 5;" +
                "-fx-border-color: #2C3E50;" +
                "-fx-border-width: 1;"
        );
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

    // Paneles de Contenido (revisados para consistencia visual y más "realismo")

    private VBox crearPanelMensaje(String mensaje, Color color) {
        Label label = new Label(mensaje);
        label.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        label.setTextFill(color);
        VBox panel = new VBox(label);
        panel.setAlignment(Pos.CENTER);
        panel.setPadding(new Insets(50));
        return panel;
    }

    private VBox crearPanelAgenda() {
        Label titulo = new Label("Mi Agenda de Citas");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titulo.setTextFill(Color.web("#2C3E50"));

        Label lblFecha = new Label("Seleccione una fecha:");
        lblFecha.setFont(Font.font("Arial", 14));

        DatePicker datePicker = new DatePicker();
        datePicker.setStyle("-fx-font-size: 14px; -fx-background-radius: 5; -fx-border-color: #BDC3C7;");
        datePicker.setPrefHeight(35);
        datePicker.setPromptText("Seleccione una fecha");

        Button btnVerDia = new Button("Ver Citas del Día");
        btnVerDia.setStyle("-fx-background-color: #28B463; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        btnVerDia.setPrefHeight(35);
        btnVerDia.setMinWidth(150);

        Label lblCitas = new Label("Citas para la fecha seleccionada:");
        lblCitas.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        ListView<String> listaAgenda = new ListView<>();
        listaAgenda.getItems().addAll(
                "10:00 AM - Paciente Ana Garcia (Consulta de seguimiento)",
                "11:00 AM - Paciente Carlos López (Nueva consulta)",
                "02:00 PM - Videollamada - Paciente María Díaz",
                "03:30 PM - Paciente Juan Pérez (Revisión de resultados)",
                "04:00 PM - Paciente Sofia Rodriguez (Segunda opinión)"
        );
        listaAgenda.setPrefHeight(200);
        listaAgenda.setStyle("-fx-border-color: #BDC3C7; -fx-border-width: 1; -fx-background-radius: 5;");

        // Simulación de interacción
        listaAgenda.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Detalles de la cita: " + newVal);
            }
        });

        HBox controlCitas = new HBox(10, datePicker, btnVerDia);
        controlCitas.setAlignment(Pos.CENTER_LEFT);

        VBox panel = new VBox(15, titulo, new Separator(), lblFecha, controlCitas, lblCitas, listaAgenda);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelInformesPacientes() {
        Label titulo = new Label("Informes y Expedientes de Pacientes");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titulo.setTextFill(Color.web("#2C3E50"));

        Label lblBuscar = new Label("Buscar paciente:");
        lblBuscar.setFont(Font.font("Arial", 14));

        TextField searchField = new TextField();
        searchField.setPromptText("Nombre, ID o Cédula...");
        searchField.setStyle("-fx-font-size: 14px; -fx-background-radius: 5; -fx-border-color: #BDC3C7;");
        searchField.setPrefHeight(35);

        Button btnBuscar = new Button("Buscar");
        btnBuscar.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        btnBuscar.setPrefHeight(35);
        btnBuscar.setMinWidth(100);

        HBox searchBar = new HBox(10, searchField, btnBuscar);
        HBox.setHgrow(searchField, Priority.ALWAYS);

        Label lblResultados = new Label("Resultados de la búsqueda:");
        lblResultados.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        // Usaremos una TableView para una mejor visualización de los datos
        TableView<Paciente> tablaPacientes = new TableView<>();
        tablaPacientes.setPrefHeight(250);
        tablaPacientes.setStyle("-fx-border-color: #BDC3C7; -fx-border-width: 1; -fx-background-radius: 5;");

        TableColumn<Paciente, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNombre()));
        colNombre.setPrefWidth(150);

        TableColumn<Paciente, String> colID = new TableColumn<>("ID");
        colID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getId()));
        colID.setPrefWidth(80);

        TableColumn<Paciente, String> colUltimaConsulta = new TableColumn<>("Última Consulta");
        colUltimaConsulta.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getUltimaConsulta()));
        colUltimaConsulta.setPrefWidth(180);

        TableColumn<Paciente, String> colAcciones = new TableColumn<>("Acciones");
        colAcciones.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("Ver Expediente"));
        colAcciones.setCellFactory(tc -> new TableCell<Paciente, String>() {
            final Button btn = new Button("Ver Expediente");
            {
                btn.setStyle("-fx-background-color: #28B463; -fx-text-fill: white; -fx-font-size: 12px; -fx-background-radius: 3;");
                btn.setOnAction(e -> {
                    Paciente paciente = getTableView().getItems().get(getIndex());
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Mostrando expediente de: " + paciente.getNombre() + " (ID: " + paciente.getId() + ")");
                });
            }
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(btn);
                    setText(null);
                }
            }
        });
        colAcciones.setPrefWidth(130);
        colAcciones.setResizable(false);


        tablaPacientes.getColumns().addAll(colNombre, colID, colUltimaConsulta, colAcciones);

        // Datos de ejemplo
        tablaPacientes.getItems().addAll(
                new Paciente("Ana Garcia", "001", "2024-05-10"),
                new Paciente("Carlos López", "002", "2024-06-18"),
                new Paciente("María Díaz", "003", "2024-06-01"),
                new Paciente("Juan Pérez", "004", "2024-04-22")
        );

        btnBuscar.setOnAction(e -> {
            String textoBusqueda = searchField.getText().toLowerCase();
            tablaPacientes.getItems().clear();
            // Simulación de búsqueda
            if (textoBusqueda.isEmpty()) {
                tablaPacientes.getItems().addAll(
                    new Paciente("Ana Garcia", "001", "2024-05-10"),
                    new Paciente("Carlos López", "002", "2024-06-18"),
                    new Paciente("María Díaz", "003", "2024-06-01"),
                    new Paciente("Juan Pérez", "004", "2024-04-22")
                );
            } else {
                if ("ana".contains(textoBusqueda) || "001".contains(textoBusqueda)) {
                    tablaPacientes.getItems().add(new Paciente("Ana Garcia", "001", "2024-05-10"));
                }
                if ("carlos".contains(textoBusqueda) || "002".contains(textoBusqueda)) {
                    tablaPacientes.getItems().add(new Paciente("Carlos López", "002", "2024-06-18"));
                }
                 if ("maria".contains(textoBusqueda) || "003".contains(textoBusqueda)) {
                    tablaPacientes.getItems().add(new Paciente("María Díaz", "003", "2024-06-01"));
                }
            }
            mostrarAlerta(Alert.AlertType.INFORMATION, "Búsqueda de pacientes simulada para: '" + searchField.getText() + "'");
        });


        VBox panel = new VBox(15, titulo, new Separator(), lblBuscar, searchBar, lblResultados, tablaPacientes);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    // Clase auxiliar para los datos de la tabla de pacientes
    public static class Paciente {
        private final String nombre;
        private final String id;
        private final String ultimaConsulta;

        public Paciente(String nombre, String id, String ultimaConsulta) {
            this.nombre = nombre;
            this.id = id;
            this.ultimaConsulta = ultimaConsulta;
        }

        public String getNombre() { return nombre; }
        public String getId() { return id; }
        public String getUltimaConsulta() { return ultimaConsulta; }
    }


    private VBox crearPanelConsultasPendientes() {
        Label titulo = new Label("Consultas y Mensajes Pendientes");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titulo.setTextFill(Color.web("#2C3E50"));

        Label lblPendientes = new Label("Lista de consultas y mensajes:");
        lblPendientes.setFont(Font.font("Arial", 14));

        ListView<String> listaConsultas = new ListView<>();
        listaConsultas.getItems().addAll(
                "Mensaje de Ana Garcia: 'Tengo una pregunta sobre mi medicación.' - Prioridad: Alta",
                "Consulta de Carlos López: 'Solicitud de renovación de receta.' - Prioridad: Media",
                "Nuevo mensaje de María Díaz: 'Dudas sobre resultados de laboratorio.' - Prioridad: Alta",
                "Mensaje de Juan Pérez: 'Necesito una cita urgente.' - Prioridad: Urgente",
                "Consulta de Sofia Rodriguez: 'Reporte de síntomas post-consulta.' - Prioridad: Baja"
        );
        listaConsultas.setPrefHeight(200);
        listaConsultas.setStyle("-fx-border-color: #BDC3C7; -fx-border-width: 1; -fx-background-radius: 5;");

        Label lblRespuesta = new Label("Responder a la consulta seleccionada:");
        lblRespuesta.setFont(Font.font("Arial", 14));

        TextArea txtRespuesta = new TextArea();
        txtRespuesta.setPromptText("Escriba su respuesta aquí...");
        txtRespuesta.setWrapText(true);
        txtRespuesta.setPrefHeight(80);
        txtRespuesta.setStyle("-fx-font-size: 14px; -fx-background-radius: 5; -fx-border-color: #BDC3C7; -fx-border-width: 1;");


        Button btnMarcarLeido = new Button("Marcar como Leído");
        btnMarcarLeido.setStyle("-fx-background-color: #5DADE2; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        btnMarcarLeido.setPrefHeight(35);
        btnMarcarLeido.setMinWidth(150);

        Button btnEnviarRespuesta = new Button("Enviar Respuesta");
        btnEnviarRespuesta.setStyle("-fx-background-color: #F39C12; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        btnEnviarRespuesta.setPrefHeight(35);
        btnEnviarRespuesta.setMinWidth(150);

        HBox controlesRespuesta = new HBox(10, btnMarcarLeido, btnEnviarRespuesta);
        controlesRespuesta.setAlignment(Pos.CENTER_RIGHT);

        btnEnviarRespuesta.setOnAction(e -> {
            String selected = listaConsultas.getSelectionModel().getSelectedItem();
            String respuesta = txtRespuesta.getText().trim();
            if (selected != null && !respuesta.isEmpty()) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Respuesta enviada a: " + selected + "\nRespuesta: " + respuesta);
                listaConsultas.getItems().remove(selected); // Simula que la consulta se procesa
                txtRespuesta.clear();
            } else if (selected == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Seleccione una consulta para responder.");
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Escriba una respuesta.");
            }
        });

        btnMarcarLeido.setOnAction(e -> {
            String selected = listaConsultas.getSelectionModel().getSelectedItem();
            if (selected != null) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Consulta marcada como leída: " + selected);
                listaConsultas.getItems().remove(selected); // Simula que se "archiva"
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Seleccione una consulta para marcar como leída.");
            }
        });


        VBox panel = new VBox(15, titulo, new Separator(), lblPendientes, listaConsultas, lblRespuesta, txtRespuesta, controlesRespuesta);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelRedesMedicas() {
        Label titulo = new Label("Red de Referencias Médicas");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titulo.setTextFill(Color.web("#2C3E50"));

        Label lblDescripcion = new Label(
                "Encuentre especialistas y centros de referencia para sus pacientes. " +
                "Conéctese con otros profesionales de la salud y amplíe su red de colaboración."
        );
        lblDescripcion.setFont(Font.font("Arial", 14));
        lblDescripcion.setTextFill(Color.web("#5D6D7E"));
        lblDescripcion.setWrapText(true);

        Label lblBuscarEspecialista = new Label("Buscar por especialidad o nombre:");
        lblBuscarEspecialista.setFont(Font.font("Arial", 14));

        TextField txtEspecialista = new TextField();
        txtEspecialista.setPromptText("Ej: Cardiología, Dr. Sánchez...");
        txtEspecialista.setStyle("-fx-font-size: 14px; -fx-background-radius: 5; -fx-border-color: #BDC3C7;");
        txtEspecialista.setPrefHeight(35);

        Button btnBuscarEspecialista = new Button("Buscar Especialista");
        btnBuscarEspecialista.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        btnBuscarEspecialista.setPrefHeight(40);

        HBox buscadorEspecialista = new HBox(10, txtEspecialista, btnBuscarEspecialista);
        HBox.setHgrow(txtEspecialista, Priority.ALWAYS);

        ListView<String> listaEspecialistas = new ListView<>();
        listaEspecialistas.setPrefHeight(150);
        listaEspecialistas.getItems().addAll(
                "Dr. Roberto Gómez (Cardiólogo) - Clínica del Corazón",
                "Dra. Laura Flores (Dermatóloga) - Centro Piel Sana",
                "Dr. Miguel Torres (Pediatra) - Hospital Infantil Vitali"
        );
        listaEspecialistas.setStyle("-fx-border-color: #BDC3C7; -fx-border-width: 1; -fx-background-radius: 5;");


        Label lblColaboradores = new Label("Mis Colaboradores y Contactos:");
        lblColaboradores.setFont(Font.font("Arial", 14));

        ListView<String> listaColaboradores = new ListView<>();
        listaColaboradores.setPrefHeight(100);
        listaColaboradores.getItems().addAll(
                "Dra. Sofía Pérez (Ginecóloga)",
                "Dr. Andrés Castro (Neurólogo)"
        );
        listaColaboradores.setStyle("-fx-border-color: #BDC3C7; -fx-border-width: 1; -fx-background-radius: 5;");


        Button btnVerColaboradores = new Button("Administrar Colaboradores");
        btnVerColaboradores.setStyle("-fx-background-color: #5DADE2; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        btnVerColaboradores.setPrefHeight(40);
        btnVerColaboradores.setOnAction(e -> mostrarAlerta(Alert.AlertType.INFORMATION, "Simulando administración de red de colaboradores..."));

        btnBuscarEspecialista.setOnAction(e -> mostrarAlerta(Alert.AlertType.INFORMATION, "Buscando especialista: " + txtEspecialista.getText()));

        VBox panel = new VBox(15, titulo, new Separator(), lblDescripcion,
                lblBuscarEspecialista, buscadorEspecialista, listaEspecialistas,
                lblColaboradores, listaColaboradores, btnVerColaboradores);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelComunicacionDoctor() {
        Label titulo = new Label("Comunicación Interna y Mensajes");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titulo.setTextFill(Color.web("#2C3E50"));

        Label lblConversaciones = new Label("Conversaciones recientes:");
        lblConversaciones.setFont(Font.font("Arial", 14));

        ListView<String> listaConversaciones = new ListView<>();
        listaConversaciones.setPrefHeight(100);
        listaConversaciones.getItems().addAll(
                "Chat con Dra. Sofía Pérez (Ginecología)",
                "Mensajes de personal de enfermería (Turno Noche)",
                "Chat grupal: Equipo de Cirugía"
        );
        listaConversaciones.setStyle("-fx-border-color: #BDC3C7; -fx-border-width: 1; -fx-background-radius: 5;");

        Label lblChat = new Label("Historial de Chat:");
        lblChat.setFont(Font.font("Arial", 14));

        TextArea chatArea = new TextArea();
        chatArea.setPromptText("Historial de mensajes con el contacto seleccionado...");
        chatArea.setEditable(false);
        chatArea.setPrefHeight(180);
        chatArea.setStyle("-fx-border-color: #BDC3C7; -fx-border-width: 1; -fx-background-radius: 5;");
        chatArea.setText("Dra. Pérez: Buenos días, Dr. ¿Podemos revisar el caso de la paciente X?\n" +
                         "Usted: Claro, Dra. Tengo disponibilidad a las 3 PM. ¿Le parece bien?\n" +
                         "Dra. Pérez: Perfecto, a las 3 PM entonces. Gracias.");


        Label lblEnviarMensaje = new Label("Escribir nuevo mensaje:");
        lblEnviarMensaje.setFont(Font.font("Arial", 14));

        TextField mensajeInput = new TextField();
        mensajeInput.setPromptText("Escriba su mensaje aquí...");
        mensajeInput.setStyle("-fx-font-size: 14px; -fx-background-radius: 5; -fx-border-color: #BDC3C7; -fx-border-width: 1;");
        mensajeInput.setPrefHeight(35);

        Button btnEnviar = new Button("Enviar Mensaje");
        btnEnviar.setStyle("-fx-background-color: #2ECC71; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        btnEnviar.setPrefHeight(35);
        btnEnviar.setMinWidth(120);

        HBox inputRow = new HBox(10, mensajeInput, btnEnviar);
        HBox.setHgrow(mensajeInput, Priority.ALWAYS);

        // Simulación de selección de chat
        listaConversaciones.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                chatArea.setText("--- Historial de " + newVal + " (Simulado) ---\n" +
                                 "Mensaje antiguo 1...\n" +
                                 "Mensaje antiguo 2...\n" +
                                 "Mensaje más reciente...\n");
            }
        });


        btnEnviar.setOnAction(e -> {
            String mensaje = mensajeInput.getText().trim();
            String selectedChat = listaConversaciones.getSelectionModel().getSelectedItem();
            if (!mensaje.isEmpty() && selectedChat != null) {
                chatArea.appendText("Usted: " + mensaje + "\n");
                mensajeInput.clear();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Mensaje enviado a: " + selectedChat);
            } else if (selectedChat == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Seleccione una conversación para enviar el mensaje.");
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Escriba un mensaje para enviar.");
            }
        });


        VBox panel = new VBox(15, titulo, new Separator(), lblConversaciones, listaConversaciones, lblChat, chatArea, lblEnviarMensaje, inputRow);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelTelemedicinaDoctor() {
        Label titulo = new Label("Gestión de Telemedicina");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titulo.setTextFill(Color.web("#2C3E50"));

        Label lblInstrucciones = new Label(
                "Acceda a sus citas de telemedicina, inicie videollamadas con pacientes, o revise el historial de teleconsultas."
        );
        lblInstrucciones.setFont(Font.font("Arial", 14));
        lblInstrucciones.setTextFill(Color.web("#5D6D7E"));
        lblInstrucciones.setWrapText(true);

        Label lblProximasCitas = new Label("Próximas Citas de Telemedicina:");
        lblProximasCitas.setFont(Font.font("Arial", 14));

        ListView<String> listaCitasTelemedicina = new ListView<>();
        listaCitasTelemedicina.setPrefHeight(150);
        listaCitasTelemedicina.getItems().addAll(
                "Hoy 10:00 AM - Paciente Elena Flores (Videoconsulta)",
                "Mañana 09:30 AM - Paciente Ricardo Vega (Seguimiento Telemedicina)",
                "22/06/2025 04:00 PM - Paciente Gabriela Rojas (Revisión de resultados)"
        );
        listaCitasTelemedicina.setStyle("-fx-border-color: #BDC3C7; -fx-border-width: 1; -fx-background-radius: 5;");

        Button btnVerDetalles = new Button("Ver Detalles de Cita");
        btnVerDetalles.setStyle("-fx-background-color: #5DADE2; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        btnVerDetalles.setPrefHeight(35);

        Button btnIniciarLlamadaPaciente = new Button("Iniciar Llamada Ahora");
        btnIniciarLlamadaPaciente.setStyle("-fx-background-color: #8E44AD; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        btnIniciarLlamadaPaciente.setPrefHeight(35);

        HBox controlesCitas = new HBox(10, btnVerDetalles, btnIniciarLlamadaPaciente);
        controlesCitas.setAlignment(Pos.CENTER_LEFT);


        btnVerDetalles.setOnAction(e -> {
            String selected = listaCitasTelemedicina.getSelectionModel().getSelectedItem();
            if (selected != null) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Detalles de la cita: " + selected + "\n (Simulación de ventana de detalles)");
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Seleccione una cita para ver los detalles.");
            }
        });

        btnIniciarLlamadaPaciente.setOnAction(e -> {
             String selected = listaCitasTelemedicina.getSelectionModel().getSelectedItem();
            if (selected != null) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Iniciando videollamada con: " + selected + "\n (Simulación de conexión de video)");
                listaCitasTelemedicina.getItems().remove(selected); // Simula que la cita se realiza
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Seleccione una cita para iniciar la llamada.");
            }
        });


        VBox panel = new VBox(15, titulo, new Separator(), lblInstrucciones,
                             lblProximasCitas, listaCitasTelemedicina, controlesCitas);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelConfiguracionDoctor() {
        Label titulo = new Label("Configuración de la Cuenta de Doctor");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titulo.setTextFill(Color.web("#2C3E50"));

        Label lblPreferencias = new Label("Preferencias de Notificaciones:");
        lblPreferencias.setFont(Font.font("Arial", 14));

        CheckBox chkNotificacionesEmail = new CheckBox("Recibir notificaciones por email");
        chkNotificacionesEmail.setFont(Font.font("Arial", 14));
        CheckBox chkRecordatoriosCitas = new CheckBox("Recibir recordatorios de citas");
        chkRecordatoriosCitas.setFont(Font.font("Arial", 14));

        Label lblSeguridad = new Label("Seguridad de la Cuenta:");
        lblSeguridad.setFont(Font.font("Arial", 14));


        PasswordField txtContrasenaActual = new PasswordField();
        txtContrasenaActual.setPromptText("Contraseña Actual (opcional)");
        txtContrasenaActual.setStyle("-fx-font-size: 14px; -fx-background-radius: 5; -fx-border-color: #BDC3C7; -fx-border-width: 1;");
        txtContrasenaActual.setPrefHeight(35);

        PasswordField txtNuevaContrasena = new PasswordField();
        txtNuevaContrasena.setPromptText("Nueva Contraseña");
        txtNuevaContrasena.setStyle("-fx-font-size: 14px; -fx-background-radius: 5; -fx-border-color: #BDC3C7; -fx-border-width: 1;");
        txtNuevaContrasena.setPrefHeight(35);

        PasswordField txtConfirmarNuevaContrasena = new PasswordField();
        txtConfirmarNuevaContrasena.setPromptText("Confirmar Nueva Contraseña");
        txtConfirmarNuevaContrasena.setStyle("-fx-font-size: 14px; -fx-background-radius: 5; -fx-border-color: #BDC3C7; -fx-border-width: 1;");
        txtConfirmarNuevaContrasena.setPrefHeight(35);

        Button btnGuardarConfig = new Button("Guardar Configuración");
        btnGuardarConfig.setStyle("-fx-background-color: #2ECC71; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        btnGuardarConfig.setPrefHeight(40);
        btnGuardarConfig.setMinWidth(200);

        btnGuardarConfig.setOnAction(e -> {
            boolean notifEmail = chkNotificacionesEmail.isSelected();
            boolean recordatorios = chkRecordatoriosCitas.isSelected();
            String currentPass = txtContrasenaActual.getText();
            String newPass = txtNuevaContrasena.getText();
            String confirmPass = txtConfirmarNuevaContrasena.getText();

            String mensaje = "Configuración guardada:\n" +
                             "Notificaciones Email: " + (notifEmail ? "Sí" : "No") + "\n" +
                             "Recordatorios Citas: " + (recordatorios ? "Sí" : "No");

            if (!newPass.isEmpty() || !confirmPass.isEmpty()) {
                if (!newPass.equals(confirmPass)) {
                    mostrarAlerta(Alert.AlertType.ERROR, "Las nuevas contraseñas no coinciden.");
                    return;
                }
                // Aquí podrías añadir lógica para validar la contraseña actual si 'currentPass' se usara
                mensaje += "\nContraseña cambiada exitosamente.";
            }
            mostrarAlerta(Alert.AlertType.INFORMATION, mensaje);
            txtContrasenaActual.clear();
            txtNuevaContrasena.clear();
            txtConfirmarNuevaContrasena.clear();
        });


        VBox panel = new VBox(15, titulo, new Separator(), lblPreferencias, chkNotificacionesEmail, chkRecordatoriosCitas,
                             new Separator(), lblSeguridad, txtContrasenaActual, txtNuevaContrasena, txtConfirmarNuevaContrasena, btnGuardarConfig);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }
}