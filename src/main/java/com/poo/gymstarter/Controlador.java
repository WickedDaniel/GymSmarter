package com.poo.gymstarter;

import Controlador.*;
import Modelo.Entidades.*;
import Modelo.Enumeraciones.TipoAlerta;
import Modelo.Enumeraciones.TipoMeta;
import Modelo.Enumeraciones.TipoMetrica;
import Modelo.Enumeraciones.TipoWearable;
import Modelo.Excepciones.CredencialesInvalidasException;
import Modelo.Excepciones.DispositivoNoEncontradoException;
import Modelo.Excepciones.UsuarioNoEncontradoException;
import Modelo.Excepciones.UsuarioYaExisteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.CheckBox;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controlador implements Initializable {
    public static ControlAplicacion controlAplicacion;
    // Paneles padre
    public AnchorPane profesional;
    public AnchorPane acceso;
    public AnchorPane cliente;

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

    // Panel Clientes
    public Button PCwearables;
    public Button PCrecomendaciones;
    public Button PCalertas;
    public Button PCmetricas;
    public Button PCmetas;
    public Button PCnotificaciones;
    public Button PCperfilusuario;
    public Button PCcerrarsesion;

    public AnchorPane pcMenu;
    public AnchorPane pcMetas;
    public AnchorPane pcAlerta;
    public AnchorPane pcSmartWear;
    public AnchorPane pcMetricas;
    public AnchorPane pcRecomendaciones;
    public AnchorPane pcNotificaciones;
    public AnchorPane pcPerfil;

    public AnchorPane pcAgregarWearable;
    public AnchorPane pcAgregarMeta;

    public TextField PCidwearable;
    public ChoiceBox<TipoWearable> PCwearableselection;
    public ListView<Wearable> PClistawearables;
    private ObservableList<Wearable> listaWearables = FXCollections.observableArrayList();

    public ListView<Recomendacion> PClistarecomendaciones;
    public TextArea PCrecomendacioncontenido;
    private ObservableList<Recomendacion> listaRecomendacionesCliente = FXCollections.observableArrayList();

    public ListView<Alerta> PClistaalertas;
    private ObservableList<Alerta> listaAlertasCliente = FXCollections.observableArrayList();

    public ListView<Metrica> PClistametricas;
    public ChoiceBox<TipoMetrica> PCfiltrometricas;
    private ObservableList<Metrica> listaMetricasCliente = FXCollections.observableArrayList();

    public ListView<Meta> PClistametas;
    public ChoiceBox<TipoMeta> PCmetaselection;
    public Spinner<Double> PCobjetivoselection;
    private ObservableList<Meta> listaMetasCliente = FXCollections.observableArrayList();

    public CheckBox puedeverFrecuencia;
    public CheckBox puedeverGlucosa;
    public CheckBox puedeverActividad;
    public CheckBox puedeverCalidadSueno;
    public CheckBox puedeverVariabilidad;


    private void cambiarPanelPadre(AnchorPane panelSeleccionado) {
        profesional.setVisible(false);
        acceso.setVisible(false);
        cliente.setVisible(false);

        panelSeleccionado.setVisible(true);
    }

    private void cambiarPanelCliente(AnchorPane panelSeleccionado) {
        pcMenu.setVisible(false);
        pcMetas.setVisible(false);
        pcAlerta.setVisible(false);
        pcSmartWear.setVisible(false);
        pcMetricas.setVisible(false);
        pcRecomendaciones.setVisible(false);
        pcNotificaciones.setVisible(false);
        pcPerfil.setVisible(false);

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

    private void filtrarMetricas(TipoMetrica filtro) {
        if (filtro == null) {
            listaMetricasCliente.setAll(listaMetricasExterna);
            return;
        }

        ArrayList<Metrica> filtradas = new ArrayList<>();

        for (Metrica metrica : listaMetricasExterna) {
            if (metrica.getTipoMetrica() == filtro) filtradas.add(metrica);
        }
        listaMetricasCliente.setAll(filtradas);
    }

    @FXML
    private void regresarAlMenuProfesional(){
        cambiarPanelProfesional(pspMenu);
    }

    @FXML
    private void regresarAlMenuCliente(){
        cambiarPanelCliente(pcMenu);
    }

    @FXML
    private void regresarAlMenuAcceso(){
        cambiarPanelAcceso(paMenu);
    }

    private ArrayList<Wearable> listaExternaWearables = new ArrayList<>();
    private ArrayList<Recomendacion> listaExternaRecomendaciones = new ArrayList<>();
    private ArrayList<Alerta> listaExternaAlertas = new ArrayList<>();
    private ArrayList<Metrica> listaMetricasExterna = new ArrayList<>();
    private ArrayList<Meta> listaExternaMetas = new ArrayList<>();

    @FXML
    private void eliminarWearable() {
        Wearable seleccionado = PClistawearables.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            System.out.println("Debe seleccionar un wearable para eliminar.");
            return;
        }

        try {
            controlAplicacion.getControladorDispositivos().eliminarWearable(
                    controlAplicacion.getUsuarioActual(),
                    seleccionado.getID()
            );
        } catch (DispositivoNoEncontradoException e) {
            mostrarError("Error al eliminar Wearable", e.getMessage());
        }
        actualizarListasCliente();
        controlAplicacion.getControladorAutenticacion().guardarUsuarios();
    }
    @FXML
    private void agregarWearable() {
        String id = PCidwearable.getText().trim();
        if (id.isEmpty()) {
            System.out.println("Debe ingresar un ID para el wearable.");
            return;
        }

        TipoWearable tipo = PCwearableselection.getValue();
        if (tipo == null) {
            System.out.println("Debe seleccionar un tipo de wearable.");
            return;
        }

        Wearable nuevo = null;

        switch (tipo) {
            case Anillo:
                nuevo = new Anillo(id, "Anillo inteligente orientado al monitoreo pasivo.");
                break;

            case Brazalete:
                nuevo = new Brazalete(id, "Brazalete inteligente diseñado para seguimiento continuo de actividad física.");
                break;

            case Reloj:
                nuevo = new Reloj(id, "Reloj inteligente de monitoreo avanzado.");
                break;
        }

        System.out.println("Wearable creado:");
        System.out.println("ID = " + nuevo.getID());
        System.out.println("Descripción = " + nuevo.getDescripcion());

        controlAplicacion.getControladorDispositivos().registrarWearable(
                controlAplicacion.getUsuarioActual(),
                nuevo
        );
        ocultarAgregarWearable();
        PCidwearable.clear();
        PCwearableselection.setValue(null);
        controlAplicacion.getControladorAutenticacion().guardarUsuarios();
        actualizarListasCliente();
    }

    @FXML
    private void agregarMeta() {
        TipoMeta tipo = PCmetaselection.getValue();
        if (tipo == null) {
            System.out.println("Debe seleccionar un tipo de meta.");
            return;
        }

        double objetivo = PCobjetivoselection.getValue();
        if (objetivo <= 0) {
            System.out.println("El objetivo debe ser mayor a 0.");
            return;
        }

        controlAplicacion.getControladorMetas().registrarMeta(controlAplicacion.getUsuarioActual(), tipo, objetivo);
        ocultarAgregarMeta();
        PCmetaselection.setValue(null);
        PCobjetivoselection.getValueFactory().setValue((double) 0);
        actualizarListasCliente();
        controlAplicacion.getControladorAutenticacion().guardarUsuarios();
    }


    public void actualizarListasCliente() {
        Cliente cliente = (Cliente) controlAplicacion.getUsuarioActual();
        listaWearables.setAll(cliente.getListaWearables());
        listaRecomendacionesCliente.setAll(cliente.getListaRecomendaciones());
        listaAlertasCliente.setAll(cliente.getListaAlertas());
        listaMetricasCliente.setAll(cliente.getListaMetricas());
        listaMetasCliente.setAll(cliente.getListaMetas());
    }


    @FXML
    private void mostrarAgregarWearable() {
        pcAgregarWearable.visibleProperty().set(true);
    }

    @FXML
    private void ocultarAgregarWearable() {
        pcAgregarWearable.visibleProperty().set(false);
    }

    @FXML
    private void mostrarAgregarMeta() {
        pcAgregarMeta.visibleProperty().set(true);
    }

    @FXML
    private void ocultarAgregarMeta() {
        pcAgregarMeta.visibleProperty().set(false);
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public TextField PSPnombre;
    public TextField PSPcorreo;
    public TextField PSPapellidos;
    private void cargarPerfilProfesional() {
        if (!(controlAplicacion.getUsuarioActual() instanceof Profesional usuario)) {
            return;
        }
        PSPnombre.setText(usuario.getNombre());
        PSPcorreo.setText(usuario.getCorreo());
        PSPapellidos.setText(usuario.getApellidos());
    }

    public TextField PCnombre;
    public TextField PCcorreo;
    public TextField PCapellidos;
    private void cargarPerfilCliente(){
        if (!(controlAplicacion.getUsuarioActual() instanceof Cliente usuario)) {
            return;
        }
        PCnombre.setText(usuario.getNombre());
        PCcorreo.setText(usuario.getCorreo());
        PCapellidos.setText(usuario.getApellidos());
    }

    public Label clienteGreet;
    public Label profesionalGreet;

    public TextField PAemail;
    public TextField PAcontrasena;

    @FXML
    private void iniciarSesion() {
        ControladorAutenticacion currentAuth = controlAplicacion.getControladorAutenticacion();
        Usuario currentUser = null;
        try {
            currentUser = currentAuth.iniciarSesion(PAemail.getText(), PAcontrasena.getText());
        } catch (CredencialesInvalidasException e) {
            mostrarError("Credenciales inválidas", e.getMessage());
            return;
        } catch (UsuarioNoEncontradoException e) {
            mostrarError("Usuario no encontrado", e.getMessage());
            return;
        }
        PAemail.setText(null);
        PAcontrasena.setText(null);
        controlAplicacion.setUsuarioActual(currentUser);
        if (currentUser instanceof Cliente) {
            cambiarPanelCliente(pcMenu);
            cambiarPanelPadre(cliente);
            clienteGreet.setText("Hola, "+currentUser.getNombre()+"!");
            cargarPerfilCliente();
            actualizarListasCliente();
        }
        if (currentUser instanceof Profesional) {
            cambiarPanelCliente(pspMenu);
            cambiarPanelPadre(profesional);
            profesionalGreet.setText("Hola, "+currentUser.getNombre()+"!");
            cargarPerfilProfesional();
        }
    }

    @FXML
    private void cerrarSesion() {
        controlAplicacion.setUsuarioActual(null);
        listaMetasCliente.clear();
        listaMetricasCliente.clear();
        listaAlertasCliente.clear();
        listaWearables.clear();
        listaRecomendacionesCliente.clear();
        cambiarPanelPadre(acceso);
        cambiarPanelAcceso(paMenu);
    }

    public TextField registroNombre;
    public TextField registroApellidos;
    public TextField registroEmail;
    public TextField registroContrasena;
    public TextField registroContrasenaConfirmacion;
    public CheckBox checkboxTerminos;

    @FXML
    private void registrarUsuario() {
        ControladorAutenticacion currentAuth = controlAplicacion.getControladorAutenticacion();
        Usuario newUser = null;
        try{
            if (!checkboxTerminos.isSelected()) {
                mostrarError("Alerta!", "Debe aceptar los terminos y condiciones");
                return;
            }
            if (!Objects.equals(registroContrasena.getText(), registroContrasenaConfirmacion.getText())) {
                mostrarError("Registro fallido", "Ambas contraseñas deben ser identicas");
                return;
            }
            newUser = currentAuth.registrarCliente(registroNombre.getText(), registroApellidos.getText(), registroEmail.getText(), registroContrasena.getText());
        } catch (UsuarioYaExisteException | CredencialesInvalidasException e) {
            mostrarError("Registro fallido", e.getMessage());
            return;
        }
        if (newUser == null) {
            return;
        }
        controlAplicacion.setUsuarioActual(newUser);
        if (newUser instanceof Cliente) {
            cambiarPanelCliente(pcMenu);
            cambiarPanelPadre(cliente);
            clienteGreet.setText("Hola, "+newUser.getNombre()+"!");
            cargarPerfilCliente();
            actualizarListasCliente();
        }
        if (newUser instanceof Profesional) {
            cambiarPanelCliente(pspMenu);
            cambiarPanelPadre(profesional);
            profesionalGreet.setText("Hola, "+newUser.getNombre()+"!");
            cargarPerfilProfesional();
        }
        registroNombre.setText("");
        registroApellidos.setText("");
        registroEmail.setText("");
        registroContrasena.setText("");
        registroContrasenaConfirmacion.setText("");
        checkboxTerminos.setSelected(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ocultarAgregarWearable();
        ocultarAgregarMeta();
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

        PCwearables.setOnAction(e -> cambiarPanelCliente(pcSmartWear));
        PCrecomendaciones.setOnAction(e -> cambiarPanelCliente(pcRecomendaciones));
        PCalertas.setOnAction(e -> cambiarPanelCliente(pcAlerta));
        PCmetricas.setOnAction(e -> cambiarPanelCliente(pcMetricas));
        PCmetas.setOnAction(e -> cambiarPanelCliente(pcMetas));
        PCnotificaciones.setOnAction(e -> cambiarPanelCliente(pcNotificaciones));
        PCperfilusuario.setOnAction(e -> cambiarPanelCliente(pcPerfil));

        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(
                0.0,
                Double.MAX_VALUE,
                0.0,
                0.1
        );
        PCobjetivoselection.setValueFactory(valueFactory);

        PCwearableselection.getItems().addAll(TipoWearable.values());
        PCfiltrometricas.getItems().add(null);
        PCfiltrometricas.getItems().addAll(TipoMetrica.values());
        PCfiltrometricas.setValue(null);
        PCmetaselection.getItems().addAll(TipoMeta.values());
        PCmetaselection.setValue(null);

        PClistawearables.setItems(listaWearables);
        PClistarecomendaciones.setItems(listaRecomendacionesCliente);
        PClistaalertas.setItems(listaAlertasCliente);
        PClistametricas.setItems(listaMetricasCliente);
        PClistametas.setItems(listaMetasCliente);

        cambiarPanelPadre(acceso);
        regresarAlMenuAcceso();

        PClistawearables.setCellFactory(list -> new ListCell<Wearable>() {
            private final Label label = new Label();

            {
                label.setWrapText(true);      // Permite que el texto se parta en varias líneas
                label.setMaxWidth(300);       // AJÚSTALO al tamaño de tu ListView
            }

            @Override
            protected void updateItem(Wearable item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    label.setText(item.toString()); // o item.getDescripcion()
                    setGraphic(label);
                }
            }
        });

        PClistarecomendaciones.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldValue, nueva) -> {
                    if (nueva != null) {
                        PCrecomendacioncontenido.setText(nueva.getContenido());
                    }
                }
        );
        PClistarecomendaciones.setCellFactory(list -> new ListCell<Recomendacion>() {

            private final Label label = new Label();

            {
                label.setWrapText(true);
            }

            @Override
            protected void updateItem(Recomendacion item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    label.setText(item.toString());
                    setGraphic(label);
                }
            }
        });

        PClistaalertas.setCellFactory(list -> new ListCell<Alerta>() {

            @Override
            protected void updateItem(Alerta item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });

        PClistametricas.setCellFactory(list -> new ListCell<Metrica>() {
            @Override
            protected void updateItem(Metrica item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.toString());
                }
            }
        });
        PCfiltrometricas.getSelectionModel().selectedItemProperty().addListener((obs, old, nueva) -> {
            filtrarMetricas(nueva);
        });

        PClistametas.setCellFactory(list -> new ListCell<Meta>() {
            @Override
            protected void updateItem(Meta item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.toString());
                }
            }
        });

    }
}
