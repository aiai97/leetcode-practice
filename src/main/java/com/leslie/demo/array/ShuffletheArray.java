package com.leslie.demo.array;

// failed -> 2 elements is a loop
class  ShuffletheArray  {
    public int[] shuffle(int[] nums, int n) {
        int[] res = new int[2*n];
        int j = 0;
        for(int i = 0; i < 2 * n;i++){
            if(j == n) break;
            if(i % 2 == 0){
                res[i] = nums[j];
            }else{
                res[i] = nums[j+n];
                ++j;
            }
        }
        return res;
    }
}