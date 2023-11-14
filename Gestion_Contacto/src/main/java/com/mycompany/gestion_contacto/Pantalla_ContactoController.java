/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestion_contacto;

import TDA.List;
import TDA.ArrayList;
import java.util.LinkedList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Conta_Prueba;
import modelo.Contacto;
import modelo.CreandoContactos;
import com.mycompany.gestion_contacto.NuevoContactoController;
import java.util.ListIterator;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import modelo.Usuario;

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
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnMenu;
    @FXML
    private ImageView imgBuscar;
    @FXML
    private ImageView imgMenu;
    
    LinkedList<Contacto> lstContacto;
    @FXML
    private ImageView imgPreviuos;
    @FXML
    private ImageView imgNext;
    @FXML
    private Button btnPrevious;
    @FXML
    private Button btnNext;
    
    private ListIterator<Contacto> listIterator;
    private int currentIndex = 0;
    @FXML
    private Label nombreMain;
    @FXML
    private Label nombreC;
    @FXML
    private Label trabocell;
    @FXML
    private Label ubicacionlbl;
    @FXML
    private Label tipocontac;
    @FXML
    private Label fechaNaci;
    @FXML
    private Label correo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // cargarContactoPrincipal();
      cargar_Imagenes();
      cargarContactoPrincipal();
      

       
        
      
        try {
            LinkedList<Contacto> lstContactos = contacto.getContactosRelacionados();
              int contador=0;
            for(Contacto c: lstContacto){
              
                FXMLLoader fxmlloader= new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("plantilla_contacto.fxml"));
                VBox vboxcontact= fxmlloader.load();
                Plantilla_contactoController controlador= fxmlloader.getController();
               controlador.setData(c);
               
               hbox_contactos.getChildren().add(vboxcontact);
               contador++;
                if (contador==1) {
                    break;
                    
                }
            }
        } catch (Exception e) {
        }
        

    }

    public void cargarContactoPrincipal(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("archivos/contactoSelec.text"))){
            contacto = (Contacto) in.readObject();
            lstContacto = contacto.getContactosRelacionados();
        }catch(FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } 
        
    }
    
    public LinkedList<Contacto> listaconta(Contacto contacto){
        
//        LinkedList<Conta_Prueba> listContacto = new LinkedList<>();
//         Conta_Prueba c1= new Conta_Prueba("Emilio", "85749644", "Cuenca, Ecuador");
//          Conta_Prueba c2= new Conta_Prueba("Piero", "+6598 1451442", "Milagro, Ecuador");
//          Conta_Prueba c3= new Conta_Prueba("Steben", "+6598 1451442", "Ecuador, Duran");
//          Conta_Prueba c4= new Conta_Prueba("Marcelo", "+593 1451442", "Ecuador, Guayaquil");
//        listContacto.addLast(c1);
//        listContacto.addLast(c2);
//        listContacto.addLast(c3);
//        listContacto.addLast(c4);
//         listContacto.addLast(c1);
//          listContacto.addLast(c1);
//        listContacto.addLast(c2);
//        listContacto.addLast(c3);
//        listContacto.addLast(c4);
//         listContacto.addLast(c1);

        LinkedList<Contacto> contactos = contacto.getContactosRelacionados();
        return contactos;
    }
    
    @FXML
    public void nuevoContacto(){
        try {
            App.setRoot("nuevoContacto");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void cargar_Imagenes(){
         String s= "src\\main\\java\\perfil_Image\\busqueda_icon.png";
          String s2= "src\\main\\java\\perfil_Image\\menu-puntos-vertical.png";
           String ruta_imgNext= "src\\main\\java\\perfil_Image\\intercalacion-a-la-derecha.png";
        String ruta_imgprevio= "src\\main\\java\\perfil_Image\\caret-izquierda.png";
    
        try {
            FileInputStream fil = new FileInputStream(s);
            Image i= new Image(fil);
            imgBuscar.setImage(i);
            // Imagen del Menu
            FileInputStream fil2 = new FileInputStream(s2);
            Image i2= new Image(fil2);
            imgMenu.setImage(i2);
            // Imagen Icono Next
            FileInputStream fil3 = new FileInputStream(ruta_imgNext);
            Image i3= new Image(fil3);
            imgNext.setImage(i3);
            // Imagen Icono Previous
            FileInputStream fil4 = new FileInputStream(ruta_imgprevio);
            Image i4= new Image(fil4);
            imgPreviuos.setImage(i4);
            
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
      /*
        Image imbus= new Image("\\src\\main\\java\\perfil_Image\\busqueda_icon.png");
       imgBuscar.setImage(imbus);
*/
       
    }

    @FXML
    private void contacto_anterior(ActionEvent event) throws IOException {
    if (lstContacto.isEmpty()) {
        // No hay elementos en la lista, manejar según sea necesario
        return;
    }

    currentIndex = (currentIndex - 1 + lstContacto.size()) % lstContacto.size();
    Contacto AntContacto = lstContacto.get(currentIndex);

    FXMLLoader fxmlloader = new FXMLLoader();
    fxmlloader.setLocation(getClass().getResource("plantilla_contacto.fxml"));
    VBox vboxcontact = fxmlloader.load();
    Plantilla_contactoController controlador = fxmlloader.getController();
    controlador.setData(AntContacto);

    // Limpia los nodos hijos antes de agregar el nuevo VBox
    hbox_contactos.getChildren().clear();
    hbox_contactos.getChildren().add(vboxcontact);

        
    }


    
    @FXML
    private void contacto_siguiente(ActionEvent event) throws IOException {
  
         if (lstContacto.isEmpty()) {
        // No hay elementos en la lista, manejar según sea necesario
        return;
    }
         
    currentIndex = (currentIndex + 1) % lstContacto.size();
    Contacto SigContacto = lstContacto.get(currentIndex);
    
    nombreMain.setText(SigContacto.getNombre()+" "+SigContacto.getApellido());
    nombreC.setText(SigContacto.getListTelefonos().get(0).getNumeroTelefono());
    trabocell.setText(SigContacto.getListTelefonos().get(1).getNumeroTelefono());
    

    FXMLLoader fxmlloader = new FXMLLoader();
    fxmlloader.setLocation(getClass().getResource("plantilla_contacto.fxml"));
    VBox vboxcontact = fxmlloader.load();
    Plantilla_contactoController controlador = fxmlloader.getController();
    controlador.setData(SigContacto);

    // Limpia los nodos hijos antes de agregar el nuevo VBox
    hbox_contactos.getChildren().clear();
    hbox_contactos.getChildren().add(vboxcontact);

    
    

    }
    
}
