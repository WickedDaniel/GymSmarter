package Modelo.Entidades;

import Modelo.Enumeraciones.TipoMetrica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Brazalete extends Wearable implements Serializable {
    public Brazalete(String ID, String descripcion) {
        super(ID, descripcion);
    }

    @Override
    public String getTipo() {
        return "Brazalete";
    }

    @Override
    public ArrayList<Metrica> monitorear(String correoCliente) {
        ArrayList<Metrica> metricas = new ArrayList<>();

        metricas.add(generarActividadFisica(correoCliente));
        return metricas;
    }

    private static Metrica generarActividadFisica(String correoCliente) {
        Random random = new Random();
        double pasos = 7000 + (random.nextDouble() * 4000) - 2000;
        if (random.nextDouble() < 0.15) {
            if (random.nextBoolean()) pasos = 500 + random.nextDouble() * 3000;
            pasos = 15000 + random.nextDouble() * 5000;
        }

        return new Metrica(correoCliente, TipoMetrica.ACTIVIDAD_FISICA, Math.round(pasos), 0);
    }
}