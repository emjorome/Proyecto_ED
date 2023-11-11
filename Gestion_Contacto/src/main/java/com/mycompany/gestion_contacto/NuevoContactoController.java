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
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Contacto;

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
    private GridPane GridTelefono;
    @FXML
    private VBox vbFecha;
    @FXML
    private GridPane GridFecha;
    @FXML
    private VBox vbCorreo;
    @FXML
    private GridPane GridCorreo;
    @FXML
    private VBox vbContacto;
    @FXML
    private HBox hbContacto;
    @FXML
    private VBox vbFoto;
    @FXML
    private GridPane GridFoto;
    @FXML
    private ComboBox<Contacto> cmbContactos;
    
    LinkedList<Contacto> lstContactos;
    GridPane copiaFecha;
    GridPane copiaCorreo;
    GridPane copiaFoto;
    GridPane copiaTelefono;
    HBox copiaContacto;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lstContactos = new LinkedList<>();
        deserializarContactos();
        contactosVB();
        tipoContacto.getItems().add("PERSONA");
        tipoContacto.getItems().add("EMPRESA");
        copiarGridsPanes();
    }
    
    public void contactosVB(){
        for(Contacto c: lstContactos){
            cmbContactos.getItems().add(c);
        }
    }
    
    public void deserializarContactos(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("archivos/contactoSelec.text"))){
            Contacto contacto = (Contacto) in.readObject();
            lstContactos = contacto.getContactosRelacionados();
        }catch(FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } 
    }
    
    public void copiarGridsPanes(){
        copiaFecha = GridFecha;
        copiaCorreo = GridCorreo;
        copiaFoto = GridFoto;
        copiaTelefono = GridTelefono;
        copiaContacto = hbContacto;
    }
    
    public void añadirGridTelefono()  {
        vbTelefono.getChildren().add(copiaTelefono);
    }
    
    public void añadirGridFecha()  {
        vbFecha.getChildren().add(copiaFecha);
    }
    
    public void añadirGridCorreo()  {
        vbCorreo.getChildren().add(copiaCorreo);
    } 
    
    public void añadirGridFoto()  {
        vbFoto.getChildren().add(copiaFoto);
    }
    
    public void añadirGridContacto()  {
        vbContacto.getChildren().add(copiaContacto);
    }
    
}
