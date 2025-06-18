package proyectof.entities;

public class Usuario {

    private String nombreCompleto;
    private String usuario;
    private String contrasena;
    private boolean esAdmin;
    private boolean esDoctor;
    private String email;
    private String telefono;

    public Usuario(String nombreCompleto, String usuario, String contrasena, String email, String telefono, boolean esAdmin, boolean esDoctor) {
        this.nombreCompleto = nombreCompleto;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.esAdmin = esAdmin;
        this.esDoctor = esDoctor;
        this.email = email;
        this.telefono = telefono;
    }

    // Getters
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public boolean isAdmin() {
        return esAdmin;
    }

    public boolean isDoctor() {
        return esDoctor;
    }

    // Setters
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public void setDoctor(boolean esDoctor) {
        this.esDoctor = esDoctor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public boolean getEsAdmin() {
        return esAdmin;
    }
    
    public boolean getEsDoctor() {
        return esDoctor;
    }
}
