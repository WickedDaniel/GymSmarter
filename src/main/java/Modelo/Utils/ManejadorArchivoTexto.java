package Modelo.Utils;

import java.io.*;
import java.util.ArrayList;

public class ManejadorArchivoTexto {

    public int escribirArchivo(String ruta, ArrayList<String> lineas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {

            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }

            bw.flush();
            return ManejadorArchivos.EXITO;

        } catch (IOException e) {
            return ManejadorArchivos.ERROR_ARCHIVO;
        }
    }

    public ArrayList<String> cargarArchivo(String ruta) {
        ArrayList<String> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            String linea = br.readLine();
            while (linea != null) {
                lineas.add(linea);
                linea = br.readLine();
            }

        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }

        return lineas;
    }
}
