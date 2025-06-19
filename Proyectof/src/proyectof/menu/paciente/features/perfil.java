
package proyectof.menu.paciente.features;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import proyectof.entities.Sesion;
import proyectof.entities.Usuario;


public class perfil {
    public static VBox crearPanelPerfilPaciente() {
        Usuario usuarioLogueado = Sesion.getUsuarioActual();

        final String BASE_COLOR = "#ECF0F3";
        final String DARK_TEXT = "#34495E";

        Label titulo = new Label("Mi Perfil");
        titulo.setFont(Font.font("Roboto", FontWeight.BOLD, 32));
        titulo.setTextFill(Color.web(DARK_TEXT));

        // Etiquetas de datos con estilo Neumorphism
        Label lblNombre = crearEtiquetaDato("Nombre", usuarioLogueado.getNombreCompleto());
        Label lblUsuario = crearEtiquetaDato("Usuario", usuarioLogueado.getUsuario());
        Label lblEmail = crearEtiquetaDato("Email", usuarioLogueado.getEmail());
        Label lblTelefono = crearEtiquetaDato("Teléfono", usuarioLogueado.getTelefono());

        VBox datos = new VBox(15, lblNombre, lblUsuario, lblEmail, lblTelefono);
        datos.setPadding(new Insets(20));
        datos.setStyle(
                "-fx-background-color: " + BASE_COLOR + "; " +
                "-fx-background-radius: 20px; " +
                "-fx-border-radius: 20px; " +
                "-fx-effect: innershadow(gaussian, rgba(0,0,0,0.1), 10, 0, 3, 3), " +
                "              innershadow(gaussian, rgba(255,255,255,0.7), 10, 0, -3, -3);"
        );

        VBox panel = new VBox(30, titulo, datos);
        panel.setAlignment(Pos.TOP_CENTER);
        panel.setPadding(new Insets(40));
        panel.setStyle("-fx-background-color: transparent;");

        return panel;
    }

    private static Label crearEtiquetaDato(String campo, String valor) {
        Label label = new Label(campo + ": " + valor);
        label.setFont(Font.font("Roboto", FontWeight.MEDIUM, 16));
        label.setTextFill(Color.web("#34495E"));
        return label;
    }
}
