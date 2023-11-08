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
    private Image imagen;
    private String nombreFoto;
    private String descripcion;

    public Foto(Image imagen, String nombreFoto, String descripcion) {
        this.imagen = imagen;
        this.nombreFoto = nombreFoto;
        this.descripcion = descripcion;
    }
    
    
}
