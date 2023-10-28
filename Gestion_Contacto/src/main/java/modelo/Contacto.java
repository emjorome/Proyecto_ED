/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;
import javafx.scene.image.Image;

/**
 *
 * @author USUARIO
 */
public class Contacto {
    private String nombre;
    private ArrayList<String> lstCorreos;
    private ArrayList<Integer> lstNumeros;
    private ArrayList<Image> lstFotos;
    private ArrayList<Date> lstFechas;

    public Contacto(String nombre, ArrayList<String> lstCorreos, ArrayList<Integer> lstNumeros, ArrayList<Image> lstFotos, ArrayList<Date> lstFechas) {
        this.nombre = nombre;
        this.lstCorreos = lstCorreos;
        this.lstNumeros = lstNumeros;
        this.lstFotos = lstFotos;
        this.lstFechas = lstFechas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Integer> getLstNumeros() {
        return lstNumeros;
    }

    public void setLstNumeros(ArrayList<Integer> lstNumeros) {
        this.lstNumeros = lstNumeros;
    }
}
