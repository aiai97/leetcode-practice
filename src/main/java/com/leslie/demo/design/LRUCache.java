package com.leslie.demo.design;


import java.util.HashMap;
import java.util.Map;

// will try the detail implementation later
class LRUCache {

    private class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();

    private final Node head = new Node(0, 0); // failed in constructor, need to initialize the head and tail nodes
    private final Node tail = new Node(0, 0);

    public LRUCache(int capacity) {
        this.capacity = capacity;

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);

        if (node == null) {
            return -1;
        }

        remove(node);
        addToHead(node);

        return node.value;
    }

    // if it execeeds the capacity, remove the tail node
    public void put(int key, int value) {
        Node node = map.get(key);

        if (node != null) {
            node.value = value;
            remove(node);
            addToHead(node);
            return;
        }

        node = new Node(key, value);
        map.put(key, node);
        addToHead(node);

        if (map.size() > capacity) {
            Node lru = tail.prev;
            remove(lru);
            map.remove(lru.key);
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }
}
