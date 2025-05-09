/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    private int detectCycleLength(ListNode head)
    {
        if(head==null) return 0;
        ListNode target = head;
        int k=1;
        while(true){
            ListNode p = target;
            for(int i=0;i<k;i++)
            {
                p=p.next;
                if(p==null) return 0;
                if(p==target) return i+1;
            }
            target = p;
            k=k*2;
        }

    }
    public ListNode detectCycle(ListNode head) {
        int cycleLen = detectCycleLength(head);
        if(cycleLen<=0) return null;
        ListNode p = head,q=head;
        for(int i=0;i<cycleLen;i++)
            q=q.next;
        while (q!=p)
        {
            p=p.next;
            q=q.next;
        }
        return p;
    }
}