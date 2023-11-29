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
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Contacto;
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
public class EditarContactoController implements Initializable {

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
    
    private LinkedList<Contacto> lstContactos;
    private Contacto contactoPrincipal;
    private int currentIndex;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentIndex = 0;
        lstContactos = new LinkedList<>();
        deserializarContactos();
        tipoContacto.getItems().add("PERSONA");
        tipoContacto.getItems().add("EMPRESA");
        agregarInformacionBasica();
        agregarTipoContacto();
        agregarTelefonos();
        agregarContactos();
        agregarFotos();
        agregarEmails();
        agregarFechas();
    }
    
    public void agregarInformacionBasica(){
        nombre.setText(contactoPrincipal.getContactosRelacionados().get(currentIndex).getNombre());
        apellido.setText(contactoPrincipal.getContactosRelacionados().get(currentIndex).getApellido());
        pais.setText(contactoPrincipal.getContactosRelacionados().get(currentIndex).getUbicacion().getPais());
        ciudad.setText(contactoPrincipal.getContactosRelacionados().get(currentIndex).getUbicacion().getCiudad());
        ubicacion.setText(contactoPrincipal.getContactosRelacionados().get(currentIndex).getUbicacion().getUbicacion());
        nomDireccion.setText(contactoPrincipal.getContactosRelacionados().get(currentIndex).getUbicacion().getNombreDireccion());
    }
    
    public void agregarTipoContacto(){
        if(contactoPrincipal.getContactosRelacionados().get(currentIndex).getTipoContac() != null){
            if(contactoPrincipal.getContactosRelacionados().get(currentIndex).getTipoContac() == TipoContacto.EMPRESA){
                tipoContacto.setValue(tipoContacto.getItems().get(1));
            }else{
                tipoContacto.setValue(tipoContacto.getItems().get(0));
            }
        }
    }
    
    public void agregarTelefonos(){
        LinkedList<Telefono> lstTel = contactoPrincipal.getContactosRelacionados().get(currentIndex).getListTelefonos();
        if(!lstTel.isEmpty()){
            for(Telefono t: lstTel){
                GridPane gridTel = new GridPane();
                
                gridTel.add(new Label("Número:"), 0, 0);
                TextField textNum = new TextField();
                textNum.setText(t.getNumeroTelefono());
                gridTel.add(textNum,1,0);
                
                gridTel.add(new Label("Descripción:"), 2, 0);
                TextField textDesc = new TextField();
                textDesc.setText(t.getNombreT());
                gridTel.add(textDesc,3,0);
                
                Platform.runLater(() -> {
                    vbTelefono.getChildren().add(gridTel);
                });
            }
        }
    }
    
    public void agregarContactos(){
        LinkedList<Contacto> lstCont = contactoPrincipal.getContactosRelacionados().get(currentIndex).getContactosRelacionados();
        if(!lstCont.isEmpty()){
            for(Contacto c: lstCont){
                HBox hb = new HBox();
                hb.setSpacing(50);
                hb.getChildren().add(new Label("Contacto:"));
                ComboBox<Contacto> cmb = new ComboBox();
                contactosCMB(cmb);
                cmb.setValue(c);
                hb.getChildren().add(cmb);
                Platform.runLater(() -> {
                    vbContacto.getChildren().add(hb);
                });
            }
        }
    }
    
    public void agregarFotos(){
        LinkedList<Foto> lstFoto = contactoPrincipal.getContactosRelacionados().get(currentIndex).getListaFotos();
        if(!lstFoto.isEmpty()){
            for(Foto f: lstFoto){
                GridPane grid = new GridPane();
                
                grid.add(new Label("Dirección de la Foto:"), 0, 0);
                TextField textDirec = new TextField();
                textDirec.setText(f.getDireccionImagen());
                grid.add(textDirec,1,0);
                
                grid.add(new Label("Nombre de la Foto:"), 0, 1);
                TextField textNom = new TextField();
                textNom.setText(f.getNombreFoto());
                grid.add(textNom,1,1);
                
                grid.add(new Label("Descripción:"), 0, 2);
                TextField textDesc = new TextField();
                textDesc.setText(f.getDescripcion());
                grid.add(textDesc,1,2);
                
                Platform.runLater(() -> {
                    vbFoto.getChildren().add(grid);
                });
            }
        }
    }
    
    public void agregarFechas(){
        LinkedList<Fecha> lstFecha = contactoPrincipal.getContactosRelacionados().get(currentIndex).getListafechas();
        if(!lstFecha.isEmpty()){
            for(Fecha f: lstFecha){
                GridPane grid = new GridPane();
                
                grid.add(new Label("Fecha:"), 0, 0);
                DatePicker date = new DatePicker();
                date.setValue(f.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                grid.add(date,1,0);
                
                grid.add(new Label("Descripción:"), 2, 0);
                TextField textDesc = new TextField();
                textDesc.setText(f.getNomFecha());
                grid.add(textDesc,3,0);
                
                Platform.runLater(() -> {
                    vbFecha.getChildren().add(grid);
                });
            }
        }
    }
    
    public void agregarEmails(){
        LinkedList<Email> lstEmails = contactoPrincipal.getContactosRelacionados().get(currentIndex).getListaemails();
        if(!lstEmails.isEmpty()){
            for(Email e: lstEmails){
                GridPane grid = new GridPane();
                
                grid.add(new Label("Correo:"), 0, 0);
                TextField textCorreo = new TextField();
                textCorreo.setText(e.getCorreo());
                grid.add(new TextField(),1,0);
                
                grid.add(new Label("Uso del Correo:"), 2, 0);
                TextField textUso = new TextField();
                textUso.setText(e.getUso());
                grid.add(new TextField(),3,0);
                
                Platform.runLater(() -> {
                    vbCorreo.getChildren().add(grid);
                });
            }
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
        
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("archivos/posicion.text"))){
            currentIndex = (int) in.readObject();
        }catch(FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void añadirGridTelefono()  {
        GridPane gridTel = new GridPane();
        gridTel.add(new Label("Número:"), 0, 0);
        gridTel.add(new TextField(),1,0);
        gridTel.add(new Label("Descripción:"), 2, 0);
        gridTel.add(new TextField(),3,0);
        Platform.runLater(() -> {
            vbTelefono.getChildren().add(gridTel);
        });
    }
    
    @FXML
    public void añadirGridFecha()  {
        GridPane grid = new GridPane();
        grid.add(new Label("Fecha:"), 0, 0);
        grid.add(new DatePicker(),1,0);
        grid.add(new Label("Descripción:"), 2, 0);
        grid.add(new TextField(),3,0);
        Platform.runLater(() -> {
            vbFecha.getChildren().add(grid);
        });
    }
    
    @FXML
    public void añadirGridCorreo()  {
        GridPane grid = new GridPane();
        grid.add(new Label("Correo:"), 0, 0);
        grid.add(new TextField(),1,0);
        grid.add(new Label("Uso del Correo:"), 2, 0);
        grid.add(new TextField(),3,0);
        Platform.runLater(() -> {
            vbCorreo.getChildren().add(grid);
        });
    }
    
    @FXML
    public void añadirGridFoto()  {
        GridPane grid = new GridPane();
        grid.add(new Label("Dirección de la Foto:"), 0, 0);
        grid.add(new TextField(),1,0);
        grid.add(new Label("Nombre de la Foto:"), 0, 1);
        grid.add(new TextField(),1,1);
        grid.add(new Label("Descripción:"), 0, 2);
        grid.add(new TextField(),1,2);
        Platform.runLater(() -> {
            vbFoto.getChildren().add(grid);
        });
    }
    
    @FXML
    public void añadirGridContacto()  {
        HBox hb = new HBox();
        hb.setSpacing(50);
        hb.getChildren().add(new Label("Contacto:"));
        ComboBox<Contacto> cmb = new ComboBox();
        contactosCMB(cmb);
        hb.getChildren().add(cmb);
        Platform.runLater(() -> {
            vbContacto.getChildren().add(hb);
        });
    }
    
    public void contactosCMB(ComboBox cmb){
        for(Contacto c: lstContactos){
            cmb.getItems().add(c);
        }
    }
}
