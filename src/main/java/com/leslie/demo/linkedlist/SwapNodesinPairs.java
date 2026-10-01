package com.leslie.demo.linkedlist;

//https://leetcode.com/problems/swap-nodes-in-pairs/
public class SwapNodesinPairs {
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
// failed -> because I forget about prevNode
// 1 -> 2 -> 3-> 4->5
// dummyNode -> get header
// currNode-> node.next.next == nextLoopNode, node.next -> nextNode , nextNode.next-> nextLoopNode
// currNode = nextLoopNode
class Solution2 {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;

        ListNode prevNode = dummyNode;
        ListNode currNode = head;
        while(currNode != null && currNode.next != null){
            ListNode nextNode = currNode.next;
            ListNode nextLoopNode = nextNode.next;

            prevNode.next = nextNode;
            // 0-> 1-> 2-> 3->4
            // 2-> 1-> 3 -> 4
            nextNode.next = currNode;
            currNode.next = nextLoopNode;

            prevNode = currNode;
            currNode = nextLoopNode;
        }
        return dummyNode.next;
    }
}