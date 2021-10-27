module com.mycompany.gestion.practicas.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires java.persistence;

    opens com.mycompany.gestion.practicas.application to javafx.fxml, org.hibernate.orm.core, java.sql;
    exports com.mycompany.gestion.practicas.application;
}
