package Modelo.Entidades;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente extends Usuario implements Serializable {

    private ArrayList<Meta> listaMetas;
    private ArrayList<Wearable> listaWearables;
    private ArrayList<Alerta> listaAlertas;
    private ArrayList<Recomendacion> listaRecomendaciones;
    private ArrayList<Metrica> listaMetricas;
    private ArrayList<Notificacion>  listaNotificaciones;

    public Cliente() {
        inicializarListas();
    }

    public Cliente(String nombre, String apellidos, String correo, String contrasena) {
        super(nombre, apellidos, correo, contrasena);
        inicializarListas();
    }

    private void inicializarListas() {
        listaMetas = new ArrayList<>();
        listaWearables = new ArrayList<>();
        listaAlertas = new ArrayList<>();
        listaRecomendaciones = new ArrayList<>();
        listaMetricas = new ArrayList<>();
        listaNotificaciones = new ArrayList<>();
    }

    public ArrayList<Meta> getListaMetas() {
        return listaMetas;
    }

    public void setListaMetas(ArrayList<Meta> listaMetas) {
        this.listaMetas = listaMetas;
    }

    public ArrayList<Wearable> getListaWearables() {
        return listaWearables;
    }

    public void setListaWearables(ArrayList<Wearable> listaWearables) {
        this.listaWearables = listaWearables;
    }

    public ArrayList<Alerta> getListaAlertas() {
        return listaAlertas;
    }

    public void setListaAlertas(ArrayList<Alerta> listaAlertas) {
        this.listaAlertas = listaAlertas;
    }

    public ArrayList<Recomendacion> getListaRecomendaciones() {
        return listaRecomendaciones;
    }

    public void setListaRecomendaciones(ArrayList<Recomendacion> listaRecomendaciones) {
        this.listaRecomendaciones = listaRecomendaciones;
    }

    public ArrayList<Metrica> getListaMetricas() {
        return listaMetricas;
    }

    public void setListaMetricas(ArrayList<Metrica> listaMetricas) {
        this.listaMetricas = listaMetricas;
    }

    public ArrayList<Notificacion> getListaNotificaciones() {
        return listaNotificaciones;
    }

    public void limpiarNotificaciones() {
        listaNotificaciones.clear();
    }

    @Override
    public String toString() {
        return getNombre() + " " + getApellidos() + " (ID: " + getCorreo() + ")";
    }

}
