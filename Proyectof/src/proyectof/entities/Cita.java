package proyectof.entities;

import java.time.LocalDateTime;

public class Cita {

    private String doctor;
    private LocalDateTime fecha;
    private String asunto;
    private String comentario;

    public Cita(String doctor, LocalDateTime fecha, String asunto, String comentario) {
        this.doctor = doctor;
        this.fecha = fecha;
        this.asunto = asunto;
        this.comentario = comentario;
    }

    public String getDoctor() {
        return doctor;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getComentario() {
        return comentario;
    }
}
