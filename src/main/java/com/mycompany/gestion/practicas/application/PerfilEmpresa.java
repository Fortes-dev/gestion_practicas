package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.hibernate.HibernateUtil;
import com.mycompany.gestion.practicas.hibernate.SessionData;
import com.mycompany.gestion.practicas.models.Empresa;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.ResourceBundle;

public class PerfilEmpresa implements Initializable {

    @FXML
    private ImageView ivLogoEmpresa;
    @FXML
    private Label lbNombreEmpresa;
    @FXML
    private Label lbTutorEmpresa;
    @FXML
    private Label lbEmail;
    @FXML
    private Label lbTelefono;

    private Session s;
    private SceneController escena = new SceneController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        s = HibernateUtil.getSessionFactory().openSession();

        Query<Empresa> q = s.createQuery("FROM Empresa e where e.id=:id");
        q.setParameter("id", SessionData.getEmpresaActual().getId());

        Empresa e = q.list().get(0);

        lbNombreEmpresa.setText(e.getNombre());
        lbTutorEmpresa.setText(e.getTutorEmpresa());
        lbEmail.setText(e.getEmailTutor());
        lbTelefono.setText(e.getTelefono().toString());

        s.close();
    }
}
