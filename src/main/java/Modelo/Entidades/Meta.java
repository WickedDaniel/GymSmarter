package Modelo.Entidades;

import Modelo.Enumeraciones.EstadoMeta;
import Modelo.Enumeraciones.TipoMeta;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Meta implements Serializable {
    private int idMeta;
    private String idUsuario;
    private TipoMeta tipoMeta;
    private double valorObjetivo;
    private LocalDate fechaInicio;
    private EstadoMeta estado;

    public Meta(int idMeta, String idUsuario, TipoMeta tipoMeta, double valorObjetivo) {
        this.idMeta = idMeta;
        this.idUsuario = idUsuario;
        this.tipoMeta = tipoMeta;
        this.valorObjetivo = valorObjetivo;
        this.fechaInicio = LocalDate.now();
        this.estado = EstadoMeta.ACTIVA;
    }

    public int getIdMeta() {
        return idMeta;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public TipoMeta getTipoMeta() {
        return tipoMeta;
    }

    public double getValorObjetivo() {
        return valorObjetivo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public EstadoMeta getEstado() {
        return estado;
    }

    public void setEstado(EstadoMeta estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String inicio = fechaInicio.format(formato);
        return  "Meta: " + tipoMeta +
                "\nObjetivo: " + valorObjetivo +
                "\nPeriodo: " + inicio +
                "\nEstado: " + estado;
    }
}
