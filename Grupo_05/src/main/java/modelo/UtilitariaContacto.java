/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
//import java.util.LinkedList;
import TDA.LinkedList;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author USUARIO
 */
public class UtilitariaContacto {
    
    public LinkedList<Contacto> almacenarContactos(VBox vbContacto) {
        LinkedList<Contacto> lstContactosRel = new LinkedList<>();
        for(int i = 1; i<vbContacto.getChildren().size(); i++){
            HBox hbContacto = (HBox) vbContacto.getChildren().get(i);
            ComboBox<Contacto> cmb = (ComboBox<Contacto>) hbContacto.getChildren().get(1);
            if(cmb.getValue() != null){
                lstContactosRel.addLast(cmb.getValue());
            }
        }
        return lstContactosRel;
    }
    
    public LinkedList<Email> almacenarCorreos(VBox vbCorreo) {
        LinkedList<Email> lstCorreos = new LinkedList<>();
        for(int i = 1; i<vbCorreo.getChildren().size(); i++){
            GridPane gridCorreo = (GridPane)vbCorreo.getChildren().get(i);
            TextField tcorreo = (TextField)getNodeFromGridPane(gridCorreo,0,1);
            TextField UsoCorreo = (TextField)getNodeFromGridPane(gridCorreo,0,3);
            
            if(tcorreo.getText() != null && UsoCorreo.getText() != null){
                lstCorreos.addLast(new Email(tcorreo.getText(),UsoCorreo.getText()));
            }
        }
        return lstCorreos;
    }
    
    public LinkedList<Fecha> almacenarFechas(VBox vbFecha){
        LinkedList<Fecha> lstFechas = new LinkedList<>();
        for(int i = 1; i<vbFecha.getChildren().size(); i++){
            GridPane gridFecha = (GridPane)vbFecha.getChildren().get(i);
            Node nodeDate = getNodeFromGridPane(gridFecha,0,1);
            
            TextField tdescripcion = (TextField)getNodeFromGridPane(gridFecha,0,3);
            
            if(nodeDate != null && tdescripcion.getText() != null){
                DatePicker date = (DatePicker)nodeDate;
                LocalDate local = date.getValue();
                Date fecha = Date.from(local.atStartOfDay(ZoneId.systemDefault()).toInstant());
                lstFechas.addLast(new Fecha(tdescripcion.getText(),fecha));
            }
        }
        return lstFechas;
    }
    
    public LinkedList<Telefono> almacenarTelefonos(VBox vbTelefono){
        LinkedList<Telefono> lstTelefonos = new LinkedList<>();
        for(int i = 1; i<vbTelefono.getChildren().size(); i++){
            GridPane gridTel = (GridPane)vbTelefono.getChildren().get(i);
            TextField tnumero = (TextField)getNodeFromGridPane(gridTel,0,1);
            TextField tdescripcion = (TextField)getNodeFromGridPane(gridTel,0,3);
            
            if(tnumero.getText() != null && tdescripcion.getText() != null){
                lstTelefonos.addLast(new Telefono(tdescripcion.getText(),tnumero.getText()));
            }
        }
        return lstTelefonos;
    }
    
