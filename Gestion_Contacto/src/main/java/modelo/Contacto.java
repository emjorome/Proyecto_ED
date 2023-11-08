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

    public Contacto(String nombre, String apellido, ArrayList<Telefono> listTelefonos, Direccion ubicacion, ArrayList<Fecha> listafechas, ArrayList<Foto> listaFotos, ArrayList<Email> listaemails, ArrayList<Contacto> contactosRelacionados, TipoContacto tipoContac, boolean favorito) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.listTelefonos = listTelefonos;
        this.ubicacion = ubicacion;
        this.listafechas = listafechas;
        this.listaFotos = listaFotos;
        this.listaemails = listaemails;
        this.contactosRelacionados = contactosRelacionados;
        this.tipoContac = tipoContac;
        this.favorito = favorito;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
