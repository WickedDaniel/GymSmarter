package Controlador;

import Modelo.Entidades.Usuario;

import javax.sound.sampled.Control;

public class ControlAplicacion {
    private final ControladorAutenticacion controladorAutenticacion;
    private final ControladorDispositivos controladorDispositivos;
    private final ControladorMetricas controladorMetricas;
    private final ControladorAlertas controladorAlertas;
    private final ControladorMetas controladorMetas;
    private final ControladorReportes controladorReportes;
    private final ControladorPermisosCompartir controladorPermisosCompartir;
    private final ControladorConstantes controladorConstantes;
    private final ControladorRecomendaciones controladorRecomendaciones;
    private final ControladorNotificaciones controladorNotificaciones;

    private Usuario usuarioActual;

    public ControlAplicacion() {
        controladorConstantes = new ControladorConstantes();
        controladorAutenticacion = new ControladorAutenticacion();
        controladorPermisosCompartir = new ControladorPermisosCompartir();
        controladorDispositivos = new ControladorDispositivos();
        controladorMetricas = new ControladorMetricas();
        controladorMetas = new ControladorMetas();
        controladorReportes = new ControladorReportes();
        controladorAlertas = new ControladorAlertas(controladorConstantes.getListaConstantes());
        controladorRecomendaciones = new ControladorRecomendaciones();
        controladorNotificaciones = new ControladorNotificaciones();

        usuarioActual = null;
    }

    public ControladorRecomendaciones  getControladorRecomendaciones() {
        return controladorRecomendaciones;
    }

    public ControladorPermisosCompartir getControladorPermisosCompartir() {
        return controladorPermisosCompartir;
    }

    public ControladorAutenticacion getControladorAutenticacion() {
        return controladorAutenticacion;
    }

    public ControladorDispositivos getControladorDispositivos() {
        return controladorDispositivos;
    }

    public ControladorMetricas getControladorMetricas() {
        return controladorMetricas;
    }

    public ControladorAlertas getControladorAlertas() {
        return controladorAlertas;
    }

    public ControladorMetas getControladorMetas() {
        return controladorMetas;
    }

    public ControladorReportes getControladorReportes() {
        return controladorReportes;
    }

    public ControladorConstantes getControladorConstantes() {
        return controladorConstantes;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public ControladorNotificaciones getControladorNotificaciones() {
        return controladorNotificaciones;
    }
}