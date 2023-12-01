/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package TDA;
import java.util.Comparator;
import java.util.Iterator;
/**
 *
 * @author pi_pa
 */


public interface List<E> extends Iterable<E> {

    boolean addFirst(E element);

    boolean addLast(E element);

    boolean removeFirst();

    boolean removeLast();

    E getFirst();

    E getLast();

    boolean insert(int index, E element);

    boolean contains(E element);

    E get(int index);

    int indexOf(E element);

    boolean isEmpty();

    E remove(int index);

    boolean remove(E element);

    E set(int index, E element);

    int size();
    
    public boolean addAll (List<E> l);
    
    public List<E> findIntersection (List<E> anotherList, Comparator<E> cmp);
    
    public List<E> removeDuplicates(Comparator<E> cmp);
    
    public int binarySearch(E element, Comparator<E> cmp);
    
    public boolean removeElement(E element, Comparator<E> cmp);
    
    public int getIndexOf(E element, Comparator<E> cmp);
    
    public List<Integer> getAllIndicesOf(E element, Comparator<E> cmp);
    
    public boolean sort(Comparator<E> cmp);
    
    public List<E> mergeSorted(List<E> anotherList, Comparator<E> cmp);
    
    public boolean insertSorted(E element, Comparator<E> cmp);
    
    public List<E> findUnion(List<E> anotherList, Comparator<E> cmp);
    
    public boolean containsAll(List<E> anotherList);
    
}

