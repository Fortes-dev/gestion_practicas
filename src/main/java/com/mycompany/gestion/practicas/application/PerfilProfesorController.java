/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.hibernate.SessionData;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author medin
 */
public class PerfilProfesorController implements Initializable {


    @FXML
    private Label nombre;
    @FXML
    private Label centro;
    @FXML
    private Label email;
    @FXML
    private ImageView imgPerfil;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nombre.setText(SessionData.getProfesorActual().getNombre());
        email.setText(SessionData.getProfesorActual().getEmail());
        imgPerfil.setImage((Image) SessionData.getProfesorActual().getFotoImg());
    }    
    
}
