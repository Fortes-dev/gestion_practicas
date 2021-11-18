/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.hibernate.HibernateUtil;
import com.mycompany.gestion.practicas.hibernate.SessionData;
import com.mycompany.gestion.practicas.models.Practica;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.hibernate.Session;

/**
 * FXML Controller class
 *
 * @author medin
 */
public class AñadirPracticasController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private HBox hbox1;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button btnAñadir;
    @FXML
    private ChoiceBox<String> tipopractica;
    @FXML
    private TextArea descripcion;
    @FXML
    private Spinner<Integer> horas;
    @FXML
    private TextArea observaciones;

    Session s;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipopractica.setItems(FXCollections.observableArrayList("Dual", "FCT"));
        tipopractica.getSelectionModel().selectFirst();

        SpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 8);
        horas.setValueFactory(svf);
        horas.getValueFactory().setValue(8);

    }

    @FXML
    private void btnAñadirHandler(ActionEvent event) {
        
        var pr = new Practica();

        Date date = Date.valueOf(datePicker.getValue());

        pr.setId(0L);
        pr.setIdAlumno(SessionData.getAlumnoActual());
        pr.setFecha(date);
        pr.setTipo(tipopractica.getValue());
        pr.setHorasEmpleadas(horas.getValue());
        pr.setDescripcion(descripcion.getText());
        pr.setObservaciones(observaciones.getText());
        
        try {
            
            s = HibernateUtil.getSessionFactory().openSession();
            var tr = s.beginTransaction();
            s.save(pr);
            tr.commit();
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("No ha sido posible añadir la nueva tarea");
            alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/error.png").toString())));
            alert.setContentText("No puede contener un campo vació o hay algún campo erróneo");
            alert.show();
        } finally {
            s.close();
        }
        

    }

}
