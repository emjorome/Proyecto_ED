/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.image.Image;

/**
 *
 * @author USUARIO
 */
public class Contacto implements Serializable{
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

    public Contacto(String nombre, String apellido, 
            ArrayList<Telefono> listTelefonos, Direccion ubicacion, 
            ArrayList<Fecha> listafechas, ArrayList<Foto> listaFotos, 
            ArrayList<Email> listaemails, ArrayList<Contacto> contactosRelacionados, 
            TipoContacto tipoContac) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.listTelefonos = listTelefonos;
        this.ubicacion = ubicacion;
        this.listafechas = listafechas;
        this.listaFotos = listaFotos;
        this.listaemails = listaemails;
        this.contactosRelacionados = contactosRelacionados;
        this.tipoContac = tipoContac;
    }

    public Contacto(String nombre, String apellido, Direccion ubicacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.ubicacion = ubicacion;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
     public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public ArrayList<Telefono> getListTelefonos() {
        return listTelefonos;
    }

    public void setListTelefonos(ArrayList<Telefono> listTelefonos) {
        this.listTelefonos = listTelefonos;
    }

    public Direccion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Direccion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public ArrayList<Fecha> getListafechas() {
        return listafechas;
    }

    public void setListafechas(ArrayList<Fecha> listafechas) {
        this.listafechas = listafechas;
    }

    public ArrayList<Foto> getListaFotos() {
        return listaFotos;
    }

    public void setListaFotos(ArrayList<Foto> listaFotos) {
        this.listaFotos = listaFotos;
    }

    public ArrayList<Email> getListaemails() {
        return listaemails;
    }

    public void setListaemails(ArrayList<Email> listaemails) {
        this.listaemails = listaemails;
    }

    public TipoContacto getTipoContac() {
        return tipoContac;
    }

    public void setTipoContac(TipoContacto tipoContac) {
        this.tipoContac = tipoContac;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
    
    public ArrayList<Contacto> getContactosRelacionados() {
        return contactosRelacionados;
    }

    public void setContactosRelacionados(ArrayList<Contacto> contactosRelacionados) {
        this.contactosRelacionados = contactosRelacionados;
    }

}
