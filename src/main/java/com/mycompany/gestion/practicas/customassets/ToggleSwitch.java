/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion.practicas.customassets;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ToggleSwitch extends StackPane {
    private Rectangle back = new Rectangle(50, 10, Color.RED);
    private Button button = new Button();
    private String buttonStyleOff = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: WHITE;";
    private String buttonStyleOn = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: #00893d;";
    private boolean state;

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return state;
    }

    public Rectangle getBack() {
        return back;
    }

    public Button getButton() {
        return button;
    }

    public String getButtonStyleOff() {
        return buttonStyleOff;
    }

    public String getButtonStyleOn() {
        return buttonStyleOn;
    }

    public boolean isState() {
        return state;
    }
    
    

    private void init() {
        getChildren().addAll(back, button);
        //setMinSize(30, 15);
        back.maxWidth(50);
        back.minWidth(50);
        back.maxHeight(10);
        back.minHeight(10);
        back.setArcHeight(back.getHeight());
        back.setArcWidth(back.getHeight());
        back.setFill(Color.valueOf("#ced5da"));
        Double r = 2.0;
        setAlignment(button, Pos.CENTER_LEFT);
        button.setShape(new Circle(2));
        button.setMaxSize(20, 20);
        button.setMinSize(20, 20);
        button.setStyle(buttonStyleOff);
    }

    public ToggleSwitch() {
        init();


        button.setFocusTraversable(false);

    }
}