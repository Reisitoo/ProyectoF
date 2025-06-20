package proyectof.menu.paciente;

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
import proyectof.menu.paciente.features.crearCita; // Se mantiene la importación
import proyectof.menu.paciente.features.perfil;   // Se mantiene la importación
import javafx.beans.property.ReadOnlyStringWrapper; // Necesario para TableView

public class MenuPaciente {

    private StackPane areaContenido;
    // Referencias a los botones del menú para poder manipular su estilo
    private Button btnPerfil;
    private Button btnCitas;
    private Button btnResultados;
    private Button btnPrescripciones;
    private Button btnComunicacion;
    private Button btnRecursosEducativos;
    private Button btnTelemedicina;
    private Button btnConfiguracion;

    private Button botonActivo = null; // Para rastrear qué botón está activo actualmente

    public void mostrar(Stage primaryStage, String nombrePaciente) {
        // Bienvenida al Paciente
        Label lblBienvenida = new Label("Bienvenido(a), " + nombrePaciente + "!");
        lblBienvenida.setFont(Font.font("Arial", FontWeight.BOLD, 12)); // Ajustado a 22px y negrita
        lblBienvenida.setTextFill(Color.web("#2C3E50")); // Azul/gris oscuro profesional para bienvenida

        // Botones del Menú Lateral
        btnPerfil = crearBotonPaciente("Perfil del Paciente");
        btnCitas = crearBotonPaciente("Citas Médicas");
        btnResultados = crearBotonPaciente("Resultados de Exámenes");
        btnPrescripciones = crearBotonPaciente("Mis Prescripciones");
        btnComunicacion = crearBotonPaciente("Comunicación con Doctor");
        btnRecursosEducativos = crearBotonPaciente("Recursos Educativos");
        btnTelemedicina = crearBotonPaciente("Telemedicina");
        btnConfiguracion = crearBotonPaciente("Configuración");

        // Botón de Cerrar Sesión (con estilo distintivo)
        Button btnCerrarSesion = new Button("Cerrar Sesión");
        btnCerrarSesion.setStyle(
                "-fx-background-color: #E74C3C;" + // Rojo más suave
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-background-radius: 5;" +
                "-fx-border-color: #C0392B;" +
                "-fx-border-width: 1;"
        );
        btnCerrarSesion.setMinWidth(220);
        btnCerrarSesion.setPrefHeight(40);
        btnCerrarSesion.setOnAction(e -> {
            PantallaInicial inicio = new PantallaInicial();
            inicio.start(primaryStage);
        });

        // Contenedor del Menú Lateral
        VBox menuLateral = new VBox(15); // Espaciado entre elementos
        menuLateral.getChildren().addAll(lblBienvenida, new Separator(), btnPerfil, btnCitas, btnResultados,
                btnPrescripciones, btnComunicacion, btnRecursosEducativos,
                btnTelemedicina, btnConfiguracion, new Separator(), btnCerrarSesion);
        menuLateral.setPadding(new Insets(25));
        menuLateral.setStyle(
                "-fx-background-color: #ECF0F1;" + // Gris claro para el menú lateral
                "-fx-border-color: #BDC3C7;" + // Borde sutil
                "-fx-border-width: 0 1 0 0;"
        );
        menuLateral.setAlignment(Pos.TOP_CENTER);
        menuLateral.setPrefWidth(250);

        // Área de Contenido Principal
        areaContenido = new StackPane();
        areaContenido.setPadding(new Insets(30));
        areaContenido.setStyle("-fx-background-color: #FFFFFF;"); // Fondo blanco puro para el contenido

        // Mensaje inicial al cargar el menú
        actualizarContenido(crearPanelMensaje("Bienvenido a su portal de paciente. Seleccione una opción para comenzar.", Color.web("#7F8C8D")));

        // --- Lógica de los botones con estado activo ---
        btnPerfil.setOnAction(e -> {
            activarBoton(btnPerfil);
            // Asumiendo que perfil.crearPanelPerfilPaciente() devuelve un Region
            actualizarContenido(perfil.crearPanelPerfilPaciente());
        });
        btnCitas.setOnAction(e -> {
            activarBoton(btnCitas);
            // Asumiendo que crearCita.crearPanelCitasMedicas() devuelve un Region
            actualizarContenido(crearCita.crearPanelCitasMedicas());
        });

        btnResultados.setOnAction(e -> {
            activarBoton(btnResultados);
            actualizarContenido(crearPanelResultadosExamenes());
        });
        btnPrescripciones.setOnAction(e -> {
            activarBoton(btnPrescripciones);
            actualizarContenido(crearPanelPrescripciones());
        });
        btnComunicacion.setOnAction(e -> {
            activarBoton(btnComunicacion);
            actualizarContenido(crearPanelComunicacion());
        });
        btnRecursosEducativos.setOnAction(e -> {
            activarBoton(btnRecursosEducativos);
            actualizarContenido(crearPanelRecursosEducativos());
        });
        btnTelemedicina.setOnAction(e -> {
            activarBoton(btnTelemedicina);
            actualizarContenido(crearPanelTelemedicina());
        });
        btnConfiguracion.setOnAction(e -> {
            activarBoton(btnConfiguracion);
            actualizarContenido(crearPanelConfiguracion());
        });

        // Integrar todo con BorderPane
        BorderPane root = new BorderPane();
        root.setLeft(menuLateral);
        root.setCenter(areaContenido);

        Scene scene = new Scene(root, 1000, 650); // Tamaño de ventana consistente
        primaryStage.setScene(scene);
        primaryStage.setTitle("Panel del Paciente - Hospital Vitali");
        primaryStage.show();
    }

