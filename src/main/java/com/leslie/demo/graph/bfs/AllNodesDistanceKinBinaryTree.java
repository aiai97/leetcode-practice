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
// failed-> cannot think of it in the beginning,will do it again
class Solution {
    Map<TreeNode, TreeNode> parents = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        dfs(root, null);
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> seen = new HashSet();
        queue.add(target);
        seen.add(target);

        int distance = 0;
        while (!queue.isEmpty() && distance < k) { //failed because I used "distance < k - 1 and then exit the while loop
            int currentLength = queue.size();
            for (int i = 0; i < currentLength; i++) {
                TreeNode node = queue.poll();
                for(TreeNode neighbor:Arrays.asList(node.left,node.right,parents.get(node))){ // failed I used List.of() and it cannot allow null elements
                    if (neighbor != null && seen.add(neighbor)) { // faild  because I didn;t check dead end
                        queue.add(neighbor);
                    }
                }
            }

            distance++;
        }
        List<Integer> res = new ArrayList();
        while(!queue.isEmpty()){
            res.add(queue.poll().val);
        }

        return res;
    }

    public void dfs(TreeNode node, TreeNode parent) {
        if(node == null) return;
        parents.put(node,parent);
        dfs(node.left,node);
        dfs(node.right,node);
    }
}