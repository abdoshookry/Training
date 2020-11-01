/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question2;

/**
 *
 * @author abdelrhman shoukry
 */
public class LinkedList {

    Node head;
     private int size = 0;
    
    public int size(){
        return size;
    }

    public void insert(int data) {
        Node node = new Node();
        node.data = data;
        node.next = null;

        if (head == null) {
            head = node;
        } else {
            Node n = head;
            while (n.next != null) {
                n = n.next;
            }
            n.next = node;
        }
        size++;
    }
    public int get(int index){
        Node node = new Node();
        node.next = null;
        node = head;
        for (int i = 0 ; i < index  ; i++){
            node = node.next;
        }
        
        return node.data;
    }

    public void insertAtStart(int data) {
        Node node = new Node();
        node.data = data;
        node.next = null;
        node.next = head;
        head = node;
        size++;
    }

    public void insertAt(int index, int data) {
        Node node = new Node();
        node.data = data;
        node.next = null;

        if (index == 0) {
            insertAtStart(data);
        } else {
            Node n = head;
            for (int i = 0; i < index - 1; i++) {
                n = n.next;
            }
            node.next = n.next;
            n.next = node;
        }
        size++;
    }

    public void deleteAtstart() {
        Node n = head;
        head = head.next;
        size--;

    }

    public void deleteAtend() {
        Node n = head;
        Node n1;
        while (n.next.next != null) {
            n = n.next;
        }
        n1 = n.next;
        n.next = n1.next;
        size--;

    }

    public void deleteAt(int index) {
        if (index == 0) {
            head = head.next;
        } else {
            Node n = head;
            Node n1 = null;
            for (int i = 0; i < index - 1; i++) {
                n = n.next;
            }
            n1 = n.next;
            n.next = n1.next;
            n1 = null;
        }
        size--;
    }

    public void show() {
        Node node = head;

        while (node.next != null) {
            System.out.println(node.data);
            node = node.next;
        }
        System.out.println(node.data);

    }
}
