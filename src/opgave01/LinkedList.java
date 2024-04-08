package opgave01;

import opgave01.models.ListEaaa;

import java.util.Iterator;

public class LinkedList<E> implements ListEaaa<E> {


    private class Node {
        private E element;
        private Node nextNode;

        public Node(E element, Node nextNode) {
            this.element = element;
            this.nextNode = nextNode;
        }


    }

    private Node head;
    private Node tail;

    @Override
    public void add(E e) {
        Node newNode = new Node(e, null);
        if (tail != null) {
            tail.nextNode = newNode;
        }

        tail = newNode;
        if (head == null) {
            head = newNode;
        }
    }

    @Override
    public boolean remove(E e) {
//GG det bliver for grimt
        boolean reachedEnd = false;
        Node curNode = head;
        Node prevNode = null;
        while (!reachedEnd) {
            if (curNode.equals(tail)) {
                reachedEnd = true;
            } else if (curNode.element == e) {

                return true;
            } else {
                curNode = curNode.nextNode;

            }
        }


        //      node.nextNode = null;
        //    tail = node;

        return false;
    }

    @Override
    public void addFirst(E e) {
        Node newNode = new Node(e, head);
        head = newNode;
    }

    @Override
    public E getFirst() {
        return head.element;
    }

    @Override
    public void removeFirst() {
        Node tempHead = head;
        head = head.nextNode;
        tempHead.nextNode = null;
        tempHead.element = null;
    }

    @Override
    public boolean contains(E e) {
        Node curNode = head;
        while (curNode.nextNode != null) {
            if (curNode.element.equals(e)) {
                return true;
            } else {
                curNode = curNode.nextNode;
            }
        }
        return curNode.equals(tail);
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    @Override
    public int size() {
        int size = 0;

        boolean reachedEnd = false;
        Node curNode = head;
        while (!reachedEnd) {
            size++;
            if (curNode.equals(tail)) {
                reachedEnd = true;
            } else {
                curNode = curNode.nextNode;
            }
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public E get(int index) {
        return loopToIndex(index).element;
    }


    public E recursionGet(int index) {
        Node n = recGet(index, 0, head);
        if (n == null) {
            return null;
        }
        return n.element;
    }

    private Node recGet(int index, int ite, Node node) {
        if (ite == index) {
            return node;
        } else if (node.nextNode == null) {
            return null;
        }
        ite++;
        return recGet(index, ite, node.nextNode);
    }


    private Node loopToIndex(int index) {

        int size = 0;
        Node returnNode = null;

        if (index == -1) {
            returnNode = head;
            return returnNode;
        } else if (index < -1) {
            throw new IndexOutOfBoundsException("Index was too low");
        }

        boolean reachedEnd = false;
        Node curNode = head;
        while ((returnNode == null) && !reachedEnd) {
            if (curNode.equals(tail)) {
                reachedEnd = true;
            } else if (size == index) {
                returnNode = curNode;
            } else {
                curNode = curNode.nextNode;
            }
            size++;
        }


        return returnNode;
    }

    @Override
    public void add(int index, E e) {

        Node tempNode = loopToIndex(index - 1);

        Node newNode = new Node(e, tempNode.nextNode);
        tempNode.nextNode = newNode;
    }


    @Override
    public E remove(int index) {


        Node tempNode = loopToIndex(index - 1);


        E tempElement = tempNode.nextNode.element;
        tempNode.nextNode = tempNode.nextNode.nextNode;

        return tempElement;
    }

    @Override
    public int indexOf(E e) {

        int index = 0;
        boolean reachedEnd = false;
        Node curNode = head;
        while (!reachedEnd) {
            index++;
            if (curNode.equals(tail)) {
                reachedEnd = true;
                index = -1;
            } else {
                curNode = curNode.nextNode;
            }
        }

        return index;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E element = current.element;
                    current = current.nextNode;
                    return element;
                }
                return null;
            }
        };
    }


}
