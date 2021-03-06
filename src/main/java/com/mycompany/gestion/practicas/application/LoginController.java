/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion.practicas.application;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.gestion.practicas.hibernate.HibernateUtil;
import com.mycompany.gestion.practicas.hibernate.SessionData;
import com.mycompany.gestion.practicas.models.Alumno;
import com.mycompany.gestion.practicas.models.Profesor;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * FXML Controller class
 *
 * @author medin
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField userField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private Hyperlink linkPassword;
    @FXML
    private Button btnAceptar;
    @FXML
    private BorderPane parent;

    private Session s;
    private SceneController escena = new SceneController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SessionData.setAdmin(false);
    }

    @FXML
    private void linkPasswordHandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Recuperar contrase??a");
        alert.setHeaderText("Oh, parece que no recuerdas tu contrase??a");
        alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/error.png").toString())));
        alert.setContentText("A??n no est?? implementada esta funcionalidad");
        alert.show();
    }

    @FXML
    private void btnAceptarHandler(ActionEvent event) {

        logIn(event);

    }

    private void logIn(ActionEvent event) {
        s = HibernateUtil.getSessionFactory().openSession();
        Query listAlumno = s.createQuery("From Alumno a WHERE a.email=:email and a.password=:password");
        listAlumno.setParameter("email", userField.getText());
        listAlumno.setParameter("password", passwordField.getText());

        Query listProfesor = s.createQuery("From Profesor p WHERE p.email=:email and p.password=:password");
        listProfesor.setParameter("email", userField.getText());
        listProfesor.setParameter("password", passwordField.getText());

        if (!listAlumno.list().isEmpty()) {
            Alumno a = (Alumno) listAlumno.list().get(0);
            SessionData.setAlumnoActual(a);
            s.close();
            try {
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                escena.switchToPrincipalAlumno(stage);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                s.close();
            }
        } else if (!listProfesor.list().isEmpty()) {
            Profesor p = (Profesor) listProfesor.list().get(0);
            SessionData.setProfesorActual(p);

            try {
                SessionData.setAdmin(Boolean.TRUE);
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                escena.switchToPrincipalProfesor(stage);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                s.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de login");
            alert.setHeaderText("No ha sido posible acceder");
            alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/error.png").toString())));
            alert.setContentText("Usuario: " + userField.getText() + " no existe o contrase??a incorrecta");
            alert.show();
        }
    }
}
