package com.leslie.demo.design;

// failed, -> need to implement it again
class Trie {
    private TrieNode root = new TrieNode();

    // failed, trieNode is a node in the tree
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    public Trie() {
    }

    public void insert(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }

            node = node.children[index];
        }

        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = find(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return find(prefix) != null;
    }

    private TrieNode find(String s) {
        TrieNode node = root;

        for (char c : s.toCharArray()) {
            int index = c - 'a';

            if (node.children[index] == null) {
                return null;
            }

            node = node.children[index];
        }

        return node;
    }
}