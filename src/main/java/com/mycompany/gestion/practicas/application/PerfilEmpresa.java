package com.mycompany.gestion.practicas.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class PerfilEmpresa implements Initializable {

    @FXML
    private ImageView ivLogoEmpresa;
    @FXML
    private Label lbNombreEmpresa;
    @FXML
    private Label lbTutorEmpresa;
    @FXML
    private Label lbEmail;
    @FXML
    private Label lbTelefono;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
