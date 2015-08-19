package com.woody.algorithm.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: woody
 * Date: 15-8-18
 * Time: 下午3:32
 * To change this template use File | Settings | File Templates.
 */
public class MyArrayList<AnyType> implements Iterable<AnyType>{

    private static final int DEAFAULT_CAPACITY = 10;
    private Object[] defaultArray = new Object[DEAFAULT_CAPACITY];
    private int index = 0;
    private int theSize;
    private AnyType[] theItems;


    public MyArrayList() {
        doClear();
    }

    public int size() {
        return theSize;
    }

    private void doClear() {
        theSize = 0;
        ensureCapacity(DEAFAULT_CAPACITY);
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize)
            return;
        AnyType[] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }

    public void add(AnyType i) {
        add(size(),i);
    }


    public static <AnyType extends Comparable<? super AnyType>> AnyType findMax(AnyType[] arr) {
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }

    public void add(int i, AnyType s) {
        if(theItems.length == size()){
            ensureCapacity(size()*2+1);
        }
        for (int j = theSize; j >i ; j--) {
             theItems[j] = theItems[j-1];
        }
        theItems[i] = s;
        theSize++;
    }

    public AnyType get(int i) {
        if(i<0 || i>=size())
            throw new ArrayIndexOutOfBoundsException();
        return theItems[i];
    }

    public AnyType set(int i, AnyType s) {
        if(i<0 || i>=size())
            throw new ArrayIndexOutOfBoundsException();
        AnyType old = theItems[i];
        theItems[i] = s;
        return old;
    }

    public AnyType remove(int i) {
        if(i<0 || i>=size())
            throw new ArrayIndexOutOfBoundsException();
        AnyType theItem = theItems[i];
        for (int j = i; j < size()-1; j++) {
            theItems[j] = theItems[j+1];
        }
        theSize--;
        return theItem;
    }


    public boolean isEmpty() {
       return size()==0;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    private class  ArrayListIterator  implements  Iterator<AnyType>{

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current< size();
        }

        @Override
        public AnyType next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return theItems[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }
}
