package com.leslie.demo.array.hashset;

//passed -> no need to do it again
public class CheckIfTheSentenceIsPangram {

    public boolean checkIfPangram(String sentence) {
        int[] res = new int[26];
        for(char ch:sentence.toCharArray()){
            res[ch-'a'] = 1;
        }
        for(int num:res){
            if(num == 0) return false;
        }
        return true;
    }
}

// better solution
class Solution {
    public boolean checkIfPangram(String sentence) {
        for(char ch='a';ch<='z';ch++){
            boolean isfound=false;
            for(int i=0;i<sentence.length();i++){
                if(sentence.charAt(i)==ch){
                    isfound=true;
                    break;
                }
            }
            if(!isfound) return false;
        }
        return true;
    }
}