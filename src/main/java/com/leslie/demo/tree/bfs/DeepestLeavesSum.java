package com.leslie.demo.tree.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// easy
public class DeepestLeavesSum {
    public int deepestLeavesSum(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> q = new LinkedList();
        q.add(root);

        List<Integer> res = new ArrayList();
        int prevSum = 0;
        while (!q.isEmpty()) {
            int levelSize = q.size();
            prevSum = 0;
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = q.poll();
                prevSum += node.val;
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
            }
        }
        return prevSum;
    }
}