/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.customassets.ToggleSwitch;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
/**
 * FXML Controller class
 *
 * @author medin
 */
public class PracticasController implements Initializable {


    @FXML
    private DatePicker datePicker;
    
    ToggleSwitch toggle = new ToggleSwitch();
    
    @FXML
    private HBox hbox1;
    @FXML
    private Button btnGuardar;
    @FXML
    private AnchorPane pane;
    @FXML
    private ChoiceBox<String> tipopractica;
    @FXML
    private TextArea descripcion;
    @FXML
    private Spinner<Integer> horas;
    @FXML
    private TextArea observaciones;
    @FXML
    private Button btnEliminar;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tipopractica.setItems(FXCollections.observableArrayList("Dual", "FCT"));
        tipopractica.getSelectionModel().selectFirst();
        
        SpinnerValueFactory svf = new IntegerSpinnerValueFactory(1,8);
        horas.setValueFactory(svf);
        horas.getValueFactory().setValue(8);
        
        toggle.setMaxWidth(50);
        toggle.setMaxHeight(28);
        
        hbox1.getChildren().add(3, toggle);
        
        descripcion.setWrapText(true);
        observaciones.setWrapText(true);
        
    }    

    @FXML
    private void btnGuardarHandler(ActionEvent event) {
        
    }

    @FXML
    private void btnEliminarHandler(ActionEvent event) {
        
    }
    
}
