package Modelo.Entidades;

import Modelo.Enumeraciones.TipoMetrica;
import java.io.Serializable;

public class ConstanteReferencial implements Serializable {

    private TipoMetrica tipoMetrica;
    private double limiteInferiorNormal;
    private double limiteSuperiorNormal;
    private double limiteInferiorCritico;
    private double limiteSuperiorCritico;
    private String unidad;

    public ConstanteReferencial() {
    }

    public ConstanteReferencial(TipoMetrica tipoMetrica, double limiteInferiorNormal, double limiteSuperiorNormal, double limiteInferiorCritico, double limiteSuperiorCritico, String unidad) {
        this.tipoMetrica = tipoMetrica;
        this.limiteInferiorNormal = limiteInferiorNormal;
        this.limiteSuperiorNormal = limiteSuperiorNormal;
        this.limiteInferiorCritico = limiteInferiorCritico;
        this.limiteSuperiorCritico = limiteSuperiorCritico;
        this.unidad = unidad;
    }

    // Getters y Setters
    public TipoMetrica getTipoMetrica() {
        return tipoMetrica;
    }

    public void setTipoMetrica(TipoMetrica tipoMetrica) {
        this.tipoMetrica = tipoMetrica;
    }

    public double getLimiteInferiorNormal() {
        return limiteInferiorNormal;
    }

    public void setLimiteInferiorNormal(double limiteInferiorNormal) {
        this.limiteInferiorNormal = limiteInferiorNormal;
    }

    public double getLimiteSuperiorNormal() {
        return limiteSuperiorNormal;
    }

    public void setLimiteSuperiorNormal(double limiteSuperiorNormal) {
        this.limiteSuperiorNormal = limiteSuperiorNormal;
    }

    public double getLimiteInferiorCritico() {
        return limiteInferiorCritico;
    }

    public void setLimiteInferiorCritico(double limiteInferiorCritico) {
        this.limiteInferiorCritico = limiteInferiorCritico;
    }

    public double getLimiteSuperiorCritico() {
        return limiteSuperiorCritico;
    }

    public void setLimiteSuperiorCritico(double limiteSuperiorCritico) {
        this.limiteSuperiorCritico = limiteSuperiorCritico;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public double calcularPuntoMedioNormal() {
        return (limiteInferiorNormal + limiteSuperiorNormal) / 2.0;
    }

    public double calcularRangoNormal() {
        return limiteSuperiorNormal - limiteInferiorNormal;
    }

    public double calcularRangoCritico() {
        return limiteSuperiorCritico - limiteInferiorCritico;
    }

    public boolean estaEnRangoNormal(double valor) {
        return valor >= limiteInferiorNormal && valor <= limiteSuperiorNormal;
    }

    public boolean estaEnRangoCritico(double valor) {
        return (valor >= limiteInferiorCritico && valor < limiteInferiorNormal) ||
                (valor > limiteSuperiorNormal && valor <= limiteSuperiorCritico);
    }

    public boolean estaFueraDeTodosLosRangos(double valor) {
        return valor < limiteInferiorCritico || valor > limiteSuperiorCritico;
    }

    @Override
    public String toString() {
        return String.format("%s: Normal[%.2f-%.2f] Crítico[%.2f-%.2f] %s",
                tipoMetrica, limiteInferiorNormal, limiteSuperiorNormal,
                limiteInferiorCritico, limiteSuperiorCritico, unidad);
    }
}