/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TDA;

import java.io.Serializable;



/**
 *
 * @author Usuario
 */
public class Node<E> implements Serializable{
    
     private E content;
    private Node<E> next;
    private Node<E> previous;
    
       public Node (E content) {
        this.content = content;
        this.next = null;
        this.previous=null;
        
    }
public Node (E content, Node<E> next) {
        this(content);
        this.next = next;
    }
 public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public Node<E> getNext() {
        return next;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return obj.equals(content);
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
    
    public Node<E> getPrevious(){
        return this.previous;
    }
    
    public void setPrevious(Node<E> previous){
        this.previous=previous;
    }
}