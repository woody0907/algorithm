package com.woody.algorithm.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: woody
 * Date: 15-8-19
 * Time: 下午5:01
 * To change this template use File | Settings | File Templates.
 */
public class MyLinkedList<T> implements Iterable<T> {
    private int theSize;
    private int modCount = 0;
    private Node<T> beginMarker;
    private Node<T> endMarker;

    public MyLinkedList() {
        clear();
    }

    public T get(int i) {
        return getNode(i).data;
    }

    public void clear() {
        doClear();
    }

    public void doClear() {
        beginMarker = new Node<T>(null, null, null);
        endMarker = new Node<T>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public T remove(int i) {
        return remove(getNode(i));
    }

    private T remove(Node<T> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        theSize--;
        modCount++;
        return node.data;  //To change body of created methods use File | Settings | File Templates.
    }


    private static class Node<T> {
        public T data;
        public Node<T> prev;
        public Node<T> next;

        private Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(int index, T x) {
        addBefore(getNode(index, 0, size()), x);
    }

    private void addBefore(Node<T> p, T x) {
        Node<T> newNode = new Node<T>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return getNode(index, 0, size() - 1);
    }

    private Node<T> getNode(int index, int lower, int upper) {
        if (index < lower || index > upper) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> p;
        if (index < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else {
            p = endMarker;
            for (int i = size(); i > index; i--) {
                p = p.prev;
            }
        }
        return p;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {

        private Node<T> current = beginMarker.next;
        private int expectedMod = modCount;
        private boolean okToMove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {
            if (modCount != expectedMod) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T nextItem = current.data;
            current = current.next;
            okToMove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if (modCount != expectedMod) {
                throw new ConcurrentModificationException();
            }
            if (!okToMove) {
                throw new IllegalStateException();
            }
            MyLinkedList.this.remove(current.prev);
            expectedMod++;
            okToMove = false;
        }
    }
}
