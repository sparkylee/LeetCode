package lc1xx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public  class lc173
{
    @Test
    public void tc() {
        TreeNode n7=TN(7),n3=TN(3),n15=TN(15),n9=TN(9),n20=TN(20);
        n7.left=n3;n7.right=n15;n15.left=n9;n15.right=n20;
        BSTIterator iterator = new BSTIterator(n7);
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
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

    class BSTIterator {
        List<TreeNode> stack = new ArrayList<>();

        private void addNewNode(TreeNode node) {
            while (true){
                if(node==null) return;
                stack.add(node);
                node = node.left;
            }
        }

        public BSTIterator(TreeNode root) {
            addNewNode(root);
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode tn = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            addNewNode(tn.right);
            return tn.val;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */


}
