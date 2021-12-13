package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.hibernate.HibernateUtil;
import com.mycompany.gestion.practicas.hibernate.SessionData;
import com.mycompany.gestion.practicas.models.Alumno;
import com.mycompany.gestion.practicas.models.Empresa;
import com.mycompany.gestion.practicas.models.Practica;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
    private TableView<Alumno> tvListaAlumnos;
    @FXML
    private Button btnAnnadirAlumno;
    @FXML
    private ChoiceBox<String> cbFiltroEmpre3;
    @FXML
    private TextField tfFiltroEmpresa;
    @FXML
    private TableView<Empresa> tvListaEmpresas;
    @FXML
    private Button btnAnnadirEmpresa;

    private Session s;
    private SceneController escena = new SceneController();
    ObservableList<Alumno> contenidoAlumno = FXCollections.observableArrayList();
    ObservableList<Empresa> contenidoEmpresa = FXCollections.observableArrayList();
    Alumno a = SessionData.getAlumnoActual();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbBienvenido.setText("Bienvenido, " + SessionData.getProfesorActual().getNombre());

        cbFiltroAlum3.setItems(FXCollections.observableArrayList("Nombre", "Apellido", "DNI", "Empresa"));
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
//        task();
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

//    public void task() {
//        var timer = new Timer();
//        var task = new TimerTask() {
//            @Override
//            public void run() {
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        cargarAlumnos();
//                        cargarEmpresa();
//                    }
//
//                });
//            }
//
//        };
//        timer.scheduleAtFixedRate(task, 0, 1500);
//    }
    @FXML
    private void onMouseClickPerfilProfesor(MouseEvent mouseEvent) {
        ActionEvent e = new ActionEvent(mouseEvent.getSource(), mouseEvent.getTarget());
        try {
            escena.switchToPerfilProfesor(e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void seleccionarAlumno(MouseEvent mouseEvent) {
        try {
            s = HibernateUtil.getSessionFactory().openSession();

            Query<Alumno> q = s.createQuery("FROM Alumno a WHERE a.id=:t");
            q.setParameter("t", tvListaAlumnos.getSelectionModel().getSelectedItem().getId());
            SessionData.setAlumnoActual(q.list().get(0));

            try {
                ActionEvent e = new ActionEvent(mouseEvent.getSource(), mouseEvent.getTarget());
                escena.switchToHistorial(e);
            } catch (Exception ex) {
                Logger.getLogger(HistorialController.class
                        .getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Error accediendo al alumno");
            alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/error.png").toString())));
            alert.show();
        } finally {
            s.close();
        }
    }

    @FXML
    private void seleccionarEmpresa(MouseEvent mouseEvent) {
        try {
            s = HibernateUtil.getSessionFactory().openSession();

            Query<Empresa> q = s.createQuery("FROM Empresa e WHERE e.id=:t");
            q.setParameter("t", tvListaEmpresas.getSelectionModel().getSelectedItem().getId());
            SessionData.setEmpresaActual(q.list().get(0));

            try {
                ActionEvent e = new ActionEvent(mouseEvent.getSource(), mouseEvent.getTarget());
                escena.switchToPerfilEmpresa(e);
            } catch (Exception ex) {
                Logger.getLogger(HistorialController.class
                        .getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Error accediendo a la empresa");
            alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/error.png").toString())));
            alert.show();
        } finally {
            s.close();
        }
    }

    @FXML
    private void filtroAlum(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                s = HibernateUtil.getSessionFactory().openSession();

                if (tfFiltroAlum.getText() == "") {
                    cargarAlumnos();
                } else if (cbFiltroAlum3.getValue() == "DNI") {
                    Query q = s.createQuery("FROM Alumno p WHERE p.dni LIKE :t");
                    q.setParameter("t", Integer.parseInt(tfFiltroAlum.getText()));
                    contenidoAlumno.clear();
                    contenidoAlumno.addAll(q.list());
                    tvListaAlumnos.setItems(contenidoAlumno);

                } else if (cbFiltroAlum3.getValue() == "Nombre") {

                    Query q = s.createQuery("FROM Alumno p WHERE p.nombre LIKE CONCAT ('%',:t,'%')");
                    q.setParameter("t", tfFiltroAlum.getText());
                    contenidoAlumno.clear();
                    contenidoAlumno.addAll(q.list());
                    tvListaAlumnos.setItems(contenidoAlumno);
                } else if (cbFiltroAlum3.getValue() == "Apellidos") {

                    Query q = s.createQuery("FROM Alumno p WHERE p.apellidos LIKE CONCAT ('%',:t,'%')");
                    q.setParameter("t", tfFiltroAlum.getText());
                    contenidoAlumno.clear();
                    contenidoAlumno.addAll(q.list());
                    tvListaAlumnos.setItems(contenidoAlumno);
                } else if (cbFiltroAlum3.getValue() == "Empresa") {

                    Query qq = s.createQuery("FROM Empresa e WHERE e.nombre LIKE CONCAT ('%',:t,'%')");
                    qq.setParameter("t", tfFiltroAlum.getText());

                    Empresa e = (Empresa) qq.uniqueResult();

                    Query q = s.createQuery("FROM Alumno p WHERE p.idEmpresa LIKE CONCAT ('%',:t,'%')");
                    q.setParameter("t", e);

                    contenidoAlumno.clear();
                    contenidoAlumno.addAll(q.list());
                    tvListaAlumnos.setItems(contenidoAlumno);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("");
                alert.setHeaderText("Error mostrando datos");
                alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/error.png").toString())));
                alert.show();
            } finally {
                s.close();
            }
        }
    }

    @FXML
    private void filtroEmpresa(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                s = HibernateUtil.getSessionFactory().openSession();

                if (tfFiltroEmpresa.getText() == "") {
                    cargarEmpresa();
                } else if (cbFiltroEmpre3.getValue() == "Nombre") {
                    Query q = s.createQuery("FROM Empresa p WHERE p.nombre LIKE CONCAT ('%',:t,'%')");
                    q.setParameter("t", tfFiltroEmpresa.getText());
                    contenidoEmpresa.clear();
                    contenidoEmpresa.addAll(q.list());
                    tvListaEmpresas.setItems(contenidoEmpresa);

                } else if (cbFiltroEmpre3.getValue() == "Tutor Empresa") {

                    Query q = s.createQuery("FROM Empresa p WHERE p.tutorEmpresa LIKE CONCAT ('%',:t,'%')");
                    q.setParameter("t", tfFiltroEmpresa.getText());
                    contenidoEmpresa.clear();
                    contenidoEmpresa.addAll(q.list());
                    tvListaEmpresas.setItems(contenidoEmpresa);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("");
                alert.setHeaderText("Error mostrando datos");
                alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/error.png").toString())));
                alert.show();
            } finally {
                s.close();
            }
        }
    }
}
