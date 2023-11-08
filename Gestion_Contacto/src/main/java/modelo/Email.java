/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Danny
 */
public class Email implements Serializable{
    private String nombreEmail;
    private String duenoEmail;

    public Email(String nombreEmail, String duenoEmail) {
        this.nombreEmail = nombreEmail;
        this.duenoEmail = duenoEmail;
    }

    
}
