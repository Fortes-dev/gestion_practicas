package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.hibernate.HibernateUtil;
import com.mycompany.gestion.practicas.hibernate.SessionData;
import com.mycompany.gestion.practicas.models.Alumno;
import com.mycompany.gestion.practicas.models.Practica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PrincipalAlumnoController implements Initializable {

    @FXML
    private Button anadirPractica;
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

    private Session s;
    private SceneController escena = new SceneController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTareas();

        lbNombreAlumno.setText(SessionData.getAlumnoActual().getNombre() + " " + SessionData.getAlumnoActual().getApellidos());
        lbCurso.setText(SessionData.getAlumnoActual().getCurso());
        lbEmpresa.setText(SessionData.getAlumnoActual().getIdEmpresa().getNombre());

        lbHorasRestantes.setText(SessionData.getAlumnoActual().getHorasFct().toString());
        lbHorasRealizadas.setText(SessionData.getAlumnoActual().getHorasDual().toString());

    }

    private void cargarTareas() {
        s = HibernateUtil.getSessionFactory().openSession();
        Query<Practica> q = s.createQuery("FROM Practica p where p.idAlumno=:actual  order by p.fecha desc").setMaxResults(5);
        q.setParameter("actual", SessionData.getAlumnoActual());
        Query qa = s.createQuery("SELECT count (*) from Practica p where p.idAlumno=:actual");
        qa.setParameter("actual", SessionData.getAlumnoActual());
        System.out.println(((Number) qa.uniqueResult()).intValue());
        Practica[] practicas = q.list().toArray(new Practica[((Number) qa.uniqueResult()).intValue()]);
        System.out.println(practicas.length);
        switch (practicas.length - 1) {
            case 0: {
                btnAnnadirTarea1.setText(practicas[0].getFecha().toString());
                btnAnnadirTarea1.setVisible(true);
                btnAnnadirTarea2.setVisible(false);
                btnAnnadirTarea3.setVisible(false);
                btnAnnadirTarea4.setVisible(false);
                btnAnnadirTarea5.setVisible(false);
                break;
            }
            case 1: {
                btnAnnadirTarea1.setText(practicas[0].getFecha().toString());
                btnAnnadirTarea2.setText(practicas[1].getFecha().toString());
                btnAnnadirTarea2.setVisible(true);
                btnAnnadirTarea3.setVisible(false);
                btnAnnadirTarea4.setVisible(false);
                btnAnnadirTarea5.setVisible(false);
                break;
            }
            case 2: {
                btnAnnadirTarea1.setText(practicas[0].getFecha().toString());
                btnAnnadirTarea2.setText(practicas[1].getFecha().toString());
                btnAnnadirTarea3.setText(practicas[2].getFecha().toString());
                btnAnnadirTarea3.setVisible(true);
                btnAnnadirTarea4.setVisible(false);
                btnAnnadirTarea5.setVisible(false);
                break;
            }
            case 3: {
                btnAnnadirTarea1.setText(practicas[0].getFecha().toString());
                btnAnnadirTarea2.setText(practicas[1].getFecha().toString());
                btnAnnadirTarea3.setText(practicas[2].getFecha().toString());
                btnAnnadirTarea4.setText(practicas[3].getFecha().toString());
                btnAnnadirTarea4.setVisible(true);
                btnAnnadirTarea5.setVisible(false);
                break;
            }
            case 4: {
                btnAnnadirTarea1.setText(practicas[0].getFecha().toString());
                btnAnnadirTarea2.setText(practicas[1].getFecha().toString());
                btnAnnadirTarea3.setText(practicas[2].getFecha().toString());
                btnAnnadirTarea4.setText(practicas[3].getFecha().toString());
                btnAnnadirTarea5.setText(practicas[4].getFecha().toString());
                btnAnnadirTarea5.setVisible(true);
                break;
            }
            default: {
                btnAnnadirTarea1.setVisible(false);
                btnAnnadirTarea2.setVisible(false);
                btnAnnadirTarea3.setVisible(false);
                btnAnnadirTarea4.setVisible(false);
                btnAnnadirTarea5.setVisible(false);
            }
        }
        s.close();
    }

    @FXML
    private void btnAnadirPracticaHandler(ActionEvent actionEvent) {
        try {
            escena.switchToAñadirPracticas(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
