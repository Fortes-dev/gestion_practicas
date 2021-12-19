package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.hibernate.HibernateUtil;
import com.mycompany.gestion.practicas.hibernate.SessionData;
import com.mycompany.gestion.practicas.models.Alumno;
import com.mycompany.gestion.practicas.models.Empresa;
import com.mycompany.gestion.practicas.models.Profesor;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * FXML Controller class
 *
 * @author hierr
 */
public class PerfilAlumnoController implements Initializable {

    @FXML
    private Label txtNombre;
    @FXML
    private Label txtClase;
    @FXML
    private Label txtEmpresa;
    @FXML
    private Label txtCentro;
    @FXML
    private Label txtDNI;
    @FXML
    private Label txtTelefono;
    @FXML
    private Label txtEmail;
    @FXML
    private Label txtNacimiento;
    @FXML
    private Label txtProfesor;

    @FXML
    private ImageView fotoAlumno;

    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnModificar;

    private Alumno a = SessionData.getAlumnoActual();
    private SceneController escena = new SceneController();
    private Session s = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (!SessionData.getAdmin()) {
            btnEliminar.setVisible(false);
            btnModificar.setVisible(false);
        }
        
        task();

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
        timer.scheduleAtFixedRate(task, 0, 2000);
    }

    public void setDatos() {
        InputStream in = null;
        try {

            in = a.getFotoImg().getBinaryStream();
            BufferedImage imagenAlumno = ImageIO.read(in);
            Image imgAlumno = SwingFXUtils.toFXImage(imagenAlumno, null);

            fotoAlumno.setImage(imgAlumno);
            txtNombre.setText(a.getNombre() + " " + a.getApellidos());
            txtClase.setText(a.getCurso());
            txtEmpresa.setText(a.getIdEmpresa().getNombre());
            txtProfesor.setText(a.getIdProfesor().getNombre());
            txtCentro.setText("Cesur Malaga Este");
            txtDNI.setText(a.getDni());
            txtTelefono.setText(a.getTelefono().toString());
            txtEmail.setText(a.getEmail());
            txtNacimiento.setText(a.getFechaNac().toString());

        } catch (SQLException ex) {
            Logger.getLogger(PerfilAlumnoController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PerfilAlumnoController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();

            } catch (IOException ex) {
                Logger.getLogger(PerfilAlumnoController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void onMouseClickPerfilTutor(MouseEvent mouseEvent) {
        ActionEvent e = new ActionEvent(mouseEvent.getSource(), mouseEvent.getTarget());
        SessionData.setProfesorActual(SessionData.getAlumnoActual().getIdProfesor());
        try {
            escena.switchToPerfilProfesor(e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onMouseClickPerfilEmpresa(MouseEvent mouseEvent) {
        ActionEvent e = new ActionEvent(mouseEvent.getSource(), mouseEvent.getTarget());
        SessionData.setProfesorActual(SessionData.getAlumnoActual().getIdProfesor());
        try {
            SessionData.setEmpresaActual(a.getIdEmpresa());
            escena.switchToPerfilEmpresa(e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnEliminar(ActionEvent event) {
        try {

            s = HibernateUtil.getSessionFactory().openSession();
            Transaction ts = s.beginTransaction();
            s.remove(a);
            ts.commit();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminad@");
            alert.setHeaderText("Eliminado con éxito");
            alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/eliminar.png").toString())));
            alert.setContentText("El alumno: ID[" + a.getId() + "] Nombre: " + a.getNombre() + " y DNI: " + a.getDni() + " ha sido eliminad@ con éxito!");
            alert.showAndWait();

            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            
            SessionData.setAlumnoActual(null);

        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error eliminando alumn@");
            alert.setHeaderText("No ha sido posible eliminar alumn@");
            alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/error.png").toString())));
            alert.show();
        } finally {
            s.close();
        }
    }

    @FXML
    private void btnModificar(ActionEvent event) {
        try {
            SceneController scene = new SceneController();
            scene.switchToModificarAlumno(event);

        } catch (IOException ex) {

            Logger.getLogger(PerfilAlumnoController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
