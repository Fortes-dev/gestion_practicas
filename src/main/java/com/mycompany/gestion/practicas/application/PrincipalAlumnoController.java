package com.mycompany.gestion.practicas.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalAlumnoController implements Initializable {


    @FXML
    private Button btnAnnadirTarea1;
    @FXML
    private Button btnAnnadirTarea2;
    @FXML
    private Button btnAnnadirTarea3;
    @FXML
    private Button btnAnnadirTarea4;
    @FXML
    private Button btnAnnadirTarea5;
    @FXML
    private HBox hbTareas;
    @FXML
    private ImageView ivLibro;
    @FXML
    private ImageView imgFotoAlumno;
    @FXML
    private ImageView imgLogoEmpresa;
    @FXML
    private Label lbNombreAlumno;
    @FXML
    private Label lbCurso;
    @FXML
    private Label lbEmpresa;
    @FXML
    private ScrollPane scrollTareasPane;
    @FXML
    private Button btnHistorial;
    @FXML
    private Label lbHorasRestantes;
    @FXML
    private Label lbHorasRealizadas;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
