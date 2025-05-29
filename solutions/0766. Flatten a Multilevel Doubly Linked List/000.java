/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    Node flatten1(Node head) {
        if(head==null) {
            return null;
        }
        Node p = head;
        while(true) {
            if(p.child!=null) {
                Node child_tail = flatten1(p.child);
                Node p_next = p.next;
                p.next = p.child;
                p.child.prev = p;                                
                child_tail.next = p_next;
                if(p_next!=null)
                    p_next.prev = child_tail;
                //
                p.child = null;
                p = p_next;
                if(p==null)
                    return child_tail;
                continue;
            }
            if(p.next==null)
                return p;
            p=p.next;
        }
     
    } 
    void printList(Node head){
        while(head!=null) {
            System.out.print(head.val+" ");
            head= head.next;
        }
    }
    public Node flatten(Node head) {
        flatten1(head);
        // printList(head);
        return head;
    }
}