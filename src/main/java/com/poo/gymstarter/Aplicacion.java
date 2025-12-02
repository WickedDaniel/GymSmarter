package com.poo.gymstarter;
import Controlador.*;
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
    }
}
