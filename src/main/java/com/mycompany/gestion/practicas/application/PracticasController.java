/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion.practicas.application;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import com.mycompany.gestion.practicas.customassets.ToggleSwitch;
import com.mycompany.gestion.practicas.hibernate.HibernateUtil;
import com.mycompany.gestion.practicas.hibernate.SessionData;
import com.mycompany.gestion.practicas.models.Practica;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.stage.Stage;
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
    private JFXComboBox<String> tipopractica;
    @FXML
    private TextArea descripcion;
    @FXML
    private JFXSlider horas;
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

        toggle.setMaxWidth(50);
        toggle.setMaxHeight(28);

        horas.setValue(SessionData.getPracticaActual().getHorasEmpleadas());

        hbox1.getChildren().add(3, toggle);

        descripcion.setWrapText(true);
        observaciones.setWrapText(true);

        disableOptions();
        Date date =SessionData.getPracticaActual().getFecha();
        LocalDate local = date.toLocalDate();
        datePicker.setValue(local);
        observaciones.setText(SessionData.getPracticaActual().getObservaciones());
        descripcion.setText(SessionData.getPracticaActual().getDescripcion());

        

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

        datePicker.setOpacity(0.9);
        tipopractica.setOpacity(0.9);
        horas.setOpacity(0.9);
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

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Guardado");
            alert.setHeaderText("Guardado con éxito");
            alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/confirmacion.png").toString())));
            alert.setContentText("La práctica: ID[" + pr.getId() + "] Fecha: " + pr.getFecha() + " ha sido modificada con éxito!");
            alert.showAndWait();

            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

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

        setData();

        try {

            s = HibernateUtil.getSessionFactory().openSession();
            var tr = s.beginTransaction();
            s.remove(pr);
            tr.commit();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminado");
            alert.setHeaderText("Eliminado con éxito");
            alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/eliminar.png").toString())));
            alert.setContentText("La práctica: ID[" + pr.getId() + "] Fecha: " + pr.getFecha() + " ha sido eliminada con éxito!");
            alert.showAndWait();
            
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        } catch (Exception e) {

            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("No ha sido posible eliminar");
            alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/error.png").toString())));
            alert.setContentText("No puede contener un campo vació o hay algún campo erróneo");
            alert.show();

        } finally {
            s.close();
        }

    }

    private void setData() {
        Date date = Date.valueOf(datePicker.getValue());

        pr.setId(SessionData.getPracticaActual().getId());
        pr.setIdAlumno(SessionData.getAlumnoActual());
        pr.setFecha(date);
        pr.setTipo(tipopractica.getValue());
        pr.setHorasEmpleadas((int) horas.getValue());
        pr.setDescripcion(descripcion.getText());
        pr.setObservaciones(observaciones.getText());
    }

}
