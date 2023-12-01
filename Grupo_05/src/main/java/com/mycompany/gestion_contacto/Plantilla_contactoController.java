/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestion_contacto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.Conta_Prueba;
import modelo.Contacto;

/**
 * FXML Controller class
 *
 * @author Danny
 */
public class Plantilla_contactoController implements Initializable {

    @FXML
    private Label lb_nombre;
    @FXML
    private Label lb_telefono;
    @FXML
    private Label lb_ubicacion;
    @FXML
    private ImageView fotoPlantilla;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Label getLb_nombre() {
        return lb_nombre;
    }

    public void setLb_nombre(Label lb_nombre) {
        this.lb_nombre = lb_nombre;
    }

    public Label getLb_telefono() {
        return lb_telefono;
    }

    public void setLb_telefono(Label lb_telefono) {
        this.lb_telefono = lb_telefono;
    }

    public Label getLb_ubicacion() {
        return lb_ubicacion;
    }

    public void setLb_ubicacion(Label lb_ubicacion) {
        this.lb_ubicacion = lb_ubicacion;
    }
    
    public void setData(Contacto c){
        this.lb_nombre.setText(c.getNombre());
        this.lb_telefono.setText(c.getListTelefonoSimple());
        this.lb_ubicacion.setText(c.getUbicacion().getNombreDireccion());
        
        System.out.println(c.getListaFotos().get(0).getDireccionImagen());
        
        String s= c.getListaFotos().get(0).getDireccionImagen();
         
    
        try {
            FileInputStream fil = new FileInputStream(s);
            Image i= new Image(fil);
            fotoPlantilla.setImage(i);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
