package com.leslie.demo.graph.bfs;



import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
public class AllNodesDistanceKinBinaryTree {
}
// failed-> I almost got it but failed
class Solution {

    HashMap<TreeNode,TreeNode> map = new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        dfs(root,null);
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> seen = new HashSet();
        queue.add(target);
        seen.add(target);

        int distanceK = 0;
        while (!queue.isEmpty() && distanceK < k) {
            int currentLength = queue.size();
            for (int i = 0; i < currentLength; i++) {
                TreeNode node = queue.poll();
                TreeNode parent = map.get(node);
                for(TreeNode neighbor:Arrays.asList(parent,node.left,node.right)){
                    if(neighbor != null && !seen.contains(neighbor)){
                        queue.add(neighbor);
                        seen.add(neighbor);
                    }
                }
            }
            distanceK += 1; //failed, after each layer
        }
        List<Integer> res = new ArrayList();
        while(!queue.isEmpty()){
            res.add(queue.poll().val);
        }

        return res;
    }

    public void dfs(TreeNode node,TreeNode parent){
        if(node == null) return;
        map.put(node,parent);
        dfs(node.right,node); // here, typo
        dfs(node.left,node);
    }
}