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
        stage.setTitle("Bienvenido "+SessionData.getAlumnoActual().getNombre());
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
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
    public void switchToAñadirAlumno(ActionEvent event) throws IOException {
        stage = new Stage();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/annadirAlumno.fxml"));
        scene = new Scene(root);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.getIcons().add(new Image("img/logocesur.png"));
        stage.setResizable(false);
        stage.setTitle("Añadir alumno");
        stage.setScene(scene);
        stage.showAndWait();

    }

    /**
     * 
     *
     * @param event
     * @throws IOException
     */
    public void switchToAñadirEmpresa(ActionEvent event) throws IOException {
        stage = new Stage();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/annadirEmpresa.fxml"));
        scene = new Scene(root);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.getIcons().add(new Image("img/logocesur.png"));
        stage.setResizable(false);
        stage.setTitle("Añadir Empresa");
        stage.setScene(scene);
        stage.showAndWait();

    }

    public void switchToAñadirPracticas(ActionEvent event) throws IOException {
        stage = new Stage();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/annadirPracticas.fxml"));
        scene = new Scene(root);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.getIcons().add(new Image("img/logocesur.png"));
        stage.setResizable(false);
        stage.setTitle("Añadir practica");
        stage.setScene(scene);
        stage.showAndWait();

    }


    public void switchToHistorial(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/historial.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToPerfilAlumno(ActionEvent event) throws IOException {
        stage = new Stage();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/perfilAlumno.fxml"));
        scene = new Scene(root);
        stage.initModality(Modality.WINDOW_MODAL);
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
        stage.initModality(Modality.WINDOW_MODAL);
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
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.getIcons().add(new Image("img/logocesur.png"));
        stage.setResizable(false);
        stage.setTitle("Perfil profesor: "+SessionData.getProfesorActual().getNombre());
        stage.setScene(scene);
        stage.showAndWait();
    }

      public void switchToPracticasMouse(MouseEvent event) throws IOException {
           stage =  new Stage();
           root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/practicas.fxml"));
           stage.initModality(Modality.WINDOW_MODAL);
           scene = new Scene(root);
           stage.setScene(scene);
           stage.getIcons().add(new Image("img/logocesur.png"));
           stage.setResizable(false);
           stage.setTitle("Prácticas");
           stage.setScene(scene);
           stage.showAndWait();
       }
    
    public void switchToPracticas(ActionEvent event) throws IOException {
           stage =  new Stage();
           root = FXMLLoader.load(getClass().getClassLoader().getResource("com/mycompany/gestion/practicas/application/practicas.fxml"));
           stage.initModality(Modality.WINDOW_MODAL);
           scene = new Scene(root);
           stage.setScene(scene);
           stage.getIcons().add(new Image("img/logocesur.png"));
           stage.setResizable(false);
           stage.setTitle("Prácticas");
           stage.setScene(scene);
           stage.showAndWait();
       }

}
