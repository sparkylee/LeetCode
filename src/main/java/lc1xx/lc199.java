package lc1xx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public  class lc199
{
    @Test
    public void test1() {
        TreeNode n1=TN(1),n2=TN(2),n5=TN(5),n3=TN(3),n4=TN(4);
        n1.left=n2;n2.right=n5;n1.right=n3;n3.right=n4;
        tc(n1);
    }

    @Test
    public void test2() {
        TreeNode n1=TN(1),n2=TN(2),n5=TN(5),n3=TN(3),n4=TN(4);
        n1.left=n2;n2.right=n5;n1.right=n3;
        tc(n1);
    }

    @Test
    public void test3() {
        TreeNode n1=TN(1),n2=TN(2),n5=TN(5),n3=TN(3),n4=TN(4);
        tc(n1);
    }

    @Test
    public void test4() {
        TreeNode n1=TN(1),n2=TN(2),n5=TN(5),n3=TN(3),n4=TN(4);
        n1.left=n2;n2.right=n5;n1.right=n3;n3.left=n4;
        tc(n1);
    }

    @Test
    public void test5() {
        TreeNode n1=TN(1),n2=TN(2),n5=TN(5),n3=TN(3),n4=TN(4),n6=TN(6);
        n1.right=n2;n2.right=n5;n5.right=n6;n5.left=n4;n4.left=n3;
        tc(n1);
    }

    private void tc(TreeNode root) {
        Solution s = new Solution();
        List<Integer> l = s.rightSideView(root);
        printList(l);
    }

    private TreeNode TN(int n) {
        return new TreeNode(n);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }

    private void printList(List<Integer> l) {
        for(int i:l) System.out.print(i+" ");
        System.out.println();
    }

    class Solution {
        int height = -1;

        private void rightSideView(TreeNode root, List<Integer> l, int h) {
            if(root==null) return ;
            if (h > this.height) {
                this.height=h;
                l.add(root.val);
            }
            rightSideView(root.right,l,h+1);
            rightSideView(root.left,l,h+1);
        }

        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> l = new ArrayList<>();
            rightSideView(root,l,0);
            return l;
        }
    }


}
