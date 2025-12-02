package Modelo.Entidades;

import Modelo.Enumeraciones.TipoAlerta;
import Modelo.Enumeraciones.TipoMetrica;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Alerta implements Serializable {
    private int idAlerta;
    private String correoUsuario;
    private TipoMetrica tipoMetrica;
    private double valorMedido;
    private TipoAlerta tipoAlerta;
    private LocalDateTime fechaHora;

    public Alerta(int idAlerta, String correoUsuario, TipoMetrica tipoMetrica, double valorMedido, TipoAlerta tipoAlerta, LocalDateTime fechaHora) {
        this.idAlerta = idAlerta;
        this.correoUsuario = correoUsuario;
        this.tipoMetrica = tipoMetrica;
        this.valorMedido = valorMedido;
        this.tipoAlerta = tipoAlerta;
        this.fechaHora = fechaHora;
    }

    public int getIdAlerta() { return idAlerta; }
    public String getCorreoUsuario() { return correoUsuario; }
    public TipoMetrica getTipoMetrica() { return tipoMetrica; }
    public double getValorMedido() { return valorMedido; }
    public TipoAlerta getTipoAlerta() { return tipoAlerta; }
    public LocalDateTime getFechaHora() { return fechaHora; }
}
