package Modelo.Entidades;

import Modelo.Enumeraciones.TipoMetrica;
import java.util.ArrayList;
import java.util.Random;

public class Anillo extends Wearable {

    public Anillo(String ID, String descripcion) {
        super(ID, descripcion);
    }

    @Override
    public String getTipo() {
        return "Anillo";
    }

    @Override
    public ArrayList<Metrica> monitorear(String correoCliente) {
        ArrayList<Metrica> metricas = new ArrayList<>();

        metricas.add(generarFrecuenciaCardiaca(correoCliente));
        metricas.add(generarGlucosa(correoCliente));
        return metricas;
    }

    private static Metrica generarFrecuenciaCardiaca(String correoCliente) {
        Random random = new Random();

        double frecuencia = 70 + (random.nextDouble() * 30) - 15; // Entre 55 y 85 bpm

        if (random.nextDouble() < 0.1) {
            if (random.nextBoolean()) frecuencia = 40 + random.nextDouble() * 15; // Bajo: 40-55;
            frecuencia = 100 + random.nextDouble() * 25; // Alto: 100-125
        }

        return new Metrica(correoCliente, TipoMetrica.FRECUENCIA_CARDIACA, Math.round(frecuencia * 10.0) / 10.0, 0);
    }

    private static Metrica generarGlucosa(String correoCliente) {
        Random random = new Random();

        double glucosa = 80 + (random.nextDouble() * 20) - 10;
        if (random.nextDouble() < 0.15) {
            if (random.nextBoolean()) {
                glucosa = 50 + random.nextDouble() * 15;
            } else {
                glucosa = 110 + random.nextDouble() * 60;
            }
        }

        return new Metrica(correoCliente, TipoMetrica.GLUCOSA, Math.round(glucosa * 10.0) / 10.0, 0);
    }
}