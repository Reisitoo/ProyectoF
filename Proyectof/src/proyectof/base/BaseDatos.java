package proyectof.base;

import proyectof.entities.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDatos {

    private static final Map<String, Usuario> usuariosRegistrados = new HashMap<>();

    static {
        // Usuario administrador
        Usuario admin = new Usuario("Administrador", "admin", "12345678", "admin@admin.com", "9999-9999", true, false);
        usuariosRegistrados.put(admin.getUsuario(), admin);
        System.out.println("BaseDatosSimulada: Usuario administrador precargado: " + admin.getUsuario());

        // Usuario doctor
        Usuario doctor = new Usuario("Dr. Juan Pérez", "doctor", "12345678", "dr.perez@gmail.com", "92949671", false, true);
        usuariosRegistrados.put(doctor.getUsuario(), doctor);
        System.out.println("BaseDatosSimulada: Doctor precargado: " + doctor.getUsuario());

        // Usuario paciente
        Usuario paciente = new Usuario("Ana Garcia", "paciente", "12345678", "ana.g@gmail.com", "8875494", false, false);
        usuariosRegistrados.put(paciente.getUsuario(), paciente);
        System.out.println("BaseDatosSimulada: Paciente precargado: " + paciente.getUsuario());
    }

    // Registrar cualquier tipo de usuario
    public static void registrarUsuario(Usuario usuario) {
        usuariosRegistrados.put(usuario.getUsuario(), usuario);
        System.out.println("BaseDatosSimulada: Usuario '" + usuario.getUsuario() + "' registrado. Total usuarios: " + usuariosRegistrados.size());
    }

    // Verifica si un usuario existe por nombre de usuario
    public static boolean existeUsuario(String usuario) {
        boolean existe = usuariosRegistrados.containsKey(usuario);
        System.out.println("BaseDatosSimulada: ¿Existe usuario '" + usuario + "'?: " + existe);
        return existe;
    }

    // Verifica las credenciales de un usuario
    public static Usuario verificarCredenciales(String usuario, String contrasena) {
        Usuario u = usuariosRegistrados.get(usuario);
        if (u != null) {
            System.out.println("BaseDatosSimulada: Buscando usuario '" + usuario + "'. Contraseña almacenada: " + u.getContrasena() + ", ingresada: " + contrasena);
        } else {
            System.out.println("BaseDatosSimulada: Usuario '" + usuario + "' NO encontrado.");
        }

        if (u != null && u.getContrasena().equals(contrasena)) {
            System.out.println("BaseDatosSimulada: Autenticación exitosa para: " + usuario);
            return u;
        }
        System.out.println("BaseDatosSimulada: Autenticación fallida para: " + usuario);
        return null;
    }

    // Obtener usuario (si necesitas mostrar datos sin autenticar)
    public static Usuario obtenerUsuario(String usuario) {
        return usuariosRegistrados.get(usuario);
    }

    // Obtener usuario (si necesitas mostrar datos sin autenticar)
    public static List<Usuario> obtenerUsuarios() {
        return new ArrayList<>(usuariosRegistrados.values());
    }

    public static boolean usuarioExiste(String username) {
        return usuariosRegistrados.containsKey(username);
    }

    public static void eliminarUsuario(String username) {
        usuariosRegistrados.remove(username);
    }

}
