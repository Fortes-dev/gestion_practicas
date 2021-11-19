/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion.practicas.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

/**
 *
 * @author CarlosFortesMedina
 */
@Entity
@Table(name = "alumno")
public class Alumno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "dni", nullable = false, length = 9)
    private String dni;

    @Column(name = "fecha_nac", nullable = false)
    private java.sql.Date fechaNac;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "telefono", nullable = false)
    private Integer telefono;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa idEmpresa;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_profesor", nullable = false)
    private Profesor idProfesor;

    @Column(name = "horas_fct", nullable = false)
    private Integer horasFct;

    @Column(name = "horas_dual", nullable = false)
    private Integer horasDual;
    
    @Column(name = "curso", nullable = false)
    private String curso;

    @Lob
    @Column(name = "foto_img")
    private Blob fotoImg;

    public Alumno() {
    }

    public Alumno(Long id, String nombre, String apellidos, String dni, java.sql.Date fechaNac, String email, String password, Integer telefono, Empresa idEmpresa, Profesor idProfesor, Integer horasFct,String curso, Integer horasDual, Blob fotoImg) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fechaNac = fechaNac;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.idEmpresa = idEmpresa;
        this.idProfesor = idProfesor;
        this.horasFct = horasFct;
        this.horasDual = horasDual;
        this.fotoImg = fotoImg;
        this.curso = curso;
    }

    public Alumno(String nombre) {
        this.id = 1L;
        this.nombre = nombre;
        this.apellidos = "García";
        this.dni = "3463456867H";
        this.fechaNac = new Date(0L);
        this.email = "email";
        this.password = "password";
        this.telefono = 123456789;
        this.idEmpresa = new Empresa();
        this.idProfesor = new Profesor();
        this.horasFct = 12;
        this.horasDual = 8;
        this.fotoImg = null;
    }

    public Blob getFotoImg() {
        return fotoImg;
    }

    public void setFotoImg(Blob fotoImg) {
        this.fotoImg = fotoImg;
    }

    public Integer getHorasDual() {
        return horasDual;
    }

    public void setHorasDual(Integer horasDual) {
        this.horasDual = horasDual;
    }

    public Integer getHorasFct() {
        return horasFct;
    }

    public void setHorasFct(Integer horasFct) {
        this.horasFct = horasFct;
    }

    public Profesor getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Profesor idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
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

    public java.sql.Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(java.sql.Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", dni='" + dni + '\'' +
                ", curso='" + curso + '\'' +
                ", fechaNac=" + fechaNac +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telefono=" + telefono +
                ", idEmpresa=" + idEmpresa +
                ", idProfesor=" + idProfesor +
                ", horasFct=" + horasFct +
                ", horasDual=" + horasDual +
                ", fotoImg=" + fotoImg +
                '}';
    }
}
