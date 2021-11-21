/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.hibernate.HibernateUtil;
import com.mycompany.gestion.practicas.hibernate.SessionData;
import com.mycompany.gestion.practicas.models.Alumno;
import com.mycompany.gestion.practicas.models.Practica;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.query.Query;
/**
 * FXML Controller class
 *
 * @author hierr
 */
public class HistorialController implements Initializable {


    @FXML
    private AnchorPane vistaHistorial;
    @FXML
    private Label nombreAlumno;
    @FXML
    private ImageView logoEmpresaImg;
    @FXML
    private TableView<Practica> tablaPracticas;
    @FXML
    private TableColumn<Practica, Long> colId;
    @FXML
    private TableColumn<Practica, String> colTipo;
    @FXML
    private TableColumn<Practica, Integer> colHoras;
    @FXML
    private TableColumn<Practica, String> colDescripcion;
    @FXML
    private TextField TextFieldBuscador;
    @FXML
    private Label claseAlumno;
    private ChoiceBox<String> choiceBoxTipo;
    @FXML
    private Button btnAnadir;
    @FXML
    private TableColumn<Practica, Date> colFecha;
    @FXML
    private ComboBox<String> comboBoxTipo;
    
    
    /**
     * Initializes the controller class.
     */
    Session s;
    ObservableList<Practica> contenido = FXCollections.observableArrayList();
    Alumno a = SessionData.getAlumnoActual();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxTipo.getItems().addAll("ID","Fecha","Tipo","Horas empleadas","Descripcion");
        comboBoxTipo.getSelectionModel().selectFirst();
       
        
        nombreAlumno.setText(a.getNombre()+a.getApellidos());
        claseAlumno.setText(a.getCurso());
//        LogoEmpresaImg.set();
        
    
    try{
      s=HibernateUtil.getSessionFactory().openSession(); 
    
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
            colHoras.setCellValueFactory(new PropertyValueFactory<>("horasEmpleadas"));
            colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
   
    Query q= s.createQuery("FROM Practica p WHERE p.idAlumno=:n");
    q.setParameter("n", a);
    contenido.addAll(q.list());
    tablaPracticas.setItems(contenido);
     
    }catch(Exception ex){
        ex.printStackTrace();
    }finally{
        s.close();
    }  
        
    } 

    @FXML
    private void btnAnadir(ActionEvent event) {
        SceneController sc = new SceneController();
        try {
            sc.switchToAñadirPracticas(event);
        } catch (IOException ex) {
            Logger.getLogger(HistorialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void valorActualizado(ActionEvent event) {
        


    
    }

    @FXML
    private void filtrar(KeyEvent event) {
                try{
              s = HibernateUtil.getSessionFactory().openSession();
            if (comboBoxTipo.getValue() == "ID") {
                Query q = s.createQuery("FROM Practica p WHERE p.id_alumno=:n and p.id LIKE '%', :t, '%'");
                q.setParameter("n", a.getId());
                q.setParameter("t", TextFieldBuscador.getText() );
                contenido.addAll(q.list());
                tablaPracticas.setItems(contenido);

            } else if (comboBoxTipo.getValue() == "Fecha") {

                Query q = s.createQuery("FROM Practica p WHERE p.id_alumno=:n and p.fecha LIKE '%', :t, '%'");
                q.setParameter("n", a.getId());
                q.setParameter("t", TextFieldBuscador.getText());
                contenido.addAll(q.list());
                tablaPracticas.setItems(contenido);
                
            } else if (comboBoxTipo.getValue() == "Horas empleadas") {

                Query q = s.createQuery("FROM Practica p WHERE p.id_alumno=:n and p.horasEmpleadas LIKE '%', :t, '%'");
                q.setParameter("n", a.getId());
                q.setParameter("t", TextFieldBuscador.getText());
                contenido.addAll(q.list());
                tablaPracticas.setItems(contenido); 
                
            } else if (comboBoxTipo.getValue() == "Descripcion") {

                Query q = s.createQuery("FROM Practica p WHERE p.id_alumno=:n and p.descripcion LIKE '%', :t, '%'");
                q.setParameter("n", a.getId());
                q.setParameter("t", TextFieldBuscador.getText());
                contenido.addAll(q.list());
                tablaPracticas.setItems(contenido);    
            }
                


           }catch(Exception ex){
               ex.printStackTrace();
               Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("");
                   alert.setHeaderText("Error mostrando datos");
                   alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/error.png").toString())));
                   alert.show();
           }finally{
            s.close();
        }
    }
}
