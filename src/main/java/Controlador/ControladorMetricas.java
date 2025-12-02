package Controlador;

import Modelo.Entidades.Cliente;
import Modelo.Entidades.Metrica;
import Modelo.Entidades.Usuario;
import Modelo.Enumeraciones.TipoMetrica;
import Modelo.Utils.ManejadorArchivoBinario;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ControladorMetricas {
    private ArrayList<Metrica> listaMetricas;
    private final String filename = "Data/metricas.dat";
    private ManejadorArchivoBinario<ArrayList<Metrica>> manejadorBinario;

    public ControladorMetricas() {
        manejadorBinario = new ManejadorArchivoBinario<>();
        cargarMetricas();
    }

    private void cargarMetricas() {
        ArrayList<Metrica> datos = manejadorBinario.cargar(filename);

        if (datos == null) {
            listaMetricas = new ArrayList<>();
            return;
        }

        listaMetricas = datos;
    }

    private void guardarMetricas() {
        manejadorBinario.guardar(filename, listaMetricas);
    }

    public Metrica registrarMetrica(Usuario usuario, TipoMetrica tipo, double valor, double valorSecundario) {
        if (!(usuario instanceof Cliente cliente)) return null;
        Metrica metrica = new Metrica(
                usuario.getCorreo(),
                tipo,
                valor,
                valorSecundario
        );

        listaMetricas.add(metrica);
        guardarMetricas();
        cliente.getListaMetricas().add(metrica);

        return metrica;
    }


    public ArrayList<Metrica> obtenerMetricasPorUsuario(String correo) {
        ArrayList<Metrica> resultado = new ArrayList<>();

        for (Metrica metrica : listaMetricas) {
            if (metrica.getCorreoCliente().equals(correo)) resultado.add(metrica);
        }
        return resultado;
    }

    public ArrayList<Metrica> obtenerMetricasPorTipo(String correo, TipoMetrica tipo) {
        ArrayList<Metrica> resultado = new ArrayList<>();
        for (Metrica metrica : listaMetricas) {
            if (metrica.getCorreoCliente().equals(correo) && metrica.getTipoMetrica() == tipo) resultado.add(metrica);
        }

        return resultado;
    }

    public Metrica obtenerUltimaMetrica(String correo, TipoMetrica tipo) {
        Metrica ultima = null;

        for (Metrica metrica : listaMetricas) {
            if (!metrica.getCorreoCliente().equals(correo)) continue;
            if (metrica.getTipoMetrica() != tipo) continue;
            if (ultima == null) {
                ultima = metrica;
                continue;
            }

            if (metrica.getFechaHora().isAfter(ultima.getFechaHora())) ultima = metrica;
        }

        return ultima;
    }

    public ArrayList<Metrica> obtenerMetricasEntreFechas(String correo, LocalDateTime inicio, LocalDateTime fin) {
        ArrayList<Metrica> resultado = new ArrayList<>();

        for (Metrica metrica : listaMetricas) {
            if (!metrica.getCorreoCliente().equals(correo)) continue;
            LocalDateTime fecha = metrica.getFechaHora();

            boolean despuesDeInicio = fecha.isAfter(inicio) || fecha.isEqual(inicio);
            boolean antesDeFin = fecha.isBefore(fin) || fecha.isEqual(fin);

            if (despuesDeInicio && antesDeFin) resultado.add(metrica);
        }

        return resultado;
    }
}