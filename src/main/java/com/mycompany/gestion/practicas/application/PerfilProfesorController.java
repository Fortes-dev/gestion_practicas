/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.hibernate.SessionData;
import com.mycompany.gestion.practicas.models.Profesor;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author medin
 */
public class PerfilProfesorController implements Initializable {

    @FXML
    private Label nombre;
    @FXML
    private Label centro;
    @FXML
    private Label email;
    @FXML
    private ImageView imgPerfil;

    private Profesor p = SessionData.getProfesorActual();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        InputStream in = null;
        try {
            in = p.getFotoImg().getBinaryStream();
            BufferedImage imagenAlumno = ImageIO.read(in);
            Image imgProfesor = SwingFXUtils.toFXImage(imagenAlumno, null);

            imgPerfil.setImage(imgProfesor);
            nombre.setText(p.getNombre());
            email.setText(p.getEmail());

        } catch (SQLException ex) {
            Logger.getLogger(PerfilProfesorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PerfilProfesorController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(PerfilProfesorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
