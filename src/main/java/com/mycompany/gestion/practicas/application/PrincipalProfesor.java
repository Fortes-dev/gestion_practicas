package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.hibernate.HibernateUtil;
import com.mycompany.gestion.practicas.hibernate.SessionData;
import com.mycompany.gestion.practicas.models.Alumno;
import com.mycompany.gestion.practicas.models.Empresa;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
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
    private TableColumn<Empresa, String> cEmpresaAlum;
    @FXML
    private TableColumn<Empresa, String> cNombreEmp;
    @FXML
    private TableColumn<Empresa, String> cLocalizacion;
    @FXML
    private TableColumn<Empresa, String> cTutor;
    @FXML
    private TableColumn<Empresa, String> cEmailEmp;
    @FXML
    private TableColumn<Empresa, Integer> cTelefonoEmp;
    @FXML
    private Label lbBienvenido;
    @FXML
    private Label lbCentro;
    @FXML
    private ImageView ivLogoCesur;
    @FXML
    private ChoiceBox<String> cbFiltroAlum3;
    @FXML
    private TextField tfFiltroAlum;
    @FXML
    private TableView tvListaAlumnos;
    @FXML
    private Button btnAnnadirAlumno;
    @FXML
    private ChoiceBox<String> cbFiltroEmpre3;
    @FXML
    private TextField tfFiltroEmpresa;
    @FXML
    private TableView tvListaEmpresas;
    @FXML
    private Button btnAnnadirEmpresa;

    private Session s;
    private SceneController escena = new SceneController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbBienvenido.setText("Bienvenido, "+SessionData.getProfesorActual().getNombre());

        cbFiltroAlum3.setItems(FXCollections.observableArrayList("DNI", "Empresa"));
        cbFiltroAlum3.getSelectionModel().selectFirst();
        cbFiltroEmpre3.setItems(FXCollections.observableArrayList("Nombre", "Tutor Empresa"));
        cbFiltroEmpre3.getSelectionModel().selectFirst();

        cNombreAlum.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cApellidoAlum.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        cDniAlum.setCellValueFactory(new PropertyValueFactory<>("dni"));
        cEmailAlum.setCellValueFactory(new PropertyValueFactory<>("email"));
        cTelefonoAlum.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        cEmpresaAlum.setCellValueFactory(new PropertyValueFactory<>("idEmpresa"));

        cNombreEmp.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cTutor.setCellValueFactory(new PropertyValueFactory<>("tutorEmpresa"));
        cLocalizacion.setCellValueFactory(new PropertyValueFactory<>("localizacionUrl"));
        cTelefonoEmp.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        cEmailEmp.setCellValueFactory(new PropertyValueFactory<>("emailTutor"));

        cargarAlumnos();
        cargarEmpresa();
    }

    private void cargarAlumnos() {
        tvListaAlumnos.getItems().clear();
        s = HibernateUtil.getSessionFactory().openSession();
        Query<Alumno> q = s.createQuery("FROM Alumno a where a.idProfesor=:profesorT");
        q.setParameter("profesorT", SessionData.getProfesorActual());
        tvListaAlumnos.getItems().addAll(q.list());
        s.close();
    }
    private void cargarEmpresa() {
        tvListaEmpresas.getItems().clear();
        s = HibernateUtil.getSessionFactory().openSession();
        Query<Empresa> q = s.createQuery("FROM Empresa");
        tvListaEmpresas.getItems().addAll(q.list());
        s.close();
    }

    @FXML
    private void btnAnadirAlumnoHandler(ActionEvent actionEvent) {
        try {
            escena.switchToAñadirAlumno(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnAnadirEmpresaHandler(ActionEvent actionEvent) {
        try {
            escena.switchToAñadirEmpresa(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
