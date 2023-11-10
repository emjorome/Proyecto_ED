/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestion_contacto;

import TDA.List;
import TDA.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Conta_Prueba;
import modelo.Contacto;

/**
 * FXML Controller class
 *
 * @author Danny
 */
public class Pantalla_ContactoController implements Initializable {

    Contacto contacto;
    @FXML
    private ImageView fotoperfil1;
    @FXML
    private HBox hbox_contactos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // cargarContactoPrincipal();
       
        try {
            List<Conta_Prueba> conj= listaconta();
              int contador=0;
            for(Conta_Prueba c: conj){
              
                FXMLLoader fxmlloader= new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("plantilla_contacto.fxml"));
                VBox vboxcontact= fxmlloader.load();
                Plantilla_contactoController controlador= fxmlloader.getController();
               controlador.setData(c);
               hbox_contactos.getChildren().add(vboxcontact);
               contador++;
                if (contador==4) {
                    break;
                    
                }
            }
        } catch (Exception e) {
        }
        

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
    
    public List<Conta_Prueba> listaconta(){
        
        LinkedList<Conta_Prueba> listContacto = new LinkedList<>();
         Conta_Prueba c1= new Conta_Prueba("Emilio", "85749644", "Cuenca, Ecuador");
          Conta_Prueba c2= new Conta_Prueba("Piero", "+6598 1451442", "Milagro, Ecuador");
          Conta_Prueba c3= new Conta_Prueba("Steben", "+6598 1451442", "Ecuador, Duran");
          Conta_Prueba c4= new Conta_Prueba("Marcelo", "+593 1451442", "Ecuador, Guayaquil");
        listContacto.addLast(c1);
        listContacto.addLast(c2);
        listContacto.addLast(c3);
        listContacto.addLast(c4);
         listContacto.addLast(c1);
          listContacto.addLast(c1);
        listContacto.addLast(c2);
        listContacto.addLast(c3);
        listContacto.addLast(c4);
         listContacto.addLast(c1);
        return listContacto;
    }
    
}
