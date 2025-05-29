/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode middleNode(ListNode head) {
        int k = 0;
        ListNode p = head;
        while(p!=null) {
            p = p.next;
            k++;
        }
        int count = k/2;
        k = 0;
        p = head;
        while(k!=count) {
            k++;
            p=p.next;
        }
        return p;
    }
}