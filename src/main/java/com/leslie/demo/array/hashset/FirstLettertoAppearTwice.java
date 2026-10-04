package com.leslie.demo.array.hashset;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//failed, this is not the best solution, but not need to do it again just because you could not remember empty character
public class FirstLettertoAppearTwice {
}
class Solution2 {
    public char repeatedCharacter(String s) {
        Map<Character,Integer> map = new HashMap();
        for(int i = 0; i < s.length();i++){
            char ch = s.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
            if(map.getOrDefault(ch,0) == 2) return ch;
        }
        return ' ';
    }
}
// better one
class Solution3 {
    public char repeatedCharacter(String s) {
        Set<Character> seen = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (seen.contains(c)) {
                return c;
            }

            seen.add(c);
        }

        return ' ';
    }
}

//  better two: int[]