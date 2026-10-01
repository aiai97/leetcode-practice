package com.leslie.demo.tree.dfs;


public class MaximumDepthofBinaryTree {
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
//https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
    // passed -> no need to do it again
    // I am so happy because I can solve it without looking at the answers
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return dfs(root,0);
    }

    private int dfs(TreeNode node, int depth){
        if(node == null) return 0;
        int leftTreeDepth = dfs(node.left,depth+1);
        int rightTreeDepth = dfs(node.right,depth+1);
        return Math.max(leftTreeDepth,rightTreeDepth) + 1;
    }
}