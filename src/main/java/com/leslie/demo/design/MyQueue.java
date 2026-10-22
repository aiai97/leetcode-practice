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
        move();
        return outStack.pop();
    }

    public int peek() {
        move();
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void move() {
        // as long as there are something in the outstack, no need to move anything from the instack to the outstack,
        // only when the outstack is empty, we need to move all the elements from the instack to the outstack
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }
}