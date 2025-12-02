package Controlador;

import Modelo.Entidades.Cliente;
import Modelo.Entidades.Reporte;
import Modelo.Entidades.Metrica;
import Modelo.Entidades.Profesional;
import Modelo.Enumeraciones.TipoMetrica;
import Modelo.Excepciones.AccesoDenegadoException;
import Modelo.Utils.ManejadorArchivoBinario;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ControladorReportes {
    private final ControladorPermisosCompartir gestionPermisos = new ControladorPermisosCompartir();
    private ArrayList<Reporte> listaReportes;
    private final String filename = "Data/reportes.dat";

    private final ManejadorArchivoBinario<ArrayList<Reporte>> manejadorBinario = new ManejadorArchivoBinario<>();

    public ControladorReportes() {
        cargarReportes();
    }

    private void cargarReportes() {
        ArrayList<Reporte> cargado = manejadorBinario.cargar(filename);
        if (cargado == null) {
            listaReportes = new ArrayList<>();
            return;
        }
        listaReportes = cargado;
    }

    private void guardarReportes() {
        manejadorBinario.guardar(filename, listaReportes);
    }

    public Reporte generarReporte(Profesional profesional, Cliente cliente, ArrayList<TipoMetrica> metricasSeleccionadas) throws AccesoDenegadoException {
        Reporte reporte = new Reporte();
        reporte.setCorreoCliente(cliente.getCorreo());
        reporte.setCorreoProfesional(profesional.getCorreo());
        reporte.setFechaHora(LocalDateTime.now());

        ArrayList<Metrica> metricasIncluidas = new ArrayList<>();
        for (TipoMetrica tipoMetrica : metricasSeleccionadas) {
            if (!(gestionPermisos.puedeVerMetrica(cliente.getCorreo(), profesional.getCorreo(), tipoMetrica))){
                throw new AccesoDenegadoException("El cliente no ha cencedido permisos para ver las metricas del tipo "+tipoMetrica.toString());
            }
            for (Metrica metrica: cliente.getListaMetricas()) {
                if (!metrica.getTipoMetrica().equals(tipoMetrica)) continue;
                metricasIncluidas.add(metrica);
            }
        }
        reporte.setMetricasIncluidas(metricasIncluidas);

        StringBuilder texto = new StringBuilder();

        texto.append("========= REPORTE PROFESIONAL =========\n\n");

        texto.append("Profesional responsable:\n");
        texto.append(" - ").append(profesional.getNombre())
                .append(" (").append(profesional.getCorreo()).append(")\n\n");

        texto.append("Cliente analizado:\n");
        texto.append(" - ").append(cliente.getCorreo()).append("\n\n");

        texto.append("Fecha de generación: ").append(reporte.getFechaHora()).append("\n");
        texto.append("=======================================\n\n");

        texto.append("MÉTRICAS INCLUIDAS EN ESTE REPORTE:\n\n");

        for (Metrica metrica : metricasIncluidas) {
            texto.append("• Tipo: ").append(metrica.getTipoMetrica()).append("\n");
            texto.append("  Valor: ").append(metrica.getValorPrimario());

            if (metrica.getValorSecundario() != 0) {
                texto.append(" / ").append(metrica.getValorSecundario());
            }

            texto.append("\n");
            texto.append("  Fecha: ").append(metrica.getFechaHora()).append("\n\n");
        }

        texto.append("=======================================\n");
        texto.append("Fin del reporte.\n");

        reporte.setResumen(texto.toString());

        listaReportes.add(reporte);
        guardarReportes();

        profesional.getListaReportes().add(reporte);

        return reporte;
    }

    public ArrayList<Reporte> obtenerReportesDeProfesional(String correoProfesional) {
        ArrayList<Reporte> resultado = new ArrayList<>();

        for (Reporte reporte : listaReportes) {
            if (reporte.getCorreoProfesional().equals(correoProfesional)) resultado.add(reporte);
        }
        return resultado;
    }


    public ArrayList<Reporte> obtenerReportesDeCliente(String correoCliente) {
        ArrayList<Reporte> resultado = new ArrayList<>();

        for (Reporte reporte : listaReportes) {
            if (reporte.getCorreoCliente().equals(correoCliente)) resultado.add(reporte);
        }
        return resultado;
    }
}
