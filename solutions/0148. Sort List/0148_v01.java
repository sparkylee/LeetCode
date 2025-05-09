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
    class ListRepr {
        ListNode head, tail;
        int size;
        ListRepr(ListNode head, ListNode tail, int size) {
            this.head = head;
            this.tail = tail;
            this.size = size;
        }
        ListRepr() {           }
    }
    void sortList(ListRepr lr) {
        if(lr==null||lr.size<=1)
            return;
        ListRepr [] lrArr = divideListRepr(lr);
        sortList(lrArr[0]);
        sortList(lrArr[1]);
        lr.head = null;
        lr.tail = null;
        ListNode node_s = lrArr[0].head;
        ListNode node_b = lrArr[1].head;
        while(true) {
            if(node_s==null && node_b==null){
                if(lr.tail!=null) {
                    lr.tail.next = null;
                }
                return;
            }

            if( node_s==null || (node_b!=null && node_s.val > node_b.val ) ) {
                ListNode node_s_tmp = node_b;
                node_b     =  node_s;
                node_s     =  node_s_tmp;
            }

            if(lr.head==null) {
                lr.head = node_s;
            }
            if(lr.tail==null) {
                lr.tail = node_s;
            } else {
                lr.tail.next = node_s;
                lr.tail = lr.tail.next;
            }
            node_s = node_s.next;
        }
    }
    ListRepr convList(ListNode head) {
        ListRepr lr = new ListRepr(head, head, 0);
        while(head!=null) {
            lr.size ++;
            lr.tail = head;
            head=head.next;
        }
        return lr;
    }
    ListRepr [] divideListRepr(ListRepr lr) {
        ListRepr [] lrArr = {new ListRepr(), new ListRepr()};
        if(lr==null || lr.size<0)
            return lrArr;
        int middle = lr.size/2;
        lrArr[0].size = middle;
        ListNode node = lr.head;
        if(lrArr[0].size!=0) {
            lrArr[0].head = lr.head;
            for(int i=0;i<lrArr[0].size-1;i++) {
                node=node.next;
            }
            lrArr[0].tail = node;
            node=node.next;
            lrArr[0].tail.next = null;
        }
        lrArr[1].size  = lr.size - middle;
        if(lrArr[1].size!=0) {
            lrArr[1].head = node;
            for(int i=0;i<lrArr[1].size-1;i++) {
                node=node.next;
            }
            lrArr[1].tail = node;
            node=node.next;
            lrArr[1].tail.next = null;
        }
        return lrArr;
    }
    public ListNode sortList(ListNode head) {
        if(head==null)
            return null;
        ListRepr lr = convList(head);
        sortList(lr);
        return lr.head;
    }
}