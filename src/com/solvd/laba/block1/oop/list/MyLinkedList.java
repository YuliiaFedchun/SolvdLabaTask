package com.solvd.laba.block1.oop.list;

import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private int length;

    public void add(T data) {
        Node<T> temp = new Node<>(data);

        if (head == null) {
            head = temp;
        } else {
            Node<T> someNode = head;
            while (someNode.getNext() != null) {
                someNode = someNode.getNext();
            }
            someNode.setNext(temp);
        }
        length++;
    }

    public T get(int position) {
        if (position < 0 || position >= length) {
            throw new IndexOutOfBoundsException("Index out of range.");
        }
        int count = 0;
        Node<T> someNode = head;
        while (count < position) {
            someNode = someNode.getNext();
            count++;
        }
        return someNode.getData();

    }

    public void clear() {
        head = null;
        length = 0;
    }

    public int indexOf(T data) {
        Node<T> someNode = head;
        int count = 0;
        while (count < length) {
            if (someNode.getData().equals(data)) {
                return count;
            }
            someNode = someNode.getNext();
            count++;
        }
        return -1;
    }

    public T remove(int position) {
        if (position < 0 || position >= length) {
            throw new IndexOutOfBoundsException("Index out of range.");
        }

        if (position == 0) {
            T value = head.getData();
            head = head.getNext();
            length--;
            return value;
        }

        Node<T> someNode1 = head;
        Node<T> someNode2 = head.getNext();
        int count = 1;
        while (count < position) {
            someNode1 = someNode2;
            someNode2 = someNode2.getNext();
            count++;
        }
        someNode1.setNext(someNode2.getNext());
        length--;
        return someNode2.getData();
    }

    public int size() {
        return length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> someNode = head;

            @Override
            public boolean hasNext() {
                return someNode != null;
            }

            @Override
            public T next() {
                T data = someNode.getData();
                someNode = someNode.getNext();
                return data;
            }
        };
    }

}
