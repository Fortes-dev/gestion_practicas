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
    
}