    public LinkedList<Foto> almacenarFoto(VBox vbFoto) {
        LinkedList<Foto> lstFoto = new LinkedList<>();
        for(int i = 1; i<vbFoto.getChildren().size(); i++){
            GridPane gridFoto = (GridPane)vbFoto.getChildren().get(i);
            TextField tdireccion = (TextField)getNodeFromGridPane(gridFoto,0,1);
            TextField tnomFoto = (TextField)getNodeFromGridPane(gridFoto,1,1);
            TextField tdescripcion = (TextField)getNodeFromGridPane(gridFoto,2,1);
            
            if(tdireccion.getText() != null && tnomFoto.getText() != null && tdescripcion.getText() != null){
                lstFoto.addLast(new Foto(tdireccion.getText(),tnomFoto.getText(),tdescripcion.getText()));
            }
        }
        return lstFoto;
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
    
    public void agregarTipoContacto(Contacto contactoPrincipal, int currentIndex, ComboBox<String> tipoContacto){
        if(contactoPrincipal.getContactosRelacionados().get(currentIndex).getTipoContac() != null){
            if(contactoPrincipal.getContactosRelacionados().get(currentIndex).getTipoContac() == TipoContacto.EMPRESA){
                tipoContacto.setValue(tipoContacto.getItems().get(1));
            }else{
                tipoContacto.setValue(tipoContacto.getItems().get(0));
            }
        }
    }
    
    public void agregarTelefonos(Contacto contactoPrincipal, int currentIndex, VBox vbTelefono){
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
    
    public void agregarContactos(Contacto contactoPrincipal, int currentIndex, VBox vbContacto, LinkedList<Contacto> lstContactos){
        LinkedList<Contacto> lstCont = contactoPrincipal.getContactosRelacionados().get(currentIndex).getContactosRelacionados();
        if(!lstCont.isEmpty()){
            for(Contacto c: lstCont){
                HBox hb = new HBox();
                hb.setSpacing(50);
                hb.getChildren().add(new Label("Contacto:"));
                ComboBox<Contacto> cmb = new ComboBox();
                contactosCMB(cmb, lstContactos);
                cmb.setValue(c);
                hb.getChildren().add(cmb);
                Platform.runLater(() -> {
                    vbContacto.getChildren().add(hb);
                });
            }
        }
    }
    
    public void agregarFotos(Contacto contactoPrincipal, int currentIndex, VBox vbFoto){
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
    
    public void agregarFechas(Contacto contactoPrincipal, int currentIndex, VBox vbFecha){
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
    
    public void agregarEmails(Contacto contactoPrincipal, int currentIndex, VBox vbCorreo){
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
    
    public void añadirGridTelefono(VBox vbTelefono)  {
        GridPane gridTel = new GridPane();
        gridTel.add(new Label("Número:"), 0, 0);
        gridTel.add(new TextField(),1,0);
        gridTel.add(new Label("Descripción:"), 2, 0);
        gridTel.add(new TextField(),3,0);
        Platform.runLater(() -> {
            vbTelefono.getChildren().add(gridTel);
        });
    }
    
    public void añadirGridFecha(VBox vbFecha)  {
        GridPane grid = new GridPane();
        grid.add(new Label("Fecha:"), 0, 0);
        grid.add(new DatePicker(),1,0);
        grid.add(new Label("Descripción:"), 2, 0);
        grid.add(new TextField(),3,0);
        Platform.runLater(() -> {
            vbFecha.getChildren().add(grid);
        });
    }
    
    public void añadirGridCorreo(VBox vbCorreo)  {
        GridPane grid = new GridPane();
        grid.add(new Label("Correo:"), 0, 0);
        grid.add(new TextField(),1,0);
        grid.add(new Label("Uso del Correo:"), 2, 0);
        grid.add(new TextField(),3,0);
        Platform.runLater(() -> {
            vbCorreo.getChildren().add(grid);
        });
    }
    
    public void añadirGridFoto(VBox vbFoto)  {
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
    
    public void añadirGridContacto(VBox vbContacto, LinkedList<Contacto> lstContactos)  {
        HBox hb = new HBox();
        hb.setSpacing(50);
        hb.getChildren().add(new Label("Contacto:"));
        ComboBox<Contacto> cmb = new ComboBox();
        contactosCMB(cmb, lstContactos);
        hb.getChildren().add(cmb);
        Platform.runLater(() -> {
            vbContacto.getChildren().add(hb);
        });
    }
    
    public void contactosCMB(ComboBox cmb, LinkedList<Contacto> lstContactos){
        for(Contacto c: lstContactos){
            cmb.getItems().add(c);
        }
    }
}
