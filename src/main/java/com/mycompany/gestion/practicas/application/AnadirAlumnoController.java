/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestion.practicas.application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
    private TextField txtFieldEmpresa;
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
    private TextField txtFieldNombreTutor;
    @FXML
    private Button btnAnadir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
