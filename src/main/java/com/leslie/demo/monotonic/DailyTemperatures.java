package com.leslie.demo.monotonic;

import java.util.Stack;

// easy -> no need to do it again
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack();
        int[] result = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                int previousDay = stack.pop();
                result[previousDay] = i - previousDay;
            }
            stack.push(i);
        }
        return result;
    }
}

