package com.leslie.demo.greedy;

import java.util.Arrays;

// failed ,maybe need to do it again because you missed the long type
// https://leetcode.com/problems/destroying-asteroids/
public class DestroyingAsteroids {
}
class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long currentMass = mass;
        for(int asteroid:asteroids){
            if(currentMass >= asteroid){
                currentMass += asteroid;
            }else{
                return false;
            }
        }
        return true;
    }
}