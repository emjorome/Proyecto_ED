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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
    
    Contacto contactoPrincipal;
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
        contactosCMB();
        tipoContacto.getItems().add("PERSONA");
        tipoContacto.getItems().add("EMPRESA");
        copiarGridsPanes();
    }
    
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
            lstContactos.add(c);
            contactoPrincipal.setContactosRelacionados(lstContactos);
            serializarContacto();
        }
    }
    
    public LinkedList<Foto> almacenarFoto() {
        LinkedList<Foto> lstFoto = new LinkedList<>();
        for(int i = 1; i<vbFoto.getChildren().size(); i++){
            GridPane gridFoto = (GridPane)vbFoto.getChildren().get(i);
            TextField tdireccion = (TextField)getNodeFromGridPane(gridFoto,0,1);
            TextField tnomFoto = (TextField)getNodeFromGridPane(gridFoto,1,1);
            TextField tdescripcion = (TextField)getNodeFromGridPane(gridFoto,2,1);
            
            if(tdireccion.getText() != null && tnomFoto.getText() != null && tdescripcion.getText() != null){
                lstFoto.add(new Foto(tdireccion.getText(),tnomFoto.getText(),tdescripcion.getText()));
            }
        }
        return lstFoto;
    }
    
    public LinkedList<Contacto> almacenarContactos() {
        LinkedList<Contacto> lstContactosRel = new LinkedList<>();
        for(int i = 1; i<vbContacto.getChildren().size(); i++){
            HBox hbContacto = (HBox) vbContacto.getChildren().get(i);
            ComboBox<Contacto> cmb = (ComboBox<Contacto>) hbContacto.getChildren().get(1);
            if(cmb.getValue() != null){
                lstContactosRel.add(cmb.getValue());
            }
        }
        return lstContactosRel;
    }
    
    public LinkedList<Email> almacenarCorreos() {
        LinkedList<Email> lstCorreos = new LinkedList<>();
        for(int i = 1; i<vbCorreo.getChildren().size(); i++){
            GridPane gridCorreo = (GridPane)vbCorreo.getChildren().get(i);
            TextField tcorreo = (TextField)getNodeFromGridPane(gridCorreo,0,1);
            TextField UsoCorreo = (TextField)getNodeFromGridPane(gridCorreo,0,3);
            
            if(tcorreo.getText() != null && UsoCorreo.getText() != null){
                lstCorreos.add(new Email(tcorreo.getText(),UsoCorreo.getText()));
            }
        }
        return lstCorreos;
    }
    
    public LinkedList<Fecha> almacenarFechas(){
        LinkedList<Fecha> lstFechas = new LinkedList<>();
        for(int i = 1; i<vbFecha.getChildren().size(); i++){
            GridPane gridFecha = (GridPane)vbFecha.getChildren().get(i);
            Node nodeDate = getNodeFromGridPane(gridFecha,0,1);
            
            TextField tdescripcion = (TextField)getNodeFromGridPane(gridFecha,0,3);
            
            if(nodeDate != null && tdescripcion.getText() != null){
                DatePicker date = (DatePicker)nodeDate;
                LocalDate local = date.getValue();
                Date fecha = Date.from(local.atStartOfDay(ZoneId.systemDefault()).toInstant());
                lstFechas.add(new Fecha(tdescripcion.getText(),fecha));
            }
        }
        return lstFechas;
    }
    
    public LinkedList<Telefono> almacenarTelefonos(){
        LinkedList<Telefono> lstTelefonos = new LinkedList<>();
        for(int i = 1; i<vbTelefono.getChildren().size(); i++){
            GridPane gridTel = (GridPane)vbTelefono.getChildren().get(i);
            TextField tnumero = (TextField)getNodeFromGridPane(gridTel,0,1);
            TextField tdescripcion = (TextField)getNodeFromGridPane(gridTel,0,3);
            
            if(tnumero.getText() != null && tdescripcion.getText() != null){
                lstTelefonos.add(new Telefono(tnumero.getText(),tdescripcion.getText()));
            }
        }
        return lstTelefonos;
    }
    
    public Node getNodeFromGridPane(GridPane gridPane, int row, int col) {
        for (Node node : gridPane.getChildren()) {
            Integer i = GridPane.getColumnIndex(node);
            Integer j = GridPane.getRowIndex(node);
            
            if(i == null) i = 0;
            if(j == null) j = 0;
            
            if (i == col && j == row) {
                return node;
            }
        }
        return null;
    }
     
    public boolean condicionObligatoria(){
        return nombre.getText() != null && apellido.getText() != null && 
                pais.getText() != null && ciudad.getText() != null
                && ubicacion.getText() != null && nomDireccion.getText() != null;
    }
    
    
    public void contactosCMB(){
        for(Contacto c: lstContactos){
            cmbContactos.getItems().add(c);
        }
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
    
    public void salir(){
        try {
            App.setRoot("Pantalla_Contacto");
        } catch (IOException ex) {
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
        vbTelefono.getChildren().add(GridTelefono);
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
