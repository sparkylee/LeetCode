package lc8xx;

import java.util.*;

public class lc897 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class LinkNode {
        TreeNode head, tail;

        LinkNode(TreeNode head, TreeNode tail) {
            this.head = head;
            this.tail = tail;
        }

        LinkNode() {
        }
    }

    public LinkNode increasingBST2Link(TreeNode root) {
        if (root == null) return null;
        LinkNode l = increasingBST2Link(root.left);
        LinkNode r = increasingBST2Link(root.right);
        LinkNode newLinkNode;
        if (l != null) {
            l.tail.right = root;
            l.tail = root;
            newLinkNode = l;
        } else {
            newLinkNode = new LinkNode(root, root);
        }
        root.left = null;
        root.right = null;
        if (r != null) {
            newLinkNode.tail.right = r.head;
            newLinkNode.tail = r.tail;
        }
        return newLinkNode;
    }

    public TreeNode increasingBST(TreeNode root) {
        LinkNode newLinkNode = increasingBST2Link(root);
        return newLinkNode.head;
    }
}
