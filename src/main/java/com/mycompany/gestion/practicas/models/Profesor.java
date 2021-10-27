/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion.practicas.models;

import java.io.Serializable;
import java.sql.Blob;

/**
 *
 * @author CarlosFortesMedina
 */
public class Profesor implements Serializable {
    private long id;
    private String nombre;
    private String email;
    private String password;
    private Blob foto_img;
    
    public Profesor() {
    }

    public Profesor(long id, String nombre, String email, String password, Blob foto_img) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.foto_img = foto_img;
    }

    @Override
    public String toString() {
        return "Profesor{" + "id=" + id + ", nombre=" + nombre + ", email=" + email + ", password=" + password + '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Blob getFoto_img() {
        return foto_img;
    }

    public void setFoto_img(Blob foto_img) {
        this.foto_img = foto_img;
    }

    
}
