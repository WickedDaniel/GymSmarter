package com.poo.gymstarter;
import Controlador.*;
import Modelo.Excepciones.CredencialesInvalidasException;
import Modelo.Excepciones.UsuarioYaExisteException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Aplicacion extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Controlador.controlAplicacion = new ControlAplicacion();
        FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("Aplicacion.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();


        try {
            Controlador.controlAplicacion.getControladorAutenticacion().registrarProfesional("ADMIN", "ADMIN", "admin@gym.com", "Admin123!");
        } catch (UsuarioYaExisteException | CredencialesInvalidasException e) {
            System.out.println(e.getMessage());
        }
    }
}
