/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import javafx.scene.image.Image;

/**
 *
 * @author Danny
 */
public class Foto implements Serializable{
    //private Image imagen;
    private String direccionImagen;
    private String nombreFoto;
    private String descripcion;

    public Foto(String direccionImagen, String nombreFoto, String descripcion) {
        //this.imagen = imagen;
        this.direccionImagen = direccionImagen;
        this.nombreFoto = nombreFoto;
        this.descripcion = descripcion;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }

    public String getNombreFoto() {
        return nombreFoto;
    }

    public void setNombreFoto(String nombreFoto) {
        this.nombreFoto = nombreFoto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
