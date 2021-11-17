/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion.practicas.application;

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
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.hibernate.Session;
import org.hibernate.query.Query;
/**
 * FXML Controller class
 *
 * @author medin
 */
public class LoginController implements Initializable {


    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Hyperlink linkPassword;
    @FXML
    private Button btnAceptar;
    
    private Session s;
    private SceneController escena = new SceneController();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        s = HibernateUtil.getSessionFactory().openSession();
        
    }    
    
    @FXML
    private void linkPasswordHandler(ActionEvent event) {
    }

    @FXML
    private void btnAceptarHandler(ActionEvent event) {
        
        Query listAlumno = s.createQuery("From Alumno a WHERE a.email=:email and a.password=:password");
        listAlumno.setParameter("email", userField.getText());
        listAlumno.setParameter("password", passwordField.getText());
        
        Query listProfesor = s.createQuery("From Profesor p WHERE p.email=:email and p.password=:password");
        listProfesor.setParameter("email", userField.getText());
        listProfesor.setParameter("password", passwordField.getText());
        
        if(!listAlumno.list().isEmpty()) {
            Alumno a = (Alumno) listAlumno.list().get(0);
            SessionData.setAlumnoActual(a);
            s.close();
            try {
                escena.switchToPrincipalAlumno(event);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(!listProfesor.list().isEmpty()) {
            Profesor p = (Profesor) listProfesor.list().get(0);
            SessionData.setProfesorActual(p);
            s.close();
            try {
                escena.switchToPrincipalProfesor(event);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de login");
            alert.setHeaderText("No ha sido posible acceder");
            alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/error.png").toString())));
            alert.setContentText("Usuario: "+userField.getText()+" no existe o contraseña incorrecta");
            alert.show();
        }
        
    }

}
