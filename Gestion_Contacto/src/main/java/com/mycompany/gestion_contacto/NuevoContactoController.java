/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestion_contacto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Comparator;
//import java.util.LinkedList;
import TDA.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Contacto;
import modelo.Direccion;
import modelo.Email;
import modelo.Fecha;
import modelo.Foto;
import modelo.Telefono;
import modelo.TipoContacto;
import modelo.Usuario;
import modelo.UtilitariaContacto;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class NuevoContactoController implements Initializable {

    @FXML
    private ComboBox<String> tipoContacto;
    @FXML
    private VBox vbTelefono;
    @FXML
    private VBox vbFecha;
    @FXML
    private VBox vbCorreo;
    @FXML
    private VBox vbContacto;
    @FXML
    private VBox vbFoto;
    @FXML
    private ComboBox<Contacto> cmbContactos;
    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TextField pais;
    @FXML
    private TextField ciudad;
    @FXML
    private TextField ubicacion;
    @FXML
    private TextField nomDireccion;
    
    private Contacto contactoPrincipal;
    private LinkedList<Contacto> lstContactos;
    private UtilitariaContacto u;
    @FXML
    private GridPane GridTelefono;
    @FXML
    private GridPane GridFecha;
    @FXML
    private GridPane GridCorreo;
    @FXML
    private HBox hbContacto;
    @FXML
    private GridPane GridFoto;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        u = new UtilitariaContacto();
        lstContactos = new LinkedList<>();
        deserializarContactos();
        u.contactosCMB(cmbContactos, lstContactos);
        tipoContacto.getItems().add("PERSONA");
        tipoContacto.getItems().add("EMPRESA");
    }
    
    @FXML
    public void crearContacto(){
        if(condicionObligatoria()){
            Direccion dic = new Direccion(nomDireccion.getText(),ubicacion.getText(),ciudad.getText(),pais.getText());
            Contacto c = new Contacto(nombre.getText(),apellido.getText(),dic);
            
            //Añadir Telefonos
            LinkedList<Telefono> lstTelefonos = almacenarTelefonos();
            if(!lstTelefonos.isEmpty()) c.setListTelefonos(lstTelefonos);
            
            //Añadir Fecha
            LinkedList<Fecha> lstFechas = almacenarFechas();
            if(!lstFechas.isEmpty()) c.setListafechas(lstFechas);
            
            //Añadir TipoContacto
            if(tipoContacto.getValue() != null &&  tipoContacto.getValue().compareTo("PERSONA") == 0){
                c.setTipoContac(TipoContacto.PERSONA);
            }
            else if(tipoContacto.getValue() != null &&  tipoContacto.getValue().compareTo("EMPRESA") == 0){
                c.setTipoContac(TipoContacto.EMPRESA);
            }
            
            //Añadir Correo
            LinkedList<Email> lstCorreos = almacenarCorreos();
            if(!lstCorreos.isEmpty()) c.setListaemails(lstCorreos);
            
            //Añadir Contactos Relacionados
            LinkedList<Contacto> lstContactosRel = almacenarContactos();
            if(!lstContactosRel.isEmpty()) c.setContactosRelacionados(lstContactosRel);
            
            //Añadir Foto
            LinkedList<Foto> lstFoto = almacenarFoto();
            if(!lstFoto.isEmpty()) c.setListaFotos(lstFoto);
            
            //Añadir a la lista
            lstContactos.addLast(c);
            contactoPrincipal.setContactosRelacionados(lstContactos);
            serializarContacto();
            actualizarUsuarios();
        }
    }
    
    public LinkedList<Foto> almacenarFoto() {
        return u.almacenarFoto(vbFoto);
    }
    
    public LinkedList<Contacto> almacenarContactos() {
        return u.almacenarContactos(vbContacto);
    }
    
    public LinkedList<Email> almacenarCorreos() {
        return u.almacenarCorreos(vbCorreo);
    }
    
    public LinkedList<Fecha> almacenarFechas(){
        return u.almacenarFechas(vbFecha);
    }
    
    public LinkedList<Telefono> almacenarTelefonos(){
        return u.almacenarTelefonos(vbTelefono);
    }
     
    public boolean condicionObligatoria(){
        return nombre.getText() != null && apellido.getText() != null && 
                pais.getText() != null && ciudad.getText() != null
                && ubicacion.getText() != null && nomDireccion.getText() != null;
    }   
    
    public void deserializarContactos(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("archivos/contactoSelec.text"))){
            contactoPrincipal = (Contacto) in.readObject();
            lstContactos = contactoPrincipal.getContactosRelacionados();
        }catch(FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } 
    }
    
    public void serializarContacto() {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("archivos/contactoSelec.text"))){
            out.writeObject(contactoPrincipal);
            out.flush();
        }catch(FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        }
    }
    
    public void actualizarUsuarios(){
        LinkedList<Usuario> lstActual = new LinkedList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("archivos/usuarios.text"))){
            lstActual = (LinkedList<Usuario>) in.readObject();
        }catch(FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }            
        
        Comparator<Contacto> cmp = (c1,c2) -> {
            int i = c1.getNombre().compareTo(c2.getNombre());
            int j = c1.getApellido().compareTo(c2.getApellido());
            if(i == 0 && j == 0){
                return 0;
            }
            return -1;
        };
        
        for(Usuario u: lstActual){
            if(cmp.compare(u.getContacto(), contactoPrincipal) == 0){
                int pos = lstActual.indexOf(u);
                lstActual.get(pos).setContacto(contactoPrincipal);
            }
        }
        
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("archivos/usuarios.text"))){
            out.writeObject(lstActual);
            out.flush();
        }catch(FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        }
    }
    
    @FXML
    public void salir(){
        try {
            App.setRoot("Pantalla_Contacto");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void añadirGridTelefono()  {
        u.añadirGridTelefono(vbTelefono);
    }
    
    @FXML
    public void añadirGridFecha()  {
        u.añadirGridFecha(vbFecha);
    }
    
    @FXML
    public void añadirGridCorreo()  {
        u.añadirGridCorreo(vbCorreo);
    } 
    
    @FXML
    public void añadirGridFoto()  {
        u.añadirGridFoto(vbFoto);
    }
    
    @FXML
    public void añadirGridContacto()  {
        u.añadirGridContacto(vbContacto, lstContactos);
    }
}
