package Modelo.Entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Recomendacion implements Serializable {

    private int idRecomendacion;
    private String correoCliente;
    private String correoProfesional;
    private String contenido;
    private LocalDateTime fecha;

    public Recomendacion() {}

    public Recomendacion(int idRecomendacion, String correoCliente, String correoProfesional, String contenido) {
        this.idRecomendacion = idRecomendacion;
        this.correoCliente = correoCliente;
        this.correoProfesional = correoProfesional;
        this.contenido = contenido;
        this.fecha = LocalDateTime.now();
    }

    public int getIdRecomendacion() {
        return idRecomendacion;
    }

    public void setIdRecomendacion(int idRecomendacion) {
        this.idRecomendacion = idRecomendacion;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getCorreoProfesional() {
        return correoProfesional;
    }

    public void setCorreoProfesional(String correoProfesional) {
        this.correoProfesional = correoProfesional;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = fecha.format(formato);
        return "Recomendacion emitida: " + fechaFormateada + "\n"+
                "Emitida por: " + correoProfesional;
    }
}
