package Controlador;

import Modelo.Entidades.ConstanteReferencial;
import Modelo.Enumeraciones.TipoMetrica;

import java.util.ArrayList;

public class ControladorConstantes {
    private ArrayList<ConstanteReferencial> listaConstantes;

    public ControladorConstantes() {
        listaConstantes = new ArrayList<>();
        cargarConstantesPredefinidas();
    }

    private void cargarConstantesPredefinidas() {

        // Frecuencia cardíaca – valores basados en literatura clínica
        listaConstantes.add(new ConstanteReferencial(
                TipoMetrica.FRECUENCIA_CARDIACA,
                60, 100,         // normal
                40, 130,         // crítico
                "bpm"
        ));

        // Variabilidad cardíaca (HRV) en ms — valores aproximados
        listaConstantes.add(new ConstanteReferencial(
                TipoMetrica.VARIABILIDAD_CARDIACA,
                50, 120,
                20, 200,
                "ms"
        ));

        // Glucosa en mg/dL (en reposo)
        listaConstantes.add(new ConstanteReferencial(
                TipoMetrica.GLUCOSA,
                70, 99,
                54, 180,
                "mg/dL"
        ));

        // Actividad física — pasos diarios
        listaConstantes.add(new ConstanteReferencial(
                TipoMetrica.ACTIVIDAD_FISICA,
                5000, 12000,      // normal
                1000, 20000,      // crítico
                "pasos"
        ));

        // Calidad de sueño — escala 0–100
        listaConstantes.add(new ConstanteReferencial(
                TipoMetrica.CALIDAD_SUENO,
                60, 85,
                30, 95,
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
}
