package com.leslie.demo.monotonic;

import java.util.Stack;

//failed -> need to do it again
// I used brute force to solve it, but it will cause TLE. I need to use monotonic stack to solve it.
public class DailyTemperatures {
}
//class Solution {
//    public int[] dailyTemperatures(int[] temperatures) {
//        int n = temperatures.length;
//        int[] res = new int[n];
//        boolean[] flags = new boolean[n];
//        for(int i = 0; i < n;i++){
//            for(int j = i; j < n;j++){
//                if(temperatures[j] > temperatures[i] && flags[i] != true){
//                    res[i] = j - i;
//                    flags[i] = true;
//                }
//            }
//        }
//        return res;
//    }
//}
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack();
        for(int i = 0; i < n;i++){
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                int previousDay = stack.pop();
                res[previousDay] = i - previousDay;
            }
            stack.push(i);

        }
        return res;
    }
}