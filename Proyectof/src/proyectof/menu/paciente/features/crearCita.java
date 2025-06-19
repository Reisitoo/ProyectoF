package proyectof.menu.paciente.features;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import proyectof.base.BaseDatos;
import proyectof.entities.Cita;
import proyectof.entities.Usuario;
import java.util.stream.Collectors;
import proyectof.entities.SesionCitas;

public class crearCita {

    public static VBox crearPanelCitasMedicas() {
        final String BASE_COLOR = "#ECF0F3";
        final String ACCENT_COLOR = "#5D9CEC";

        Label titulo = new Label("Citas medicas");
        titulo.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 25));
        titulo.setTextFill(Color.web("#2E7D32"));
        VBox.setMargin(titulo, new Insets(0, 0, 10, 0)); // top, right, bottom, left

        List<Usuario> doctores = BaseDatos.obtenerUsuarios().stream().filter(Usuario::isDoctor).collect(Collectors.toList());

        // --- Formulario Superior ---
        ComboBox<String> comboDoctor = new ComboBox<>();
        comboDoctor.getItems().addAll(
                doctores.stream()
                        .map(Usuario::getNombreCompleto)
                        .collect(Collectors.toList())
        );
        comboDoctor.setPromptText("Seleccione un doctor");
        comboDoctor.setMaxWidth(250);
        applyNeumorphismComboBoxStyle(comboDoctor, BASE_COLOR);

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Fecha");
        applyNeumorphismDatePickerStyle(datePicker, BASE_COLOR);

        TextField txtHora = new TextField();
        txtHora.setPromptText("Hora (Ej: 10:30 AM)");
        applyNeumorphismInputStyle(txtHora, BASE_COLOR);

        HBox filaFechaHora = new HBox(10, datePicker, txtHora); // espacio entre elementos
        filaFechaHora.setAlignment(Pos.CENTER_LEFT);

        TextField txtAsunto = new TextField();
        txtAsunto.setPromptText("Asunto");
        applyNeumorphismInputStyle(txtAsunto, BASE_COLOR);

        TextField txtComentario = new TextField();
        txtComentario.setPromptText("Comentario");
        applyNeumorphismInputStyle(txtComentario, BASE_COLOR);

        Button btnAgregar = new Button("Agregar Cita");
        applyNeumorphismButtonStyle(btnAgregar, ACCENT_COLOR, true);
        btnAgregar.setMaxWidth(Double.MAX_VALUE);

        // --- Tabla Inferior ---
        TableView<Cita> tablaCitas = new TableView<>();
        tablaCitas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Cita, String> colDoctor = new TableColumn<>("Doctor");
        colDoctor.setCellValueFactory(new PropertyValueFactory<>("doctor"));

        TableColumn<Cita, String> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(cellData -> {
            LocalDateTime fecha = cellData.getValue().getFecha();
            return new SimpleStringProperty(fecha.toLocalDate().toString() + " " + fecha.toLocalTime().toString());
        });

        TableColumn<Cita, String> colAsunto = new TableColumn<>("Asunto");
        colAsunto.setCellValueFactory(new PropertyValueFactory<>("asunto"));

        TableColumn<Cita, String> colComentario = new TableColumn<>("Comentario");
        colComentario.setCellValueFactory(new PropertyValueFactory<>("comentario"));

        tablaCitas.getColumns().addAll(colDoctor, colFecha, colAsunto, colComentario);
        tablaCitas.setPrefHeight(200);
        tablaCitas.setStyle("-fx-background-radius: 20; -fx-border-radius: 20;");

        ObservableList<Cita> citas = FXCollections.observableArrayList();
        tablaCitas.setItems(citas);

        // --- Acción del botón ---
        btnAgregar.setOnAction(e -> {
            String doctor = comboDoctor.getValue();
            LocalDate fecha = datePicker.getValue();
            String horaTexto = txtHora.getText();
            String asunto = txtAsunto.getText();
            String comentario = txtComentario.getText();

            if (doctor == null || fecha == null || horaTexto.isEmpty() || asunto.isEmpty()) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Campos Incompletos");
                alerta.setHeaderText("Por favor, completa todos los campos antes de continuar.");
                alerta.showAndWait();
                return;
            }

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
                LocalTime hora = LocalTime.parse(horaTexto.toUpperCase(), formatter);

                LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);
                Cita nueva = new Cita(doctor, fechaHora, asunto, comentario);
                citas.add(nueva);
                SesionCitas.agregarCita(nueva);

                comboDoctor.setValue(null);
                datePicker.setValue(null);
                txtHora.clear();
                txtAsunto.clear();
                txtComentario.clear();
            } catch (DateTimeParseException ex) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Hora Invalida");
                alerta.setHeaderText("Hora inválida. Usa el formato HH:MM AM/PM");
                alerta.showAndWait();
            }
        });

        // --- Layouts ---
        VBox formulario = new VBox(12, comboDoctor, filaFechaHora, txtAsunto, txtComentario, btnAgregar);
        formulario.setPadding(new Insets(10));
        formulario.setStyle(
                "-fx-background-color: " + BASE_COLOR + "; "
                + "-fx-background-radius: 20px; "
                + "-fx-border-radius: 20px; "
                + "-fx-effect: innershadow(gaussian, rgba(0,0,0,0.1), 8, 0, 3, 3), "
                + "              innershadow(gaussian, rgba(255,255,255,0.7), 8, 0, -3, -3);"
        );

        VBox panel = new VBox(20, titulo, formulario, tablaCitas);
        panel.setAlignment(Pos.TOP_CENTER);
        panel.setPadding(new Insets(10));
        panel.setStyle("-fx-background-color: transparent;");
        return panel;
    }

    private static void applyNeumorphismInputStyle(TextField input, String baseColor) {
        input.setStyle(
                "-fx-background-color: " + baseColor + "; "
                + "-fx-background-radius: 10px; "
                + "-fx-border-radius: 10px; "
                + "-fx-font-size: 12px; "
                + "-fx-text-fill: #34495E; "
                + "-fx-prompt-text-fill: #6C7A89; "
                + "-fx-padding: 10px 15px; "
                + "-fx-effect: innershadow(gaussian, rgba(0,0,0,0.1), 8, 0, 2, 2), "
                + "             innershadow(gaussian, rgba(255,255,255,0.6), 8, 0, -2, -2);"
        );
    }

    private static void applyNeumorphismButtonStyle(Button button, String accentColor, boolean primary) {
        button.setStyle(
                "-fx-background-color: " + accentColor + "; "
                + "-fx-text-fill: white; "
                + "-fx-background-radius: 15px; "
                + "-fx-border-radius: 15px; "
                + "-fx-font-size: 15px; "
                + "-fx-font-weight: bold; "
                + (primary
                        ? "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 20, 0.3, 5, 5);"
                        : "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0.1, 3, 3);")
        );
    }

    private static void applyNeumorphismComboBoxStyle(ComboBox<?> combo, String baseColor) {
        combo.setStyle(
                "-fx-background-color: " + baseColor + "; "
                + "-fx-background-radius: 10px; "
                + "-fx-border-radius: 10px; "
                + "-fx-font-size: 13px; "
                + "-fx-padding: 5px 10px; "
                + "-fx-effect: innershadow(gaussian, rgba(0,0,0,0.1), 8, 0, 2, 2), "
                + "             innershadow(gaussian, rgba(255,255,255,0.6), 8, 0, -2, -2);"
        );
    }

    private static void applyNeumorphismDatePickerStyle(DatePicker datePicker, String baseColor) {
        datePicker.setStyle(
                "-fx-background-color: " + baseColor + "; "
                + "-fx-background-radius: 10px; "
                + "-fx-border-radius: 10px; "
                + "-fx-font-size: 13px; "
                + "-fx-padding: 5px 10px; "
                + "-fx-effect: innershadow(gaussian, rgba(0,0,0,0.1), 8, 0, 2, 2), "
                + "             innershadow(gaussian, rgba(255,255,255,0.6), 8, 0, -2, -2);"
        );
    }

}
