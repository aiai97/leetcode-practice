package com.leslie.demo.linkedlist;

// easy
public class RemoveDuplicatesfromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = head;
        while(curr != null && curr.next != null){
            ListNode nextNode = curr.next;
            if(curr.val == nextNode.val){
                curr.next = nextNode.next; // move reference
            }else{
                curr = nextNode; // move to next node
            }
        }
        return dummy.next;
    }
}