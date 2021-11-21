package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.hibernate.HibernateUtil;
import com.mycompany.gestion.practicas.models.Empresa;
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
import java.util.ResourceBundle;

public class AnnadirEmpresa implements Initializable {

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

    private Session s;
    private SceneController escena = new SceneController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    private void btnAnadirHandler(ActionEvent actionEvent) {
        Empresa e = new Empresa();
        e.setNombre(tfNombreEmpresa.getText());
        e.setTutorEmpresa(tfTutorEmpresa.getText());
        e.setEmailTutor(tfEmail.getText());
        e.setTelefono(Integer.parseInt(tfTelefono.getText()));
        e.setLocalizacionUrl(null);
        e.setLogoImg(null);

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
