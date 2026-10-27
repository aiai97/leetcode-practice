package com.leslie.demo.design;

import java.util.Stack;

import java.util.Stack;
// passed
class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if(!minStack.isEmpty() && minStack.peek() < val){
            minStack.push(minStack.peek());
        }else{
            minStack.push(val);
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}