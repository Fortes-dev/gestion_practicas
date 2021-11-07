/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion.practicas.application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author medin
 */
public class AñadirPracticasController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private HBox hbox1;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button btnAñadir;
    @FXML
    private ChoiceBox<String> tipopractica;
    @FXML
    private TextArea descripcion;
    @FXML
    private Spinner<Integer> horas;
    @FXML
    private TextArea observaciones;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipopractica.setItems(FXCollections.observableArrayList("Dual", "FCT"));
        tipopractica.getSelectionModel().selectFirst();

        SpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 8);
        horas.setValueFactory(svf);
        horas.getValueFactory().setValue(8);
    }

    @FXML
    private void btnAñadirHandler(ActionEvent event) {
    }

}
