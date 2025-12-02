package Modelo.Entidades;

import Modelo.Enumeraciones.TipoMetrica;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Metrica implements Serializable {
    private String correoCliente;
    private TipoMetrica tipoMetrica;
    private double valorPrimario;
    private double valorSecundario; // opcional
    private LocalDateTime fechaHora;

    public Metrica() {}

    public Metrica(String correoCliente, TipoMetrica tipoMetrica, double valorPrimario, double valorSecundario) {
        this.correoCliente = correoCliente;
        this.tipoMetrica = tipoMetrica;
        this.valorPrimario = valorPrimario;
        this.valorSecundario = valorSecundario;
        this.fechaHora = LocalDateTime.now();
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public TipoMetrica getTipoMetrica() {
        return tipoMetrica;
    }

    public void setTipoMetrica(TipoMetrica tipoMetrica) {
        this.tipoMetrica = tipoMetrica;
    }

    public double getValorPrimario() {
        return valorPrimario;
    }

    public void setValorPrimario(double valorPrimario) {
        this.valorPrimario = valorPrimario;
    }

    public double getValorSecundario() {
        return valorSecundario;
    }

    public void setValorSecundario(double valorSecundario) {
        this.valorSecundario = valorSecundario;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = fechaHora.format(formato);

        String valores = (valorSecundario != 0)
                ? valorPrimario + " / " + valorSecundario
                : String.valueOf(valorPrimario);

        return "Métrica: " + tipoMetrica +
                "\nValor: " + valores +
                "\nFecha: " + fechaFormateada;
    }

}
