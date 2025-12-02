package Modelo.Entidades;

import Modelo.Enumeraciones.TipoMetrica;
import java.io.Serializable;
import java.util.ArrayList;

public class PermisoCompartir implements Serializable {
    private String correoCliente;
    private String correoProfesional;
    private ArrayList<TipoMetrica> metricasPermitidas;

    public PermisoCompartir(String correoCliente, String correoProfesional) {
        this.correoCliente = correoCliente;
        this.correoProfesional = correoProfesional;
        this.metricasPermitidas = new ArrayList<>();
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public String getCorreoProfesional() {
        return correoProfesional;
    }

    public ArrayList<TipoMetrica> getMetricasPermitidas() {
        return metricasPermitidas;
    }

    public void setMetricasPermitidas(ArrayList<TipoMetrica> metricasPermitidas) {
        this.metricasPermitidas = metricasPermitidas;
    }
}
