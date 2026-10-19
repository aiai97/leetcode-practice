package com.leslie.demo.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// easy -> no need to do it again
public class ValidParentheses {

    public boolean isValid(String s) {
        Map<Character,Character> map = new HashMap<>();
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if(map.containsKey(ch)){
                stack.push(ch);
            } else {
                if(stack.isEmpty() || !map.get(stack.pop()).equals(ch)){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}

// Early returns make the failure checks concise.
class Solution2 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;

                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}