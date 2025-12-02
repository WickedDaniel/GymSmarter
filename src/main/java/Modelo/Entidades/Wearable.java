package Modelo.Entidades;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Wearable implements Serializable {
    protected String ID;
    protected String descripcion;

    public Wearable(String ID, String descripcion) {
        this.ID = ID;
        this.descripcion = descripcion;
    }

    public String getID() {
        return ID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public abstract ArrayList<Metrica> monitorear(String correoCliente);

    public abstract String getTipo();

    @Override
    public String toString() {
        return getTipo() + " [ID: " + ID + "] - " + descripcion;
    }
}