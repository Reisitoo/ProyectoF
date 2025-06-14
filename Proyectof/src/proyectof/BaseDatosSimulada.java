package hospitalvitali;

import java.util.HashMap;
import java.util.Map;

public class BaseDatosSimulada {

    private static Map<String, Doctor> doctoresRegistrados = new HashMap<>();
    private static Map<String, Paciente> pacientesRegistrados = new HashMap<>();

    // Bloque estático: se ejecuta una vez cuando la clase BaseDatosSimulada es cargada por la JVM.
    // Aquí precargamos algunos datos para facilitar las pruebas.
    static {
        // Precargar algunos doctores
        Doctor d1 = new Doctor("Dr. Juan Pérez", "juan.perez", "passDoc");
        doctoresRegistrados.put(d1.getUsuario(), d1);
        System.out.println("BaseDatosSimulada: Precargado doctor: " + d1.getUsuario());

        // Precargar algunos pacientes
        Paciente p1 = new Paciente("Ana Garcia", "ana.g", "passPac");
        pacientesRegistrados.put(p1.getUsuario(), p1);
        System.out.println("BaseDatosSimulada: Precargado paciente: " + p1.getUsuario());
    }

    // --- Métodos para Doctores ---

    public static void registrarDoctor(Doctor doctor) {
        doctoresRegistrados.put(doctor.getUsuario(), doctor);
        System.out.println("BaseDatosSimulada: Doctor '" + doctor.getUsuario() + "' registrado. Total doctores: " + doctoresRegistrados.size());
    }

    public static boolean existeDoctor(String usuario) {
        boolean existe = doctoresRegistrados.containsKey(usuario);
        System.out.println("BaseDatosSimulada: Existe doctor '" + usuario + "': " + existe);
        return existe;
    }

    public static Doctor verificarDoctor(String usuario, String contrasena) {
        Doctor doctor = doctoresRegistrados.get(usuario);
        if (doctor != null) {
            System.out.println("BaseDatosSimulada: Buscando doctor '" + usuario + "'. Contraseña almacenada: " + doctor.getContrasena() + ", ingresada: " + contrasena);
        } else {
            System.out.println("BaseDatosSimulada: Doctor '" + usuario + "' NO encontrado.");
        }

        if (doctor != null && doctor.getContrasena().equals(contrasena)) {
            System.out.println("BaseDatosSimulada: Autenticación exitosa para doctor: " + usuario);
            return doctor;
        }
        System.out.println("BaseDatosSimulada: Autenticación fallida para doctor: " + usuario);
        return null;
    }

    // --- Métodos para Pacientes ---

    public static void registrarPaciente(Paciente paciente) {
        pacientesRegistrados.put(paciente.getUsuario(), paciente);
        System.out.println("BaseDatosSimulada: Paciente '" + paciente.getUsuario() + "' registrado. Total pacientes: " + pacientesRegistrados.size());
    }

    public static boolean existePaciente(String usuario) {
        boolean existe = pacientesRegistrados.containsKey(usuario);
        System.out.println("BaseDatosSimulada: Existe paciente '" + usuario + "': " + existe);
        return existe;
    }

    public static Paciente verificarPaciente(String usuario, String contrasena) {
        Paciente paciente = pacientesRegistrados.get(usuario);
        if (paciente != null) {
            System.out.println("BaseDatosSimulada: Buscando paciente '" + usuario + "'. Contraseña almacenada: " + paciente.getContrasena() + ", ingresada: " + contrasena);
        } else {
            System.out.println("BaseDatosSimulada: Paciente '" + usuario + "' NO encontrado.");
        }

        if (paciente != null && paciente.getContrasena().equals(contrasena)) {
            System.out.println("BaseDatosSimulada: Autenticación exitosa para paciente: " + usuario);
            return paciente;
        }
        System.out.println("BaseDatosSimulada: Autenticación fallida para paciente: " + usuario);
        return null;
    }
}