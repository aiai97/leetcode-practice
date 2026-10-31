package com.leslie.demo.tree.dfs;

public class MaximumDifference {
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

class Solution222 {
    private int result = 0;

    public int maxAncestorDiff(TreeNode root) {
        dfs(root, root.val, root.val); // failed, the minRes must be >= 0
        return result;
    }

    private void dfs(TreeNode node, int maxVal, int minVal) {
        if (node == null) {
            return;
        }
        maxVal = Math.max(maxVal, node.val);
        minVal = Math.min(minVal, node.val);

        result = Math.max(result, maxVal - minVal);

        dfs(node.left, maxVal, minVal);
        dfs(node.right, maxVal, minVal);
    }
}
