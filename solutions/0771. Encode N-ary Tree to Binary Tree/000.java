/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Codec {
    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
       if(root==null) {
            return null;
       }
       return  encoNode(root,false);
    }
    private TreeNode encoNode(Node root, boolean dir) {
        if(root==null)
            return null;
        TreeNode tr = new TreeNode(root.val);        
        if(root.children==null || root.children.isEmpty()) {
            return tr;
        }
        TreeNode tr_i = tr;
        for(Node child: root.children) {
            TreeNode tc = encoNode(child, !dir);
            if(dir) {
                tr_i.right = tc;
            } else {
                tr_i.left  = tc;
            }
            tr_i = tc;
        }
        return tr;
    }
    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if(root==null)
            return null;
        return decode(root, false);        
    }
    private Node decode(TreeNode root, boolean dir) {
        if(root==null)
            return null;
        Node nr = new Node(root.val);
        nr.children = new ArrayList<>();
        while(true) {            
            TreeNode next;            
            next = dir ? root.right:root.left;
            Node nc = decode(next, !dir);
            if(nc==null)
                return nr;
            nr.children.add(nc);
            root = next;
        }        
    } 

}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(root));