package Modelo.Utils;

import java.io.*;

public class ManejadorArchivoBinario<T> implements IManejadorArchivo<T> {

    @Override
    public int guardar(String ruta, T objeto) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(objeto);
            return ManejadorArchivos.EXITO;
        } catch (IOException e) {
            return ManejadorArchivos.ERROR_ARCHIVO;
        }
    }

    @Override
    public T cargar(String ruta) {
        File archivo = new File(ruta);
        if (!archivo.exists())
            return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
