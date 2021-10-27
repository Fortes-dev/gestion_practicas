/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion.practicas.models;

import java.io.Serializable;

/**
 *
 * @author CarlosFortesMedina
 */
public class Practica implements Serializable {
    private long id;
    private long id_alumno;
    private String fecha;
    private String tipo;
    private int horas_empleadas;
    private String descripcion;
    private String observaciones;
    
    public Practica(long id_alumno, String fecha, String tipo, int horas_empleadas, String descripcion, String observaciones) {
        this.id_alumno = id_alumno;
        this.fecha = fecha;
        this.tipo = tipo;
        this.horas_empleadas = horas_empleadas;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Practica{" + "id=" + id + ", id_alumno=" + id_alumno + ", fecha=" + fecha + ", tipo=" + tipo + ", horas_empleadas=" + horas_empleadas + ", descripcion=" + descripcion + ", observaciones=" + observaciones + '}';
    }

    public Practica() {
    }
    
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(long id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getHoras_empleadas() {
        return horas_empleadas;
    }

    public void setHoras_empleadas(int horas_empleadas) {
        this.horas_empleadas = horas_empleadas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    
    
}
