package proyectof;

/**
 * @author Daniel Espinoza
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuAdmin {

    private ObservableList<Usuario> usuarios;

    public void mostrar(Stage primaryStage) {
        Label titulo = new Label("Gestión de Usuarios");
        titulo.setFont(Font.font("Roboto", FontWeight.BOLD, 28));

        // Tabla
        TableView<Usuario> tablaUsuarios = new TableView<>();
        tablaUsuarios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Usuario, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));

        TableColumn<Usuario, String> colUsuario = new TableColumn<>("Usuario");
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        TableColumn<Usuario, String> colRol = new TableColumn<>("Rol");
        colRol.setCellValueFactory(celda -> {
            String rol = celda.getValue().getEsAdmin() ? "Admin" : celda.getValue().getEsDoctor() ? "Doctor" : "Paciente";
            return new javafx.beans.property.SimpleStringProperty(rol);
        });

        tablaUsuarios.getColumns().addAll(colNombre, colUsuario, colRol);

        // Cargar datos
        usuarios = FXCollections.observableArrayList(BaseDatosSimulada.obtenerUsuarios());
        tablaUsuarios.setItems(usuarios);

        // Botones de acción
        Button btnAgregar = new Button("Agregar");
        Button btnEditar = new Button("Editar");
        Button btnEliminar = new Button("Eliminar");
        Button btnVolver = new Button("Cerrar sesión");

        btnAgregar.setOnAction(e -> mostrarFormulario(null, tablaUsuarios));
        btnEditar.setOnAction(e -> {
            Usuario seleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                mostrarFormulario(seleccionado, tablaUsuarios);
            } else {
                mostrarAlerta("Selecciona un usuario para editar.");
            }
        });
        btnEliminar.setOnAction(e -> {
            Usuario seleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                usuarios.remove(seleccionado);
                BaseDatosSimulada.eliminarUsuario(seleccionado.getUsuario());
            } else {
                mostrarAlerta("Selecciona un usuario para eliminar.");
            }
        });
        btnVolver.setOnAction(e -> new Login().mostrar(primaryStage));

        HBox botones = new HBox(10, btnAgregar, btnEditar, btnEliminar, btnVolver);
        botones.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(20, titulo, tablaUsuarios, botones);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Panel de Administración");
        primaryStage.show();
    }

    private void mostrarFormulario(Usuario usuarioEditar, TableView<Usuario> tabla) {
        Stage ventana = new Stage();
        ventana.initModality(Modality.APPLICATION_MODAL);
        ventana.setTitle(usuarioEditar == null ? "Nuevo Usuario" : "Editar Usuario");

        // Campos de entrada
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre completo");
        estilizarCampo(txtNombre);

        TextField txtUsuario = new TextField();
        txtUsuario.setPromptText("Usuario");
        estilizarCampo(txtUsuario);

        PasswordField txtPassword = new PasswordField();
        txtPassword.setPromptText("Contraseña");
        estilizarCampo(txtPassword);

        TextField txtEmail = new TextField();
        txtEmail.setPromptText("Correo (Opcional)");
        estilizarCampo(txtEmail);       
        
        TextField txtTelefono = new TextField();
        txtTelefono.setPromptText("Teléfono (Opcional)");
        estilizarCampo(txtTelefono);       

        // Radio buttons para el rol
        ToggleGroup grupoRol = new ToggleGroup();
        RadioButton rbAdmin = new RadioButton("Admin");
        RadioButton rbDoctor = new RadioButton("Doctor");
        RadioButton rbPaciente = new RadioButton("Paciente");

        rbAdmin.setToggleGroup(grupoRol);
        rbDoctor.setToggleGroup(grupoRol);
        rbPaciente.setToggleGroup(grupoRol);

        estilizarRadio(rbAdmin);
        estilizarRadio(rbDoctor);
        estilizarRadio(rbPaciente);

        if (usuarioEditar != null) {
            txtNombre.setText(usuarioEditar.getNombreCompleto());
            txtUsuario.setText(usuarioEditar.getUsuario());
            txtUsuario.setDisable(true);
            txtPassword.setText(usuarioEditar.getContrasena());
            txtEmail.setText(usuarioEditar.getEmail());
            txtTelefono.setText(usuarioEditar.getTelefono());
            

            if (usuarioEditar.getEsAdmin()) {
                rbAdmin.setSelected(true);
            } else if (usuarioEditar.getEsDoctor()) {
                rbDoctor.setSelected(true);
            } else {
                rbPaciente.setSelected(true);
            }
        }

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setPrefHeight(45);
        btnGuardar.setMinWidth(150);
        btnGuardar.setFont(Font.font("Roboto", FontWeight.BOLD, 16));
        btnGuardar.setStyle(
                "-fx-background-color: #2196F3;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 20;"
                + "-fx-font-family: 'Roboto';"
                + "-fx-effect: dropshadow(three-pass-box, rgba(33, 150, 243, 0.3), 10, 0, 0, 4);"
        );

        btnGuardar.setOnAction(e -> {
            String nombre = txtNombre.getText().trim();
            String usuario = txtUsuario.getText().trim();
            String pass = txtPassword.getText().trim();
            String correo = txtEmail.getText().trim();
            String telefono = txtTelefono.getText().trim();
            boolean esAdmin = rbAdmin.isSelected();
            boolean esDoctor = rbDoctor.isSelected();

            if (nombre.isEmpty() || usuario.isEmpty() || pass.isEmpty() || grupoRol.getSelectedToggle() == null) {
                mostrarAlerta("Todos los campos son obligatorios.");
                return;
            }

            Usuario nuevoUsuario = new Usuario(nombre, usuario, pass, correo, telefono, esAdmin, esDoctor);

            if (usuarioEditar == null) {
                if (BaseDatosSimulada.usuarioExiste(usuario)) {
                    mostrarAlerta("El nombre de usuario ya existe.");
                    return;
                }
                usuarios.add(nuevoUsuario);
                BaseDatosSimulada.registrarUsuario(nuevoUsuario);
            } else {
                usuarioEditar.setNombreCompleto(nombre);
                usuarioEditar.setContrasena(pass);
                usuarioEditar.setAdmin(esAdmin);
                usuarioEditar.setDoctor(esDoctor);
                tabla.refresh();
            }

            ventana.close();
        });

        VBox layout = new VBox(12, txtNombre, txtUsuario, txtPassword, rbAdmin, rbDoctor, rbPaciente, txtEmail, txtTelefono, btnGuardar);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);

        Scene escena = new Scene(layout, 420, 480);
        ventana.setScene(escena);
        ventana.showAndWait();
    }

    private void estilizarCampo(TextField campo) {
        campo.setFont(Font.font("Roboto", 14));
        campo.setStyle("-fx-background-color: #E0E0E0; -fx-background-radius: 10; -fx-border-color: transparent; -fx-padding: 10;");
        campo.setPrefHeight(45);
        campo.setMaxWidth(280);
    }

    private void estilizarRadio(RadioButton rb) {
        rb.setFont(Font.font("Roboto", 14));
        rb.setStyle("-fx-text-fill: #333;");
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
