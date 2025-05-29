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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int c = 0;
        ListNode head = null, tail = null;
        while(!(l1==null && l2==null)) {
            int l1_v = l1==null ? 0: l1.val;
            int l2_v = l2==null ? 0: l2.val;
            int sum  = l1_v + l2_v + c;
            int val = sum % 10;
            c = sum / 10;
            ListNode node = new ListNode(val);
            if(head==null) {
                head = node;                
            } else {
                tail.next = node;
            }
            tail = node;
            l1 = l1==null? null : l1.next;
            l2 = l2==null? null : l2.next;
        }
        if(c!=0) {
            ListNode node = new ListNode(c);
             tail.next = node;
        }
        return head;
    }
}