module com.mycompany.gestion.practicas.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires java.persistence;
    requires javafx.web;
    requires java.sql.rowset;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.swing;
    
    
    opens com.mycompany.gestion.practicas.application to javafx.fxml, org.hibernate.orm.core, java.sql, javafx.web, java.sql.rowset, java.desktop, javafx.graphics, javafx.swing;
    opens com.mycompany.gestion.practicas.models;
    opens com.mycompany.gestion.practicas.hibernate;
    opens com.mycompany.gestion.practicas.customassets;
    exports com.mycompany.gestion.practicas.application;
}
