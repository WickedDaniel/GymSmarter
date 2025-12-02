package Modelo.Entidades;

import Modelo.Enumeraciones.TipoAlerta;
import Modelo.Enumeraciones.TipoMetrica;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Alerta implements Serializable {
    private int idAlerta;
    private String correoCliente;
    private TipoMetrica tipoMetrica;
    private double valorMedido;
    private TipoAlerta tipoAlerta;
    private LocalDateTime fechaHora;

    public Alerta(int idAlerta, String correoUsuario, TipoMetrica tipoMetrica, double valorMedido, TipoAlerta tipoAlerta, LocalDateTime fechaHora) {
        this.idAlerta = idAlerta;
        this.correoCliente = correoUsuario;
        this.tipoMetrica = tipoMetrica;
        this.valorMedido = valorMedido;
        this.tipoAlerta = tipoAlerta;
        this.fechaHora = fechaHora;
    }

    public int getIdAlerta() { return idAlerta; }
    public String getCorreoCliente() { return correoCliente; }
    public TipoMetrica getTipoMetrica() { return tipoMetrica; }
    public double getValorMedido() { return valorMedido; }
    public TipoAlerta getTipoAlerta() { return tipoAlerta; }
    public LocalDateTime getFechaHora() { return fechaHora; }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = fechaHora.format(formato);

        return  "Alerta emitida: " + fechaFormateada + "\n" +
                "Cliente: " + correoCliente + "\n" +
                "Métrica: " + tipoMetrica + " (" + valorMedido + ")\n" +
                "Tipo de alerta: " + tipoAlerta;
    }


}
