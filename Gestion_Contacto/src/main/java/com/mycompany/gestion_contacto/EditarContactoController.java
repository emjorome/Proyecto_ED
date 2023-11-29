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
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
import modelo.Usuario;

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
    
    @FXML
    public void guardarCambios(){
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
            
            //Guardar cambios      
            lstContactos.remove(currentIndex);
            lstContactos.add(currentIndex, c);
            contactoPrincipal.setContactosRelacionados(lstContactos);
            serializarContacto();
            actualizarUsuarios();
            
            //salir
            salir();
        }
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
    
    public boolean condicionObligatoria(){
        return nombre.getText() != null && apellido.getText() != null && 
                pais.getText() != null && ciudad.getText() != null
                && ubicacion.getText() != null && nomDireccion.getText() != null;
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
    
    public void actualizarUsuarios(){
        LinkedList<Usuario> lstActual = new LinkedList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("archivos/usuarios.text"))){
            lstActual = (LinkedList<Usuario>) in.readObject();
        }catch(FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }            
        
        Comparator<Contacto> cmp = (c1,c2) -> {
            int i = c1.getNombre().compareTo(c2.getNombre());
            int j = c1.getApellido().compareTo(c2.getApellido());
            if(i == 0 && j == 0){
                return 0;
            }
            return -1;
        };
        
        for(Usuario u: lstActual){
            if(cmp.compare(u.getContacto(), contactoPrincipal) == 0){
                int pos = lstActual.indexOf(u);
                lstActual.get(pos).setContacto(contactoPrincipal);
            }
        }
        
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("archivos/usuarios.text"))){
            out.writeObject(lstActual);
            out.flush();
        }catch(FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        }
    }
    
    @FXML
    public void salir(){
        try {
            App.setRoot("Pantalla_Contacto");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
