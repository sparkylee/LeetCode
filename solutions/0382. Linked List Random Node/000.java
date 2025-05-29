/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
       Random robj = new Random();
        ListNode head;
        Integer len = 0;
    public Solution(ListNode head) {
          this.head = head;
            ListNode p = head;
            while(p!=null) {
                len++;
                p=p.next;
            }
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
         int rv = Math.abs(robj.nextInt())%this.len;
            ListNode p = head;
            for(int i = 0;i<rv;i++)
            {
                p=p.next;
            }
            return p.val;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */