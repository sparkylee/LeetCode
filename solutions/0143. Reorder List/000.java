/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
       int count  = 0;
            ListNode p  =head;
            while(p!=null)
            {
                count++;
                p=p.next;
            }
            if(count<=1) return;
            int index_middle = (count+1)/2;
            int i =0;
            p  =head;
            while (i<index_middle)
            {
               p=p.next;
               i++;
            }
            List<ListNode> tmp = new ArrayList<>();
            while(p!=null)
            {
                tmp.add(p);
                p=p.next;
            }
            p=head;
            ListNode pn = p.next;
            for(i=tmp.size()-1;i>=0;i--)
            {
                p.next =tmp.get(i);
                p.next.next=pn;

                p = pn;
                if(p!=null) {
                    pn = p.next;
                }
            }
            if(p!=null) p.next = null;
  
    }
}