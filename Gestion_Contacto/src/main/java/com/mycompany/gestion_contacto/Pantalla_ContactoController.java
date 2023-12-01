/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestion_contacto;

import TDA.List;
import TDA.ArrayList;
import TDA.DoublyLinkedList;
//import java.util.LinkedList;
import TDA.LinkedList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Contacto;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Stack;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author Danny
 */
public class Pantalla_ContactoController {

    Contacto contacto;
    @FXML
    private ImageView fotoperfil1;
    @FXML
    private HBox hbox_contactos;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnMenu;
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
    

    static DoublyLinkedList<Contacto> LCDE= new DoublyLinkedList<>();
      Contacto SigContacto ;
    Contacto SigContacto1 ;
    Contacto SigContacto2 ;
    Contacto SigContacto3 ;
    @FXML
    private ComboBox<String> cbbordenar;
    @FXML
    private TextField txtFiltro;
    @FXML
    private ComboBox<String> cmbFiltro;
    

    public void initialize() {

       // cargarContactoPrincipal();
      cargar_Imagenes();
      cargarContactoPrincipal();
      cmbFiltro.getItems().addAll("Nombre", "Apellido");
       
       
        
      
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
        iniciaLCDE();
        SigContacto= LCDE.get(0) ;
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
            //imgBuscar.setImage(i);
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
    
    //currentIndex = (currentIndex + 1) % LCDE.size();
   
    SigContacto = SigContacto1;
    SigContacto1 = SigContacto2;
    SigContacto2 = SigContacto3;
    SigContacto3= LCDE.obtenerSiguiente(SigContacto3);

    nombreMain.setText(SigContacto1.getNombre() + " " + SigContacto.getApellido());
    nombreC.setText(SigContacto1.getListTelefonos().get(0).getNumeroTelefono());
    trabocell.setText(SigContacto1.getListTelefonos().get(1).getNumeroTelefono());
    correo.setText(SigContacto1.getListaemails().get(0).getCorreo());
    ubicacionlbl.setText(SigContacto1.getUbicacion().getPais()+","+SigContacto1.getUbicacion().getCiudad());
    tipocontac.setText(SigContacto1.getTipoContac().name());
    fechaNaci.setText(SigContacto1.getListafechas().get(0).getFecha()+"");
    
     String s= SigContacto1.getListaFotos().get(0).getDireccionImagen();
         
    
        try {
            FileInputStream fil = new FileInputStream(s);
            Image i= new Image(fil);
            fotoperfil1.setImage(i);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

    // Cargar el archivo FXML una vez
    FXMLLoader fxmlloader = new FXMLLoader();
    fxmlloader.setLocation(getClass().getResource("plantilla_contacto.fxml"));

    // Cargar la primera instancia
    VBox vboxcontact = fxmlloader.load();
    Plantilla_contactoController controlador = fxmlloader.getController();
    controlador.setData(SigContacto);

    // Crear un nuevo FXMLLoader para la segunda instancia
    FXMLLoader fxmlloader1 = new FXMLLoader();
    fxmlloader1.setLocation(getClass().getResource("plantilla_contacto.fxml"));

    // Cargar la segunda instancia
    VBox vboxcontact1 = fxmlloader1.load();
    Plantilla_contactoController controlador1 = fxmlloader1.getController();
    controlador1.setData(SigContacto1);

    // Crear un nuevo FXMLLoader para la tercera instancia
    FXMLLoader fxmlloader2 = new FXMLLoader();
    fxmlloader2.setLocation(getClass().getResource("plantilla_contacto.fxml"));

    // Cargar la tercera instancia
    VBox vboxcontact2 = fxmlloader2.load();
    Plantilla_contactoController controlador2 = fxmlloader2.getController();
    controlador2.setData(SigContacto2);

    // Crear un nuevo FXMLLoader para la cuarta instancia
    FXMLLoader fxmlloader3 = new FXMLLoader();
    fxmlloader3.setLocation(getClass().getResource("plantilla_contacto.fxml"));

    // Cargar la cuarta instancia
    VBox vboxcontact3 = fxmlloader3.load();
    Plantilla_contactoController controlador3 = fxmlloader3.getController();
    controlador3.setData(SigContacto3);

    // Limpia los nodos hijos antes de agregar los nuevos VBox
    hbox_contactos.getChildren().clear();
    hbox_contactos.getChildren().addAll(vboxcontact, vboxcontact1, vboxcontact2, vboxcontact3);
    }

@FXML
private void contacto_siguiente(ActionEvent event) throws IOException {
    if (lstContacto.isEmpty()) {
        // No hay elementos en la lista, manejar según sea necesario
        return;
    }
    

    //currentIndex = (currentIndex + 1) % LCDE.size();
   
    SigContacto1 = SigContacto;
    SigContacto2 = LCDE.obtenerSiguiente(SigContacto);
    SigContacto3 = LCDE.obtenerSiguiente(LCDE.obtenerSiguiente(SigContacto));
    SigContacto= LCDE.obtenerPrevio(SigContacto);

    nombreMain.setText(SigContacto1.getNombre() + " " + SigContacto.getApellido());
    nombreC.setText(SigContacto1.getListTelefonos().get(0).getNumeroTelefono());
    trabocell.setText(SigContacto1.getListTelefonos().get(1).getNumeroTelefono());
    correo.setText(SigContacto1.getListaemails().get(0).getCorreo());
    ubicacionlbl.setText(SigContacto1.getUbicacion().getPais()+","+SigContacto1.getUbicacion().getCiudad());
    tipocontac.setText(SigContacto1.getTipoContac().name());
    fechaNaci.setText(SigContacto1.getListafechas().get(0).getFecha()+"");
    
    String s= SigContacto1.getListaFotos().get(0).getDireccionImagen();
         
    
        try {
            FileInputStream fil = new FileInputStream(s);
            Image i= new Image(fil);
            fotoperfil1.setImage(i);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

    // Cargar el archivo FXML una vez
    FXMLLoader fxmlloader = new FXMLLoader();
    fxmlloader.setLocation(getClass().getResource("plantilla_contacto.fxml"));

    // Cargar la primera instancia
    VBox vboxcontact = fxmlloader.load();
    Plantilla_contactoController controlador = fxmlloader.getController();
    controlador.setData(SigContacto);

    // Crear un nuevo FXMLLoader para la segunda instancia
    FXMLLoader fxmlloader1 = new FXMLLoader();
    fxmlloader1.setLocation(getClass().getResource("plantilla_contacto.fxml"));

    // Cargar la segunda instancia
    VBox vboxcontact1 = fxmlloader1.load();
    Plantilla_contactoController controlador1 = fxmlloader1.getController();
    controlador1.setData(SigContacto1);

    // Crear un nuevo FXMLLoader para la tercera instancia
    FXMLLoader fxmlloader2 = new FXMLLoader();
    fxmlloader2.setLocation(getClass().getResource("plantilla_contacto.fxml"));

    // Cargar la tercera instancia
    VBox vboxcontact2 = fxmlloader2.load();
    Plantilla_contactoController controlador2 = fxmlloader2.getController();
    controlador2.setData(SigContacto2);

    // Crear un nuevo FXMLLoader para la cuarta instancia
    FXMLLoader fxmlloader3 = new FXMLLoader();
    fxmlloader3.setLocation(getClass().getResource("plantilla_contacto.fxml"));

    // Cargar la cuarta instancia
    VBox vboxcontact3 = fxmlloader3.load();
    Plantilla_contactoController controlador3 = fxmlloader3.getController();
    controlador3.setData(SigContacto3);

    // Limpia los nodos hijos antes de agregar los nuevos VBox
    hbox_contactos.getChildren().clear();
    hbox_contactos.getChildren().addAll(vboxcontact, vboxcontact1, vboxcontact2, vboxcontact3);

}
    @FXML
  public void editarContacto(){
        serializarPosicion();
        
        try {
            App.setRoot("editarContacto");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void serializarPosicion(){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("archivos/posicion.text"))){
            out.writeObject(contacto.getContactosRelacionados().indexOf(SigContacto1));
            out.flush();
        }catch(FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        }
    }
    
    @FXML
    public void removerContacto() {       
        Platform.runLater(() -> {
            contacto.getContactosRelacionados().remove(contacto.getContactosRelacionados().indexOf(SigContacto1));
            serializarContacto(contacto);
            actualizarUsuarios(contacto);
            hbox_contactos.getChildren().clear();
            LCDE.clear();
            initialize();
        });
    }
    
    public void serializarContacto(Contacto c){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("archivos/contactoSelec.text"))){
            out.writeObject(c);
            out.flush();
        }catch(FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        }
    }
    
