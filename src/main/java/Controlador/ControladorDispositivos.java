package Controlador;

import Modelo.Entidades.Cliente;
import Modelo.Entidades.Usuario;
import Modelo.Entidades.Wearable;
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

    private void guardarWearables() {
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
        if (wearableEncontrado == null) throw new DispositivoNoEncontradoException();

        cliente.getListaWearables().remove(wearableEncontrado);
        listaWearablesGlobal.remove(wearableEncontrado);
        guardarWearables();
    }

    public ArrayList<Wearable> obtenerWearablesDeUsuario(Usuario usuario) {
        if (!esCliente(usuario)) return new ArrayList<>();
        return ((Cliente) usuario).getListaWearables();
    }
}
