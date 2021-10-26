module com.mycompany.gestion.practicas.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.gestion.practicas.application to javafx.fxml;
    exports com.mycompany.gestion.practicas.application;
}
