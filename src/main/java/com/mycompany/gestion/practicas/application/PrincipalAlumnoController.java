package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.customassets.Constants;
import com.mycompany.gestion.practicas.hibernate.HibernateUtil;
import com.mycompany.gestion.practicas.hibernate.SessionData;
import com.mycompany.gestion.practicas.models.Alumno;
import com.mycompany.gestion.practicas.models.Practica;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.scene.layout.VBox;

public class PrincipalAlumnoController implements Initializable {

    @FXML
    private Button moverPIzq;
    @FXML
    private Button moverPDer;
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
    private Practica[] practicasT;
    private Alumno a = SessionData.getAlumnoActual();
    
    @FXML
    private VBox vFondo;
    @FXML
    private Label fctHorasRestantes;
    @FXML
    private Label fctHorasRealizadas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbNombreAlumno.setText(SessionData.getAlumnoActual().getNombre() + " " + SessionData.getAlumnoActual().getApellidos());
        lbCurso.setText(SessionData.getAlumnoActual().getCurso());
        lbEmpresa.setText(SessionData.getAlumnoActual().getIdEmpresa().getNombre());

        task();
    }
    
    public void cargarHoras() {
        lbHorasRestantes.setText(getDualHoras(a, "restantes").toString());
        lbHorasRealizadas.setText(getDualHoras(a, "totales").toString());
        fctHorasRestantes.setText(getFctHoras(a, "restantes").toString());
        fctHorasRealizadas.setText(getFctHoras(a, "totales").toString());
    }

    public void cargarTareas() {

        s = HibernateUtil.getSessionFactory().openSession();
        
        Query<Practica> q = s.createQuery("FROM Practica p where p.idAlumno=:actual  order by p.fecha desc");
        q.setParameter("actual", SessionData.getAlumnoActual());
        
        Query qa = s.createQuery("SELECT count (*) from Practica p where p.idAlumno=:actual");
        qa.setParameter("actual", SessionData.getAlumnoActual());
       
        Query<Alumno> ap = s.createQuery("FROM Alumno a where a.id=:id");
        ap.setParameter("id", a.getId());
        
        a = ap.list().get(0);
        practicasT = q.list().toArray(new Practica[((Number) qa.uniqueResult()).intValue()]);
        switch (practicasT.length - 1) {
            case 0: {
                btnAnnadirTarea1.setText(practicasT[0].getFecha().toLocalDate().toString());
                btnAnnadirTarea1.setVisible(true);
                btnAnnadirTarea2.setVisible(false);
                btnAnnadirTarea3.setVisible(false);
                btnAnnadirTarea4.setVisible(false);
                btnAnnadirTarea5.setVisible(false);
                break;
            }
            case 1: {
                btnAnnadirTarea1.setText(practicasT[0].getFecha().toString());
                btnAnnadirTarea2.setText(practicasT[1].getFecha().toString());
                btnAnnadirTarea2.setVisible(true);
                btnAnnadirTarea3.setVisible(false);
                btnAnnadirTarea4.setVisible(false);
                btnAnnadirTarea5.setVisible(false);
                break;
            }
            case 2: {
                btnAnnadirTarea1.setText(practicasT[0].getFecha().toString());
                btnAnnadirTarea2.setText(practicasT[1].getFecha().toString());
                btnAnnadirTarea3.setText(practicasT[2].getFecha().toString());
                btnAnnadirTarea3.setVisible(true);
                btnAnnadirTarea4.setVisible(false);
                btnAnnadirTarea5.setVisible(false);
                break;
            }
            case 3: {
                btnAnnadirTarea1.setText(practicasT[0].getFecha().toString());
                btnAnnadirTarea2.setText(practicasT[1].getFecha().toString());
                btnAnnadirTarea3.setText(practicasT[2].getFecha().toString());
                btnAnnadirTarea4.setText(practicasT[3].getFecha().toString());
                btnAnnadirTarea4.setVisible(true);
                btnAnnadirTarea5.setVisible(false);
                break;
            }
            case 4: {
                btnAnnadirTarea1.setText(practicasT[0].getFecha().toString());
                btnAnnadirTarea2.setText(practicasT[1].getFecha().toString());
                btnAnnadirTarea3.setText(practicasT[2].getFecha().toString());
                btnAnnadirTarea4.setText(practicasT[3].getFecha().toString());
                btnAnnadirTarea5.setText(practicasT[4].getFecha().toString());
                btnAnnadirTarea5.setVisible(true);
                break;
            }
            default: {
                btnAnnadirTarea1.setText(practicasT[0].getFecha().toString());
                btnAnnadirTarea2.setText(practicasT[1].getFecha().toString());
                btnAnnadirTarea3.setText(practicasT[2].getFecha().toString());
                btnAnnadirTarea4.setText(practicasT[3].getFecha().toString());
                btnAnnadirTarea5.setText(practicasT[4].getFecha().toString());
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

    @FXML
    private void btnIrDer(ActionEvent actionEvent) {
        scrollTareasPane.setHvalue(scrollTareasPane.getHvalue() + 0.4);
    }

    @FXML
    private void btnIrIzq(ActionEvent actionEvent) {
        scrollTareasPane.setHvalue(scrollTareasPane.getHvalue() - 0.4);
    }

    @FXML
    private void btnHistorialHandler(ActionEvent actionEvent) {
        try {
            escena.switchToHistorial(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void imgOnMouseClick(MouseEvent mouseEvent) {
        ActionEvent e = new ActionEvent(mouseEvent.getSource(), mouseEvent.getTarget());
        try {
            escena.switchToPerfilAlumno(e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnVerPracticaHandler(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnAnnadirTarea1) {
            SessionData.setPracticaActual(practicasT[0]);
        } else if (actionEvent.getSource() == btnAnnadirTarea2) {
            SessionData.setPracticaActual(practicasT[1]);
        } else if (actionEvent.getSource() == btnAnnadirTarea3) {
            SessionData.setPracticaActual(practicasT[2]);
        } else if (actionEvent.getSource() == btnAnnadirTarea4) {
            SessionData.setPracticaActual(practicasT[3]);
        } else if (actionEvent.getSource() == btnAnnadirTarea5) {
            SessionData.setPracticaActual(practicasT[4]);
        }
        try {
            escena.switchToPracticas(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer getDualHoras(Alumno a, String total) {

        Integer horas = 0;
        var list = a.getPracticas();

        for (Practica p : list) {
            if (p.getTipo().equals("Dual")) {
                horas += p.getHorasEmpleadas();
            }
        }

        if (total.equals("restantes")) {
            return Constants.HORAS_TOTAL_DUAL - horas;
        } else {
            return horas;
        }
    }

    private Integer getFctHoras(Alumno a, String total) {

        Integer horas = 0;
        var list = a.getPracticas();

        for (Practica p : list) {
            if (p.getTipo().equals("FCT")) {
                horas += p.getHorasEmpleadas();
            }
        }
        if (total.equals("restantes")) {
            return Constants.HORAS_TOTAL_DUAL - horas;
        } else {
            return horas;
        }
    }

    public void task() {
        var timer = new Timer();
        var task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        cargarTareas();
                        cargarHoras();
                    }

                });
            }

        };
        timer.scheduleAtFixedRate(task, 0, 1500);
    }
}
