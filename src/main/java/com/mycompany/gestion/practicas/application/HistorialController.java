/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestion.practicas.application;

import com.jfoenix.controls.JFXComboBox;
import com.mycompany.gestion.practicas.hibernate.HibernateUtil;
import com.mycompany.gestion.practicas.hibernate.SessionData;
import com.mycompany.gestion.practicas.models.Alumno;
import com.mycompany.gestion.practicas.models.Practica;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import static javafx.scene.input.MouseEvent.MOUSE_ENTERED;
import static javafx.scene.input.MouseEvent.MOUSE_EXITED;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * FXML Controller class
 *
 * @author hierr
 */
public class HistorialController implements Initializable {

    @FXML
    private AnchorPane vistaHistorial;
    @FXML
    private Label nombreAlumno;
    @FXML
    private ImageView logoEmpresaImg;
    @FXML
    private TableView<Practica> tablaPracticas;
    @FXML
    private TableColumn<Practica, Long> colId;
    @FXML
    private TableColumn<Practica, String> colTipo;
    @FXML
    private TableColumn<Practica, Integer> colHoras;
    @FXML
    private TableColumn<Practica, String> colDescripcion;
    @FXML
    private TextField TextFieldBuscador;
    @FXML
    private Label claseAlumno;
    private ChoiceBox<String> choiceBoxTipo;
    @FXML
    private Button btnAnadir;
    @FXML
    private TableColumn<Practica, Date> colFecha;
    @FXML
    private JFXComboBox<String> comboBoxTipo;
    @FXML
    private Button btnRefresh;

    /**
     * Initializes the controller class.
     */
    Session s;
    ObservableList<Practica> contenido = FXCollections.observableArrayList();
    Alumno a = SessionData.getAlumnoActual();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        task();

        initTabla();

    }

    public void task() {
        var timer = new Timer();
        var task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        setDatos();
                    }

                });
            }

        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void setDatos() {

        if(SessionData.getAlumnoActual() == null) {
            Stage stage = (Stage) btnAnadir.getScene().getWindow();
            stage.close();
        }
        
        comboBoxTipo.getItems().addAll("ID", "Fecha", "Tipo", "Horas empleadas", "Descripcion");
        comboBoxTipo.getSelectionModel().selectFirst();

        nombreAlumno.setText(a.getNombre() + " " + a.getApellidos());
        claseAlumno.setText(a.getCurso());
    }

    @FXML

    private void btnAnadir(ActionEvent event) {
        SceneController sc = new SceneController();
        try {
            sc.switchToAñadirPracticas(event);

        } catch (IOException ex) {
            Logger.getLogger(HistorialController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void filtrar(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            try {
                s = HibernateUtil.getSessionFactory().openSession();

                if (TextFieldBuscador.getText() == "") {
                    initTabla();
                } else if (comboBoxTipo.getValue() == "ID") {
                    Query q = s.createQuery("FROM Practica p WHERE p.idAlumno=:n and p.id LIKE :t");
                    q.setParameter("n", a);
                    q.setParameter("t", Long.parseLong(TextFieldBuscador.getText()));
                    contenido.clear();
                    contenido.addAll(q.list());
                    tablaPracticas.setItems(contenido);

                } else if (comboBoxTipo.getValue() == "Fecha") {

                    Query q = s.createQuery("FROM Practica p WHERE p.idAlumno=:n and p.fecha LIKE CONCAT ('%',:t,'%')");
                    q.setParameter("n", a);
                    q.setParameter("t", TextFieldBuscador.getText());
                    contenido.clear();
                    contenido.addAll(q.list());
                    tablaPracticas.setItems(contenido);
                } else if (comboBoxTipo.getValue() == "Horas empleadas") {

                    Query q = s.createQuery("FROM Practica p WHERE p.idAlumno=:n and p.horasEmpleadas LIKE :t");
                    q.setParameter("n", a);
                    q.setParameter("t", Integer.parseInt(TextFieldBuscador.getText()));
                    contenido.clear();
                    contenido.addAll(q.list());
                    tablaPracticas.setItems(contenido);
                } else if (comboBoxTipo.getValue() == "Descripcion") {

                    Query q = s.createQuery("FROM Practica p WHERE p.idAlumno=:n and p.descripcion LIKE CONCAT ('%',:t,'%')");
                    q.setParameter("n", a);
                    q.setParameter("t", TextFieldBuscador.getText());
                    contenido.clear();
                    contenido.addAll(q.list());
                    tablaPracticas.setItems(contenido);
                } else if (comboBoxTipo.getValue() == "Tipo") {
                    Query q = s.createQuery("FROM Practica p WHERE p.idAlumno=:n and p.tipo LIKE CONCAT ('%',:t,'%')");
                    q.setParameter("n", a);
                    q.setParameter("t", TextFieldBuscador.getText());
                    contenido.clear();
                    contenido.addAll(q.list());
                    tablaPracticas.setItems(contenido);
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
    private void seleccionar(MouseEvent mouseEvent) {

        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Long id = tablaPracticas.getSelectionModel().getSelectedItem().getId();
            SceneController sc = new SceneController();

            Query q = s.createQuery("FROM Practica p WHERE p.id=:t");
            q.setParameter("t", id);
            SessionData.setPracticaActual((Practica) q.list().get(0));

            try {
                ActionEvent e = new ActionEvent(mouseEvent.getSource(), mouseEvent.getTarget());
                sc.switchToPracticas(e);

            } catch (Exception ex) {
                Logger.getLogger(HistorialController.class
                        .getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Error accediendo a la practica");
            alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/error.png").toString())));
            alert.show();
        } finally {
            s.close();
        }
    }

    public void initTabla() {
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            contenido.clear();

            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
            colHoras.setCellValueFactory(new PropertyValueFactory<>("horasEmpleadas"));
            colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

            Query q = s.createQuery("FROM Practica p WHERE p.idAlumno=:n");
            q.setParameter("n", a);
            contenido.addAll(q.list());
            tablaPracticas.setItems(contenido);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            s.close();
        }
    }

    @FXML
    private void btnNombreAlumno(MouseEvent mouseEvent) {
        try {
            SceneController sc = new SceneController();
            ActionEvent e = new ActionEvent(mouseEvent.getSource(), mouseEvent.getTarget());
            sc.switchToPerfilAlumno(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void refreshTable(ActionEvent event) {
        initTabla();
    }


}
