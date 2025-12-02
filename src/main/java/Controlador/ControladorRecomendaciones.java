package Controlador;

import Modelo.Entidades.Cliente;
import Modelo.Entidades.Profesional;
import Modelo.Entidades.Recomendacion;
import Modelo.Utils.ManejadorArchivoBinario;

import java.util.ArrayList;

public class ControladorRecomendaciones {
    private final String filename = "Data/recomendaciones.dat";
    private final ManejadorArchivoBinario<ArrayList<Recomendacion>> manejadorBinario = new ManejadorArchivoBinario<>();

    private ArrayList<Recomendacion> listaRecomendaciones;
    private int consecutivo = 1;

    public ControladorRecomendaciones() {
        cargarRecomendaciones();
    }

    private void cargarRecomendaciones() {
        ArrayList<Recomendacion> datosCargados = manejadorBinario.cargar(filename);

        if (datosCargados == null) {
            listaRecomendaciones = new ArrayList<>();
        } else {
            listaRecomendaciones = datosCargados;

            for (Recomendacion recomendacion : listaRecomendaciones) {
                if (recomendacion.getIdRecomendacion() >= consecutivo) consecutivo = recomendacion.getIdRecomendacion() + 1;
            }
        }
    }

    private void guardarRecomendaciones() {
        manejadorBinario.guardar(filename, listaRecomendaciones);
    }

    public Recomendacion emitirRecomendacion(Profesional profesional, Cliente cliente, String contenido) {
        if (profesional == null || cliente == null || contenido == null) return null;

        Recomendacion nueva = new Recomendacion(consecutivo, cliente.getCorreo(), profesional.getCorreo(), contenido);

        listaRecomendaciones.add(nueva);
        guardarRecomendaciones();

        cliente.getListaRecomendaciones().add(nueva);
        profesional.getListaRecomendacionesEmitidas().add(nueva);

        consecutivo++;
        return nueva;
    }

    public ArrayList<Recomendacion> obtenerRecomendacionesCliente(String correoCliente) {
        ArrayList<Recomendacion> resultado = new ArrayList<>();

        for (Recomendacion recomendacion : listaRecomendaciones) {
            if (recomendacion.getCorreoCliente().equals(correoCliente)) resultado.add(recomendacion);
        }

        return resultado;
    }

    public ArrayList<Recomendacion> obtenerRecomendacionesProfesional(String correoProfesional) {
        ArrayList<Recomendacion> resultado = new ArrayList<>();

        for (Recomendacion recomendacion : listaRecomendaciones) {
            if (recomendacion.getCorreoProfesional().equals(correoProfesional)) resultado.add(recomendacion);
        }

        return resultado;
    }
}
