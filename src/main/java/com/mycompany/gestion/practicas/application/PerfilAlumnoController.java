/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.hibernate.HibernateUtil;
import com.mycompany.gestion.practicas.hibernate.SessionData;
import com.mycompany.gestion.practicas.models.Alumno;
import com.mycompany.gestion.practicas.models.Empresa;
import com.mycompany.gestion.practicas.models.Profesor;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.hibernate.Session;
import org.hibernate.query.Query;
/**
 * FXML Controller class
 *
 * @author hierr
 */
public class PerfilAlumnoController implements Initializable {


    @FXML
    private Label txtNombre;
    @FXML
    private Label txtClase;
    @FXML
    private Label txtEmpresa;
    @FXML
    private Label txtCentro;
    @FXML
    private Label txtDNI;
    @FXML
    private Label txtTelefono;
    @FXML
    private Label txtEmail;
    @FXML
    private Label txtNacimiento;
    @FXML
    private Label txtProfesor;
    private DatePicker datepickerInicioAlt;
    private DatePicker datepickerFinalAlt;
    /**
     * Initializes the controller class.
     */
    Session s;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         s=HibernateUtil.getSessionFactory().openSession();
        try{
        SessionData.getAlumnoActual();
        Alumno a = s.load(Alumno.class, SessionData.getAlumnoActual());
       
         
        
        // Busco al profesor que tiene el ID que me pasa el SessionData 
        Query <Profesor> q = s.createQuery("From Profesor p where nombre =:n" );
        q.setParameter("n",a.getIdProfesor());
        Profesor profesor = q.list().get(0);
        
        // Busco la empresa que tiene el ID que me pasa el SessionData 
        Query <Empresa> q2 = s.createQuery("From Empresa e where nombre =:n" );
        q2.setParameter("n",a.getIdEmpresa());
        Empresa empresa = q2.list().get(0);
        
        
        
        txtNombre.setText(a.getNombre()+a.getApellidos());
        txtClase.setText(a.getCurso());
        txtEmpresa.setText(empresa.getNombre());// Saco el nombre de la empresa que me ha devuelto la consulta q2
        txtProfesor.setText(profesor.getNombre());// Saco el nombre del tutor que me ha devuelto la consulta q1 
        txtCentro.setText("Cesur Malaga Peste");
        txtDNI.setText(a.getDni());
        txtTelefono.setText(a.getTelefono().toString());
        txtEmail.setText(a.getEmail());
        txtNacimiento.setText(a.getFechaNac().toString());
        datepickerInicioAlt.setValue(LocalDate.now());
        datepickerFinalAlt.setValue(LocalDate.now());
        
        
        
        }catch(Exception ex){
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en la carga de datos");
            alert.setHeaderText("No ha sido posible cargar los datos del alumno");
            alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/error.png").toString())));
            alert.show();
            
        }finally{
            s.close();
        }
        
        
        
        
        
    }    
    
}
