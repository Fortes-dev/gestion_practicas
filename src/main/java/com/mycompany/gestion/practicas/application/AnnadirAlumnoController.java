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
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * FXML Controller class
 *
 * @author hierr
 */
public class AnnadirAlumnoController implements Initializable {

    @FXML
    private TextField txtFieldNombre;
    @FXML
    private TextField txtFieldCurso;
    @FXML
    private TextField txtFieldCentro;
    @FXML
    private ImageView imgAñadirFoto;
    private Label txtAñadirFoto;
    @FXML
    private TextField txtFieldDNI;
    @FXML
    private TextField txtFieldTelefono;
    @FXML
    private TextField txtFieldEmail;
    @FXML
    private DatePicker DatepickerNacimiento;
    @FXML
    private TextField txtFieldApellidos;
    @FXML
    private ChoiceBox<String> cbEmpresa;
    @FXML
    private ChoiceBox<String> cbNombreTutor;
    @FXML
    private TextField txtFieldContraseña;
    @FXML
    private Button añadirImg;
    @FXML
    private Button btnAnadir;

    private FileChooser fileChooser = new FileChooser();
    private File selectedFile;
    private Blob blob;
    private BufferedImage bufferedImage;
    private InputStream inputStream;
    Session s;

    /**
     * Initializes the controller class.
     */


    Session s;
    @FXML
    private Button btnAnadir;
   

    /**
     * Creo ObsevableLists de Empresas y Profesores a través de consultas HQL
     * Los resultados de esas consultas aparecen en los ChoiceBox para que el
     * usuario los pueda seleccionar
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        añadirImg.addEventHandler(MouseEvent.MOUSE_CLICKED,
                e -> {
                    var stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    selectedFile = fileChooser.showOpenDialog(stage);

                    try {
                        s = HibernateUtil.getSessionFactory().openSession();
                        s.beginTransaction();
                        blob = Hibernate.getLobCreator(s).createBlob(Files.readAllBytes(Paths.get(selectedFile.getPath())));
                        s.getTransaction().commit();
                        bufferedImage = ImageIO.read(selectedFile);
                        imgAñadirFoto.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
                        añadirImg.setText("");
                    } catch (IOException ex) {
                        Logger.getLogger(AnnadirEmpresa.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        s.close();
                    }
                });

        s = HibernateUtil.getSessionFactory().openSession();
        try {
            ObservableList<String> empresas = FXCollections.observableArrayList();//Contenido del ChoiceBox de Empresas
            Query<Empresa> q = s.createQuery("FROM Empresa");
            q.list().forEach((e) -> empresas.add(e.getNombre()));
            cbEmpresa.setItems(empresas);

            ObservableList<String> profesores = FXCollections.observableArrayList();//Contenido del ChoiceBox de Profesores
            Query<Profesor> q2 = s.createQuery("FROM Profesor");
            q2.list().forEach((p) -> profesores.add(p.getNombre()));
            cbNombreTutor.setItems(profesores);
            cbNombreTutor.getSelectionModel().select(SessionData.getProfesorActual().getNombre());
        } finally {
            s.close();
        }

    }

    @FXML
    private void btnAceptar(ActionEvent event) {

        try {
// Busco el id de la empresa seleccionada para añadirlo a la tabla de alumno
            s = HibernateUtil.getSessionFactory().openSession();
            Query<Empresa> q = s.createQuery("FROM Empresa e WHERE e.nombre=:n");
            q.setParameter("n", cbEmpresa.getValue());

// Busco el id del tutor seleccionado para añadirlo a la tabla de alumno    
            Query<Profesor> q2 = s.createQuery("FROM Profesor e WHERE e.nombre=:n");
            q2.setParameter("n", cbNombreTutor.getValue());

            Alumno alumno = new Alumno();

            alumno.setNombre(txtFieldNombre.getText());
            alumno.setApellidos(txtFieldApellidos.getText());
            alumno.setDni(txtFieldDNI.getText());
            alumno.setCurso(txtFieldCurso.getText());
            alumno.setFechaNac(Date.valueOf(DatepickerNacimiento.getValue()));
            alumno.setEmail(txtFieldEmail.getText());
            alumno.setTelefono(Integer.parseInt(txtFieldTelefono.getText()));
            alumno.setPassword(txtFieldContraseña.getText());
            alumno.setIdEmpresa(q.list().get(0));
            alumno.setIdProfesor(q2.list().get(0));
            alumno.setHorasDual(15);
            alumno.setHorasFct(15);
            alumno.setFotoImg(blob);

            Transaction tr = s.beginTransaction();
            s.save(alumno);
            tr.commit();

            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error añadiendo alumno");
            alert.setHeaderText("No ha sido posible añadir al alumno");
            alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/error.png").toString())));
            alert.show();
        } finally {
            s.close();
        }

    }

}
