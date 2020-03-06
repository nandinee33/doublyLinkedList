package defination;

import adt.ListADT;

public class DoublyLinkedList<E> implements ListADT<E> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public Node<E> getNode(int index) {
        Node<E> response = head;
        for (int i = 0; i < index; i++) {
            response = response.getNext();
        }
        return response;
    }

    private boolean addFirst(E item) {
        Node<E> newNode = new Node<>(item, head, tail);
        head = newNode;
        tail = newNode;
        size++;
        return true;
    }

    private boolean addAfter(Node<E> node, E item) {
        Node<E> newNode = null;
        if (node.next == null) {
            newNode = new Node<>(item, node, tail);
            node.next = newNode;
            tail = newNode;
        } else {
            newNode = new Node<>(item, node, node.getNext());
            node.next = newNode;
        }
        size++;
        return true;
    }

    public boolean add(int index, E item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        } else if (index == 0) {
            return addFirst(item);
        } else {
            return addAfter(getNode(index - 1), item);
        }
    }

    @Override
    public boolean add(E item) {
        return add(size, item);
    }

    private E removeFirst() {
        E response = null;
        Node<E> temp = head;
        if (head != null) {
            head = head.getNext();
        }
        if (temp != null) {
            size--;
            response = temp.getData();
        }
        return response;
    }


    private E removeAfter(Node<E> node) {
        E response = null;
        Node<E> temp = node.getNext();
        if (temp != null) {
            node.next = temp.getNext();
            size--;
            response = temp.getData();
        }
        return response;
    }


    public E remove(int index) {
        E response = null;
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        } else if (index == 0) {
            response = removeFirst();
        } else {
            Node<E> previousNode = getNode(index - 1);
            response = removeAfter(previousNode);
        }
        return response;
    }

    @Override
    public E remove() {
        return remove(size - 1);
    }


    @Override
    public int search(E item) {
        int response = -1;
        Node<E> temp = head;
        for (int i = 0; i < size && temp != null; i++) {
            if (item == temp.getData()) {
                response = i;
                break;
            }
            temp = temp.getNext();
        }
        return response;
    }

    @Override
    public void sort() {

    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[");
        Node<E> temp = head;
        for (int i = 0; i < size && temp != null; i++) {
            E data = temp.getData();
            sb.append(data).append(i < size - 1 ? ", " : "");
            temp = temp.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    private static class Node<E> {
        E data;
        Node<E> previous;
        Node<E> next;

        private Node(E data, Node<E> previous, Node<E> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }

        private E getData() {
            return data;
        }

        private Node<E> getPrevious() {
            return previous;
        }

        private Node<E> getNext() {
            return next;
        }

        private String printArrows() {
            StringBuilder response = new StringBuilder();
            if (this.getPrevious() != null) {
                response.append("<-");
            }
            if (this.getData() != null) {
                response.append(data.toString());
            }
            if (this.getNext() != null) {
                response.append("->");
            }
            return response.toString();
        }
    }
}