package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.customassets.Constants;
import com.mycompany.gestion.practicas.hibernate.SessionData;
import com.mycompany.gestion.practicas.models.Empresa;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

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

        String mapsUrl = urlBuilder(e.getLatitud(), e.getLongitud());
        
        String content = htmlContent(mapsUrl, e.getLocalizacionUrl());

        webEngine = wvDireccionEmpresa.getEngine();

        webEngine.loadContent(content);

        lbNombreEmpresa.setText(e.getNombre());
        lbTutorEmpresa.setText(e.getTutorEmpresa());
        lbEmail.setText(e.getEmailTutor());
        lbTelefono.setText(e.getTelefono().toString());

    }

    private String urlBuilder(Double latitud, Double longitud) {

        var builder = new StringBuilder();

        builder.append(Constants.MAP_API_URL).append(latitud + "," + longitud);
        builder.append(Constants.MAP_API_MARKER).append(latitud + "," + longitud);
        builder.append(Constants.MAP_API_PARAMS);
        builder.append(Constants.API_KEY);

        return builder.toString();

    }

    private String htmlContent(String url, String linkRef) {

        String html = "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "    <head>\n"
                        + "        <title>TODO supply a title</title>\n"
                        + "        <meta charset=\"UTF-8\">\n"
                        + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                        + "    </head>\n"
                        + "    <body>\n"
                        + "        <div>\n"
                        + "           <a href=" + linkRef + ">"
                        + "            <img src='" + url + "'/>"
                        + "           </a>"
                        + "        </div>\n"
                        + "    </body>\n"
                        + "</html>";

        return html;
    }

}
