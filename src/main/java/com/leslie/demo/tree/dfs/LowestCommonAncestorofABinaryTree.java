package com.leslie.demo.tree.dfs;

// failed
public class LowestCommonAncestorofABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root,p,q);
    }

    private TreeNode dfs(TreeNode node,TreeNode p, TreeNode q){
        if(node == null) return null; // failed
        if(node == p || node == q) return node;
        TreeNode left = dfs(node.left, p, q);
        TreeNode right = dfs(node.right, p, q);
        if(left != null && right != null) return node; // failed,
        if(left != null) return left; // failed
        return right;
    }
}