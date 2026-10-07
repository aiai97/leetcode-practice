package com.leslie.demo.tree.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// passed-> no need to do it again

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
}

public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null) return new ArrayList<>();
        Queue<TreeNode> q = new LinkedList();
        q.add(root);

        List<Integer> res = new ArrayList();
        while(!q.isEmpty()){
            int levelSize = q.size();
            for(int i = 0; i < levelSize;i++){
                TreeNode node = q.poll();
                if(i == (levelSize - 1)){
                    res.add(node.val);
                }
                if(node.left != null) q.add(node.left);
                if(node.right != null) q.add(node.right);
            }
        }
        return res;
    }
}
// my previous implementation is better
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int currentLength = queue.size();
            int prev = 0;

            for (int i = 0; i < currentLength; i++) {
                TreeNode node = queue.remove();
                prev = node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            ans.add(prev);
        }

        return ans;
    }
}