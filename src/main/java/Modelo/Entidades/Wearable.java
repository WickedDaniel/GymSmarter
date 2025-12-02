package Modelo.Entidades;

import java.io.Serializable;

public class Wearable implements Serializable {
    private String ID;
    private String tipo;
    private String descripcion;

    public Wearable(String identificador, String tipo, String descripcion) {
        this.ID = identificador;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public String getID() {
        return ID;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
