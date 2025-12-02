module com.poo.gymstarter {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.poo.gymstarter to javafx.fxml;
    exports com.poo.gymstarter;
}