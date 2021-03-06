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
import javafx.scene.input.MouseEvent;
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
    public void switchToLogin(Stage stage) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/login.fxml"));
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
    public void switchToPrincipalAlumno(Stage stage) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/principalAlumno.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Bienvenido "+SessionData.getAlumnoActual().getNombre());
        stage.show();
    }

    /**
     * Nos lleva a la escena principal
     *
     * @param event
     * @throws IOException
     */
    public void switchToPrincipalProfesor(Stage stage) throws IOException {

        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/principalProfesor.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Bienvenido "+SessionData.getProfesorActual().getNombre());
        stage.show();
    }

    /**
     * 
     *
     * @param event
     * @throws IOException
     */
    public void switchToA??adirAlumno(ActionEvent event) throws IOException {
        stage = new Stage();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/annadirAlumno.fxml"));
        scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.getIcons().add(new Image("img/logocesur.png"));
        stage.setResizable(false);
        stage.setTitle("A??adir alumno");
        stage.setScene(scene);
        stage.showAndWait();

    }
    
    public void switchToModificarAlumno(ActionEvent event) throws IOException {
        stage = new Stage();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/modificarAlumno.fxml"));
        scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.getIcons().add(new Image("img/logocesur.png"));
        stage.setResizable(false);
        stage.setTitle("Modificar alumno");
        stage.setScene(scene);
        stage.showAndWait();
    }

    /**
     * 
     *
     * @param event
     * @throws IOException
     */
    public void switchToA??adirEmpresa(ActionEvent event) throws IOException {
        stage = new Stage();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/annadirEmpresa.fxml"));
        scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.getIcons().add(new Image("img/logocesur.png"));
        stage.setResizable(false);
        stage.setTitle("A??adir empresa");
        stage.setScene(scene);
        stage.showAndWait();

    }

    public void switchToA??adirPracticas(ActionEvent event) throws IOException {
        stage = new Stage();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/annadirPracticas.fxml"));
        scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.getIcons().add(new Image("img/logocesur.png"));
        stage.setResizable(false);
        stage.setTitle("A??adir pr??ctica");
        stage.setScene(scene);
        stage.showAndWait();

    }


    public void switchToHistorial(ActionEvent event) throws IOException {

        stage = new Stage();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/historial.fxml"));
        scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.getIcons().add(new Image("img/logocesur.png"));
        stage.setResizable(false);
        stage.setTitle("Historial de pr??cticas");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void switchToPerfilAlumno(ActionEvent event) throws IOException {
        stage = new Stage();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/perfilAlumno.fxml"));
        scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.getIcons().add(new Image("img/logocesur.png"));
        stage.setResizable(false);
        stage.setTitle("Perfil Alumno: "+SessionData.getAlumnoActual().getNombre());
        stage.setScene(scene);
        stage.showAndWait();

    }

    public void switchToPerfilEmpresa(ActionEvent event) throws IOException {
        stage = new Stage();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/perfilEmpresa.fxml"));
        scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.getIcons().add(new Image("img/logocesur.png"));
        stage.setResizable(false);
        stage.setTitle("Perfil empresa: "+SessionData.getEmpresaActual().getNombre());
        stage.setScene(scene);
        stage.showAndWait();

    }

    public void switchToPerfilProfesor(ActionEvent event) throws IOException {
        stage = new Stage();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/perfilProfesor.fxml"));
        scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.getIcons().add(new Image("img/logocesur.png"));
        stage.setResizable(false);
        stage.setTitle("Perfil profesor: "+SessionData.getProfesorActual().getNombre());
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    public void switchToPracticas(ActionEvent event) throws IOException {
           stage =  new Stage();
           root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/practicas.fxml"));
           stage.initModality(Modality.APPLICATION_MODAL);
           scene = new Scene(root);
           stage.setScene(scene);
           stage.getIcons().add(new Image("img/logocesur.png"));
           stage.setResizable(false);
           stage.setTitle("Pr??cticas");
           stage.setScene(scene);
           stage.showAndWait();
       }

}
