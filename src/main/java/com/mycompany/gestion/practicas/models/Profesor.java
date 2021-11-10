/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion.practicas.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;

/**
 *
 * @author CarlosFortesMedina
 */
@Entity
@Table(name = "profesor")
public class Profesor implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Lob
    @Column(name = "foto_img", nullable = false)
    private Blob fotoImg;

    public Profesor() {
    }

    public Profesor(Long id, String nombre, String email, String password, Blob fotoImg) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.fotoImg = fotoImg;
    }

    public Blob getFotoImg() {
        return fotoImg;
    }

    public void setFotoImg(Blob fotoImg) {
        this.fotoImg = fotoImg;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fotoImg=" + fotoImg +
                '}';
    }
}
