/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Date;
import javafx.scene.image.Image;

/**
 *
 * @author USUARIO
 */
public class Contacto implements Serializable{
    private String nombre;
    private String apellido;
    private LinkedList<Telefono> listTelefonos;
    private Direccion ubicacion;
    private LinkedList<Fecha> listafechas;
    private LinkedList<Foto> listaFotos;
    private LinkedList<Email> listaemails;
    private LinkedList<Contacto> contactosRelacionados;
    private TipoContacto tipoContac;
    private boolean favorito;

    public Contacto(String nombre, String apellido, 
            LinkedList<Telefono> listTelefonos, Direccion ubicacion, 
            LinkedList<Fecha> listafechas, LinkedList<Foto> listaFotos, 
            LinkedList<Email> listaemails, LinkedList<Contacto> contactosRelacionados, 
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
        this.favorito = favorito;
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

    public LinkedList<Telefono> getListTelefonos() {
        return listTelefonos;
    }

    public void setListTelefonos(LinkedList<Telefono> listTelefonos) {
        this.listTelefonos = listTelefonos;
    }

    public Direccion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Direccion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LinkedList<Fecha> getListafechas() {
        return listafechas;
    }

    public void setListafechas(LinkedList<Fecha> listafechas) {
        this.listafechas = listafechas;
    }

    public LinkedList<Foto> getListaFotos() {
        return listaFotos;
    }

    public void setListaFotos(LinkedList<Foto> listaFotos) {
        this.listaFotos = listaFotos;
    }

    public LinkedList<Email> getListaemails() {
        return listaemails;
    }

    public void setListaemails(LinkedList<Email> listaemails) {
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
    
    public LinkedList<Contacto> getContactosRelacionados() {
        return contactosRelacionados;
    }

    public void setContactosRelacionados(LinkedList<Contacto> contactosRelacionados) {
        this.contactosRelacionados = contactosRelacionados;
    }
    
    @Override
    public String toString(){
        return nombre + " " + apellido;
    }

}