    public void actualizarUsuarios(Contacto c){
        LinkedList<Usuario> lst = new LinkedList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("archivos/usuarios.text"))){
            lst = (LinkedList<Usuario>) in.readObject();
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
            
            if(i==0 && j==0) return 0;
            
            return 1;
        };
        
        for(int i = 0; i<lst.size(); i++){
            if(cmp.compare(lst.get(i).getContacto(), c) == 0){
                lst.get(i).setContacto(c);
            }
        }
        
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("archivos/usuarios.text"))){
            out.writeObject(lst);
            out.flush();
        }catch(FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        }
    }



public  void iniciaLCDE(){
    for (int i = 0; i < lstContacto.size(); i++) {
        LCDE.add(lstContacto.get(i));
    }
}

    @FXML
    private void ActionBuscar(ActionEvent event) throws IOException {
        
        Stack<Contacto> resultadoStack = new Stack<>();

        // Filtrar la lista directamente
        switch (cmbFiltro.getSelectionModel().getSelectedItem()) {
            case "Nombre":
                for (Contacto contacto : LCDE) {
                    if (contacto.getNombre().toLowerCase().contains(txtFiltro.getText().toLowerCase())) {
                        resultadoStack.push(contacto);
                    }
                }
                break;
            // Agregamos más casos según los tipos de filtro que desees implementar
            case "Apellido":
                for (Contacto contacto : LCDE) {
                    if (contacto.getApellido().toLowerCase().contains(txtFiltro.getText().toLowerCase())) {
                        resultadoStack.push(contacto);
                    }
                }
                break;
                

            
        }

        // Actualizar nueva lista con los resultados del filtro
        
        DoublyLinkedList<Contacto> dc = new DoublyLinkedList<>();
        dc.clear();
        dc.addAll(resultadoStack);
        FXMLLoader fxmlloader = new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("plantilla_contacto.fxml"));
        
        for(Contacto c: dc){
           
            VBox vboxcontact = fxmlloader.load();
            Plantilla_contactoController controlador = fxmlloader.getController();
            controlador.setData(c);
            hbox_contactos.getChildren().clear();
            hbox_contactos.getChildren().addAll(vboxcontact);
            
            nombreMain.setText(c.getNombre() + " " + c.getApellido());
            nombreC.setText(c.getListTelefonos().get(0).getNumeroTelefono());
            trabocell.setText(c.getListTelefonos().get(1).getNumeroTelefono());
            correo.setText(c.getListaemails().get(0).getCorreo());
            ubicacionlbl.setText(c.getUbicacion().getPais()+","+c.getUbicacion().getCiudad());
            tipocontac.setText(c.getTipoContac().name());
            fechaNaci.setText(c.getListafechas().get(0).getFecha()+"");
            String s= c.getListaFotos().get(0).getDireccionImagen();
         
    
        try {
            FileInputStream fil = new FileInputStream(s);
            Image i= new Image(fil);
            fotoperfil1.setImage(i);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        }
        
        
    }

 



}
