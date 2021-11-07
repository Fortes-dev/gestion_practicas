package com.mycompany.gestion.practicas.application;

import com.mycompany.gestion.practicas.customassets.ToggleSwitch;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        ToggleSwitch boton = new ToggleSwitch();
        scene = new Scene(loadFXML("practicas"));
        stage.setResizable(false);
        stage.setTitle("CESUR Gestión de prácticas");
        //stage.getIcons().add(new Image("img/logocesur.png"));
        stage.setScene(scene);

        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}