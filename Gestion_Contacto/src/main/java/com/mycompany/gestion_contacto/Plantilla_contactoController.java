/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestion_contacto;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import modelo.Conta_Prueba;

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
    
    public void setData(Conta_Prueba c){
        this.lb_nombre.setText(c.getNombre());
        this.lb_telefono.setText(c.getTelefono());
        this.lb_ubicacion.setText(c.getUbicacion());
    }
}
