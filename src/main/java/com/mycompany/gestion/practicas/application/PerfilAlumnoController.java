/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;
import org.hibernate.Session;
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

    private SceneController escena = new SceneController();
    @FXML
    private ImageView fotoAlumno;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InputStream in = null;
        try {
            Alumno a = SessionData.getAlumnoActual();
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
            Logger.getLogger(PerfilAlumnoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PerfilAlumnoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(PerfilAlumnoController.class.getName()).log(Level.SEVERE, null, ex);
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
}
