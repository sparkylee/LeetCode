package lc3xx;

import org.junit.Test;

import java.awt.*;

public  class lc328
{

    @Test
    public void test1() {
        tc(new int [] {1,2,3,4,5});
        tc(new int [] {1,2,3,4,5});
        tc(new int [] {2,1,3,5,6,4,7});
        tc(new int [] {2,1,3,5,6,4});
    }

    private void tc(int[] nums) {
        ListNode head = createListNodes(nums);
        Solution s = new Solution();
        head = s.oddEvenList(head);
        printListNodes(head);
    }

    private void printListNodes(ListNode head) {
        while (head!=null) {
            System.out.print(head.val + " ");
            head=head.next;
        }
        System.out.println();
    }

    private ListNode createListNodes(int[] nums) {
        if(nums==null || nums.length<1) return null;
        ListNode head = LN(nums[0]),tail = head;
        for (int i = 1; i < nums.length; i++) {
            tail.next = LN(nums[i]);
            tail=tail.next;
        }
        return head;
    }

    private ListNode LN(int n) {return new ListNode(n);}

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode oddEvenList(ListNode head) {
            int count = 0;
            ListNode p = head;
            ListNode tail = null;
            while (p != null) {
                tail = p;
                p=p.next;
                count++;
            }
            if(count<2) return head;
            p = head;
            int k = 0;
            while (k + 1 < count) {
                tail.next = p.next;
                tail = tail.next;
                p.next = p.next.next;
                p = p.next;
                tail.next = null;
                k += 2;
            }
            return head;
        }
    }

}
