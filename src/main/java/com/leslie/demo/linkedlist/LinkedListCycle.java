package com.leslie.demo.linkedlist;

public class LinkedListCycle {
}
// failed
// https://leetcode.com/problems/linked-list-cycle/
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) return true; //failed,  initially fast and slow are the same
            // no need to compare them in the beginning
        }
        return false;
    }
}
