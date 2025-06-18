package proyectof;

import java.time.LocalDateTime;

public class Cita {
    public String Doctor;
    public LocalDateTime  Fecha;
    public String Asunto;
    public String Comentario;

    public Cita(String Doctor, LocalDateTime Fecha, String Asunto, String Comentario) {
        this.Doctor = Doctor;
        this.Fecha = Fecha;
        this.Asunto = Asunto;
        this.Comentario = Comentario;
    }
}

