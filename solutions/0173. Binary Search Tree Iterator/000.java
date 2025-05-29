/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class BSTIterator {

      List<TreeNode> stack = new ArrayList<>();
        private void addNewNode(TreeNode node)
        {
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