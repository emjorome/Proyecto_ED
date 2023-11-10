/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.image.Image;

/**
 *
 * @author USUARIO
 */
public class CreandoContactos implements Serializable{
    public void crearContactos(){
        //creandoUsuario();
        ArrayList<Usuario> lstUsuarios = new ArrayList<>();

        //Persona Usuario 
        ArrayList<Telefono> tel_0 = new ArrayList<>();
        tel_0.add(new Telefono("Personal","098-676-5837"));
        tel_0.add(new Telefono("Trabajo", "098-354-1418"));
        Direccion dic_0 = new Direccion("Casa", "Villa Club", "Daule", "Ecuador");
        ArrayList<Fecha> fechas_0 = new ArrayList<>();
        fechas_0.add(new Fecha("Cumpleaños", new Date(1998, 3, 12)));
        fechas_0.add(new Fecha("Graduacion", new Date(2016, 5, 8)));
        ArrayList<Foto> fotos_0 = new ArrayList<>();
        fotos_0.add(new Foto("src\\main\\java\\perfil_Image\\Jennifer.jpg", "Foto perfil", "Perfil"));
        /*try {
            Image im0 = new Image(new FileInputStream("src\\main\\java\\perfil_Image\\Jennifer.jpg"));
            fotos_0.add(new Foto(im0, "Foto perfil", "Perfil"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }*/
        ArrayList<Email> emails_0 = new ArrayList<>();
        emails_0.add(new Email("jennifer4411@gmail.com", "Personal"));
        emails_0.add(new Email("jennifervaldez@empresa.com", "Trabajo"));
        ArrayList<Contacto> crP_0 = new ArrayList<>();
        
        Contacto cJennifer = new Contacto("Jennifer","Valdez",dic_0);
        cJennifer.setListafechas(fechas_0);
        cJennifer.setListTelefonos(tel_0);
        cJennifer.setListaemails(emails_0);
        cJennifer.setListaFotos(fotos_0);
        cJennifer.setTipoContac(TipoContacto.PERSONA);
        cJennifer.setFavorito(false);
        
        //Persona 1 - Piero
        ArrayList<Telefono> tel_1 = new ArrayList<>();
        tel_1.add(new Telefono("Personal","096-905-4199"));
        tel_1.add(new Telefono("Casa", "285-6499"));
        Direccion dic_1 = new Direccion("Casa", "Garzota", "Guayaquil", "Ecuador");
        ArrayList<Fecha> fechas_1 = new ArrayList<>();
        fechas_1.add(new Fecha("Cumpleaños", new Date(2003, 2, 8)));
        fechas_1.add(new Fecha("Graduacion", new Date(2020, 3, 6)));
        ArrayList<Foto> fotos_1 = new ArrayList<>();
        fotos_1.add(new Foto("src\\main\\java\\perfil_Image\\Piero.png", "Foto perfil", "Perfil"));
        /*try {
            Image im1 = new Image(new FileInputStream("src\\main\\java\\perfil_Image\\Piero.png"));
            fotos_1.add(new Foto(im1, "Foto perfil", "Perfil"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }*/
        ArrayList<Email> emails_1 = new ArrayList<>();
        emails_1.add(new Email("pazminopiero@gmail.com", "Personal"));
        ArrayList<Contacto> crP_1 = new ArrayList<>();
        
        Contacto cPiero = new Contacto("Piero","Pazmino",dic_1);
        cPiero.setListafechas(fechas_1);
        cPiero.setListTelefonos(tel_1);
        cPiero.setListaemails(emails_1);
        cPiero.setListaFotos(fotos_1);
        cPiero.setTipoContac(TipoContacto.PERSONA);
        cPiero.setFavorito(false);
        
        //Persona 2 - Martha
        ArrayList<Telefono> tel_2 = new ArrayList<>();
        tel_2.add(new Telefono("Personal","099-005-8888"));
        tel_2.add(new Telefono("Casa","391-4523"));
        Direccion dic_2 = new Direccion("Casa Sauces","Sauces 8","Guayaquil","Ecuador");
        ArrayList<Fecha> fechas_2 = new ArrayList<>();
        fechas_2.add(new Fecha("Cumpleaños",new Date(1996,4,7)));
        fechas_2.add(new Fecha("Aniversario",new Date(2020,4,7)));
        ArrayList<Foto> fotos_2 = new ArrayList<>();
        fotos_2.add(new Foto("src\\main\\java\\perfil_Image\\Martha.jpg", "Foto perfil", "Perfil"));
        /*try {
            Image im2 = new Image(new FileInputStream("src\\main\\java\\perfil_Image\\Martha.jpg"));
            fotos_2.add(new Foto(im2, "Foto perfil", "Perfil"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }*/
        ArrayList<Email> emails_2 = new ArrayList<>();
        emails_2.add(new Email("marthitaoliva@gmail.com","Personal"));
        ArrayList<Contacto> crP_2 = new ArrayList<>();
        
        Contacto cMartha = new Contacto("Martha","Reyes",dic_2);
        cMartha.setListafechas(fechas_2);
        cMartha.setListTelefonos(tel_2);
        cMartha.setListaemails(emails_2);
        cMartha.setListaFotos(fotos_2);
        cMartha.setTipoContac(TipoContacto.PERSONA);
        cMartha.setFavorito(false);
        
        //Persona 3 - Jason
        ArrayList<Telefono> tel_3 = new ArrayList<>();
        tel_3.add(new Telefono("Personal","097-095-2848"));
        tel_3.add(new Telefono("Casa","487-0912"));
        Direccion dic_3 = new Direccion("Casa Milagro","Milagro","Milagro","Ecuador");
        ArrayList<Fecha> fechas_3 = new ArrayList<>();
        fechas_3.add(new Fecha("Cumpleaños",new Date(1995,3,12)));
        fechas_3.add(new Fecha("Cumpleaños_Hijo",new Date(2022,2,10)));
        ArrayList<Foto> fotos_3 = new ArrayList<>();
        fotos_3.add(new Foto("src\\main\\java\\perfil_Image\\Jason.jpg", "Foto perfil", "Perfil"));
        /*try {
            Image im3 = new Image(new FileInputStream("src\\main\\java\\perfil_Image\\Jason.jpg"));
            fotos_3.add(new Foto(im3, "Foto perfil", "Perfil"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }*/
        ArrayList<Email> emails_3 = new ArrayList<>();
        emails_3.add(new Email("jason34rt@gmail.com","Personal"));
        ArrayList<Contacto> crP_3 = new ArrayList<>();
        
        Contacto cJason = new Contacto("Jason","Roldan",dic_3);
        cJason.setListafechas(fechas_3);
        cJason.setListTelefonos(tel_3);
        cJason.setListaemails(emails_3);
        cJason.setListaFotos(fotos_3);
        cJason.setTipoContac(TipoContacto.PERSONA);
        cJason.setFavorito(false);
        
        //Persona 4 - Jeremy
        ArrayList<Telefono> tel_4 = new ArrayList<>();
        tel_4.add(new Telefono("Trabajo","097-156-3489"));
        tel_4.add(new Telefono("Personal","091-312-4592"));
        Direccion dic_4 = new Direccion("Casa","Ciudad Celeste","Samborondon","Ecuador");
        ArrayList<Fecha> fechas_4 = new ArrayList<>();
        fechas_4.add(new Fecha("Cumpleaños",new Date(1974,10,11)));
        fechas_4.add(new Fecha("Fundacion Empresa",new Date(2002,6,8)));
        ArrayList<Foto> fotos_4 = new ArrayList<>();
        fotos_4.add(new Foto("src\\main\\java\\perfil_Image\\Jeremy.jpg", "Foto perfil", "Perfil"));
        /*try {
            Image im4 = new Image(new FileInputStream("src\\main\\java\\perfil_Image\\Jeremy.jpg"));
            fotos_4.add(new Foto(im4, "Foto perfil", "Perfil"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }*/
        ArrayList<Email> emails_4 = new ArrayList<>();
        emails_4.add(new Email("jeremyjefe@empresa.com","Trabajo"));
        emails_4.add(new Email("jeremyAl@gmail.com","Personal"));
        ArrayList<Contacto> crP_4 = new ArrayList<>();
        
        Contacto cJeremy = new Contacto("Jeremy","Dominguez",dic_4);
        cJeremy.setListafechas(fechas_4);
        cJeremy.setListTelefonos(tel_4);
        cJeremy.setListaemails(emails_4);
        cJeremy.setListaFotos(fotos_4);
        cJeremy.setTipoContac(TipoContacto.EMPRESA);
        cJeremy.setFavorito(false);
        
        //Persona 5 - Claudio
        ArrayList<Telefono> tel_5 = new ArrayList<>();
        tel_5.add(new Telefono("Personal","092-312-5671"));
        tel_5.add(new Telefono("Trabajo","091-439-6230"));
        Direccion dic_5 = new Direccion("Casa","San Juan","Quito","Ecuador");
        ArrayList<Fecha> fechas_5 = new ArrayList<>();
        fechas_5.add(new Fecha("Cumpleaños",new Date(1986,2,5)));
        fechas_5.add(new Fecha("Aniversario",new Date(2014,4,7)));
        ArrayList<Foto> fotos_5 = new ArrayList<>();
        fotos_5.add(new Foto("src\\main\\java\\perfil_Image\\Claudio.png", "Foto perfil", "Perfil"));
        /*try {
            Image im5 = new Image(new FileInputStream("src\\main\\java\\perfil_Image\\Claudio.png"));
            fotos_5.add(new Foto(im5, "Foto perfil", "Perfil"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }*/
        ArrayList<Email> emails_5 = new ArrayList<>();
        emails_5.add(new Email("claudiochef@hotmail.com","Trabajo"));
        emails_5.add(new Email("claudio543@gmail.com","Personal"));
        ArrayList<Contacto> crP_5 = new ArrayList<>();
        
        Contacto cClaudio = new Contacto("Claudio","Falconi",dic_5);
        cClaudio.setListafechas(fechas_5);
        cClaudio.setListTelefonos(tel_5);
        cClaudio.setListaemails(emails_5);
        cClaudio.setListaFotos(fotos_5);
        cClaudio.setTipoContac(TipoContacto.PERSONA);
        cClaudio.setFavorito(false);
        
        //Contactos para Piero
        crP_1.add(cJennifer);
        crP_1.add(cMartha);
        crP_1.add(cClaudio);
        cPiero.setContactosRelacionados(crP_1);
        
        //Contactos para Martha
        crP_2.add(cJennifer);
        crP_2.add(cPiero);
        cMartha.setContactosRelacionados(crP_2);
        
        //Contactos para Jason
        crP_3.add(cJennifer);
        crP_3.add(cClaudio);
        cJason.setContactosRelacionados(crP_3);
        
        //Contactos para Jeremy
        crP_4.add(cJennifer);
        cJeremy.setContactosRelacionados(crP_4);
        
        //Contactos Claudio
        crP_5.add(cJennifer);
        crP_5.add(cPiero);
        crP_5.add(cJason);
        cClaudio.setContactosRelacionados(crP_5);
        
        //Contactos para Usuario/Jennifer
        crP_0.add(cPiero);
        crP_0.add(cMartha);
        crP_0.add(cJason);
        crP_0.add(cJeremy);
        crP_0.add(cClaudio);
        cJennifer.setContactosRelacionados(crP_0);
        
        //Creando Usuario para Jennifer
        Usuario uJennifer = new Usuario("jenny","123",cJennifer);
        lstUsuarios.add(uJennifer);
        serializarUsuarios(lstUsuarios);
    }
    
    public void serializarUsuarios(ArrayList<Usuario> lst){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("archivos/usuarios.text"))){
            out.writeObject(lst);
            out.flush();
        }
        catch(FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        }
    }
}
