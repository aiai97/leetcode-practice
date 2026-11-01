package com.leslie.demo.linkedlist;

// easy
public class RemoveDuplicatesfromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = head;
        while(curr != null && curr.next != null){
            ListNode nextNode = curr.next;
            if(curr.val == nextNode.val){
                curr.next = nextNode.next;
            }else{
                curr = curr.next;
            }
        }
        return dummy.next;
    }
}