/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
            ListNode h = null;
            while (head!=null){
                ListNode x = head;
                head = head.next;
                ListNode p = h;
                if(p==null || p.val> x.val)
                {
                    x.next = p;
                    h = x;
                    continue;
                }
                while (p.next!=null && p.next.val<= x.val)
                    p=p.next;
                x.next = p.next;
                p.next= x;
            }
            return h;
        }
}