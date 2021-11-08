package com.mycompany.gestion.practicas.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class AnnadirEmpresa implements Initializable {

    @FXML
    private ImageView ivLogoEmpresa;
    @FXML
    private TextField tfNombreEmpresa;
    @FXML
    private TextField tfTutorEmpresa;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfTelefono;
    @FXML
    private Button btnAnnadir;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
