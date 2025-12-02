package com.poo.gymstarter;

import Controlador.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controlador implements Initializable {
    public static ControlAplicacion controlAplicacion;
    // Paneles padre
    public AnchorPane profesional;
    public AnchorPane acceso;

    // Panel profesional
    public Button PSclientes;
    public Button PSrecomendaciones;
    public Button PSsistemaalertas;
    public Button PSperfilusuario;
    public Button PSreportes;
    public Button PScerrarsesion;

    public AnchorPane pspRecomendaciones;
    public AnchorPane pspClientes;
    public AnchorPane pspMenu;
    public AnchorPane pspReportes;
    public AnchorPane pspAlertas;
    public AnchorPane pspPerfil;

    // Panel Acceso
    public AnchorPane paMenu;
    public AnchorPane paIniciarSesion;
    public AnchorPane paRegistro;
    public AnchorPane paRestablecerPaso1;
    public AnchorPane paRestablecerPaso2;

    public Button PAiniciosesion;
    public Button PAregistrarse;
    public Hyperlink PAirapaso1;
    public Hyperlink PAiraregistro;
    public Hyperlink PAirainiciosesion;
    public Hyperlink PAirainiciosesionB;
    public Button PAirapaso2;

    private void cambiarPanelPadre(AnchorPane panelSeleccionado) {
        profesional.setVisible(false);
        acceso.setVisible(false);

        panelSeleccionado.setVisible(true);
    }


    private void cambiarPanelProfesional(AnchorPane panelSeleccionado) {
        pspRecomendaciones.setVisible(false);
        pspClientes.setVisible(false);
        pspMenu.setVisible(false);
        pspReportes.setVisible(false);
        pspAlertas.setVisible(false);
        pspPerfil.setVisible(false);

        panelSeleccionado.setVisible(true);
    }

    private void cambiarPanelAcceso(AnchorPane panelSeleccionado) {
        paMenu.setVisible(false);
        paIniciarSesion.setVisible(false);
        paRegistro.setVisible(false);
        paRestablecerPaso1.setVisible(false);
        paRestablecerPaso2.setVisible(false);

        panelSeleccionado.setVisible(true);
    }


    @FXML
    private void regresarAlMenuProfesional(){
        cambiarPanelProfesional(pspMenu);
    }

    @FXML
    private void regresarAlMenuAcceso(){
        cambiarPanelAcceso(paMenu);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PSclientes.setOnAction(e -> cambiarPanelProfesional(pspClientes));
        PSrecomendaciones.setOnAction(e -> cambiarPanelProfesional(pspRecomendaciones));
        PSsistemaalertas.setOnAction(e -> cambiarPanelProfesional(pspAlertas));
        PSperfilusuario.setOnAction(e -> cambiarPanelProfesional(pspPerfil));
        PSreportes.setOnAction(e -> cambiarPanelProfesional(pspReportes));

        PAiniciosesion.setOnAction(e -> cambiarPanelAcceso(paIniciarSesion));
        PAregistrarse.setOnAction(e -> cambiarPanelAcceso(paRegistro));
        PAirapaso1.setOnAction(e -> cambiarPanelAcceso(paRestablecerPaso1));
        PAiraregistro.setOnAction(e -> cambiarPanelAcceso(paRegistro));
        PAirainiciosesion.setOnAction(e -> cambiarPanelAcceso(paIniciarSesion));
        PAirainiciosesionB.setOnAction(e -> cambiarPanelAcceso(paIniciarSesion));
        PAirapaso2.setOnAction(e -> cambiarPanelAcceso(paRestablecerPaso2));

        cambiarPanelPadre(acceso);
        cambiarPanelAcceso(paMenu);
    }
}
