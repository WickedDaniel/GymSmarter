package Modelo.Entidades;

import java.io.Serializable;
import java.util.ArrayList;

public class Profesional extends Usuario implements Serializable {
    private ArrayList<Reporte> listaReportes;
    private ArrayList<Recomendacion> listaRecomendacionesEmitidas;

    public Profesional() {
        inicializarListas();
    }

    public Profesional(String nombre, String apellidos, String correo, String contrasena) {
        super(nombre, apellidos, correo, contrasena);
        inicializarListas();
    }

    private void inicializarListas() {
        listaReportes = new ArrayList<>();
        listaRecomendacionesEmitidas = new ArrayList<>();
    }

    public ArrayList<Reporte> getListaReportes() {
        return listaReportes;
    }

    public void setListaReportes(ArrayList<Reporte> listaReportes) {
        this.listaReportes = listaReportes;
    }

    public ArrayList<Recomendacion> getListaRecomendacionesEmitidas() {
        return listaRecomendacionesEmitidas;
    }

    public void setListaRecomendacionesEmitidas(ArrayList<Recomendacion> listaRecomendacionesEmitidas) {
        this.listaRecomendacionesEmitidas = listaRecomendacionesEmitidas;
    }
}
