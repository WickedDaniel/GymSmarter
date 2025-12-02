package Controlador;

import Modelo.Entidades.Alerta;
import Modelo.Entidades.Cliente;
import Modelo.Entidades.ConstanteReferencial;
import Modelo.Entidades.Metrica;
import Modelo.Enumeraciones.TipoAlerta;
import Modelo.Enumeraciones.TipoMetrica;
import Modelo.Utils.ManejadorArchivoBinario;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ControladorAlertas {
    private final String RUTA_ARCHIVO = "Data/alertas.dat";
    private ArrayList<Alerta> listaAlertas;
    private final ArrayList<ConstanteReferencial> listaConstantes;
    private final ManejadorArchivoBinario<ArrayList<Alerta>> manejador = new ManejadorArchivoBinario<>();

    private int contadorAlertas = 1;

    public ControladorAlertas(ArrayList<ConstanteReferencial> listaConstantes) {
        this.listaConstantes = listaConstantes;
        cargarAlertas();
    }

    private void cargarAlertas() {
        ArrayList<Alerta> datos = manejador.cargar(RUTA_ARCHIVO);
        if (datos == null) {
            listaAlertas = new ArrayList<>();
            return;
        }
        listaAlertas = datos;

        if (!listaAlertas.isEmpty()) contadorAlertas = listaAlertas.getLast().getIdAlerta() + 1;
    }

    private void guardarAlertas() {
        manejador.guardar(RUTA_ARCHIVO, listaAlertas);
    }

    public Alerta procesarMetrica(Metrica metrica, Cliente cliente) {
        ConstanteReferencial constanteReferencial = buscarConstante(metrica.getTipoMetrica());
        if (constanteReferencial == null) return null;

        double valor = metrica.getValorPrimario();

        TipoAlerta tipo = calcularTipoAlerta(constanteReferencial, valor);
        if (tipo == null) return null;

        Alerta alerta = new Alerta(contadorAlertas++, cliente.getCorreo(), metrica.getTipoMetrica(), valor, tipo, LocalDateTime.now());

        listaAlertas.add(alerta);
        guardarAlertas();
        cliente.getListaAlertas().add(alerta);

        return alerta;
    }

    private ConstanteReferencial buscarConstante(TipoMetrica tipo) {
        for (ConstanteReferencial constanteReferencial : listaConstantes) if (constanteReferencial.getTipoMetrica() == tipo) return constanteReferencial;
        return null;
    }

    private TipoAlerta calcularTipoAlerta(ConstanteReferencial constanteReferencial, double valor) {
        if (valor < constanteReferencial.getLimiteInferiorCritico() || valor > constanteReferencial.getLimiteSuperiorCritico()) return TipoAlerta.CRITICA;
        if (valor < constanteReferencial.getLimiteInferiorNormal() || valor > constanteReferencial.getLimiteSuperiorNormal()) return TipoAlerta.ADVERTENCIA;

        return null;
    }

    public ArrayList<Alerta> obtenerAlertasUsuario(String correo) {
        ArrayList<Alerta> resultado = new ArrayList<>();

        for (Alerta alerta : listaAlertas) {
            if (alerta.getCorreoUsuario().equals(correo)) resultado.add(alerta);
        }

        return resultado;
    }
}
