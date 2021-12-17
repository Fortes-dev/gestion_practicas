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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

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
    @FXML
    private WebView wvDireccionEmpresa;

    private WebEngine webEngine;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Empresa e = SessionData.getEmpresaActual();
        String apiKey = "AIzaSyDckbK-ihblb2K5olTERcS_G6HnQ_3t4Ao";
        String iframe = "https://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=13&size=600x300&maptype=roadmap\n"
                + "&markers=color:blue%7Clabel:S%7C40.702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318\n"
                + "&markers=color:red%7Clabel:C%7C40.718217,-73.998284\n"
                + "&key=" + apiKey;

        String content = "<!DOCTYPE html>\n"
                + "<!--\n"
                + "To change this license header, choose License Headers in Project Properties.\n"
                + "To change this template file, choose Tools | Templates\n"
                + "and open the template in the editor.\n"
                + "-->\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>TODO supply a title</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div>\n"
                + "            <iframe\n"
                + "  width=\"600\"\n"
                + "  height=\"450\"\n"
                + "  style=\"border:0\"\n"
                + "  loading=\"lazy\"\n"
                + "  allowfullscreen\n"
                + "  src=\"https://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=13&size=600x300&maptype=roadmap&markers=color:blue%7Clabel:S%7C40.702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318&markers=color:red%7Clabel:C%7C40.718217,-73.998284&key=AIzaSyDckbK-ihblb2K5olTERcS_G6HnQ_3t4Ao&signature=KVZQEip_A8ZaQH1ZILCHI2pecw8="
                + "    &q=Space+Needle,Seattle+WA\">\n" 
                +    "</iframe>"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>";

        webEngine = wvDireccionEmpresa.getEngine();

        webEngine.loadContent(content);

        lbNombreEmpresa.setText(e.getNombre());
        lbTutorEmpresa.setText(e.getTutorEmpresa());
        lbEmail.setText(e.getEmailTutor());
        lbTelefono.setText(e.getTelefono().toString());

    }
}
