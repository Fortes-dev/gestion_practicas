package com.mycompany.gestion.practicas.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalProfesor implements Initializable {

    @FXML
    private Label lbBienvenido;
    @FXML
    private Label lbCentro;
    @FXML
    private ImageView ivLogoCesur;
    @FXML
    private ChoiceBox<String> cbFiltroAlum1;
    @FXML
    private ChoiceBox<String> cbFiltroAlum2;
    @FXML
    private ChoiceBox<String> cbFiltroAlum3;
    @FXML
    private TextField tfFiltroAlum;
    @FXML
    private TableView tvListaAlumnos;
    @FXML
    private Button btnAnnadirAlumno;
    @FXML
    private ChoiceBox<String> cbFiltroEmpre1;
    @FXML
    private ChoiceBox<String> cbFiltroEmpre2;
    @FXML
    private ChoiceBox<String> cbFiltroEmpre3;
    @FXML
    private TextField tfFiltroEmpresa;
    @FXML
    private TableView tvListaEmpresas;
    @FXML
    private Button btnAnnadirEmpresa;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
