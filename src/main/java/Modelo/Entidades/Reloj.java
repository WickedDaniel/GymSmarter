package Modelo.Entidades;

import Modelo.Enumeraciones.TipoMetrica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Reloj extends Wearable implements Serializable  {

    public Reloj(String ID, String descripcion) {
        super(ID, descripcion);
    }

    @Override
    public String getTipo() {
        return "Reloj";
    }

    @Override
    public ArrayList<Metrica> monitorear(String correoCliente) {
        ArrayList<Metrica> metricas = new ArrayList<>();

        metricas.add(generarCalidadSueno(correoCliente));
        metricas.add(generarVariabilidadCardiaca(correoCliente));
        return metricas;
    }

    private static Metrica generarCalidadSueno(String correoCliente) {
        Random random = new Random();

        // Rango normal: 60-85 puntos
        double calidad = 70 + (random.nextDouble() * 15) - 7.5; // Entre 62.5 y 77.5 puntos

        // 15% de probabilidad de mala calidad
        if (random.nextDouble() < 0.15) {
            if (random.nextBoolean()) {
                calidad = 25 + random.nextDouble() * 30; // Mala: 25-55
            } else {
                calidad = 85 + random.nextDouble() * 10; // Excelente: 85-95
            }
        }

        return new Metrica(correoCliente, TipoMetrica.CALIDAD_SUENO,
                Math.round(calidad * 10.0) / 10.0, 0);
    }

    private static Metrica generarVariabilidadCardiaca(String correoCliente) {
        Random random = new Random();

        // Rango normal: 50-120 ms
        double variabilidad = 70 + (random.nextDouble() * 40) - 20; // Entre 50 y 110 ms

        // 10% de probabilidad de valor anormal
        if (random.nextDouble() < 0.1) {
            if (random.nextBoolean()) {
                variabilidad = 20 + random.nextDouble() * 25; // Bajo: 20-45
            } else {
                variabilidad = 130 + random.nextDouble() * 50; // Alto: 130-180
            }
        }

        return new Metrica(correoCliente, TipoMetrica.VARIABILIDAD_CARDIACA,
                Math.round(variabilidad * 10.0) / 10.0, 0);
    }
}