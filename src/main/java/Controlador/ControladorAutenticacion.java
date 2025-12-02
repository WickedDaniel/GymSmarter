package Controlador;

import Modelo.Entidades.Cliente;
import Modelo.Entidades.Profesional;
import Modelo.Entidades.Usuario;
import Modelo.Excepciones.CredencialesInvalidasException;
import Modelo.Excepciones.GymSmarterException;
import Modelo.Excepciones.UsuarioNoEncontradoException;
import Modelo.Excepciones.UsuarioYaExisteException;
import Modelo.Utils.ManejadorArchivoBinario;
import Modelo.Utils.Encriptador;

import java.util.ArrayList;

public class ControladorAutenticacion {
    private ArrayList<Usuario> listaUsuarios;
    private final String filename = "Data/usuarios.dat";
    private final ManejadorArchivoBinario<ArrayList<Usuario>> manejadorUsuarios = new ManejadorArchivoBinario<>();

    public ControladorAutenticacion() {
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        ArrayList<Usuario> cargados = manejadorUsuarios.cargar(filename);
        listaUsuarios = (cargados != null) ? cargados : new ArrayList<>();
    }

    private void guardarUsuarios() {
        manejadorUsuarios.guardar(filename, listaUsuarios);
    }

    private boolean existeCorreo(String correo) {
        for (Usuario usuario : listaUsuarios) if (usuario.getCorreo().equalsIgnoreCase(correo)) return true;
        return false;
    }

    public Usuario registrarCliente(String nombre, String apellido, String correo, String password) throws UsuarioYaExisteException {
        if (existeCorreo(correo)) throw new UsuarioYaExisteException("El correo ya está registrado.");

        if (!Encriptador.validarFortaleza(password)) {
            throw new UsuarioYaExisteException(
                    "La contraseña no cumple con los requisitos de seguridad.\n" +
                            Encriptador.obtenerRequisitosFortaleza()
            );
        }

        String passwordEncriptada = Encriptador.encriptar(password);

        if (passwordEncriptada == null) {
            throw new UsuarioYaExisteException("Error al procesar la contraseña.");
        }

        Cliente nuevo = new Cliente(nombre, apellido, correo, passwordEncriptada);
        listaUsuarios.add(nuevo);
        guardarUsuarios();
        return nuevo;
    }

    public Usuario registrarProfesional(String nombre, String apellido, String correo, String password) throws UsuarioYaExisteException, CredencialesInvalidasException {
        if (existeCorreo(correo)) throw new UsuarioYaExisteException("El correo ya está registrado.");

        String passwordEncriptada = Encriptador.encriptar(password);

        if (passwordEncriptada == null) {throw new CredencialesInvalidasException("Error al procesar la contraseña.");}

        Profesional nuevo = new Profesional(nombre, apellido, correo, passwordEncriptada);
        listaUsuarios.add(nuevo);
        guardarUsuarios();
        return nuevo;
    }

    public Usuario iniciarSesion(String correo, String password) throws CredencialesInvalidasException, UsuarioNoEncontradoException {
        for (Usuario usuario : listaUsuarios) {
            if (!usuario.getCorreo().equalsIgnoreCase(correo)) continue;

            if (!Encriptador.verificar(password, usuario.getContrasena())) throw new CredencialesInvalidasException("Contraseña incorrecta.");

            return usuario;
        }

        throw new UsuarioNoEncontradoException("Correo no encontrado.");
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
}