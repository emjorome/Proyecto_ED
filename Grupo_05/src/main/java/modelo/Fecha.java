/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Danny
 */
public class Fecha implements Serializable{
    private String nomFecha;
    private Date fecha;

    public Fecha(String nomFecha, Date fecha) {
        this.nomFecha = nomFecha;
        this.fecha = fecha;
    }

    public String getNomFecha() {
        return nomFecha;
    }

    public void setNomFecha(String nomFecha) {
        this.nomFecha = nomFecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
