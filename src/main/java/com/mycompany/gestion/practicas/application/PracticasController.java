/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.customassets.ToggleSwitch;
import com.mycompany.gestion.practicas.hibernate.HibernateUtil;
import com.mycompany.gestion.practicas.hibernate.SessionData;
import com.mycompany.gestion.practicas.models.Practica;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.StackPane.setAlignment;
import javafx.scene.paint.Color;
import org.hibernate.Session;

/**
 * FXML Controller class
 *
 * @author medin
 */
public class PracticasController implements Initializable {

    @FXML
    private DatePicker datePicker;

    ToggleSwitch toggle = new ToggleSwitch();

    @FXML
    private HBox hbox1;
    @FXML
    private Button btnGuardar;
    @FXML
    private AnchorPane pane;
    @FXML
    private ChoiceBox<String> tipopractica;
    @FXML
    private TextArea descripcion;
    @FXML
    private Spinner<Integer> horas;
    @FXML
    private TextArea observaciones;
    @FXML
    private Button btnEliminar;

    Session s;

    Practica pr = new Practica();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tipopractica.setItems(FXCollections.observableArrayList("Dual", "FCT"));
        tipopractica.getSelectionModel().select(SessionData.getPracticaActual().getTipo());

        SpinnerValueFactory svf = new IntegerSpinnerValueFactory(1, 8);
        horas.setValueFactory(svf);
        horas.getValueFactory().setValue(SessionData.getPracticaActual().getHorasEmpleadas());

        toggle.setMaxWidth(50);
        toggle.setMaxHeight(28);

        hbox1.getChildren().add(3, toggle);

        descripcion.setWrapText(true);
        observaciones.setWrapText(true);

        disableOptions();

        observaciones.setText(SessionData.getPracticaActual().getObservaciones());
        descripcion.setText(SessionData.getPracticaActual().getDescripcion());

        setData();

        toggle.setOnMouseClicked((Event t) -> {
            if (toggle.getState()) {

                toggle.getButton().setStyle(toggle.getButtonStyleOff());
                toggle.getBack().setFill(Color.valueOf("#ced5da"));
                setAlignment(toggle.getButton(), Pos.CENTER_LEFT);

                toggle.setState(false);
                disableOptions();
            } else {

                toggle.getButton().setStyle(toggle.getButtonStyleOn());
                toggle.getBack().setFill(Color.valueOf("#80C49E"));
                setAlignment(toggle.getButton(), Pos.CENTER_RIGHT);

                toggle.setState(true);
                enableOptions();
            }
        });
    }

    private void disableOptions() {
        datePicker.setDisable(true);
        horas.setDisable(true);
        tipopractica.setDisable(true);
        descripcion.setEditable(false);
        observaciones.setEditable(false);
    }

    private void enableOptions() {
        datePicker.setDisable(false);
        horas.setDisable(false);
        tipopractica.setDisable(false);
        descripcion.setEditable(true);
        observaciones.setEditable(true);
    }

    @FXML
    private void btnGuardarHandler(ActionEvent event) {

        setData();

        try {

            s = HibernateUtil.getSessionFactory().openSession();
            var tr = s.beginTransaction();
            s.update(pr);
            tr.commit();

        } catch (Exception e) {

            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("No ha sido posible modificar la tarea");
            alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/error.png").toString())));
            alert.setContentText("No puede contener un campo vació o hay algún campo erróneo");
            alert.show();
        } finally {
            s.close();
        }

    }

    @FXML
    private void btnEliminarHandler(ActionEvent event) {

    }

    private void setData() {
        Date date = Date.valueOf(datePicker.getValue());

        pr.setId(SessionData.getPracticaActual().getId());
        pr.setIdAlumno(SessionData.getAlumnoActual());
        pr.setFecha(date);
        pr.setTipo(tipopractica.getValue());
        pr.setHorasEmpleadas(horas.getValue());
        pr.setDescripcion(descripcion.getText());
        pr.setObservaciones(observaciones.getText());
    }

}
