/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestion.practicas.application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
/**
 * FXML Controller class
 *
 * @author hierr
 */
public class HistorialController implements Initializable {


    @FXML
    private AnchorPane vistaHistorial;
    @FXML
    private Label nombreAlumno;
    @FXML
    private ImageView logoEmpresaImg;
    @FXML
    private TableView<?> tablaPracticas;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colIdAlumno;
    @FXML
    private TableColumn<?, ?> colTipo;
    @FXML
    private TableColumn<?, ?> colHoras;
    @FXML
    private TableColumn<?, ?> colDescripcion;
    @FXML
    private TableColumn<?, ?> colObservaciones;
    @FXML
    private TextField TextFieldBuscador;
    @FXML
    private Button botonAnadir;
    @FXML
    private Label claseAlumno;
    @FXML
    private ChoiceBox<String> choiceBoxTipo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceBoxTipo.getItems().addAll("FCT","Dual");
        choiceBoxTipo.getSelectionModel().selectFirst();
    }    
    
}
