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
public class Empresa implements Serializable {
    
    private Long id;
    private String nombre;
    private String tutor_empresa;
    private String localizacion_url;
    private int telefono;
    private String email_tutor;
    private Blob logo_img;

    public Empresa() {
    }

    public Empresa(Long id, String nombre, String tutor_empresa, String localizacion_url, int telefono, String email_tutor, Blob logo_img) {
        this.id = id;
        this.nombre = nombre;
        this.tutor_empresa = tutor_empresa;
        this.localizacion_url = localizacion_url;
        this.telefono = telefono;
        this.email_tutor = email_tutor;
        this.logo_img = logo_img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTutor_empresa() {
        return tutor_empresa;
    }

    public void setTutor_empresa(String tutor_empresa) {
        this.tutor_empresa = tutor_empresa;
    }

    public String getLocalizacion_url() {
        return localizacion_url;
    }

    public void setLocalizacion_url(String localizacion_url) {
        this.localizacion_url = localizacion_url;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail_tutor() {
        return email_tutor;
    }

    public void setEmail_tutor(String email_tutor) {
        this.email_tutor = email_tutor;
    }

    public Blob getLogo_img() {
        return logo_img;
    }

    public void setLogo_img(Blob logo_img) {
        this.logo_img = logo_img;
    }

    @Override
    public String toString() {
        return "Empresa{" + "id=" + id + ", nombre=" + nombre + ", tutor_empresa=" + tutor_empresa + ", localizacion_url=" + localizacion_url + ", telefono=" + telefono + ", email_tutor=" + email_tutor + '}';
    }
    
    
}
