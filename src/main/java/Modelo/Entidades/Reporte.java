package Modelo.Entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reporte implements Serializable {
    private int idReporte;
    private String correoCliente;
    private String correoProfesional;
    private LocalDateTime fechaHora;
    private ArrayList<Metrica> metricasIncluidas;
    private String resumen;

    public Reporte(int idReporte, String correoUsuario, String correoProfesional, ArrayList<Metrica> metricasIncluidas, String resumen) {
        this.idReporte = idReporte;
        this.correoCliente = correoUsuario;
        this.correoProfesional = correoProfesional;
        this.metricasIncluidas = metricasIncluidas;
        this.resumen = resumen;
        this.fechaHora = LocalDateTime.now();
    }

    public Reporte() {
    }

    public int getIdReporte() {
        return idReporte;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public String getCorreoProfesional() {
        return correoProfesional;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public ArrayList<Metrica> getMetricasIncluidas() {
        return metricasIncluidas;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public void setCorreoProfesional(String correoProfesional) {
        this.correoProfesional = correoProfesional;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setMetricasIncluidas(ArrayList<Metrica> metricasIncluidas) {
        this.metricasIncluidas = metricasIncluidas;
    }
}
