/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
          if(head==null ) return head;
            RandomListNode p = head;
            RandomListNode head_new = new RandomListNode(head.label);
            RandomListNode q = head_new;
            Map<Integer,RandomListNode> rnMap = new HashMap<>();
            rnMap.putIfAbsent(head.label,head_new);
            while(true)
            {
                p=p.next;
                if(p==null) break;
                RandomListNode node = new RandomListNode(p.label);
                q.next = node;
                rnMap.putIfAbsent(p.label,node);
                q=q.next;
            }
            p = head;
            q=head_new;
            while(true)
            {
                if(p==null) break;
                if(p.random!=null)
                    q.random = rnMap.get(p.random.label);
                p=p.next;
                q=q.next;
            }
            return head_new;
    }
}