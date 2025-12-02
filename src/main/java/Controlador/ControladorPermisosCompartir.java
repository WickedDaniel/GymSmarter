package Controlador;

import Modelo.Entidades.PermisoCompartir;
import Modelo.Enumeraciones.TipoMetrica;
import Modelo.Utils.ManejadorArchivoBinario;

import java.util.ArrayList;

public class ControladorPermisosCompartir {
    private final String filename = "Data/permisos.dat";
    private static ArrayList<PermisoCompartir> listaPermisos;
    private final ManejadorArchivoBinario<ArrayList<PermisoCompartir>> manejador = new ManejadorArchivoBinario<>();

    public ControladorPermisosCompartir() {
        cargarPermisos();
    }

    private void cargarPermisos() {
        ArrayList<PermisoCompartir> cargado = manejador.cargar(filename);
        listaPermisos = (cargado == null) ? new ArrayList<>() : cargado;
    }

    private void guardarPermisos() {
        manejador.guardar(filename, listaPermisos);
    }

    public void asignarPermisos(String correoCliente, String correoProfesional, ArrayList<TipoMetrica> seleccionadas) {
        PermisoCompartir permiso = obtenerPermiso(correoCliente, correoProfesional);

        if (permiso == null) {
            permiso = new PermisoCompartir(correoCliente, correoProfesional);
            listaPermisos.add(permiso);
        }

        permiso.setMetricasPermitidas(seleccionadas);

        guardarPermisos();
    }

    public PermisoCompartir obtenerPermiso(String correoCliente, String correoProfesional) {
        for (PermisoCompartir permisoCompartir : listaPermisos) {
            if (permisoCompartir.getCorreoCliente().equals(correoCliente) && permisoCompartir.getCorreoProfesional().equals(correoProfesional)) {
                return permisoCompartir;
            }
        }
        return null;
    }

    public boolean puedeVerMetrica(String correoCliente, String correoProfesional, TipoMetrica tipo) {
        PermisoCompartir permiso = obtenerPermiso(correoCliente, correoProfesional);
        if (permiso == null) return false;
        return permiso.getMetricasPermitidas().contains(tipo);
    }
}
