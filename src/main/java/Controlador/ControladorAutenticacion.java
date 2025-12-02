package Controlador;

import Modelo.Entidades.Cliente;
import Modelo.Entidades.Profesional;
import Modelo.Entidades.Usuario;
import Modelo.Excepciones.CredencialesInvalidasException;
import Modelo.Excepciones.UsuarioNoEncontradoException;
import Modelo.Excepciones.UsuarioYaExisteException;
import Modelo.Utils.ManejadorArchivoBinario;

import java.util.ArrayList;

public class ControladorAutenticacion {
    private ArrayList<Usuario> listaUsuarios;
    private final String filename = "Data/usuarios.dat";
    private ManejadorArchivoBinario<ArrayList<Usuario>> manejadorUsuarios = new ManejadorArchivoBinario<>();

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

        Cliente nuevo = new Cliente(nombre, apellido, correo, password);
        listaUsuarios.add(nuevo);
        guardarUsuarios();
        return nuevo;
    }

    public Usuario registrarProfesional(String nombre, String apellido, String correo, String password) throws UsuarioYaExisteException {
        if (existeCorreo(correo)) throw new UsuarioYaExisteException("El correo ya está registrado.");

        Profesional nuevo = new Profesional(nombre, apellido, correo, password);
        listaUsuarios.add(nuevo);
        guardarUsuarios();
        return nuevo;
    }

    public Usuario iniciarSesion(String correo, String password) throws CredencialesInvalidasException, UsuarioNoEncontradoException {
        for (Usuario usuario : listaUsuarios) {
            if (!usuario.getCorreo().equalsIgnoreCase(correo)) continue;
            if (!usuario.getContrasena().equals(password)) throw new CredencialesInvalidasException("Contraseña incorrecta.");
            return usuario;
        }

        throw new UsuarioNoEncontradoException("Correo no encontrado.");
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
}
