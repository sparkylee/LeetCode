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
    int count_nodes(ListNode l){
        int count = 0;
        while(l!=null) {
            count++;
            l=l.next;
        }
        return count;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int count_l1 = count_nodes(l1);
        int count_l2 = count_nodes(l2);
        boolean compared = count_l1 >= count_l2;
        ListNode ll = compared ? l1:l2;
        ListNode sl = compared ? l2:l1;
        int count_ll = compared ? count_l1: count_l2;
        int count_sl = compared ? count_l2: count_l1;
        Stack<ListNode> stack = new Stack();
        int count_delta = count_ll - count_sl;
        for(int i=0;i<count_delta;i++){
            stack.push(ll);
            ll = ll.next;
        }
        for(int i=0;i<count_sl;i++) {
            ll.val = ll.val + sl.val;
            stack.push(ll);
            ll = ll.next;
            sl = sl.next;
        }
        ListNode head = null;
        int carry = 0 ;
        while(!stack.isEmpty()){
            ListNode node = stack.pop();
            node.next = head;
            int newVal = node.val + carry;
            node.val = newVal % 10;
            carry = newVal / 10;
            head = node;
        }
        if(carry!=0) {
            ListNode node = new ListNode(carry);
            node.next = head;
            head = node;
        }
        return head;

    }
}