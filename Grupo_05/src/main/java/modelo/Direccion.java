/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Danny
 */
public class Direccion implements Serializable{
    private String nombreDireccion;
    private String ubicacion;
    private String ciudad;
    private String pais; 

    public Direccion(String nombreDireccion, String ubicacion, String ciudad, String pais) {
        this.nombreDireccion = nombreDireccion;
        this.ubicacion = ubicacion;
        this.ciudad = ciudad;
        this.pais = pais;
    }

    public String getNombreDireccion() {
        return nombreDireccion;
    }

    public void setNombreDireccion(String nombreDireccion) {
        this.nombreDireccion = nombreDireccion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "" + ubicacion + "";
    }
    
    
}
