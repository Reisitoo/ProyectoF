
package proyectof.menu.paciente.features;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class crearCita {
    
    public static VBox crearPanelCitasMedicas() {
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

        VBox panel = new VBox(10, titulo, listaCitas, btnAgendarNueva);
        panel.setAlignment(Pos.TOP_LEFT);
        panel.setPadding(new Insets(20));
        return panel;
    }
    
    
    
}
