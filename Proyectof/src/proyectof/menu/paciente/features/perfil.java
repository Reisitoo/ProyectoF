package proyectof.menu.paciente.features;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.time.LocalDate; // ¡Añade esta importación si vas a usar LocalDate con DatePicker!

public class perfil {

    public static ScrollPane crearPanelPerfilPaciente() { // ¡Cambiado de VBox a ScrollPane!
        Label titulo = new Label("Mi Perfil de Paciente");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titulo.setTextFill(Color.web("#2C3E50")); // Color más oscuro para el título

        Label lblDescripcion = new Label("Gestione y visualice su información personal y médica principal.");
        lblDescripcion.setFont(Font.font("Arial", 14));
        lblDescripcion.setTextFill(Color.web("#5D6D7E"));
        lblDescripcion.setWrapText(true);


        // --- Sección de Datos Personales ---
        Label subTituloPersonal = new Label("Datos Personales y Contacto");
        subTituloPersonal.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        subTituloPersonal.setTextFill(Color.web("#2C3E50"));

        GridPane gridPersonal = new GridPane();
        gridPersonal.setHgap(15);
        gridPersonal.setVgap(10);
        gridPersonal.setPadding(new Insets(10, 0, 10, 0));

        // Etiquetas y Campos de Datos Personales (simulados)
        Label lblNombre = new Label("Nombre Completo:");
        TextField txtNombre = new TextField("Ana García Pérez");
        txtNombre.setEditable(false); // Por defecto no editable
        txtNombre.setStyle("-fx-background-color: #F8F9F9; -fx-border-color: #D5DBDB; -fx-border-radius: 4; -fx-background-radius: 4;");

        Label lblFechaNacimiento = new Label("Fecha de Nacimiento:");
        DatePicker dpFechaNacimiento = new DatePicker(); // Representación de fecha
        // Simular fecha de nacimiento para Ana (Descomenta y asegúrate de importar java.time.LocalDate)
        dpFechaNacimiento.setValue(LocalDate.of(1990, 5, 15)); // Ejemplo de uso de LocalDate
        dpFechaNacimiento.setDisable(true); // Por defecto no editable
        dpFechaNacimiento.setStyle("-fx-background-color: #F8F9F9; -fx-border-color: #D5DBDB; -fx-border-radius: 4; -fx-background-radius: 4;");

        Label lblGenero = new Label("Género:");
        ComboBox<String> cmbGenero = new ComboBox<>();
        cmbGenero.getItems().addAll("Femenino", "Masculino", "No binario", "Prefiero no decirlo");
        cmbGenero.setValue("Femenino"); // Valor simulado
        cmbGenero.setDisable(true);
        cmbGenero.setStyle("-fx-background-color: #F8F9F9; -fx-border-color: #D5DBDB; -fx-border-radius: 4; -fx-background-radius: 4;");


        Label lblEmail = new Label("Email:");
        TextField txtEmail = new TextField("ana.g@gmail.com");
        txtEmail.setEditable(false);
        txtEmail.setStyle("-fx-background-color: #F8F9F9; -fx-border-color: #D5DBDB; -fx-border-radius: 4; -fx-background-radius: 4;");

        Label lblTelefono = new Label("Teléfono:");
        TextField txtTelefono = new TextField("+(504) 8875-4940");
        txtTelefono.setEditable(false);
        txtTelefono.setStyle("-fx-background-color: #F8F9F9; -fx-border-color: #D5DBDB; -fx-border-radius: 4; -fx-background-radius: 4;");

        Label lblDireccion = new Label("Dirección:");
        TextArea txtDireccion = new TextArea("Calle Principal, Casa #123, Colonia El Centro, El Progreso");
        txtDireccion.setEditable(false);
        txtDireccion.setPrefRowCount(2);
        txtDireccion.setWrapText(true);
        txtDireccion.setStyle("-fx-background-color: #F8F9F9; -fx-border-color: #D5DBDB; -fx-border-radius: 4; -fx-background-radius: 4;");


        gridPersonal.add(lblNombre, 0, 0); gridPersonal.add(txtNombre, 1, 0);
        gridPersonal.add(lblFechaNacimiento, 0, 1); gridPersonal.add(dpFechaNacimiento, 1, 1);
        gridPersonal.add(lblGenero, 0, 2); gridPersonal.add(cmbGenero, 1, 2);
        gridPersonal.add(lblEmail, 0, 3); gridPersonal.add(txtEmail, 1, 3);
        gridPersonal.add(lblTelefono, 0, 4); gridPersonal.add(txtTelefono, 1, 4);
        gridPersonal.add(lblDireccion, 0, 5); gridPersonal.add(txtDireccion, 1, 5);


        // --- Sección de Información Médica Clave ---
        Label subTituloMedico = new Label("Información Médica Clave");
        subTituloMedico.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        subTituloMedico.setTextFill(Color.web("#2C3E50"));

        GridPane gridMedico = new GridPane();
        gridMedico.setHgap(15);
        gridMedico.setVgap(10);
        gridMedico.setPadding(new Insets(10, 0, 10, 0));

        Label lblIdPaciente = new Label("ID Paciente:");
        TextField txtIdPaciente = new TextField("PA20250001");
        txtIdPaciente.setEditable(false);
        txtIdPaciente.setStyle("-fx-background-color: #F8F9F9; -fx-border-color: #D5DBDB; -fx-border-radius: 4; -fx-background-radius: 4;");

        Label lblGrupoSanguineo = new Label("Grupo Sanguíneo:");
        ComboBox<String> cmbGrupoSanguineo = new ComboBox<>();
        cmbGrupoSanguineo.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
        cmbGrupoSanguineo.setValue("O+"); // Valor simulado
        cmbGrupoSanguineo.setDisable(true);
        cmbGrupoSanguineo.setStyle("-fx-background-color: #F8F9F9; -fx-border-color: #D5DBDB; -fx-border-radius: 4; -fx-background-radius: 4;");

        Label lblAlergias = new Label("Alergias:");
        TextArea txtAlergias = new TextArea("Penicilina, Polen");
        txtAlergias.setEditable(false);
        txtAlergias.setPrefRowCount(2);
        txtAlergias.setWrapText(true);
        txtAlergias.setStyle("-fx-background-color: #F8F9F9; -fx-border-color: #D5DBDB; -fx-border-radius: 4; -fx-background-radius: 4;");

        Label lblCondiciones = new Label("Condiciones Médicas:");
        TextArea txtCondiciones = new TextArea("Hipertensión (Controlada), Asma");
        txtCondiciones.setEditable(false);
        txtCondiciones.setPrefRowCount(2);
        txtCondiciones.setWrapText(true);
        txtCondiciones.setStyle("-fx-background-color: #F8F9F9; -fx-border-color: #D5DBDB; -fx-border-radius: 4; -fx-background-radius: 4;");

        Label lblMedicamentos = new Label("Medicamentos Actuales:");
        TextArea txtMedicamentos = new TextArea("Losartán (para hipertensión)");
        txtMedicamentos.setEditable(false);
        txtMedicamentos.setPrefRowCount(2);
        txtMedicamentos.setWrapText(true);
        txtMedicamentos.setStyle("-fx-background-color: #F8F9F9; -fx-border-color: #D5DBDB; -fx-border-radius: 4; -fx-background-radius: 4;");


        gridMedico.add(lblIdPaciente, 0, 0); gridMedico.add(txtIdPaciente, 1, 0);
        gridMedico.add(lblGrupoSanguineo, 0, 1); gridMedico.add(cmbGrupoSanguineo, 1, 1);
        gridMedico.add(lblAlergias, 0, 2); gridMedico.add(txtAlergias, 1, 2);
        gridMedico.add(lblCondiciones, 0, 3); gridMedico.add(txtCondiciones, 1, 3);
        gridMedico.add(lblMedicamentos, 0, 4); gridMedico.add(txtMedicamentos, 1, 4);


        // --- Sección de Contacto de Emergencia ---
        Label subTituloEmergencia = new Label("Contacto de Emergencia");
        subTituloEmergencia.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        subTituloEmergencia.setTextFill(Color.web("#2C3E50"));

        GridPane gridEmergencia = new GridPane();
        gridEmergencia.setHgap(15);
        gridEmergencia.setVgap(10);
        gridEmergencia.setPadding(new Insets(10, 0, 10, 0));

        Label lblNombreEmergencia = new Label("Nombre:");
        TextField txtNombreEmergencia = new TextField("Juan García");
        txtNombreEmergencia.setEditable(false);
        txtNombreEmergencia.setStyle("-fx-background-color: #F8F9F9; -fx-border-color: #D5DBDB; -fx-border-radius: 4; -fx-background-radius: 4;");

        Label lblParentescoEmergencia = new Label("Parentesco:");
        TextField txtParentescoEmergencia = new TextField("Hermano");
        txtParentescoEmergencia.setEditable(false);
        txtParentescoEmergencia.setStyle("-fx-background-color: #F8F9F9; -fx-border-color: #D5DBDB; -fx-border-radius: 4; -fx-background-radius: 4;");

        Label lblTelefonoEmergencia = new Label("Teléfono:");
        TextField txtTelefonoEmergencia = new TextField("+(504) 9987-6543");
        txtTelefonoEmergencia.setEditable(false);
        txtTelefonoEmergencia.setStyle("-fx-background-color: #F8F9F9; -fx-border-color: #D5DBDB; -fx-border-radius: 4; -fx-background-radius: 4;");

        gridEmergencia.add(lblNombreEmergencia, 0, 0); gridEmergencia.add(txtNombreEmergencia, 1, 0);
        gridEmergencia.add(lblParentescoEmergencia, 0, 1); gridEmergencia.add(txtParentescoEmergencia, 1, 1);
        gridEmergencia.add(lblTelefonoEmergencia, 0, 2); gridEmergencia.add(txtTelefonoEmergencia, 1, 2);


        // Botón para "Editar Perfil" (simulado, la funcionalidad de guardar es compleja)
        Button btnEditarPerfil = new Button("Editar Perfil");
        btnEditarPerfil.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        btnEditarPerfil.setPrefHeight(40);
        btnEditarPerfil.setMinWidth(150);

        Button btnGuardarCambios = new Button("Guardar Cambios");
        btnGuardarCambios.setStyle("-fx-background-color: #2ECC71; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        btnGuardarCambios.setPrefHeight(40);
        btnGuardarCambios.setMinWidth(150);
        btnGuardarCambios.setVisible(false); // Oculto inicialmente

        // Lógica para habilitar/deshabilitar edición
        btnEditarPerfil.setOnAction(e -> {
            boolean editable = !txtNombre.isEditable(); // Toggle state
            txtNombre.setEditable(editable);
            dpFechaNacimiento.setDisable(!editable);
            cmbGenero.setDisable(!editable);
            txtEmail.setEditable(editable);
            txtTelefono.setEditable(editable);
            txtDireccion.setEditable(editable);
            txtAlergias.setEditable(editable);
            txtCondiciones.setEditable(editable);
            txtMedicamentos.setEditable(editable);
            txtNombreEmergencia.setEditable(editable);
            txtParentescoEmergencia.setEditable(editable);
            txtTelefonoEmergencia.setEditable(editable);
            cmbGrupoSanguineo.setDisable(!editable);

            btnGuardarCambios.setVisible(editable);
            btnEditarPerfil.setText(editable ? "Cancelar Edición" : "Editar Perfil");
            btnEditarPerfil.setStyle(editable ? "-fx-background-color: #E74C3C; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;" : "-fx-background-color: #3498DB; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        });

        btnGuardarCambios.setOnAction(e -> {
            // Aquí iría la lógica para GUARDAR los datos en una base de datos real.
            // Por ahora, solo simulamos el guardado y volvemos a deshabilitar los campos.
            mostrarAlerta(Alert.AlertType.INFORMATION, "Datos de perfil actualizados exitosamente (simulado).");

            txtNombre.setEditable(false);
            dpFechaNacimiento.setDisable(true);
            cmbGenero.setDisable(true);
            txtEmail.setEditable(false);
            txtTelefono.setEditable(false);
            txtDireccion.setEditable(false);
            txtAlergias.setEditable(false);
            txtCondiciones.setEditable(false);
            txtMedicamentos.setEditable(false);
            txtNombreEmergencia.setEditable(false);
            txtParentescoEmergencia.setEditable(false);
            txtTelefonoEmergencia.setEditable(false);
            cmbGrupoSanguineo.setDisable(true);

            btnGuardarCambios.setVisible(false);
            btnEditarPerfil.setText("Editar Perfil");
            btnEditarPerfil.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        });


        HBox botonesAccion = new HBox(15, btnEditarPerfil, btnGuardarCambios);
        botonesAccion.setAlignment(Pos.CENTER_RIGHT); // Alinea los botones a la derecha

        // El VBox que contiene todo el contenido del perfil
        VBox contenidoPerfil = new VBox(20); // Espaciado entre secciones
        contenidoPerfil.getChildren().addAll(
            titulo,
            new Separator(),
            lblDescripcion,
            subTituloPersonal,
            gridPersonal,
            new Separator(),
            subTituloMedico,
            gridMedico,
            new Separator(),
            subTituloEmergencia,
            gridEmergencia,
            new Separator(),
            botonesAccion
        );
        contenidoPerfil.setAlignment(Pos.TOP_LEFT);
        contenidoPerfil.setPadding(new Insets(20)); // Padding dentro del VBox

        // Crear el ScrollPane y establecer el VBox como su contenido
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(contenidoPerfil);
        scrollPane.setFitToWidth(true); // Hace que el ScrollPane ajuste su ancho al contenido
        // Opcional: Deshabilita la barra de desplazamiento horizontal si no es necesaria
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        // Opcional: Establece la política de la barra de desplazamiento vertical
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        // El método ahora devuelve el ScrollPane
        return scrollPane;
    }

    // Método auxiliar para mostrar alertas, copiado para que funcione de forma independiente
    private static void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo, mensaje, ButtonType.OK);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
}