    // Método auxiliar para crear botones del menú del paciente con estilo personalizado
    private Button crearBotonPaciente(String texto) {
        Button btn = new Button(texto);
        btn.setMinWidth(220); // Ancho consistente
        btn.setPrefHeight(40); // Altura consistente
        String estiloNormal =
                "-fx-background-color: #2ECC71;" + // Verde vibrante pero suave
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-background-radius: 5;" +
                "-fx-border-color: #27AE60;" + // Borde más oscuro
                "-fx-border-width: 1;";
        btn.setStyle(estiloNormal);

        // Efecto hover sutil
        btn.setOnMouseEntered(e -> {
            if (btn != botonActivo) {
                btn.setStyle(
                        "-fx-background-color: #27AE60;" + // Verde un poco más oscuro al pasar el ratón
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-background-radius: 5;" +
                        "-fx-border-color: #27AE60;" +
                        "-fx-border-width: 1;"
                );
            }
        });
        btn.setOnMouseExited(e -> {
            if (btn != botonActivo) {
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
                    "-fx-background-color: #2ECC71;" + // Verde normal
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 15px;" +
                    "-fx-background-radius: 5;" +
                    "-fx-border-color: #27AE60;" +
                    "-fx-border-width: 1;"
            );
        }
        // Establecer el nuevo botón como activo y aplicar su estilo de "activo"
        botonActivo = nuevoBotonActivo;
        botonActivo.setStyle(
                "-fx-background-color: #1ABC9C;" + // Un verde azulado más oscuro para el estado activo
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" + // Negrita para el texto del botón activo
                "-fx-background-radius: 5;" +
                "-fx-border-color: #1ABC9C;" +
                "-fx-border-width: 1;"
        );
    }

    // Método auxiliar para cambiar el contenido en el área central
    private void actualizarContenido(Region nuevoContenido) {
        areaContenido.getChildren().clear();
        areaContenido.getChildren().add(nuevoContenido);
        StackPane.setAlignment(nuevoContenido, Pos.TOP_CENTER);
    }

    // Método auxiliar para mostrar alertas
    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo, mensaje, ButtonType.OK);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }

    // Paneles de Contenido (mejorados para el paciente)

    private VBox crearPanelMensaje(String mensaje, Color color) {
        Label label = new Label(mensaje);
        label.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        label.setTextFill(color);
        VBox panel = new VBox(label);
        panel.setAlignment(Pos.CENTER);
        panel.setPadding(new Insets(50));
        return panel;
    }

    private VBox crearPanelResultadosExamenes() {
        Label titulo = new Label("Resultados de Exámenes");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titulo.setTextFill(Color.web("#2C3E50"));

        Label lblDescripcion = new Label("Aquí puede visualizar y descargar sus resultados de laboratorio e imagenología.");
        lblDescripcion.setFont(Font.font("Arial", 14));
        lblDescripcion.setTextFill(Color.web("#5D6D7E"));
        lblDescripcion.setWrapText(true);

        TableView<ExamenResultado> tablaResultados = new TableView<>();
        tablaResultados.setPrefHeight(250);
        tablaResultados.setStyle("-fx-border-color: #BDC3C7; -fx-border-width: 1; -fx-background-radius: 5;");

        TableColumn<ExamenResultado, String> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFecha()));
        colFecha.setPrefWidth(120);

        TableColumn<ExamenResultado, String> colTipo = new TableColumn<>("Tipo de Examen");
        colTipo.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTipoExamen()));
        colTipo.setPrefWidth(200);

        TableColumn<ExamenResultado, String> colEstado = new TableColumn<>("Estado");
        colEstado.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEstado()));
        colEstado.setPrefWidth(100);

        TableColumn<ExamenResultado, String> colAccion = new TableColumn<>("Acción");
        colAccion.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("Ver/Descargar"));
        colAccion.setCellFactory(tc -> new TableCell<ExamenResultado, String>() {
            final Button btn = new Button("Ver/Descargar");
            {
                btn.setStyle("-fx-background-color: #28B463; -fx-text-fill: white; -fx-font-size: 12px; -fx-background-radius: 3;");
                btn.setOnAction(e -> {
                    ExamenResultado resultado = getTableView().getItems().get(getIndex());
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Simulando descarga/visualización de: " + resultado.getTipoExamen() + " (" + resultado.getFecha() + ")");
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
        colAccion.setPrefWidth(120);
        colAccion.setResizable(false);


        tablaResultados.getColumns().addAll(colFecha, colTipo, colEstado, colAccion);

        tablaResultados.getItems().addAll(
                new ExamenResultado("2025-06-15", "Hemograma Completo", "Finalizado"),
                new ExamenResultado("2025-06-10", "Análisis de Orina", "Finalizado"),
                new ExamenResultado("2025-06-01", "Rayos X de Tórax", "Finalizado"),
                new ExamenResultado("2025-05-28", "Perfil Lipídico", "Finalizado"),
                new ExamenResultado("2025-06-19", "Cultivo Bacteriano", "Pendiente")
        );

        VBox panel = new VBox(15, titulo, new Separator(), lblDescripcion, tablaResultados);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    // Clase auxiliar para los datos de la tabla de resultados de exámenes
    public static class ExamenResultado {
        private final String fecha;
        private final String tipoExamen;
        private final String estado;

        public ExamenResultado(String fecha, String tipoExamen, String estado) {
            this.fecha = fecha;
            this.tipoExamen = tipoExamen;
            this.estado = estado;
        }

        public String getFecha() { return fecha; }
        public String getTipoExamen() { return tipoExamen; }
        public String getEstado() { return estado; }
    }


    private VBox crearPanelPrescripciones() {
        Label titulo = new Label("Mis Prescripciones");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titulo.setTextFill(Color.web("#2C3E50"));

        Label lblDescripcion = new Label("Aquí puede ver el historial de sus medicamentos recetados.");
        lblDescripcion.setFont(Font.font("Arial", 14));
        lblDescripcion.setTextFill(Color.web("#5D6D7E"));
        lblDescripcion.setWrapText(true);


        TableView<Prescripcion> tablaPrescripciones = new TableView<>();
        tablaPrescripciones.setPrefHeight(250);
        tablaPrescripciones.setStyle("-fx-border-color: #BDC3C7; -fx-border-width: 1; -fx-background-radius: 5;");

        TableColumn<Prescripcion, String> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFecha()));
        colFecha.setPrefWidth(100);

        TableColumn<Prescripcion, String> colMedicamento = new TableColumn<>("Medicamento");
        colMedicamento.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getMedicamento()));
        colMedicamento.setPrefWidth(180);

        TableColumn<Prescripcion, String> colDosis = new TableColumn<>("Dosis");
        colDosis.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDosis()));
        colDosis.setPrefWidth(150);

        TableColumn<Prescripcion, String> colDoctor = new TableColumn<>("Recetado por");
        colDoctor.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDoctor()));
        colDoctor.setPrefWidth(150);

        TableColumn<Prescripcion, String> colAccion = new TableColumn<>("Acción");
        colAccion.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("Renovar"));
        colAccion.setCellFactory(tc -> new TableCell<Prescripcion, String>() {
            final Button btn = new Button("Solicitar Renovación");
            {
                btn.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white; -fx-font-size: 11px; -fx-background-radius: 3;");
                btn.setOnAction(e -> {
                    Prescripcion prescripcion = getTableView().getItems().get(getIndex());
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Simulando solicitud de renovación para: " + prescripcion.getMedicamento());
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
        colAccion.setPrefWidth(160);
        colAccion.setResizable(false);


        tablaPrescripciones.getColumns().addAll(colFecha, colMedicamento, colDosis, colDoctor, colAccion);

        tablaPrescripciones.getItems().addAll(
                new Prescripcion("2025-06-01", "Amoxicilina 500mg", "1 cada 8 horas por 7 días", "Dr. Juan Pérez"),
                new Prescripcion("2025-05-15", "Ibuprofeno 400mg", "Según necesidad", "Dr. Juan Pérez"),
                new Prescripcion("2025-04-20", "Vitamina D 1000 UI", "1 diaria", "Dra. Ana Gómez")
        );

        VBox panel = new VBox(15, titulo, new Separator(), lblDescripcion, tablaPrescripciones);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    // Clase auxiliar para los datos de la tabla de prescripciones
    public static class Prescripcion {
        private final String fecha;
        private final String medicamento;
        private final String dosis;
        private final String doctor;

        public Prescripcion(String fecha, String medicamento, String dosis, String doctor) {
            this.fecha = fecha;
            this.medicamento = medicamento;
            this.dosis = dosis;
            this.doctor = doctor;
        }

        public String getFecha() { return fecha; }
        public String getMedicamento() { return medicamento; }
        public String getDosis() { return dosis; }
        public String getDoctor() { return doctor; }
    }


    private VBox crearPanelComunicacion() {
        Label titulo = new Label("Comunicación con tu Doctor");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titulo.setTextFill(Color.web("#2C3E50"));

        Label lblSeleccionarDoctor = new Label("Seleccionar Doctor para Chatear:");
        lblSeleccionarDoctor.setFont(Font.font("Arial", 14));

        ComboBox<String> cmbDoctores = new ComboBox<>();
        cmbDoctores.setPromptText("Elige un doctor...");
        cmbDoctores.getItems().addAll("Dr. Juan Pérez (Médico General)", "Dra. Ana Gómez (Cardióloga)", "Dr. Miguel Torres (Pediatra)");
        cmbDoctores.setPrefHeight(35);
        cmbDoctores.setStyle("-fx-font-size: 14px; -fx-background-radius: 5; -fx-border-color: #BDC3C7;");
        cmbDoctores.setMinWidth(250); // Ajuste de ancho

        Label lblChat = new Label("Historial de Mensajes:");
        lblChat.setFont(Font.font("Arial", 14));

        TextArea chatArea = new TextArea();
        chatArea.setPromptText("Selecciona un doctor para ver el historial o enviar un mensaje.");
        chatArea.setEditable(false);
        chatArea.setPrefHeight(200);
        chatArea.setStyle("-fx-border-color: #BDC3C7; -fx-border-width: 1; -fx-background-radius: 5;");


        Label lblNuevoMensaje = new Label("Escribe tu mensaje:");
        lblNuevoMensaje.setFont(Font.font("Arial", 14));

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

        // Simulación de carga de historial al seleccionar un doctor
        cmbDoctores.setOnAction(e -> {
            String doctorSeleccionado = cmbDoctores.getSelectionModel().getSelectedItem();
            if (doctorSeleccionado != null) {
                chatArea.setText("--- Historial con " + doctorSeleccionado + " (Simulado) ---\n" +
                                 "Doctor: Hola, ¿cómo se siente hoy?\n" +
                                 "Usted: Me siento mejor, gracias. Tengo una duda sobre...\n" +
                                 "Doctor: Entiendo, por favor, explique más a detalle.\n");
            } else {
                chatArea.clear();
            }
        });

        btnEnviar.setOnAction(e -> {
            String mensaje = mensajeInput.getText().trim();
            String doctorSeleccionado = cmbDoctores.getSelectionModel().getSelectedItem();
            if (!mensaje.isEmpty() && doctorSeleccionado != null) {
                chatArea.appendText("Usted: " + mensaje + "\n");
                mensajeInput.clear();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Mensaje enviado a: " + doctorSeleccionado);
            } else if (doctorSeleccionado == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Seleccione un doctor para enviar el mensaje.");
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Escriba un mensaje para enviar.");
            }
        });


        VBox panel = new VBox(15, titulo, new Separator(), lblSeleccionarDoctor, cmbDoctores,
                             lblChat, chatArea, lblNuevoMensaje, inputRow);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelRecursosEducativos() {
        Label titulo = new Label("Recursos Educativos para tu Salud");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titulo.setTextFill(Color.web("#2C3E50"));

        Label lblDescripcion = new Label("Explore artículos, guías y videos para mejorar su bienestar y conocimiento sobre su salud.");
        lblDescripcion.setFont(Font.font("Arial", 14));
        lblDescripcion.setTextFill(Color.web("#5D6D7E"));
        lblDescripcion.setWrapText(true);

        Accordion accordion = new Accordion();
        accordion.setStyle("-fx-border-color: #BDC3C7; -fx-border-width: 1; -fx-background-radius: 5;");

        // Sección de Guías de Ejercicio
        VBox contenidoEjercicios = new VBox(10);
        contenidoEjercicios.setPadding(new Insets(10));
        contenidoEjercicios.getChildren().addAll(
                new Hyperlink("Introducción al Ejercicio Moderado (PDF)"),
                new Hyperlink("Rutinas de Estiramiento para el Hogar (Video)"),
                new Hyperlink("Beneficios de la Caminata Diaria (Artículo)")
        );
        contenidoEjercicios.getChildren().forEach(node -> {
            if (node instanceof Hyperlink) {
                ((Hyperlink) node).setOnAction(e -> mostrarAlerta(Alert.AlertType.INFORMATION, "Abriendo recurso: " + ((Hyperlink) node).getText()));
            }
        });
        TitledPane tpEjercicios = new TitledPane("Guías de Ejercicio", contenidoEjercicios);
        tpEjercicios.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        tpEjercicios.setStyle("-fx-text-fill: #2ECC71;"); // Color verde para título del TitledPane


        // Sección de Planes de Dieta Saludable
        VBox contenidoDietas = new VBox(10);
        contenidoDietas.setPadding(new Insets(10));
        contenidoDietas.getChildren().addAll(
                new Hyperlink("Recetas Saludables para el Desayuno (PDF)"),
                new Hyperlink("Guía para una Dieta Balanceada (Artículo)"),
                new Hyperlink("Cómo Leer Etiquetas Nutricionales (Video)")
        );
         contenidoDietas.getChildren().forEach(node -> {
            if (node instanceof Hyperlink) {
                ((Hyperlink) node).setOnAction(e -> mostrarAlerta(Alert.AlertType.INFORMATION, "Abriendo recurso: " + ((Hyperlink) node).getText()));
            }
        });
        TitledPane tpDietas = new TitledPane("Planes de Dieta Saludable", contenidoDietas);
        tpDietas.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        tpDietas.setStyle("-fx-text-fill: #2ECC71;");


        // Sección de Información sobre Condiciones Médicas
        VBox contenidoCondiciones = new VBox(10);
        contenidoCondiciones.setPadding(new Insets(10));
        contenidoCondiciones.getChildren().addAll(
                new Hyperlink("Manejo de la Diabetes Tipo 2 (Artículo)"),
                new Hyperlink("Todo sobre la Hipertensión (PDF)"),
                new Hyperlink("Preguntas Frecuentes sobre el Colesterol (FAQ)")
        );
         contenidoCondiciones.getChildren().forEach(node -> {
            if (node instanceof Hyperlink) {
                ((Hyperlink) node).setOnAction(e -> mostrarAlerta(Alert.AlertType.INFORMATION, "Abriendo recurso: " + ((Hyperlink) node).getText()));
            }
        });
        TitledPane tpCondiciones = new TitledPane("Información sobre Condiciones Médicas", contenidoCondiciones);
        tpCondiciones.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        tpCondiciones.setStyle("-fx-text-fill: #2ECC71;");


        accordion.getPanes().addAll(tpEjercicios, tpDietas, tpCondiciones);

        VBox panel = new VBox(15, titulo, new Separator(), lblDescripcion, accordion);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelTelemedicina() {
        Label titulo = new Label("Telemedicina: Videollamada con tu Doctor");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titulo.setTextFill(Color.web("#2C3E50"));

        Label lblInstrucciones = new Label(
                "Conéctate con tu doctor desde casa. Asegúrate de tener una buena conexión a internet " +
                "y un entorno tranquilo para su consulta."
        );
        lblInstrucciones.setFont(Font.font("Arial", 14));
        lblInstrucciones.setTextFill(Color.web("#5D6D7E"));
        lblInstrucciones.setWrapText(true);

        Label lblProximasCitas = new Label("Sus Próximas Citas de Telemedicina:");
        lblProximasCitas.setFont(Font.font("Arial", 14));

        ListView<String> listaCitasTelemedicina = new ListView<>();
        listaCitasTelemedicina.setPrefHeight(150);
        listaCitasTelemedicina.getItems().addAll(
                "Hoy 10:00 AM - Dr. Juan Pérez (Videoconsulta)",
                "Mañana 09:30 AM - Dra. Ana Gómez (Seguimiento Telemedicina)",
                "22/06/2025 04:00 PM - Dr. Miguel Torres (Revisión de resultados)"
        );
        listaCitasTelemedicina.setStyle("-fx-border-color: #BDC3C7; -fx-border-width: 1; -fx-background-radius: 5;");


        Button btnIniciarLlamada = new Button("Iniciar Videollamada Ahora");
        btnIniciarLlamada.setStyle("-fx-background-color: #E64A19; -fx-text-fill: white; -fx-font-size: 15px; -fx-background-radius: 5;"); // Naranja
        btnIniciarLlamada.setPrefHeight(40);
        btnIniciarLlamada.setMinWidth(200);

        Button btnAgendarLlamada = new Button("Agendar Nueva Videollamada");
        btnAgendarLlamada.setStyle("-fx-background-color: #FFA000; -fx-text-fill: white; -fx-font-size: 15px; -fx-background-radius: 5;"); // Naranja más claro
        btnAgendarLlamada.setPrefHeight(40);
        btnAgendarLlamada.setMinWidth(200);

        HBox controlesLlamada = new HBox(20, btnIniciarLlamada, btnAgendarLlamada);
        controlesLlamada.setAlignment(Pos.CENTER);


        btnIniciarLlamada.setOnAction(e -> {
            String selected = listaCitasTelemedicina.getSelectionModel().getSelectedItem();
            if (selected != null && selected.startsWith("Hoy")) { // Simula que solo se puede iniciar la de hoy
                mostrarAlerta(Alert.AlertType.INFORMATION, "¡Iniciando videollamada con: " + selected + "!");
                // Aquí iría la lógica real para abrir la conexión de video
                listaCitasTelemedicina.getItems().remove(selected); // Simula que la cita se "consume"
            } else if (selected == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Seleccione una cita para iniciar la llamada.");
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Solo puede iniciar citas programadas para hoy.");
            }
        });

        btnAgendarLlamada.setOnAction(e -> mostrarAlerta(Alert.AlertType.INFORMATION, "Abriendo formulario para agendar nueva videollamada."));


        VBox panel = new VBox(15, titulo, new Separator(), lblInstrucciones,
                             lblProximasCitas, listaCitasTelemedicina, controlesLlamada);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }

    private VBox crearPanelConfiguracion() {
        Label titulo = new Label("Configuración de la Cuenta");
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

        Button btnGuardarCambios = new Button("Guardar Cambios");
        btnGuardarCambios.setStyle("-fx-background-color: #2ECC71; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        btnGuardarCambios.setPrefHeight(40);
        btnGuardarCambios.setMinWidth(200);

        btnGuardarCambios.setOnAction(e -> {
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
                             new Separator(), lblSeguridad, txtContrasenaActual, txtNuevaContrasena, txtConfirmarNuevaContrasena, btnGuardarCambios);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }
}