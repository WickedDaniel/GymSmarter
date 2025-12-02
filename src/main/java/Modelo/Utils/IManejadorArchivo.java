package Modelo.Utils;

import java.io.IOException;

public interface IManejadorArchivo<T> {
    int guardar(String ruta, T objeto) throws IOException;
    T cargar(String ruta) throws IOException, ClassNotFoundException;
}
