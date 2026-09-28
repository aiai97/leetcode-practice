package com.leslie.demo.systemdesign.cache;

import java.util.HashMap;
import java.util.Map;

class LFUCache {

    private final int capacity;
    private int minFreq = 0;

    // key -> Node，快速找到元素
    private final Map<Integer, Node> keyMap = new HashMap<>();

    // freq -> LinkedList，按使用次数分组
    private final Map<Integer, DoublyLinkedList> freqMap = new HashMap<>();

    static class Node {
        int key;
        int value;
        int freq = 1;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // 双向链表：头部最久未使用，尾部最近使用
    static class DoublyLinkedList {
        Node head;
        Node tail;
        int size;

        DoublyLinkedList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        void addLast(Node node) {
            Node last = tail.prev;

            last.next = node;
            node.prev = last;

            node.next = tail;
            tail.prev = node;

            size++;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;

            node.prev = null;
            node.next = null;

            size--;
        }

        Node removeFirst() {
            if (size == 0) return null;

            Node first = head.next;
            remove(first);
            return first;
        }

        boolean isEmpty() {
            return size == 0;
        }
    }

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = keyMap.get(key);

        if (node == null) {
            return -1;
        }

        increaseFrequency(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        // 已存在：更新值，并增加使用次数
        if (keyMap.containsKey(key)) {
            Node node = keyMap.get(key);
            node.value = value;
            increaseFrequency(node);
            return;
        }

        // 容量已满：删除最少使用且最久未使用的元素
        if (keyMap.size() == capacity) {
            DoublyLinkedList minList = freqMap.get(minFreq);
            Node removed = minList.removeFirst();

            keyMap.remove(removed.key);

            if (minList.isEmpty()) {
                freqMap.remove(minFreq);
            }
        }

        // 插入新元素，初始使用次数为 1
        Node node = new Node(key, value);
        keyMap.put(key, node);

        freqMap.computeIfAbsent(1, k -> new DoublyLinkedList())
                .addLast(node);

        minFreq = 1;
    }

    private void increaseFrequency(Node node) {
        int oldFreq = node.freq;

        // 从原频率组中移除
        DoublyLinkedList oldList = freqMap.get(oldFreq);
        oldList.remove(node);

        // 如果原来的最小频率组空了，最小频率 +1
        if (oldFreq == minFreq && oldList.isEmpty()) {
            minFreq++;
        }

        // 使用次数增加
        node.freq++;

        // 放入新的频率组尾部，表示最近使用
        freqMap.computeIfAbsent(node.freq, k -> new DoublyLinkedList())
                .addLast(node);

        // 清理空的旧频率组
        if (oldList.isEmpty()) {
            freqMap.remove(oldFreq);
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */