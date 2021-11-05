/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestion.practicas.application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    @FXML
    private DatePicker datepickerInicioAlt;
    @FXML
    private DatePicker datepickerFinalAlt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
