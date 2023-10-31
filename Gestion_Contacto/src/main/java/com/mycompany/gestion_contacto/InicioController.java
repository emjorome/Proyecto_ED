/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestion_contacto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class InicioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ArrayList<Usuario> lstUsuarios;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lstUsuarios = new ArrayList<>();
    }

    public void cargarUsuarios(){
        try(ObjectInputStream oj = new ObjectInputStream(new FileInputStream("archivo.text"))){
            lstUsuarios = (ArrayList<Usuario>) oj.readObject();
        }catch(FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } 
    }
    
    public void crearNuevoUsuario(){
        
    }
    
}
