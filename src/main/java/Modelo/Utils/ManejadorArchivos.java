package Modelo.Utils;

public abstract class ManejadorArchivos implements IManejadorArchivo<Object> {
    public static final int EXITO = 0;
    public static final int ARCHIVO_NO_UBICADO = 1;
    public static final int ERROR_ARCHIVO = 2;

}
