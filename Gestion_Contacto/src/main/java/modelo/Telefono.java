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
public class Telefono implements Serializable{
    private String nombreT;
    private String numeroTelefono;

    public String getNombreT() {
        return nombreT;
    }

    public void setNombreT(String nombreT) {
        this.nombreT = nombreT;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public Telefono(String nombreT, String numeroTelefono) {
        this.nombreT = nombreT;
        this.numeroTelefono = numeroTelefono;
    }



    @Override
    public String toString() {
        return "" + numeroTelefono + "";
    }
    
    
}
