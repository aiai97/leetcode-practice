package com.leslie.demo.array.twopinter;

//failed in interview -> because I didn't use ide to give me prompts,
// I wrote it all manually, cannot remember the syntax of s[left];
//char[] → mutable sequence of characters
//         ↓
//         access + modify
//
//String → immutable sequence of characters
//         ↓
//         access only
public class ReverseString {
    public static void main(String[] args) {
        char[] s = {'h','e','l','l','o'};
        reverseString(s);
        System.out.println(s);
    }
    public static void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while(left < right){
            char num = s[left];
            s[left] = s[right];
            s[right] = num;
            left++;
            right--;
        }

    }
}