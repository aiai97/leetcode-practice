package com.leslie.demo.stack;

import java.util.*;

// failed -> have problems with understanding the question + missing use cases if(i > lastNum) return res;
// maybe need to do it later
public class BuildanArrayWithStackOperations {
    public List<String> buildArray(int[] target, int n) {
        Stack stack = new Stack<Integer>();
        Set set = new HashSet<>();
        for(int num:target){
            set.add(num);
        }
        List res = new ArrayList<String>();

        int lastNum = target[target.length - 1];
        for(int i = 1; i <= n;i++){
            if(i > lastNum) return res;
            if(set.contains(i)){
                stack.push(i);
                res.add("Push");
            }else{
                res.add("Push");
                res.add("Pop");
            }
        }
        return res;
    }
}
class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList();
        int i = 0;

        for (int num : target) {
            while (i < num - 1) {
                ans.add("Push");
                ans.add("Pop");
                i++;
            }

            ans.add("Push");
            i++;
        }

        return ans;
    }
}