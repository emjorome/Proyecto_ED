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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import modelo.Contacto;
import modelo.Usuario;
import modelo.*;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class InicioController implements Initializable {

    
    ArrayList<Usuario> lstUsuarios;
    @FXML
    private TextField nomUsuario;
    @FXML
    private TextField contrasena;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        creandoUsuario();
        lstUsuarios = new ArrayList<>();
        cargarUsuarios();
    }
    
    public void creandoUsuario(){
        Contacto c1 = new Contacto("Fernando","Garcia",new Direccion("Sauces","Guayaquil","Guayaquil","Ecuador"));
        Usuario usuario = new Usuario("fergar","123",c1);
        ArrayList<Usuario> Usuarios = new ArrayList<>();
        Usuarios.add(usuario);
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("archivos/usuarios.text"))){
            out.writeObject(Usuarios);
            out.flush();
        }
        catch(FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        }
    }

    public void cargarUsuarios(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("archivos/usuarios.text"))){
            lstUsuarios = (ArrayList<Usuario>) in.readObject();
        }catch(FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } 
    }
    
    public void buscarUsuario(){
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
            }
        }
    }
    
    @FXML
    public void validacion() {
        if (nomUsuario.getText() != null && contrasena.getText() != null) {
            buscarUsuario();
            ingresoPantallaContacto();
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
