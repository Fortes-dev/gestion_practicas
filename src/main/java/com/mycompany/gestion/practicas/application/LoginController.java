/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion.practicas.application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author CarlosFortesMedina
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void linkPasswordHandler(ActionEvent event) {
    }

    @FXML
    private void btnAceptarHandler(ActionEvent event) {
    }

}
