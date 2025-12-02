module com.poo.gymstarter {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.desktop;


    opens com.poo.gymstarter to javafx.fxml;
    exports Controlador;
    exports com.poo.gymstarter;
}