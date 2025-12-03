package com.poo.gymstarter;

import Controlador.*;
import Modelo.Entidades.*;
import Modelo.Enumeraciones.TipoAlerta;
import Modelo.Enumeraciones.TipoMeta;
import Modelo.Enumeraciones.TipoMetrica;
import Modelo.Enumeraciones.TipoWearable;
import Modelo.Excepciones.*;
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

import java.lang.reflect.Array;
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

    public ChoiceBox<Cliente> clienteSeleccionadoClientes;
    public ChoiceBox<Cliente> clienteSeleccionadoRecomendaciones;
    public ChoiceBox<Cliente> clienteSeleccionadoReportes;
    public ChoiceBox<ConstanteReferencial> constantesRegistradas;

    public ListView<Metrica> listaActividadFisica;
    private ObservableList<Metrica> listaActividadFisicaCliente = FXCollections.observableArrayList();
    public ListView<Metrica> listaSignosVitales;
    private ObservableList<Metrica> listaSignosVitalesCliente = FXCollections.observableArrayList();
    public ListView<Metrica> listaCalidadSueno;
    private ObservableList<Metrica> listaCalidadSuenoCliente = FXCollections.observableArrayList();

    public ListView<Recomendacion> recomendacionesEmitidasACliente;
    private ObservableList<Recomendacion> listaRecomendacionesEmitidasACliente = FXCollections.observableArrayList();

    public ListView<Reporte> reportesGenerados;
    private ObservableList<Reporte> listaReportesGenerados = FXCollections.observableArrayList();

    private void cargarPermisosCliente() {
        if (!(controlAplicacion.getUsuarioActual() instanceof Cliente cliente)) {
            return;
        }



        Profesional profesionalSeleccionado = PCprofesionalseleccionado.getValue();
        if (profesionalSeleccionado == null) {
            return;
        }

        String correoProfesional = profesionalSeleccionado.getCorreo();

        ControladorPermisosCompartir controladorPermisos = controlAplicacion.getControladorPermisosCompartir();
        PermisoCompartir permiso = controladorPermisos.obtenerPermiso(
                cliente.getCorreo(),
                correoProfesional
        );

        if (permiso == null) {
            puedeverFrecuencia.setSelected(false);
            puedeverGlucosa.setSelected(false);
            puedeverActividad.setSelected(false);
            puedeverCalidadSueno.setSelected(false);
            puedeverVariabilidad.setSelected(false);
            return;
        }

        ArrayList<TipoMetrica> permitidas = permiso.getMetricasPermitidas();

        puedeverFrecuencia.setSelected(permitidas.contains(TipoMetrica.FRECUENCIA_CARDIACA));
        puedeverGlucosa.setSelected(permitidas.contains(TipoMetrica.GLUCOSA));
        puedeverActividad.setSelected(permitidas.contains(TipoMetrica.ACTIVIDAD_FISICA));
        puedeverCalidadSueno.setSelected(permitidas.contains(TipoMetrica.CALIDAD_SUENO));
        puedeverVariabilidad.setSelected(permitidas.contains(TipoMetrica.VARIABILIDAD_CARDIACA));
    }


    private void actualizarPermisos() {
        if (!(controlAplicacion.getUsuarioActual() instanceof Cliente cliente)) {
            return;
        }

        Profesional profesionalSeleccionado = PCprofesionalseleccionado.getValue();
        if (profesionalSeleccionado == null) {
            System.err.println("No hay profesional seleccionado");
            return;
        }

        String correoProfesional = profesionalSeleccionado.getCorreo();

        ArrayList<TipoMetrica> metricasPermitidas = new ArrayList<>();

        if (puedeverFrecuencia.isSelected()) {
            metricasPermitidas.add(TipoMetrica.FRECUENCIA_CARDIACA);
        }

        if (puedeverGlucosa.isSelected()) {
            metricasPermitidas.add(TipoMetrica.GLUCOSA);
        }

        if (puedeverActividad.isSelected()) {
            metricasPermitidas.add(TipoMetrica.ACTIVIDAD_FISICA);
        }

        if (puedeverCalidadSueno.isSelected()) {
            metricasPermitidas.add(TipoMetrica.CALIDAD_SUENO);
        }

        if (puedeverVariabilidad.isSelected()) {
            metricasPermitidas.add(TipoMetrica.VARIABILIDAD_CARDIACA);
        }

        ControladorPermisosCompartir controladorPermisos = controlAplicacion.getControladorPermisosCompartir();
        controladorPermisos.asignarPermisos(
                cliente.getCorreo(),
                correoProfesional,
                metricasPermitidas
        );

        System.out.println("Permisos actualizados para " + cliente.getCorreo());
        System.out.println("Profesional: " + correoProfesional);
        System.out.println("Métricas compartidas: " + metricasPermitidas.size());
    }

    private void cargarClientesEnChoiceBoxes() {

        // Limpia las listas actuales
        ArrayList<Cliente> clientes = new ArrayList<>();
        for (Usuario usuario: controlAplicacion.getControladorAutenticacion().getListaUsuarios()) {
            if (!(usuario instanceof Cliente)) continue;
            clientes.add((Cliente) usuario);
        }
        if (clientes.isEmpty()) return;
        clienteSeleccionadoClientes.getItems().clear();
        clienteSeleccionadoRecomendaciones.getItems().clear();
        clienteSeleccionadoReportes.getItems().clear();
        // Carga los clientes
        clienteSeleccionadoClientes.getItems().addAll(clientes);
        clienteSeleccionadoRecomendaciones.getItems().addAll(clientes);
        clienteSeleccionadoReportes.getItems().addAll(clientes);
    }

    private void cargarConstantesEnChoiceBox() {
        ArrayList<ConstanteReferencial> constantes = controlAplicacion.getControladorConstantes().getListaConstantes();
        if (constantes == null || constantes.isEmpty()) return;
        constantesRegistradas.getItems().clear();
        constantesRegistradas.getItems().addAll(constantes);
    }


    private void configurarListenersPermisos() {
        // Listener para cada checkbox
        puedeverFrecuencia.selectedProperty().addListener((obs, oldVal, newVal) -> {
            actualizarPermisos();
        });

        puedeverGlucosa.selectedProperty().addListener((obs, oldVal, newVal) -> {
            actualizarPermisos();
        });

        puedeverActividad.selectedProperty().addListener((obs, oldVal, newVal) -> {
            actualizarPermisos();
        });

        puedeverCalidadSueno.selectedProperty().addListener((obs, oldVal, newVal) -> {
            actualizarPermisos();
        });

        puedeverVariabilidad.selectedProperty().addListener((obs, oldVal, newVal) -> {
            actualizarPermisos();
        });
    }

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
            listaMetricasCliente.setAll(((Cliente)  controlAplicacion.getUsuarioActual()).getListaMetricas());
            return;
        }

        ArrayList<Metrica> filtradas = new ArrayList<>();

        for (Metrica metrica : ((Cliente)  controlAplicacion.getUsuarioActual()).getListaMetricas()) {
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

    public ChoiceBox<Profesional> PCprofesionalseleccionado;
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

        cargarProfesionalesDisponibles();
        cargarPermisosCliente();
    }

    public Label clienteGreet;
    public Label profesionalGreet;

    public TextField PAemail;
    public TextField PAcontrasena;

    private void cargarProfesionalesDisponibles() {
        ArrayList<Usuario> todosLosUsuarios = controlAplicacion.getControladorAutenticacion().getListaUsuarios();
        ArrayList<Profesional> profesionales = new ArrayList<>();

        for (Usuario usuario : todosLosUsuarios) {
            if (usuario instanceof Profesional profesional) {
                profesionales.add(profesional);
            }
        }

        PCprofesionalseleccionado.getItems().clear();
        PCprofesionalseleccionado.getItems().addAll(profesionales);

        // Seleccionar el primero por defecto
        if (!profesionales.isEmpty()) {
            PCprofesionalseleccionado.setValue(profesionales.get(0));
        }

        // Listener para cuando se cambia el profesional
        PCprofesionalseleccionado.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldProf, newProf) -> {
                    if (newProf != null) {
                        cargarPermisosCliente();
                    }
                }
        );
    }

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
            ActualizarNotificaciones();
        }
        if (currentUser instanceof Profesional) {
            cambiarPanelProfesional(pspMenu);
            cambiarPanelPadre(profesional);
            profesionalGreet.setText("Hola, "+currentUser.getNombre()+"!");
            cargarPerfilProfesional();
            cargarClientesEnChoiceBoxes();
            cargarConstantesEnChoiceBox();
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
            ActualizarNotificaciones();
        }
        if (newUser instanceof Profesional) {
            cambiarPanelProfesional(pspMenu);
            cambiarPanelPadre(profesional);
            profesionalGreet.setText("Hola, "+newUser.getNombre()+"!");
            cargarPerfilProfesional();
            cargarClientesEnChoiceBoxes();
            cargarConstantesEnChoiceBox();
        }
        registroNombre.setText("");
        registroApellidos.setText("");
        registroEmail.setText("");
        registroContrasena.setText("");
        registroContrasenaConfirmacion.setText("");
        checkboxTerminos.setSelected(false);
    }
    public Label noticeMetricasVitales;
    public Label noticeMetricasSueno;
    public Label noticeMetricasFisica;

    private void cargarActividadFisicaCliente(Cliente clienteSeleccionado) {
        listaActividadFisicaCliente.clear();

        if (clienteSeleccionado == null) return;

        boolean permiso = controlAplicacion.getControladorPermisosCompartir().puedeVerMetrica(clienteSeleccionado.getCorreo(), controlAplicacion.getUsuarioActual().getCorreo(), TipoMetrica.ACTIVIDAD_FISICA);

        if (!permiso) {
            noticeMetricasFisica.setText("El cliente no le ha concedido acceso a estas metricas");
            return;
        }
        noticeMetricasFisica.setText("");
        for (Metrica m : clienteSeleccionado.getListaMetricas()) {
            if (m.getTipoMetrica() != TipoMetrica.ACTIVIDAD_FISICA) continue;
            listaActividadFisicaCliente.add(m);
        }
    }

    private void cargarSignosVitalesCliente(Cliente clienteSeleccionado) {
        listaSignosVitalesCliente.clear();
        if (clienteSeleccionado == null) return;

        String correoCliente = clienteSeleccionado.getCorreo();
        String correoProfesional = controlAplicacion.getUsuarioActual().getCorreo();

        boolean puedeHRV = controlAplicacion.getControladorPermisosCompartir().puedeVerMetrica(correoCliente, correoProfesional, TipoMetrica.VARIABILIDAD_CARDIACA);
        boolean puedeFC = controlAplicacion.getControladorPermisosCompartir().puedeVerMetrica(correoCliente, correoProfesional, TipoMetrica.FRECUENCIA_CARDIACA);
        boolean puedeGlucosa = controlAplicacion.getControladorPermisosCompartir().puedeVerMetrica(correoCliente, correoProfesional, TipoMetrica.GLUCOSA);

        if (!puedeHRV && !puedeFC && !puedeGlucosa) {
            noticeMetricasVitales.setText("El cliente no le ha concedido acceso a estas metricas");
            return;
        }
        noticeMetricasVitales.setText("");
        for (Metrica m : clienteSeleccionado.getListaMetricas()) {

            TipoMetrica tipo = m.getTipoMetrica();

            if (tipo == TipoMetrica.VARIABILIDAD_CARDIACA && puedeHRV) {
                listaSignosVitalesCliente.add(m);
            }

            if (tipo == TipoMetrica.FRECUENCIA_CARDIACA && puedeFC) {
                listaSignosVitalesCliente.add(m);
            }

            if (tipo == TipoMetrica.GLUCOSA && puedeGlucosa) {
                listaSignosVitalesCliente.add(m);
            }
        }
    }

    private void cargarCalidadSuenoCliente(Cliente clienteSeleccionado) {

        // 1. Limpiar la lista
        listaCalidadSuenoCliente.clear();

        // 2. Si no hay cliente seleccionado → salir
        if (clienteSeleccionado == null) return;

        // 3. Obtener correos
        String correoCliente = clienteSeleccionado.getCorreo();
        String correoProfesional = controlAplicacion.getUsuarioActual().getCorreo();

        // 4. Verificar permiso
        boolean puedeSueno = controlAplicacion.getControladorPermisosCompartir()
                .puedeVerMetrica(correoCliente, correoProfesional, TipoMetrica.CALIDAD_SUENO);

        // 5. Si NO tiene permiso → nada que cargar
        if (!puedeSueno) {
            noticeMetricasSueno.setText("El cliente no le ha concedido acceso a estas metricas");
            return;
        }
        noticeMetricasSueno.setText("");

        // 6. Cargar solo métricas de CALIDAD_SUENO
        for (Metrica m : clienteSeleccionado.getListaMetricas()) {
            if (m.getTipoMetrica() == TipoMetrica.CALIDAD_SUENO) {
                listaCalidadSuenoCliente.add(m);
            }
        }
    }

    private void cargarRecomendacionesEmitidasACliente(Cliente clienteSeleccionado) {
        listaRecomendacionesEmitidasACliente.clear();
        if (clienteSeleccionado == null) return;

        Usuario actual = controlAplicacion.getUsuarioActual();
        if (!(actual instanceof Profesional profesional)) {
            return;
        }

        String correoCliente = clienteSeleccionado.getCorreo();

        for (Recomendacion recomendacion : profesional.getListaRecomendacionesEmitidas()) {
            if (recomendacion.getCorreoCliente().equals(correoCliente)) {
                listaRecomendacionesEmitidasACliente.add(recomendacion);
            }
        }
    }

    public TextArea recomendacionContenido;
    private void mostrarContenidoRecomendacion(Recomendacion recomendacion) {
        if (recomendacion == null) {
            recomendacionContenido.clear();
            return;
        }

        recomendacionContenido.setText(recomendacion.getContenido());
    }

    private void cargarReportesDeCliente(Cliente clienteSeleccionado) {
        listaReportesGenerados.clear();
        if (clienteSeleccionado == null) return;

        Usuario actual = controlAplicacion.getUsuarioActual();
        if (!(actual instanceof Profesional profesional)) {
            return;
        }

        String correoCliente = clienteSeleccionado.getCorreo();

        for (Reporte r : profesional.getListaReportes()) {
            if (r.getCorreoCliente().equals(correoCliente)) {
                listaReportesGenerados.add(r);
            }
        }
    }


    public TextArea contenidoRecomendacion;
    public Label emitirNotice;
    @FXML
    private void emitirRecomendacion() {
        Cliente cliente = clienteSeleccionadoRecomendaciones.getValue();

        if (cliente == null) {
            mostrarError("Error!", "Seleccione un cliente valido");
            return;
        }
        if (!(controlAplicacion.getUsuarioActual() instanceof Profesional profesional)) {
            return;
        }

        Recomendacion nueva = controlAplicacion.getControladorRecomendaciones().emitirRecomendacion(
                profesional,
                cliente,
                contenidoRecomendacion.getText()
        );
        if (nueva == null) return;
        emitirNotice.setText("Emitida con exito!");
        contenidoRecomendacion.setText("");
        controlAplicacion.getControladorNotificaciones().emitirNotificacionACliente(cliente, "Nueva recomendación", "Tiene una nueva recomendacion disponible.");
        controlAplicacion.getControladorAutenticacion().guardarUsuarios();
        cargarRecomendacionesEmitidasACliente(cliente);
    }

    public TextArea reporteContenido;
    public Label reporteNotice;
    @FXML
    private void generarReporte() {
        Cliente cliente = clienteSeleccionadoReportes.getValue();
        if (cliente == null) {
            mostrarError("Error!", "Seleccione un cliente valido");
            return;
        }
        if (!(controlAplicacion.getUsuarioActual() instanceof Profesional actual)) {
            return;
        }
        PermisoCompartir permisos = controlAplicacion.getControladorPermisosCompartir().obtenerPermiso(
                cliente.getCorreo(),
                actual.getCorreo()
        );
        if (permisos == null || permisos.getMetricasPermitidas().size() <= 0) {
            mostrarError("Error!", "No existe el permiso con el usuario o el usuario no le ha concedido permisos a las metricas");
            return;
        }
        Reporte reporte = null;
        try {
            reporte = controlAplicacion.getControladorReportes().generarReporte(
                    actual,
                    cliente,
                    permisos.getMetricasPermitidas()
            );
        } catch (AccesoDenegadoException e) {
            mostrarError("Error!", e.getMessage());
            return;
        }

        if (reporte == null) return;
        reporteContenido.setText(reporte.getResumen());
        controlAplicacion.getControladorAutenticacion().guardarUsuarios();
        cargarReportesDeCliente(cliente);
    }


    public Spinner<Double> limitecriticoSuperior;
    public Spinner<Double> limitecriticoMenor;
    public Spinner<Double> limitenormalSuperior;
    public Spinner<Double> limitenormalMenor;
    boolean cargandoConstantes = false;
    private void cargarConstanteEnSpinners(ConstanteReferencial c) {
        if (c == null) return;
        cargandoConstantes = true;
        limitecriticoMenor.getValueFactory().setValue(c.getLimiteInferiorCritico());
        limitecriticoSuperior.getValueFactory().setValue(c.getLimiteSuperiorCritico());
        limitenormalMenor.getValueFactory().setValue(c.getLimiteInferiorNormal());
        limitenormalSuperior.getValueFactory().setValue(c.getLimiteSuperiorNormal());
        cargandoConstantes = false;
    }

    private void actualizarConstanteSeleccionada() {
        ConstanteReferencial seleccionada = constantesRegistradas.getValue();
        if (seleccionada == null) return;
        if (cargandoConstantes) return;

        double criticoMenor = limitecriticoMenor.getValue();
        double criticoMayor = limitecriticoSuperior.getValue();
        double normalMenor = limitenormalMenor.getValue();
        double normalMayor = limitenormalSuperior.getValue();

        boolean actualizada = controlAplicacion.getControladorConstantes().actualizarConstante(
                seleccionada.getTipoMetrica(),
                normalMenor,
                normalMayor,
                criticoMenor,
                criticoMayor
        );

        if (!actualizada) {
            mostrarError("Error al actualizar constante", "No se pudo actualizar la constante deseada");
        }
    }

    public ListView<Notificacion> PClistanotificaciones;
    private ObservableList<Notificacion> listaNotificaciones = FXCollections.observableArrayList();

    public void ActualizarNotificaciones() {
        if (!(controlAplicacion.getUsuarioActual() instanceof Cliente actual)) {
            return;
        }
        listaNotificaciones.setAll(actual.getListaNotificaciones());
    }

    @FXML
    public void limpiarNotificaciones() {
        if (!(controlAplicacion.getUsuarioActual() instanceof Cliente actual)) {
            return;
        }
        controlAplicacion.getControladorNotificaciones().limpiarNotificacionesDeCliente(actual);
        listaNotificaciones.clear();
    }

    @FXML
    public void generarSimulacion() {
        Usuario usuario = controlAplicacion.getUsuarioActual();
        if (!(usuario instanceof Cliente cliente)) {
            System.out.println("Debe iniciar sesión como cliente.");
            return;
        }

        ArrayList<Wearable> listaDispositivos = cliente.getListaWearables();
        if (listaDispositivos.isEmpty()) {
            System.out.println("El cliente no tiene dispositivos registrados.");
            return;
        }

        ControladorAlertas controladorAlertas = controlAplicacion.getControladorAlertas();
        ControladorMetricas controladorMetricas = controlAplicacion.getControladorMetricas();
        ControladorDispositivos controladorDispositivos = controlAplicacion.getControladorDispositivos();
        ControladorNotificaciones controladorNotificaciones = controlAplicacion.getControladorNotificaciones();
        for (Wearable wearable : listaDispositivos) {
            try {
                controladorDispositivos.sincronizarDatos(cliente, wearable.getID(), controladorMetricas, controladorAlertas, controladorNotificaciones);
            } catch (DispositivoNoEncontradoException e) {
                mostrarError("Error en la simulacion", e.getMessage());
            }
        }

        System.out.println("---- Simulación completada ----");
        ActualizarNotificaciones();
        actualizarListasCliente();
        controlAplicacion.getControladorAutenticacion().guardarUsuarios();
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
        listaActividadFisica.setItems(listaActividadFisicaCliente);
        listaSignosVitales.setItems(listaSignosVitalesCliente);
        listaCalidadSueno.setItems(listaCalidadSuenoCliente);
        recomendacionesEmitidasACliente.setItems(listaRecomendacionesEmitidasACliente);
        reportesGenerados.setItems(listaReportesGenerados);
        PClistanotificaciones.setItems(listaNotificaciones);



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

        PClistanotificaciones.setCellFactory(list -> new ListCell<Notificacion>() {
            private final Label label = new Label();

            {
                label.setWrapText(true);
                label.setMaxWidth(350);
            }

            @Override
            protected void updateItem(Notificacion item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    label.setText(item.toString());
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

        recomendacionesEmitidasACliente.setCellFactory(list -> new ListCell<Recomendacion>() {

            private final Label label = new Label();

            {
                label.setWrapText(true);   // permite varias líneas
                label.setMaxWidth(380);    // AJUSTA al ancho de tu ListView
            }

            @Override
            protected void updateItem(Recomendacion item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    label.setText(item.toString());  // o item.formatearBonito()
                    setGraphic(label);
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

        reportesGenerados.setCellFactory(list -> new ListCell<Reporte>() {

            private final Label label = new Label();

            {
                label.setWrapText(true);
                label.setMaxWidth(380); // ajusta al ancho del ListView
            }

            @Override
            protected void updateItem(Reporte item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    label.setText(item.toString());
                    setGraphic(label);
                }
            }
        });


        clienteSeleccionadoClientes.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> cargarActividadFisicaCliente(newV)
        );
        clienteSeleccionadoClientes.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> cargarSignosVitalesCliente(newV)
        );
        clienteSeleccionadoClientes.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> cargarCalidadSuenoCliente(newV)
        );
        clienteSeleccionadoRecomendaciones.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> cargarRecomendacionesEmitidasACliente(newVal)
        );
        clienteSeleccionadoReportes.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> cargarReportesDeCliente(newVal));
        recomendacionesEmitidasACliente.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> mostrarContenidoRecomendacion(newVal));

        limitecriticoMenor.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 99999, 0, 1));
        limitecriticoSuperior.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 99999, 0, 1));
        limitenormalMenor.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 99999, 0, 1));
        limitenormalSuperior.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 99999, 0, 1));
        limitecriticoMenor.valueProperty().addListener((obs, oldVal, newVal) -> actualizarConstanteSeleccionada());
        limitecriticoSuperior.valueProperty().addListener((obs, oldVal, newVal) -> actualizarConstanteSeleccionada());
        limitenormalMenor.valueProperty().addListener((obs, oldVal, newVal) -> actualizarConstanteSeleccionada());
        limitenormalSuperior.valueProperty().addListener((obs, oldVal, newVal) -> actualizarConstanteSeleccionada());
        constantesRegistradas.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> cargarConstanteEnSpinners(newVal));

        configurarListenersPermisos();
    }
}
