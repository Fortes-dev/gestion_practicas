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
@Table(name = "empresa")
public class Empresa implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "tutor_empresa", nullable = false)
    private String tutorEmpresa;

    @Column(name = "localizacion_url", nullable = false, length = 800)
    private String localizacionUrl;

    @Column(name = "telefono", nullable = false)
    private Integer telefono;

    @Column(name = "email_tutor", nullable = false)
    private String emailTutor;

    @Lob
    @Column(name = "logo_img", nullable = false)
    private Blob logoImg;

    public Empresa() {
    }

    public Empresa(Long id, String nombre, String tutorEmpresa, String localizacionUrl, Integer telefono, String emailTutor, Blob logoImg) {
        this.id = id;
        this.nombre = nombre;
        this.tutorEmpresa = tutorEmpresa;
        this.localizacionUrl = localizacionUrl;
        this.telefono = telefono;
        this.emailTutor = emailTutor;
        this.logoImg = logoImg;
    }

    public Blob getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(Blob logoImg) {
        this.logoImg = logoImg;
    }

    public String getEmailTutor() {
        return emailTutor;
    }

    public void setEmailTutor(String emailTutor) {
        this.emailTutor = emailTutor;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getLocalizacionUrl() {
        return localizacionUrl;
    }

    public void setLocalizacionUrl(String localizacionUrl) {
        this.localizacionUrl = localizacionUrl;
    }

    public String getTutorEmpresa() {
        return tutorEmpresa;
    }

    public void setTutorEmpresa(String tutorEmpresa) {
        this.tutorEmpresa = tutorEmpresa;
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
        return "Empresa{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tutorEmpresa='" + tutorEmpresa + '\'' +
                ", localizacionUrl='" + localizacionUrl + '\'' +
                ", telefono=" + telefono +
                ", emailTutor='" + emailTutor + '\'' +
                ", logoImg=" + logoImg +
                '}';
    }
}
