package Controlador;

import Modelo.Entidades.Cliente;
import Modelo.Entidades.Notificacion;

public class ControladorNotificaciones {

    public void emitirNotificacionACliente(Cliente cliente, String titulo, String descripcion) {
        if (cliente == null) return;

        Notificacion n = new Notificacion(titulo, descripcion);
        cliente.getListaNotificaciones().add(n);

        System.out.println("✓ Notificación emitida a " + cliente.getCorreo());
    }

    public void limpiarNotificacionesDeCliente(Cliente cliente) {
        if (cliente == null) return;

        cliente.getListaNotificaciones().clear();

        System.out.println("✓ Notificaciones eliminadas para " + cliente.getCorreo());
    }
}
