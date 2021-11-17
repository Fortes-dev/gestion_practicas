package com.mycompany.gestion.practicas.hibernate;

import com.mycompany.gestion.practicas.models.Alumno;
import com.mycompany.gestion.practicas.models.Empresa;
import com.mycompany.gestion.practicas.models.Practica;
import com.mycompany.gestion.practicas.models.Profesor;



public class SessionData {

    private static Alumno alumnoActual;
    private static Profesor profesorActual;
    private static Practica practicaActual;
    private static Empresa empresaActual;

    public static Alumno getAlumnoActual() {
        return alumnoActual;
    }

    public static void setAlumnoActual(Alumno alumnoActual) {
        SessionData.alumnoActual = alumnoActual;
    }

    public static Profesor getProfesorActual() {
        return profesorActual;
    }

    public static void setProfesorActual(Profesor profesorActual) {
        SessionData.profesorActual = profesorActual;
    }

    public static Practica getPracticaActual() {
        return practicaActual;
    }

    public static void setPracticaActual(Practica practicaActual) {
        SessionData.practicaActual = practicaActual;
    }

    public static Empresa getEmpresaActual() {
        return empresaActual;
    }

    public static void setEmpresaActual(Empresa empresaActual) {
        SessionData.empresaActual = empresaActual;
    }

    
    
}
