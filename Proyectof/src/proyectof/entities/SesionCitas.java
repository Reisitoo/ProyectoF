
package proyectof.entities;

import java.util.ArrayList;
import java.util.List;

public class SesionCitas {

    private static final List<Cita> citasRegistradas = new ArrayList<>();

    public static void agregarCita(Cita cita) {
        citasRegistradas.add(cita);
    }

    public static List<Cita> obtenerCitas() {
        return new ArrayList<>(citasRegistradas); 
    }

    public static void limpiarCitas() {
        citasRegistradas.clear();
    }
}
