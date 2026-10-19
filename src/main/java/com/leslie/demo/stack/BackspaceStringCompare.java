package com.leslie.demo.stack;


public class BackspaceStringCompare {
    public boolean backspaceCompare(String s, String t) {
        return cleanUselessSymbols(s).equals(cleanUselessSymbols(t));
    }

    private String cleanUselessSymbols(String str){
        StringBuilder builder = new StringBuilder();
        for(char ch: str.toCharArray()){
            if(ch == '#'){
                if(builder.length() < 1) continue;
                builder.deleteCharAt(builder.length()-1);
            }else{
                builder.append(ch);
            }
        }
        return builder.toString();
    }
}