package Controlador;

import Modelo.Entidades.ConstanteReferencial;
import Modelo.Enumeraciones.TipoMetrica;
import Modelo.Utils.ManejadorArchivoBinario;

import java.util.ArrayList;

public class ControladorConstantes {
    private ArrayList<ConstanteReferencial> listaConstantes;
    private final String filename = "Data/constantes.dat";
    private final ManejadorArchivoBinario<ArrayList<ConstanteReferencial>> manejador = new ManejadorArchivoBinario<>();

    public ControladorConstantes() {
        cargarConstantes();
    }

    private void cargarConstantes() {
        ArrayList<ConstanteReferencial> cargadas = manejador.cargar(filename);

        if (cargadas == null || cargadas.isEmpty()) {
            listaConstantes = new ArrayList<>();
            crearConstantesPredefinidas();
            guardarConstantes();
            System.out.println("Constantes referenciales creadas por primera vez");
        } else {
            listaConstantes = cargadas;
            System.out.println("Constantes referenciales cargadas desde archivo (" + listaConstantes.size() + " tipos)");
        }
    }

    private void guardarConstantes() {
        int resultado = manejador.guardar(filename, listaConstantes);
        if (resultado == 0) {
            System.out.println("Constantes guardadas exitosamente");
        } else {
            System.err.println("Error al guardar constantes");
        }
    }

    private void crearConstantesPredefinidas() {
        // Frecuencia cardíaca en reposo (bpm)
        // Fuente: American Heart Association
        listaConstantes.add(new ConstanteReferencial(
                TipoMetrica.FRECUENCIA_CARDIACA,
                60, 100,         // Normal: 60-100 bpm
                40, 130,         // Crítico: <40 o >130 bpm
                "bpm"
        ));

        // Variabilidad cardíaca (HRV) en milisegundos
        // Fuente: Shaffer & Ginsberg (2017)
        listaConstantes.add(new ConstanteReferencial(
                TipoMetrica.VARIABILIDAD_CARDIACA,
                50, 120,         // Normal: 50-120 ms
                20, 200,         // Crítico: <20 o >200 ms
                "ms"
        ));

        // Glucosa en ayunas (mg/dL)
        // Fuente: American Diabetes Association (2024)
        listaConstantes.add(new ConstanteReferencial(
                TipoMetrica.GLUCOSA,
                70, 99,          // Normal: 70-99 mg/dL
                54, 180,         // Crítico: <54 o >180 mg/dL
                "mg/dL"
        ));

        // Actividad física (pasos diarios)
        // Fuente: CDC Physical Activity Guidelines
        listaConstantes.add(new ConstanteReferencial(
                TipoMetrica.ACTIVIDAD_FISICA,
                5000, 12000,     // Normal: 5000-12000 pasos
                1000, 25000,     // Crítico: <1000 o >25000 pasos
                "pasos"
        ));

        // Calidad de sueño (escala 0-100)
        // Fuente: Pittsburgh Sleep Quality Index adaptado
        listaConstantes.add(new ConstanteReferencial(
                TipoMetrica.CALIDAD_SUENO,
                60, 85,          // Normal: 60-85 puntos
                30, 95,          // Crítico: <30 o >95 puntos
                "puntos"
        ));
    }

    public ArrayList<ConstanteReferencial> getListaConstantes() {
        return listaConstantes;
    }

    public ConstanteReferencial buscarConstante(TipoMetrica tipo) {
        for (ConstanteReferencial ref : listaConstantes) {
            if (ref.getTipoMetrica() == tipo) {
                return ref;
            }
        }
        return null;
    }

    public boolean actualizarConstante(TipoMetrica tipo,
                                       double limiteInferiorNormal,
                                       double limiteSuperiorNormal,
                                       double limiteInferiorCritico,
                                       double limiteSuperiorCritico) {
        ConstanteReferencial constante = buscarConstante(tipo);

        if (constante == null) {
            return false;
        }

        // Validar que los rangos sean lógicos
        if (!validarRangos(limiteInferiorCritico, limiteInferiorNormal,
                limiteSuperiorNormal, limiteSuperiorCritico)) {
            System.err.println("✗ Rangos inválidos: deben cumplir limInfCritico < limInfNormal < limSupNormal < limSupCritico");
            return false;
        }

        constante.setLimiteInferiorNormal(limiteInferiorNormal);
        constante.setLimiteSuperiorNormal(limiteSuperiorNormal);
        constante.setLimiteInferiorCritico(limiteInferiorCritico);
        constante.setLimiteSuperiorCritico(limiteSuperiorCritico);

        guardarConstantes();
        System.out.println("✓ Constante actualizada: " + tipo);
        return true;
    }

    private boolean validarRangos(double limInfCritico, double limInfNormal,
                                  double limSupNormal, double limSupCritico) {
        return limInfCritico < limInfNormal &&
                limInfNormal < limSupNormal &&
                limSupNormal < limSupCritico;
    }

    public boolean agregarConstante(ConstanteReferencial nuevaConstante) {
        // Verificar que no exista ya
        if (buscarConstante(nuevaConstante.getTipoMetrica()) != null) {
            System.err.println("✗ Ya existe una constante para " + nuevaConstante.getTipoMetrica());
            return false;
        }

        listaConstantes.add(nuevaConstante);
        guardarConstantes();
        System.out.println("✓ Nueva constante agregada: " + nuevaConstante.getTipoMetrica());
        return true;
    }

    public boolean eliminarConstante(TipoMetrica tipo) {
        ConstanteReferencial constante = buscarConstante(tipo);

        if (constante == null) {
            System.err.println("✗ No existe constante para " + tipo);
            return false;
        }

        listaConstantes.remove(constante);
        guardarConstantes();
        System.out.println("✓ Constante eliminada: " + tipo);
        return true;
    }

    public void resetearConstantes() {
        listaConstantes.clear();
        crearConstantesPredefinidas();
        guardarConstantes();
        System.out.println("✓ Constantes reseteadas a valores predefinidos");
    }

    public void mostrarConstantes() {
        System.out.println("\n========== CONSTANTES REFERENCIALES ==========");
        for (ConstanteReferencial constante : listaConstantes) {
            System.out.println(constante);
        }
        System.out.println("==============================================\n");
    }

    public String generarReporte() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("========== REPORTE DE CONSTANTES REFERENCIALES ==========\n\n");

        for (ConstanteReferencial constante : listaConstantes) {
            reporte.append("Tipo: ").append(constante.getTipoMetrica()).append("\n");
            reporte.append("  Unidad: ").append(constante.getUnidad()).append("\n");
            reporte.append("  Rango Normal: ").append(constante.getLimiteInferiorNormal())
                    .append(" - ").append(constante.getLimiteSuperiorNormal()).append("\n");
            reporte.append("  Rango Crítico: ").append(constante.getLimiteInferiorCritico())
                    .append(" - ").append(constante.getLimiteSuperiorCritico()).append("\n");
            reporte.append("  Punto Medio Normal: ").append(String.format("%.2f", constante.calcularPuntoMedioNormal())).append("\n");
            reporte.append("  Amplitud Rango Normal: ").append(String.format("%.2f", constante.calcularRangoNormal())).append("\n");
            reporte.append("\n");
        }

        reporte.append("=========================================================\n");
        return reporte.toString();
    }
}