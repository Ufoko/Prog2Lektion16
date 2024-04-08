package opgave02;

import opgave01.LinkedList;
import opgave02.models.SortedListEaaa;

import java.util.Iterator;

public class SortedLinkedList<E extends Comparable<E>> implements SortedListEaaa<E> {

    private class Node {
        private E element;
        private Node nextNode;
        private Node prevNode;

        public Node(E element, Node nextNode) {
            this.element = element;
            this.nextNode = nextNode;
        }


    }

    private Node head;
    private Node tail;


    @Override
    public void add(E incomingElement) {
        Node newNode = new Node(incomingElement, null);

// First element
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        //second element
        if (head == tail) {
            tail = newNode;
            head.nextNode = tail;
            tail.prevNode = head;
            return;
        }


        Node curNode = head;
        boolean foundPlace = false;

        while (!foundPlace) {


            //incoming element is larger
            if (curNode.element.compareTo(incomingElement) < 0) {
                curNode = curNode.nextNode;
            }
            //incoming element is lower
            else if (curNode.element.compareTo(incomingElement) > 0) {

                if (curNode.prevNode != null) {
                    curNode.prevNode.nextNode = newNode;
                    newNode.nextNode = curNode;
                    newNode.prevNode = curNode.prevNode;
                    curNode.prevNode = newNode;
                } else {
                    head.prevNode = newNode;
                    newNode.nextNode = head;
                    head = newNode;
                }
                foundPlace = true;
            }
            //They must then be equal
            else if (curNode.element.compareTo(incomingElement) == 0) {
                curNode.nextNode.prevNode = newNode;
                newNode.nextNode = curNode.nextNode;
                curNode.nextNode = newNode;
                newNode.prevNode = curNode;

                foundPlace = true;
            }
            //Incoming must be the largest or second largest
            if (curNode.nextNode == null) {
                if (curNode.element.compareTo(incomingElement) < 0) {
                    newNode.prevNode = tail;
                    tail.nextNode = newNode;
                    tail = newNode;
                } else {
                    newNode.prevNode = tail.prevNode;
                    tail.prevNode.nextNode = newNode;
                    newNode.nextNode = tail;
                    tail.prevNode = newNode;
                }
                foundPlace = true;
            }
        }

    }

    @Override
    public boolean remove(E incomingElement) {

        Node curNode = head;
        boolean foundElement = false;

        while (!foundElement && curNode.nextNode != null) {


            if (curNode.element.compareTo(incomingElement) != 0) {
                curNode = curNode.nextNode;
            } else {
                foundElement = true;
                removeHelp(curNode);
            }

        }
        if (curNode.element.compareTo(incomingElement) == 0) {
            foundElement = true;
            removeHelp(curNode);
        }

        return foundElement;

    }

    private void removeHelp(Node nodeToRemove) {
        nodeToRemove.prevNode.nextNode = nodeToRemove.nextNode;
        if (nodeToRemove != tail) {
            nodeToRemove.nextNode.prevNode = nodeToRemove.prevNode;
        } else {
            tail = nodeToRemove.prevNode;
        }
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
    public E getFirst() {
        return head.element;
    }

    @Override
    public void removeFirst() {
        head = head.nextNode;
    }

    @Override
    public E getLast() {
        return tail.element;
    }

    @Override
    public void removeLast() {
        tail.prevNode.nextNode = null;
        tail = tail.prevNode;
    }

    @Override
    public Iterator<E> descendingIterator() {

        return new Iterator<E>() {

            Node current = tail;

            @Override
            public boolean hasNext() {
                return current.prevNode != null;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E element = current.element;
                    current = current.prevNode;
                    return element;
                }
                return null;
            }
        };

    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node current = head;

            @Override
            public boolean hasNext() {
                return current.nextNode != null;
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
