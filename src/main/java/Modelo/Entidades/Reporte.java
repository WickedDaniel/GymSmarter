package Modelo.Entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = fechaHora.format(formato);

        int cantidadMetricas = (metricasIncluidas == null) ? 0 : metricasIncluidas.size();

        return "Reporte #" + idReporte + "\n" +
                "Fecha: " + fechaFormateada + "\n" +
                "Cliente: " + correoCliente + "\n" +
                "Métricas incluidas: " + cantidadMetricas;
    }

}
