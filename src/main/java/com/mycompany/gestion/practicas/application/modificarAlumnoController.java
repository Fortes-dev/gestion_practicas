/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestion.practicas.application;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.gestion.practicas.hibernate.HibernateUtil;
import com.mycompany.gestion.practicas.hibernate.SessionData;
import com.mycompany.gestion.practicas.models.Alumno;
import com.mycompany.gestion.practicas.models.Empresa;
import com.mycompany.gestion.practicas.models.Profesor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Date;
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
public class modificarAlumnoController implements Initializable {

    @FXML
    private JFXTextField txtFieldNombre;
    @FXML
    private JFXTextField txtFieldApellidos;
    @FXML
    private JFXTextField txtFieldCurso;
    @FXML
    private JFXComboBox<String> cbEmpresa;
    @FXML
    private JFXTextField txtFieldCentro;
    @FXML
    private JFXTextField txtFieldContraseña;
    @FXML
    private ImageView imgAñadirFoto;
    @FXML
    private JFXTextField txtFieldDNI;
    @FXML
    private JFXTextField txtFieldTelefono;
    @FXML
    private JFXTextField txtFieldEmail;
    @FXML
    private DatePicker DatepickerNacimiento;
    @FXML
    private JFXComboBox<String> cbNombreTutor;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button añadirImg;

    /**
     * Initializes the controller class.
     */
    Session s;
    Alumno a = SessionData.getAlumnoActual();

    private FileChooser fileChooser = new FileChooser();
    private File selectedFile;
    private Blob blob;
    private BufferedImage bufferedImage;
    private InputStream in;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            s = HibernateUtil.getSessionFactory().openSession();

            blob = a.getFotoImg();
            
            in = a.getFotoImg().getBinaryStream();
            BufferedImage imagen = ImageIO.read(in);
            Image img = SwingFXUtils.toFXImage(imagen, null);
            //      HAGO UNA CONSULTA DE EMPRESAS Y LAS METO EN EL CHOICEBOX USANDO LA OBSERVABLELIST
            ObservableList<String> empresas = FXCollections.observableArrayList();
            Query<Empresa> q = s.createQuery("FROM Empresa");
            q.list().forEach((e) -> empresas.add(e.getNombre()));
            cbEmpresa.setItems(empresas);

            //      HAGO UNA CONSULTA DE TUTORES Y LOS METO EN EL CHOICEBOX USANDO LA OBSERVABLELIST
            ObservableList<String> profesores = FXCollections.observableArrayList();
            Query<Profesor> q2 = s.createQuery("FROM Profesor");
            q2.list().forEach((p) -> profesores.add(p.getNombre()));
            cbNombreTutor.setItems(profesores);

            //      HAGO UNA CONSULTA PARA VER QUÉ EMPRESA TIENE ASIGNADA EL ALUMNO Y SE LA PASO AL CHOICEBOX      
//            Query<Empresa> q3 = s.createQuery("FROM Empresa e WHERE e.id=:n");
//            q.setParameter("n", a.getIdEmpresa());
            cbEmpresa.setValue(a.getIdEmpresa().getNombre());

            //      HAGO UNA CONSULTA PARA VER QUÉ TUTOR TIENE ASIGNADO EL ALUMNO Y SE LA PASO AL CHOICEBOX 
//            Query<Profesor> q4 = s.createQuery("FROM Profesor p WHERE p.id=:n");
//            q.setParameter("n", a.getIdProfesor());
            cbNombreTutor.setValue(a.getIdProfesor().getNombre());

//      PINTO LOS ATRIBUTOS DEL ALUMNO EN SUS RESPECTIVOS CAMPOS  
            txtFieldNombre.setText(a.getNombre());
            txtFieldApellidos.setText(a.getApellidos());
            txtFieldCentro.setText("Cesur Málaga Este");
            txtFieldContraseña.setText(a.getPassword());
            txtFieldDNI.setText(a.getDni());
            txtFieldTelefono.setText(a.getTelefono().toString());
            txtFieldEmail.setText(a.getEmail());
            DatepickerNacimiento.setValue(a.getFechaNac().toLocalDate());
            imgAñadirFoto.setImage(img);
            txtFieldCurso.setText(a.getCurso());

        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error cargando datos");
            alert.setHeaderText("No ha sido posible cargar los datos del alumno");
            alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/error.png").toString())));
            alert.show();
        } finally {
            s.close();
        }

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
    }

    @FXML
    private void btnAceptar(ActionEvent event) {

        try {
            
            s = HibernateUtil.getSessionFactory().openSession();
            a.setNombre(txtFieldNombre.getText());
            a.setApellidos(txtFieldApellidos.getText());
            a.setCurso(txtFieldCurso.getText());
            a.setPassword(txtFieldContraseña.getText());
            a.setDni(txtFieldDNI.getText());
            a.setTelefono(Integer.parseInt(txtFieldTelefono.getText()));
            a.setEmail(txtFieldEmail.getText());
            a.setFechaNac(Date.valueOf(DatepickerNacimiento.getValue()));
            a.setFotoImg(blob);

//            HAGO UNA CONSULTA PARA ENCONTRAR EL ID DEL PROFESOR CUYO NOMBRE APARECE COMO STRING EN EL CHOICEBOX
            Query<Profesor> q = s.createQuery("FROM Profesor p WHERE p.nombre=:n");
            q.setParameter("n", cbNombreTutor.getValue());
            a.setIdProfesor(q.list().get(0));//Actualizo el IdProfesor de alumno almacenado en el SessionData con el objeto profesor que me devuelve la consulta

//            HAGO UNA CONSULTA PARA ENCONTRAR EL ID DE LA EMPRESA CUYO NOMBRE APARECE COMO STRING EN EL CHOICEBOX
            Query<Empresa> q2 = s.createQuery("FROM Empresa e WHERE e.nombre=:n");
            q2.setParameter("n", cbEmpresa.getValue());
            a.setIdEmpresa(q2.list().get(0));//Actualizo el IdEmpresa de alumno almacenado en el SessionData con el objeto empresa que me devuelve la consulta

            Transaction ts = s.beginTransaction();
            s.update(a);
            ts.commit();
            
            SessionData.setAlumnoActual(a);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modificad@");
            alert.setHeaderText("Modificad@ con éxito");
            alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/confirmacion.png").toString())));
            alert.setContentText("El/La alumn@: ID[" + a.getId() + "] Nombre: " + a.getNombre() + " y DNI: " + a.getDni() + " ha sido modificad@ con éxito!");
            alert.showAndWait();

            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error actualizando datos");
            alert.setHeaderText("No ha sido posible modificar los datos del alumno");
            alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/error.png").toString())));
            alert.show();

        } finally {
            s.close();
        }

    }

}
