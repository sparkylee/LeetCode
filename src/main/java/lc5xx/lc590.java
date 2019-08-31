package lc5xx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class lc590 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public void postorder(Node root, List<Integer> list) {
        if (root == null) return;
        for (Node n : root.children) {
            postorder(n, list);
        }
        list.add(root.val);
    }

    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

}
