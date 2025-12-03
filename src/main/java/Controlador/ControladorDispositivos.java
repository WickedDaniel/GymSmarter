package Controlador;

import Modelo.Entidades.*;
import Modelo.Excepciones.DispositivoNoEncontradoException;
import Modelo.Utils.ManejadorArchivoBinario;

import java.util.ArrayList;

public class ControladorDispositivos {
    private final String filename = "Data/wearables.dat";
    private ArrayList<Wearable> listaWearablesGlobal;
    private ManejadorArchivoBinario<ArrayList<Wearable>> manejadorBinario = new ManejadorArchivoBinario<>();

    public ControladorDispositivos() {
        cargarWearables();
    }

    private void cargarWearables() {
        ArrayList<Wearable> datosCargados = manejadorBinario.cargar(filename);

        if (datosCargados == null) {
            listaWearablesGlobal = new ArrayList<>();
            return;
        }

        listaWearablesGlobal = datosCargados;
    }

    public void guardarWearables() {
        manejadorBinario.guardar(filename, listaWearablesGlobal);
    }

    private boolean esCliente(Usuario usuario) {
        return usuario instanceof Cliente;
    }

    public void registrarWearable(Usuario usuario, Wearable nuevoWearable) {
        if (!esCliente(usuario)) return;

        Cliente cliente = (Cliente) usuario;
        cliente.getListaWearables().add(nuevoWearable);
        listaWearablesGlobal.add(nuevoWearable);
        guardarWearables();
    }

    public void eliminarWearable(Usuario usuario, String wearableID) throws DispositivoNoEncontradoException {
        if (!esCliente(usuario)) return;

        Cliente cliente = (Cliente) usuario;
        Wearable wearableEncontrado = null;

        for (Wearable wearable : cliente.getListaWearables()) {
            if (wearable.getID().equals(wearableID)) {
                wearableEncontrado = wearable;
                break;
            }
        }
        if (wearableEncontrado == null) throw new DispositivoNoEncontradoException("Wearable no encontrado");

        cliente.getListaWearables().remove(wearableEncontrado);
        listaWearablesGlobal.remove(wearableEncontrado);
        guardarWearables();
    }

    public ArrayList<Wearable> obtenerWearablesDeUsuario(Usuario usuario) {
        if (!esCliente(usuario)) return new ArrayList<>();
        return ((Cliente) usuario).getListaWearables();
    }

    public ArrayList<Metrica> sincronizarDatos(Usuario usuario, String wearableID, ControladorMetricas controladorMetricas, ControladorAlertas controladorAlertas) throws DispositivoNoEncontradoException {

        if (!esCliente(usuario)) {
            throw new DispositivoNoEncontradoException("Solo los clientes pueden sincronizar wearables");
        }

        Cliente cliente = (Cliente) usuario;
        Wearable wearable = null;
        for (Wearable w : cliente.getListaWearables()) {
            if (w.getID().equals(wearableID)) {
                wearable = w;
                break;
            }
        }

        if (wearable == null) {
            throw new DispositivoNoEncontradoException("Wearable con ID " + wearableID + " no encontrado");
        }

        ArrayList<Metrica> metricasGeneradas = wearable.monitorear(cliente.getCorreo());
        for (Metrica metrica : metricasGeneradas) {
            Metrica metricaRegistrada = controladorMetricas.registrarMetrica(usuario, metrica.getTipoMetrica(), metrica.getValorPrimario(), metrica.getValorSecundario());

            if (metricaRegistrada != null) {
                controladorAlertas.procesarMetrica(metricaRegistrada, cliente);
            }
        }

        return metricasGeneradas;
    }

    public ArrayList<Metrica> sincronizarDatos(Usuario usuario, String wearableID, ControladorMetricas controladorMetricas, ControladorAlertas controladorAlertas, ControladorNotificaciones controladorNotificaciones) throws DispositivoNoEncontradoException {

        if (!esCliente(usuario)) {
            throw new DispositivoNoEncontradoException("Solo los clientes pueden sincronizar wearables");
        }

        Cliente cliente = (Cliente) usuario;
        Wearable wearable = null;
        for (Wearable w : cliente.getListaWearables()) {
            if (w.getID().equals(wearableID)) {
                wearable = w;
                break;
            }
        }

        if (wearable == null) {
            throw new DispositivoNoEncontradoException("Wearable con ID " + wearableID + " no encontrado");
        }

        ArrayList<Metrica> metricasGeneradas = wearable.monitorear(cliente.getCorreo());
        for (Metrica metrica : metricasGeneradas) {
            Metrica metricaRegistrada = controladorMetricas.registrarMetrica(usuario, metrica.getTipoMetrica(), metrica.getValorPrimario(), metrica.getValorSecundario());

            if (metricaRegistrada != null) {
                Alerta isAlerta = controladorAlertas.procesarMetrica(metricaRegistrada, cliente);
                if (isAlerta != null) {
                    controladorNotificaciones.emitirNotificacionACliente(cliente, "Nueva alerta", "Tiene una nueva alerta disponible\n"+isAlerta.getTipoAlerta());
                };
            }
        }

        return metricasGeneradas;
    }

    public int sincronizarTodosLosWearables(Usuario usuario, ControladorMetricas controladorMetricas, ControladorAlertas controladorAlertas) {
        if (!esCliente(usuario)) return 0;

        Cliente cliente = (Cliente) usuario;
        int totalMetricas = 0;

        for (Wearable wearable : cliente.getListaWearables()) {
            try {
                ArrayList<Metrica> metricas = sincronizarDatos(usuario, wearable.getID(), controladorMetricas, controladorAlertas);
                totalMetricas += metricas.size();

                System.out.println("Sincronizado " + wearable.getTipo() + " [" + wearable.getID() + "]: " + metricas.size() + " métricas");

            } catch (DispositivoNoEncontradoException e) {
                System.err.println("Error al sincronizar " + wearable.getID() + ": " + e.getMessage());
            }
        }

        return totalMetricas;
    }
}