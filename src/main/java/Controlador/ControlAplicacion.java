package Controlador;

import Modelo.Entidades.Usuario;

public class ControlAplicacion {
    private final ControladorAutenticacion controladorAutenticacion;
    private final ControladorDispositivos controladorDispositivos;
    private final ControladorMetricas controladorMetricas;
    private final ControladorAlertas controladorAlertas;
    private final ControladorMetas controladorMetas;
    private final ControladorReportes controladorReportes;
    private final ControladorConstantes controladorConstantes;  // ← AHORA ES ACCESIBLE

    private Usuario usuarioActual;

    public ControlAplicacion() {
        controladorConstantes = new ControladorConstantes();

        controladorAutenticacion = new ControladorAutenticacion();
        controladorDispositivos = new ControladorDispositivos();
        controladorMetricas = new ControladorMetricas();
        controladorMetas = new ControladorMetas();
        controladorReportes = new ControladorReportes();
        controladorAlertas = new ControladorAlertas(controladorConstantes.getListaConstantes());

        usuarioActual = null;
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

    public ControladorConstantes getControladorConstantes() {  // ← NUEVO GETTER
        return controladorConstantes;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
}