package com.leslie.demo.tree.dfs;

// failed ->  I missed to calculate the sum before traversals,  int currentSum = currSum + node.val;
// my current code is not good because it needs to remember a lot of stuffs and deduction is the best
public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null && targetSum == 0) return false;
        return dfs(root,0,targetSum);
    }

    private boolean dfs(TreeNode node, int currSum, int targetSum){
        if (node == null) return false;
        int currentSum = currSum + node.val;
        if(node.left == null && node.right == null){
            return currentSum == targetSum;
        }
        boolean leftFlag = false; boolean rightFlag = false;
        if(node.left != null)  leftFlag = dfs(node.left, currentSum,targetSum);
        if(node.right != null) rightFlag = dfs(node.right, currentSum,targetSum);
        return leftFlag || rightFlag;
    }
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
class Solution2 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        if(root.left == null && root.right == null){
            return targetSum == root.val;
        }
        int remaining = targetSum - root.val;
        return hasPathSum(root.left, remaining) ||
                hasPathSum(root.right, remaining);
    }
}