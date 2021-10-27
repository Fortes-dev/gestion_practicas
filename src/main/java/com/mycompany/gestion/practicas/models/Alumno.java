/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion.practicas.models;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

/**
 *
 * @author CarlosFortesMedina
 */
public class Alumno implements Serializable {
    
    private Long id;
    private String nombre;
    private String apellidos;
    private String dni;
    private Date fecha_nac;
    private String email;
    private String password;
    private int telefono;
    private Long id_empresa;
    private Long id_profesor;
    private int horas_fct;
    private int horas_dual;
    private Blob foto_img;

    public Alumno() {
    }

    public Alumno(Long id, String nombre, String apellidos, String dni, Date fecha_nac, String email, String password, int telefono, Long id_empresa, Long id_profesor, int horas_fct, int horas_dual, Blob foto_img) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fecha_nac = fecha_nac;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.id_empresa = id_empresa;
        this.id_profesor = id_profesor;
        this.horas_fct = horas_fct;
        this.horas_dual = horas_dual;
        this.foto_img = foto_img;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Long getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Long id_empresa) {
        this.id_empresa = id_empresa;
    }

    public Long getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(Long id_profesor) {
        this.id_profesor = id_profesor;
    }

    public int getHoras_fct() {
        return horas_fct;
    }

    public void setHoras_fct(int horas_fct) {
        this.horas_fct = horas_fct;
    }

    public int getHoras_dual() {
        return horas_dual;
    }

    public void setHoras_dual(int horas_dual) {
        this.horas_dual = horas_dual;
    }

    public Blob getFoto_img() {
        return foto_img;
    }

    public void setFoto_img(Blob foto_img) {
        this.foto_img = foto_img;
    }

    @Override
    public String toString() {
        return "Alumno{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", fecha_nac=" + fecha_nac + ", email=" + email + ", telefono=" + telefono + '}';
    }
    
    
    
}
