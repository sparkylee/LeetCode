/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
            int count = 0;
            ListNode p = head;
            ListNode tail = null;
            while(p!=null)
            {
                tail = p;
                p=p.next;
                count++;
            }
            if(count<2) return head;
            p = head;
            int k = 0;
            while(k+1<count)
            {
               tail.next = p.next;
               tail = tail.next;
               p.next = p.next.next;
               p=p.next;
               tail.next= null;
               k+=2;
            }
            return head;
    }
}