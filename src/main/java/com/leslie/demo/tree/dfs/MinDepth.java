package com.leslie.demo.tree.dfs;

public class MinDepth {
    public int minDepth(TreeNode root) {
        return dp(root);
    }

    private int dp(TreeNode node){
        if(node == null) return 0;
        if(node.left == null && node.right == null) return 1;
        if (node.left == null) { // failed, I forgot to consider about one subtree could be null
            return dp(node.right) + 1;
        }
        if (node.right == null) { // failed, I forgot to consider about one subtree could be null
            return dp(node.left) + 1;
        }
        return Math.min(dp(node.left),dp(node.right)) + 1;
    }
}