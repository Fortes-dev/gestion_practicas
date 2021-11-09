package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.models.Alumno;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalProfesor implements Initializable {

    @FXML
    private TableColumn<Alumno, String> cNombreAlum;
    @FXML
    private TableColumn<Alumno, String> cApellidoAlum;
    @FXML
    private TableColumn<Alumno, String> cDniAlum;
    @FXML
    private TableColumn<Alumno, String> cEmailAlum;
    @FXML
    private TableColumn<Alumno, String> cTelefonoAlum;
    @FXML
    private TableColumn<Alumno, Long> cEmpresaAlum;
    @FXML
    private TableColumn cNombreEmp;
    @FXML
    private TableColumn cLocalizacion;
    @FXML
    private TableColumn cTutor;
    @FXML
    private TableColumn cEmailEmp;
    @FXML
    private TableColumn cTelefonoEmp;
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
        cNombreAlum.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cApellidoAlum.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        cDniAlum.setCellValueFactory(new PropertyValueFactory<>("dni"));
        cEmailAlum.setCellValueFactory(new PropertyValueFactory<>("email"));
        cTelefonoAlum.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        cEmpresaAlum.setCellValueFactory(new PropertyValueFactory<>("id_empresa"));

        tvListaAlumnos.getItems().add(new Alumno("Carlos"));
        tvListaAlumnos.getItems().add(new Alumno("Alberto"));
        tvListaAlumnos.getItems().add(new Alumno("Pablo"));
        tvListaAlumnos.getItems().add(new Alumno("Roberto"));
        tvListaAlumnos.getItems().add(new Alumno("Marcos"));
    }
}
