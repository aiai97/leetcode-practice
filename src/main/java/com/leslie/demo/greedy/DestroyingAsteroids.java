package com.leslie.demo.greedy;

import java.util.Arrays;


// https://leetcode.com/problems/destroying-asteroids/
public class DestroyingAsteroids {
}
// maybe I need to use long for + or calculation most of the time
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