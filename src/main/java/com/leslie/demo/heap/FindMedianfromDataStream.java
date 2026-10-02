package com.leslie.demo.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
// no need to do it again although it could be better
//https://leetcode.com/problems/find-median-from-data-stream/description/
public class FindMedianfromDataStream {
}
class MedianFinder {
    // maxHeap ：3， 2 minHeap： 1
    PriorityQueue<Integer> minHeap; // 3, 2,1
    PriorityQueue<Integer> maxHeap; // 1,2,3
    public MedianFinder() {
        minHeap = new PriorityQueue();
        maxHeap = new PriorityQueue(Comparator.reverseOrder());
    }

    public void addNum(int num) {
        maxHeap.add(num);
        minHeap.add(maxHeap.remove());
        if(minHeap.size() > maxHeap.size() + 1){
            maxHeap.add(minHeap.remove());
        }
    }
    // 1,2,3,4,5
    // maxHeap: 1
    // minHeap:2,3

    public double findMedian() {
        int count = minHeap.size() + maxHeap.size();
        if(count % 2 == 1){
            return minHeap.peek();
        }
        return (double)(maxHeap.peek() + minHeap.peek()) / 2;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

//better one
//class MedianFinder {
//    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
//    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());;
//
//    public void addNum(int num) {
//        maxHeap.add(num);
//        minHeap.add(maxHeap.remove());
//        if (minHeap.size() > maxHeap.size()) {
//            maxHeap.add(minHeap.remove());
//        }
//    }
//
//    public double findMedian() {
//        if (maxHeap.size() > minHeap.size()) {
//            return maxHeap.peek();
//        }
//
//        return (minHeap.peek() + maxHeap.peek()) / 2.0;
//    }
//}