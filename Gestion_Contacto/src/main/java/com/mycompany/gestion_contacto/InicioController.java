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
import java.io.Serializable;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import modelo.*;
import java.util.*;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class InicioController implements Initializable,Serializable {

    
    LinkedList<Usuario> lstUsuarios;
    @FXML
    private TextField nomUsuario;
    @FXML
    private TextField contrasena;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //creandoUsuario();
        lstUsuarios = new LinkedList<>();
        CreandoContactos c = new CreandoContactos();
        c.crearContactos();
        deserializarUsuarios();
    }

    public void deserializarUsuarios(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("archivos/usuarios.text"))){
            lstUsuarios = (LinkedList<Usuario>) in.readObject();
        }catch(FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        for(Usuario u: lstUsuarios){
            System.out.println(u.getUsuario() + " "+ u.getContraseña());
        }
    }
    
    public void buscarUsuario(){
        boolean Encontrado = false;
        for(Usuario u: lstUsuarios) {
            if(u.getUsuario().compareTo(nomUsuario.getText()) == 0 && 
                    u.getContraseña().compareTo(contrasena.getText()) == 0){
                try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("archivos/contactoSelec.text"))){
                    out.writeObject(u.getContacto());
                    out.flush();
                }catch(FileNotFoundException f){
                    f.printStackTrace();
                }catch(IOException io){
                    io.printStackTrace();
                }
                Encontrado = true;
            }
        }
        if(Encontrado){
            borrar();
            ingresoPantallaContacto();
        }
    }
    
    @FXML
    public void validacion() {
        if (nomUsuario.getText() != null && contrasena.getText() != null) {
            buscarUsuario(); 
        } 
    }
    
    public void ingresoPantallaContacto(){
        try {
            App.setRoot("Pantalla_Contacto");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void borrar() {
        nomUsuario.setText("");
        contrasena.setText("");
    }
    
}
