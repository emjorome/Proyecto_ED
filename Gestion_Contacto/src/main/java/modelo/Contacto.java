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
    private String apellido;
    private ArrayList<Telefono> listTelefonos;
    private Direccion ubicacion;
    private ArrayList<Fecha> listafechas;
    private ArrayList<Foto> listaFotos;
    private ArrayList<Email> listaemails;
    private ArrayList<Contacto> contactosRelacionados;
    private TipoContacto tipoContac;
    private boolean favorito;
    

    public Contacto(String nombre) {
        this.nombre = nombre;
     
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
