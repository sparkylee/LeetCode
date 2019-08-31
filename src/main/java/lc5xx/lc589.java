package lc5xx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class lc589 {
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

    ;

    @Test
    public void test() {

    }

    public void preorder(Node root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        for (Node n : root.children) {
            preorder(n, list);
        }
    }

    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

}
