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
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import modelo.Contacto;

/**
 * FXML Controller class
 *
 * @author Danny
 */
public class Pantalla_ContactoController implements Initializable {

    Contacto contacto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarContactoPrincipal();
    }

    public void cargarContactoPrincipal(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("archivos/contactoSelec.text"))){
            contacto = (Contacto) in.readObject();
        }catch(FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } 
    }
    
}
