package lc5xx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class lc559 {
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

    @Test
    public void test() {
    }

    public int maxDepth(Node root) {
        if (root == null) return 0;
        int depth = 0;
        for (Node node : root.children) {
            depth = Math.max(maxDepth(node), depth);
        }
        return depth + 1;
    }

}
