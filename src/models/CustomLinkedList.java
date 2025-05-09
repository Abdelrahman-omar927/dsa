package models;

public class CustomLinkedList {
    private ListNode head;
    private ListNode tail;

    public CustomLinkedList() {
        head = null;
        tail = null;
    }

    public void add(int data, String name) {
        ListNode newNode = new ListNode(data, name);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public boolean remove(int data) {
        ListNode current = head;
        while (current != null && current.data != data) {
            current = current.next;
        }
        if (current == null) {
            return false; // Element not found
        }
        if (current == head) {
            head = current.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
        } else if (current == tail) {
            tail = current.prev;
            tail.next = null;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        return true;
    }

    public boolean contains(int data) {
        ListNode current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int size() {
        int count = 0;
        ListNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public int[] toArray() {
        int size = size();
        int[] result = new int[size];
        ListNode current = head;
        for (int i = 0; i < size; i++) {
            result[i] = current.data;
            current = current.next;
        }
        return result;
    }
}