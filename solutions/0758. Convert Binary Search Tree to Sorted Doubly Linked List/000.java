/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

class Solution {
    class DL {
        Node head;
        Node tail;
        DL(Node head, Node tail)  {
            this.head=head;
            this.tail=tail;
        }       
    }
    DL tree2DoublyList(Node root) {
        if(root==null)
            return null;
        DL leftDL = tree2DoublyList(root.left);
        DL rightDL = tree2DoublyList(root.right);
        DL rootDL = new DL(root, root);
        if(leftDL!=null) {
            rootDL.head = leftDL.head;
            leftDL.tail.right = root;
            root.left = leftDL.tail;
        }
        if(rightDL!=null) {
            rootDL.tail = rightDL.tail;
            rightDL.head.left = root;
            root.right = rightDL.head; 
        }
        rootDL.tail.right = rootDL.head;
        rootDL.head.left  = rootDL.tail;
        return rootDL;

    }
    public Node treeToDoublyList(Node root) {
        DL rootDL = tree2DoublyList(root);
        if(rootDL!=null) {           
            return rootDL.head;
        }
        return null;
    }
}