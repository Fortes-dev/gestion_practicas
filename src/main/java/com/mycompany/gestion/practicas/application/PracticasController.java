/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.customassets.ToggleSwitch;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.StackPane.setAlignment;
import javafx.scene.paint.Color;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tipopractica.setItems(FXCollections.observableArrayList("Dual", "FCT"));
        tipopractica.getSelectionModel().selectFirst();

        SpinnerValueFactory svf = new IntegerSpinnerValueFactory(1, 8);
        horas.setValueFactory(svf);
        horas.getValueFactory().setValue(8);


        toggle.setMaxWidth(50);
        toggle.setMaxHeight(28);

        hbox1.getChildren().add(3, toggle);

        descripcion.setWrapText(true);
        observaciones.setWrapText(true);
        
        disableOptions();


        descripcion.setText("hola que tal");

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

    }

    @FXML
    private void btnEliminarHandler(ActionEvent event) {

    }

}
