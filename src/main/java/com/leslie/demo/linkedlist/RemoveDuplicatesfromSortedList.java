package com.leslie.demo.linkedlist;

// failed -> need to do it again
public class RemoveDuplicatesfromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return head;
        ListNode current = head;
        while(current.next != null && current.next != null){
            if(current.next.val == current.val){
                current.next = current.next.next; // move reference
            }else{
                current = current.next; // move to next node
            }
        }
        return head;
    }
}