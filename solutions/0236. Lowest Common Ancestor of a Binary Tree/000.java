/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
      class Record {
        TreeNode node;
        boolean left;
        boolean right;
        Record(TreeNode node, boolean left, boolean right) {
            this.node = node;
            this.left = left;
            this.right = right;
        }
    }


    private boolean search4Node(TreeNode root, TreeNode tn) {
        if (root == tn) return true;
        if (root == null) return false;
       return (search4Node(root.left, tn) || search4Node(root.right, tn));
    }
    private boolean searchFirstNode(TreeNode root, TreeNode tn,List<Record> records) {
        if (root == null || tn==null)
            return false;
        Record r = new Record(root,false,false);
        if(root==tn)
        {
            r.left = false;
            r.right = false;
            records.add(r);
            return true;
        }
        r.left = true;
        r.right = false;
        records.add(r);
        if(searchFirstNode(root.left,tn,records))
            return true;
        r.left = false;
        r.right = true;
        if(searchFirstNode(root.right,tn,records))
            return true;
        records.remove(records.size()-1);
        return false;
    }
    private TreeNode search4AnotherNode(TreeNode tn, final List<Record> records) {
        for (int i = records.size() - 1; i >= 0; i--) {
            Record r = records.get(i);
            if(r.node==tn)
                return r.node;
            if(!r.left)
            {
                boolean isFound = search4Node(r.node.left, tn);
                if(isFound) return r.node;
            }
            if(!r.right)
            {
                boolean isFound = search4Node(r.node.right, tn);
                if(isFound) return r.node;
            }
        }
        return null;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<Record> records = new ArrayList<>();
        searchFirstNode(root,p,records);
        TreeNode x = search4AnotherNode(q, records);
        return x;
    }

}