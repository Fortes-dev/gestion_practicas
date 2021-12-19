package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.hibernate.HibernateUtil;
import com.mycompany.gestion.practicas.models.Empresa;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import javax.sql.rowset.serial.SerialBlob;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.hibernate.Hibernate;

public class AnnadirEmpresa implements Initializable {
    
    private FileChooser fileChooser = new FileChooser();
    private File selectedFile;
    private Blob blob;
    private BufferedImage bufferedImage;  
    
    
    @FXML
    private ImageView ivLogoEmpresa;
    @FXML
    private TextField tfNombreEmpresa;
    @FXML
    private TextField tfTutorEmpresa;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfTelefono;
    @FXML
    private Button btnAnnadir;
    private Label txtAñadirFoto;
    private Session s;
    @FXML
    private TextField txtLatitud;
    @FXML
    private TextField txtLongitud;
    @FXML
    private TextField txturl;
    @FXML
    private Button añadirFoto;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        añadirFoto.addEventHandler(MouseEvent.MOUSE_CLICKED,
                e -> {
                    var stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    selectedFile = fileChooser.showOpenDialog(stage);

                    try {
                        s = HibernateUtil.getSessionFactory().openSession();
                        s.beginTransaction();
                        blob = Hibernate.getLobCreator(s).createBlob(Files.readAllBytes(Paths.get(selectedFile.getPath())));
                        s.getTransaction().commit();
                        bufferedImage = ImageIO.read(selectedFile);
                        ivLogoEmpresa.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
                        añadirFoto.setText("");
                    } catch (IOException ex) {
                        Logger.getLogger(AnnadirEmpresa.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        s.close();
                    }
                });
    }

    @FXML
    private void btnAnadirHandler(ActionEvent actionEvent) throws IOException {
        Empresa e = new Empresa();
        e.setNombre(tfNombreEmpresa.getText());
        e.setTutorEmpresa(tfTutorEmpresa.getText());
        e.setEmailTutor(tfEmail.getText());
        e.setTelefono(Integer.parseInt(tfTelefono.getText()));
        e.setLocalizacionUrl(txturl.getText());
        e.setLongitud(Double.parseDouble(txtLongitud.getText()));
        e.setLatitud(Double.parseDouble(txtLatitud.getText()));
        e.setLogoImg(blob);

        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction ts = s.beginTransaction();
            s.save(e);
            ts.commit();
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (Exception ex) {

            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("No ha sido posible añadir la nueva tarea");
            alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/error.png").toString())));
            alert.setContentText("No puede contener un campo vació o hay algún campo erróneo");
            alert.show();
        } finally {
            s.close();
        }
    }
}
