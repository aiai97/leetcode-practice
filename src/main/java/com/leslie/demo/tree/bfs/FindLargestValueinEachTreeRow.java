package com.leslie.demo.tree.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// failed-> not need to do it again because I ignored the constraints of the question  -231 <= Node.val <= 231 - 1
public class FindLargestValueinEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        if(root == null) return new ArrayList<>();
        Queue<TreeNode> q = new LinkedList();
        q.add(root);

        List<Integer> res = new ArrayList();
        while(!q.isEmpty()){
            int levelSize = q.size();
            int max = Integer.MIN_VALUE; // failed
            for(int i = 0; i < levelSize;i++){
                TreeNode node = q.poll();
                max = Math.max(max,node.val);
                if(i == (levelSize - 1)){
                    res.add(max);
                }
                if(node.left != null) q.add(node.left);
                if(node.right != null) q.add(node.right);
            }
        }
        return res;
    }
}
