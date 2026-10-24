package com.leslie.demo.tree.dfs;


import java.util.Stack;

// passed-> no need to do it again
class PathSumNewestVersion {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null && targetSum == 0) return false;
        return dfs(root,targetSum);
    }

    private boolean dfs(TreeNode node, int remainingTarget){
        if(node == null) return false;
        remainingTarget -= node.val;

        if(node.left == null && node.right == null){
            return remainingTarget == 0;
        }
        return dfs(node.left, remainingTarget) || dfs(node.right, remainingTarget);

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
//class Pair {
//    TreeNode node;
//    int previousSum;
//    Pair(TreeNode node, int previousSum) {
//        this.node = node;
//        this.previousSum = previousSum;
//    }
//}
//class SolutionStack3 {
//    public boolean hasPathSum(TreeNode root, int targetSum) {
//        if (root == null) {
//            return false;
//        }
//
//        Stack<Pair> stack = new Stack<>();
//        stack.push(new Pair(root, 0));
//
//
//        while (!stack.empty()) {
//            Pair pair = stack.pop();
//            TreeNode node = pair.node;
//            int previousSum = pair.previousSum;
//            previousSum += node.val;
//
//            if(node.left == null && node.right == null){
//                if (previousSum == targetSum) { // failed-> I used to write "return previousSum == targetSum"
//                    return true;
//                }
//            }
//
//            if (node.left != null) {
//                stack.push(new Pair(node.left, previousSum));
//            }
//            if (node.right != null) {
//                stack.push(new Pair(node.right, previousSum));
//            }
//        }
//        return false;
//    }
//}

// easy
public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(root, targetSum);
    }

    private boolean dfs(TreeNode node, int target) {
        if (node == null) return false;
        if (node.right == null && node.left == null) {
            if (node.val == target) return true;
        }

        return dfs(node.left, target - node.val) || dfs(node.right, target - node.val);
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