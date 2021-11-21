/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion.practicas.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author CarlosFortesMedina
 */
@Entity
@Table(name = "practicas")
public class Practica implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_alumno", nullable = false)
    private Alumno idAlumno;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "tipo", nullable = false, length = 4)
    private String tipo;

    @Column(name = "horas_empleadas", nullable = false)
    private Integer horasEmpleadas;

    @Column(name = "descripcion", nullable = false, length = 1000)
    private String descripcion;

    @Column(name = "observaciones", nullable = false, length = 1000)
    private String observaciones;

    public Practica() {
    }

    public Practica(Long id, Alumno idAlumno, Date fecha, String tipo, Integer horasEmpleadas, String descripcion, String observaciones) {
        this.id = id;
        this.idAlumno = idAlumno;
        this.fecha = fecha;
        this.tipo = tipo;
        this.horasEmpleadas = horasEmpleadas;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getHorasEmpleadas() {
        return horasEmpleadas;
    }

    public void setHorasEmpleadas(Integer horasEmpleadas) {
        this.horasEmpleadas = horasEmpleadas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Practica{" +
                "id=" + id +
                ", idAlumno=" + idAlumno +
                ", fecha=" + fecha +
                ", tipo='" + tipo + '\'' +
                ", horasEmpleadas=" + horasEmpleadas +
                ", descripcion='" + descripcion + '\'' +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}
