/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TDA;

/**
 *
 * @author pi_pa
 */
import java.util.Iterator;
import java.util.List;

public class DoublyLinkedList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;
    

    public DoublyLinkedList() {
        this.first = null;
        this.last=null;
        this.size=0;
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        String result = "{";
        if(this.size()==1)
            return result +first+" }";
        Node<E> p;
        for (p = first; p != null && !p.getNext().equals(first); p = p.getNext()) {

            result += p.getContent() + ", ";
        }
        result = result.substring(0, result.length() - 2);
        return result + "}";
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public void clear() {
        this.size=0;
        this.first=null;
        this.last=null;
    }

    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        }
        Node<E> nuevo = new Node<>(element);
        
        if (this.isEmpty()) {
            nuevo.setNext(nuevo);
            nuevo.setPrevious(nuevo);
            this.first = nuevo;
            this.last=nuevo;
        }else{
            nuevo.setNext(first);
            nuevo.setPrevious(last);
            last.setNext(nuevo);
            first.setPrevious(nuevo);
        }
        
        this.first = nuevo;
        this.size++;
        return true;
    }

    public boolean addLast(E element) {
        if (element == null) {
            return false;
        }
        Node<E> nuevo = new Node<>(element);
        if (this.isEmpty()) {
            this.first = nuevo;
        } else {
            this.last.setNext(nuevo);
            nuevo.setPrevious(last);
            nuevo.setNext(first);
            this.first.setPrevious(nuevo);
        }
        this.last = nuevo;
        this.size++;
        return true;
    }

    public void removeFirst() {
        if (this.isEmpty())
            return;
        if(this.size==1){
            this.clear();
        }else if(this.size>1){
            this.first=this.first.getNext();
            this.last.setNext(first);
            this.first.setPrevious(last);
        }
        this.size--;
    }

    public void removeLast() {
        if (this.isEmpty())
            return;
        if(this.size==1){
            this.clear();
        }else if(this.size>1){
            this.last=this.last.getPrevious();
            this.first.setPrevious(this.last);
            this.last.setNext(first);
        }
        this.size--;
    }

    public boolean add(int index, E element) {
        if(element==null||index>=this.size)
            return false;
        Node<E> nuevo=new Node(element);
        Node<E> viajero=first;
        for (int i=0; i<this.size;i++) {
            if(i== index){
                nuevo.setPrevious(viajero.getPrevious());
                nuevo.setNext(viajero);
                viajero.setPrevious(nuevo);
                this.size++;
                return true;
            }
            viajero=viajero.getNext();
        }
        return false;
    }
    
    public boolean addAll(List<E> l){
        if(l.isEmpty())
            return false;
        for(E e:l){
            this.addLast(e);
        }
        return true;
    }

    
    public boolean remove(E element){
        Node<E> viajero=first;
        Node<E> remover=new Node(element);
        for (int i=0; i<this.size;i++) {
            if(viajero.equals(remover)){
                Node<E>newPrevious=viajero.getPrevious();
                Node<E>newNext=viajero.getNext();
                newPrevious.setNext(newNext);
                newNext.setPrevious(newPrevious);
                this.size--;
                return true;
            } 
            viajero=viajero.getNext();
        }
        return false;
    }

    public boolean remove(int index) {
      
        Node<E> viajero=first;
        for (int i=0; i<this.size;i++) {
            if(i== index){
                Node<E>newPrevious=viajero.getPrevious();
                Node<E>newNext=viajero.getNext();
                newPrevious.setNext(newNext);
                newNext.setPrevious(newPrevious);
                this.size--;
                return true;
            }
            viajero=viajero.getNext();
        }
        return false;
    }

    public E get(int index) {
        Node<E> viajero=first;
        for (int i=0; i<this.size;i++) {
            if(i== index){
                return viajero.getContent();
            }
            viajero=viajero.getNext();
        }
        return null;
    }
    
    public boolean contains(E element){
        Node<E> viajero=first;
        for (int i=0; i<this.size;i++) {
            if(viajero.getContent().equals(element)){
                return true;
            }
            viajero=viajero.getNext();
        }
        return false;
    }
    
    public int getIndex(E element){
        Node<E> viajero=first;
        for (int i=0; i<this.size;i++) {
            if(viajero.getContent().equals(element)){
                return i;
            }
            viajero=viajero.getNext();
        }
        return -1;
    }

    public boolean set(int index, E element) {
        Node<E> viajero=first;
        Node<E> nuevo= new Node(element);
        for (int i=0; i<this.size;i++) {
            if(i== index){
                Node<E>newPrevious=viajero.getPrevious();
                Node<E>newNext=viajero.getNext();
                nuevo.setNext(newNext);
                nuevo.setPrevious(newPrevious);
                newPrevious.setNext(nuevo);
                newNext.setPrevious(nuevo);
                return true;
            }
            viajero=viajero.getNext();
        }
        return false;
    }

    public Iterator<E> iterator() {
        
        Iterator<E> it = new Iterator<E>() {
            Node<E> cursor = first;
            @Override
            public boolean hasNext() {
                return cursor != null;
}

            @Override
            public E next() {
                E e = cursor.getContent();
                cursor = cursor.getNext().getNext();
                return e;
            }
            
        };
        
        return it;
        
        
    }
}
