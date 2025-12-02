package Controlador;

import Modelo.Entidades.Cliente;
import Modelo.Entidades.Meta;
import Modelo.Entidades.Usuario;
import Modelo.Enumeraciones.TipoMeta;
import Modelo.Enumeraciones.EstadoMeta;
import Modelo.Utils.ManejadorArchivoBinario;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ControladorMetas {
    private ArrayList<Meta> listaMetas;
    private final String RUTA_ARCHIVO = "Data/metas.dat";

    private ManejadorArchivoBinario<ArrayList<Meta>> manejadorBinario = new ManejadorArchivoBinario<>();
    private int contadorMetas = 1;

    public ControladorMetas() {
        cargarMetas();
    }

    private void cargarMetas() {
        ArrayList<Meta> datos = manejadorBinario.cargar(RUTA_ARCHIVO);

        if (datos == null) {
            listaMetas = new ArrayList<>();
            return;
        }
        listaMetas = datos;

        int maxID = 0;
        for (Meta meta : listaMetas) {
            if (meta.getIdMeta() > maxID) maxID = meta.getIdMeta();
        }
        contadorMetas = maxID + 1;
    }

    private void guardarMetas() {
        manejadorBinario.guardar(RUTA_ARCHIVO, listaMetas);
    }

    public Meta registrarMeta(Usuario usuario, TipoMeta tipoMeta, double valorObjetivo) {
        if (!(usuario instanceof Cliente cliente)) return null;

        Meta meta = new Meta(contadorMetas, usuario.getCorreo(), tipoMeta, valorObjetivo);

        contadorMetas++;
        listaMetas.add(meta);
        cliente.getListaMetas().add(meta);
        guardarMetas();

        return meta;
    }


    public ArrayList<Meta> obtenerMetasPorUsuario(String idUsuario) {
        ArrayList<Meta> resultado = new ArrayList<>();

        for (Meta meta : listaMetas) {
            if (meta.getIdUsuario().equals(idUsuario)) resultado.add(meta);
        }
        return resultado;
    }

    public Meta buscarMetaPorID(int idMeta) {
        for (Meta meta : listaMetas) {
            if (meta.getIdMeta() == idMeta) return meta;
        }
        return null;
    }

    public boolean cambiarEstadoMeta(int idMeta, EstadoMeta nuevoEstado) {
        Meta meta = buscarMetaPorID(idMeta);
        if (meta == null) return false;
        meta.setEstado(nuevoEstado);
        guardarMetas();
        return true;
    }

    public ArrayList<Meta> obtenerTodasLasMetas() {
        return listaMetas;
    }
}
