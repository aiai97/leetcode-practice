package com.leslie.demo.tree.dfs;

// faild-> I cannot handle ans variable
// that is to say, if I could know I should set a regional variable like ans == 0, then I can work it out
// down direction is better than up direction, I thought if I could calculate the subtrees and then add the root node, but I failed
public class CountGoodNodesinBinaryTree {
    public int goodNodes(TreeNode root) {
        return dfs(root,Integer.MIN_VALUE);
    }

    private int dfs(TreeNode node, int maxNumberSoFar) {
        if (node == null) return 0;
        int ans = node.val >= maxNumberSoFar ? 1 : 0;
        int newMax = Math.max(maxNumberSoFar, node.val);

        return ans + dfs(node.left, newMax) + dfs(node.right, newMax);
    }
}

//  This is very ugly because if I could solve something very elegantly, why would I have to check the same condition again?
//  maybe when I was trying to resolve it, I forgot to update the maxNumberSoFar before passing it to subtrees, so I failed
class Solution22 {
    public int goodNodes(TreeNode root) {
        return dfs(root,0);
    }

    private int dfs(TreeNode node,int maxNumberSoFar){
        if(node == null) return 0;
        if(node.val >= maxNumberSoFar) maxNumberSoFar = node.val;
        int ans = dfs(node.left,maxNumberSoFar) + dfs(node.right,maxNumberSoFar);
        if(node.val >= maxNumberSoFar) ans += 1;
        return ans;
    }
}