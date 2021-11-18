/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.models.Alumno;
import com.mycompany.gestion.practicas.models.Empresa;
import com.mycompany.gestion.practicas.models.Profesor;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * FXML Controller class
 *
 * @author hierr
 */
public class AnadirAlumnoController implements Initializable {

    @FXML
    private TextField txtFieldNombre;
    @FXML
    private TextField txtFieldCurso;
    @FXML
    private TextField txtFieldCentro;
    @FXML
    private ImageView imgAñadirFoto;
    @FXML
    private Label txtAñadirFoto;
    @FXML
    private TextField txtFieldDNI;
    @FXML
    private TextField txtFieldTelefono;
    @FXML
    private TextField txtFieldEmail;
    @FXML
    private DatePicker DatepickerNacimiento;
    @FXML
    private Button btnAnadir;
    @FXML
    private TextField txtFieldApellidos;
    @FXML
    private ChoiceBox<Empresa> cbEmpresa;
    @FXML
    private ChoiceBox<Profesor> cbNombreTutor;
    @FXML
    private TextField txtFieldContraseña;

    /**
     * Initializes the controller class.
     */
    Session s;
   
    
    /**
     * Creo ObsevableLists de Empresas y Profesores a través de consultas HQL
     * Los resultados de esas consultas aparecen en los ChoiceBox para que el usuario los pueda seleccionar
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        s.getSessionFactory();
        try{
        ObservableList<Empresa> empresas = FXCollections.observableArrayList();//Contenido del ChoiceBox de Empresas
        Query<Empresa> q = s.createQuery("nombre FROM Empresa");
        q.list().forEach((e) -> empresas.add(0, e));
        cbEmpresa.setItems(empresas);
        
        ObservableList<Profesor> profesores = FXCollections.observableArrayList();//Contenido del ChoiceBox de Profesores
        Query<Profesor> q2 = s.createQuery("nombre FROM Profesor");
        q2.list().forEach((p) -> profesores.add(0, p));
        cbNombreTutor.setItems(profesores);
        }finally{
            s.close();
        }
         
        
    }    

    @FXML
    private void btnAceptar(ActionEvent event) {
        
// Busco el id de la empresa seleccionada para añadirlo a la tabla de alumno
        s.getSessionFactory();
        Query<Empresa> q = s.createQuery("id FROM Empresa WHERE nombre:=n");
        q.setParameter("n", cbEmpresa.getValue());
        
// Busco el id del tutor seleccionado para añadirlo a la tabla de alumno    
        Query<Profesor> q2 = s.createQuery("id FROM Empresa WHERE nombre:=np");
        q.setParameter("np",cbNombreTutor.getValue());
        try{
            Alumno alumno = new Alumno();
            alumno.setNombre(txtFieldNombre.getText());
            alumno.setApellidos(txtFieldApellidos.getText());
            alumno.setDni(txtFieldDNI.getText());
            alumno.setFechaNac(Date.valueOf(DatepickerNacimiento.getValue()));
            alumno.setEmail(txtFieldEmail.getText());
            alumno.setTelefono(Integer.parseInt(txtFieldTelefono.getText()));
            alumno.setPassword(txtFieldContraseña.getText());
            alumno.setIdEmpresa(q.list().get(0));
            alumno.setIdProfesor(q2.list().get(0));
            alumno.setHorasDual(null);
            alumno.setFotoImg(null);

            Transaction tr = s.beginTransaction();
            s.save(alumno);
            tr.commit();
        }catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error añadiendo alumno");
            alert.setHeaderText("No ha sido posible añadir al alumno");
            alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/error.png").toString())));
            alert.show();
        }finally{
            s.close();
        }
        
        
        
    }
    
}
