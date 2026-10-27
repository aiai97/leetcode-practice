package com.leslie.demo.design;

import java.util.Stack;

// failed, only in steps-> when pop something to outstack, no need to move it back to instack
class MyQueue {
    private Stack<Integer> inStack = new Stack<>();
    private Stack<Integer> outStack = new Stack<>();

    public MyQueue() {

    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        if(!outStack.isEmpty()){
            return outStack.pop();
        }
        while(!inStack.isEmpty()){
            outStack.push(inStack.pop());
        }
        return outStack.pop();
    }

    public int peek() {
        if(!outStack.isEmpty()){
            return outStack.peek();
        }

        while(!inStack.isEmpty()){
            outStack.push(inStack.pop());
        }
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.empty() && outStack.empty();
    }
}