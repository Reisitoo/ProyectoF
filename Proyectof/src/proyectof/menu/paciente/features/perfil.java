
package proyectof.menu.paciente.features;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import proyectof.entities.Sesion;
import proyectof.entities.Usuario;


public class perfil {
    public static VBox crearPanelPerfilPaciente() {
        Usuario usuarioLogueado = Sesion.getUsuarioActual();

        Label titulo = new Label("Mi Perfil");
         titulo.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 26));
        titulo.setTextFill(Color.web("#2E7D32"));

        Label lblNombre = crearEtiquetaDato("Nombre", usuarioLogueado.getNombreCompleto());
        Label lblUsuario = crearEtiquetaDato("Usuario", usuarioLogueado.getUsuario());
        Label lblEmail = crearEtiquetaDato("Email", usuarioLogueado.getEmail());
        Label lblTelefono = crearEtiquetaDato("Teléfono", usuarioLogueado.getTelefono());

        GridPane grid = new GridPane();
        grid.setVgap(12);
        grid.setHgap(20);
        grid.setPadding(new Insets(10, 0, 20, 0));
        grid.add(lblNombre, 0, 0);
        grid.add(lblUsuario, 0, 1);
        grid.add(lblEmail, 0, 2);
        grid.add(lblTelefono, 0, 3);

        VBox panel = new VBox(20, titulo, grid);
        panel.setAlignment(Pos.BASELINE_CENTER.TOP_LEFT);
        panel.setPadding(new Insets(30));
        panel.setStyle("-fx-background-color: #F9F9F9; -fx-border-color: #E0E0E0; -fx-border-width: 1px; -fx-border-radius: 5px;");
        return panel;
    }

    private static Label crearEtiquetaDato(String campo, String valor) {
        Label label = new Label(campo + ": " + valor);
        label.setFont(Font.font("Segoe UI", FontWeight.MEDIUM, 14));
        label.setTextFill(Color.web("#333333"));
        return label;
    }
}
