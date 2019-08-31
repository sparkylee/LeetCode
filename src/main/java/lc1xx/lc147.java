package lc1xx;

import org.junit.Test;

import java.util.List;

public class lc147 {


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }

    @Test
    public void test1() {
        tc(new int[ ]{4,2,1,3});
        tc(new int[ ]{-1,5,3,4,0});
        tc(new int[ ]{-1});
        tc(new int[ ]{});
    }

    private void tc(int[] array) {
        if(array==null || array.length<1) return ;
        ListNode [] lns = new ListNode[array.length];
        for (int i = 0; i < array.length; i++) {
            lns[i] = new ListNode(array[i]);
        }
        for (int i = 0; i < array.length - 1; i++) {
            lns[i].next = lns[i+1];
        }

        Solution s = new Solution();
        ListNode result = s.insertionSortList(lns[0]);
        printListNodes(result);

    }

    private void printListNodes(ListNode h) {
        while (h != null) {
            System.out.print(h.val);
            System.out.print(' ');
            h=h.next;
        }
        System.out.println();
    }

    class Solution {
        public ListNode insertionSortList(ListNode head) {
            ListNode h = null;
            while (head!=null){
                ListNode x = head;
                head = head.next;
                ListNode p = h;
                if (p == null || p.val > x.val) {
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
}
