package com.leslie.demo.tree.bfs;

import java.util.*;


// easy
public class ZigZagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        Queue<TreeNode> q = new LinkedList();
        q.add(root);

        List<List<Integer>> res = new ArrayList<>();
        boolean leftToRight = true;
        while (!q.isEmpty()) {
            int levelSize = q.size();
            List<Integer> currLayer = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = q.poll();
                currLayer.add(node.val);
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
            }
            if (!leftToRight) {
                Collections.reverse(currLayer);
            }
            res.add(currLayer);
            leftToRight = !leftToRight;
        }
        return res;
    }
}