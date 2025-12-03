package Modelo.Entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notificacion implements Serializable{

    private String titulo;
    private String descripcion;
    private LocalDateTime fecha;

    public Notificacion(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = LocalDateTime.now();
    }

    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public LocalDateTime getFecha() { return fecha; }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return titulo + "\n" +
                descripcion + "\n" +
                "Fecha: " + fecha.format(formato);
    }
}

