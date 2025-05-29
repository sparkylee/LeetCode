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
class Solution {
    int addOne(ListNode head) {
        if(head==null) {
            return 0;
        }
        int c;
        if(head.next==null) {
            c = 1;
        } else {
            c = addOne(head.next);
        }         
        int val_plus_one = head.val + c;
        head.val = val_plus_one % 10;
        return val_plus_one / 10;
    }
    public ListNode plusOne(ListNode head) {
        int c = addOne(head);
        if(c!=0) {
            ListNode node = new  ListNode(c);
            node.next = head;
            head = node;
        }
        return head;
    }
}