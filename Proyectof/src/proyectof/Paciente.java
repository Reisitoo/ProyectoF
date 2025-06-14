package hospitalvitali;

public class Paciente {
    private String nombreCompleto;
    private String usuario;
    private String contrasena;
    // private String email; // Opcional, si quieres almacenarlos en el objeto
    // private String telefono; // Opcional

    public Paciente(String nombreCompleto, String usuario, String contrasena) {
        this.nombreCompleto = nombreCompleto;
        this.usuario = usuario;
        this.contrasena = contrasena;
        // this.email = email;
        // this.telefono = telefono;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // public String getEmail() { return email; }
    // public void setEmail(String email) { this.email = email; }
    // public String getTelefono() { return telefono; }
    // public void setTelefono(String telefono) { this.telefono = telefono; }
}