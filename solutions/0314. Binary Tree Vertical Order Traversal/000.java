/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    class NodeOrder {
        TreeNode node;
        int order;
        NodeOrder(TreeNode node, int order) {
            this.node = node;
            this.order = order;
        }
    }
    void verticalOrder(TreeNode root, int k, List<List<Integer>> lst) {
        if(root==null) {
            return;
        }
        lst.get(k).add(root.val); 
        verticalOrder(root.left, k-1,lst);
        verticalOrder(root.right, k+1,lst);
    }
    int checkLeftMost(TreeNode root, int shift) {
        if(root==null)
            return shift;
        int shift1 = shift, shift2= shift;
        if(root.left!=null) {
            shift1 = checkLeftMost(root.left,shift+1);
        }
        if(root.right!=null) {
            shift2 = checkLeftMost(root.right,shift-1);
        }
        int shift_new = Math.max( Math.max(shift, shift1), shift2);
        return shift_new;
    }
    int checkRightMost(TreeNode root, int shift) {
        if(root==null)
            return shift;
        int shift1 = shift, shift2= shift;
        if(root.right!=null) {
            shift1 = checkRightMost(root.right,shift+1);
        }
        if(root.left!=null) {
            shift2 = checkRightMost(root.left,shift-1);
        }
        int shift_new = Math.max( Math.max(shift, shift1), shift2);
        return shift_new;
    }
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> lst1 = new ArrayList<>();
        if(root==null)
            return lst1;
        TreeNode tn = root;
        int count_left = checkLeftMost(root,0);
        // while(tn!=null) {
        //     tn = tn.left;
        //     count_left ++;
        // }
        tn = root;
        int count_right = checkRightMost(root,0);
        // while(tn!=null) {
        //     tn = tn.right;
        //     count_right ++;
        // }
        // System.out.println("left="+count_left + " right="+count_right);
        int count = count_left + count_right + 1;
        List<List<Integer>> lst = new ArrayList<>();
        for(int i=0;i<count;i++) {
            lst.add(new ArrayList<>());
        }
        List<NodeOrder> cnl = new ArrayList<>(); // current node-order list
        cnl.add(new NodeOrder(root, count_left ));
        while (!cnl.isEmpty()) {
            List<NodeOrder> cnl_new = new ArrayList<>();
            for(NodeOrder no: cnl) {
                lst.get(no.order).add(no.node.val);
                if(no.node.left!=null) {
                    cnl_new.add(new NodeOrder(no.node.left, no.order-1));
                }
                if(no.node.right!=null) {
                    cnl_new.add(new NodeOrder(no.node.right, no.order+1));
                }
            }
            cnl = cnl_new;
        }    
        for(List<Integer> li : lst) {
            if(!li.isEmpty()) {
                lst1.add(li);
            }
        }
        return lst1;
    }
}