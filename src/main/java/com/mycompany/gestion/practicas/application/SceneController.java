/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.hibernate.SessionData;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controlador general de escenas
 *
 * @author medin
 *
 */
public class SceneController {

    /**
     * Elementos del controlador
     */
    private Stage stage = new Stage();
    private Scene scene;
    private Parent root;

    /**
     * Nos lleva a la escena de login
     *
     * @param event
     * @throws IOException
     */
    public void switchToLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Nos lleva a la escena de registro
     *
     * @param event
     * @throws IOException
     */
    public void switchToPrincipalAlumno(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/principalAlumno.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Nos lleva a la escena principal
     *
     * @param event
     * @throws IOException
     */
    public void switchToPrincipalProfesor(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/principalProfesor.fxml"));
        scene = new Scene(root);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.getIcons().add(new Image("img/logocesur.png"));
        stage.setResizable(false);
        stage.setTitle("Bienvenido " + SessionData.getProfesorActual().getNombre());
        stage.show();
    }

    /**
     * Nos lleva a la escena de crud de ongs
     *
     * @param event
     * @throws IOException
     */
    public void switchToAñadirAlumno(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/anadirAlumno.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Nos lleva a la escena de lista de ongs
     *
     * @param event
     * @throws IOException
     */
    public void switchToAñadirEmpresa(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/annadirEmpresa.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAñadirPracticas(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/añadirPracticas.fxml"));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.getIcons().add(new Image("img/logocesur.png"));
        stage.setResizable(false);
        stage.setTitle("Añadir práctica");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHistorial(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/historial.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToPerfilAlumno(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/perfilAlumno.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToPerfilEmpresa(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/perfilEmpresa.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToPerfilProfesor(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/perfilProfesor.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToPracticas(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/practicas.fxml"));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.getIcons().add(new Image("img/logocesur.png"));
        stage.setResizable(false);
        stage.setTitle("Prácticas");
        stage.setScene(scene);
        stage.show();
    }

}
