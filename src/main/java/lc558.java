import org.junit.Test;

import java.util.List;

public class lc558 {
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
        }

        public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }

    private boolean isTrueLeaf(Node node) {
        return node != null && node.isLeaf && node.val;
    }

    public Node intersect(Node quadTree1, Node quadTree2) {
        if (quadTree1 == null || quadTree2 == null) return null;
        if (isTrueLeaf(quadTree1))
            return quadTree1;
        if (isTrueLeaf(quadTree2))
            return quadTree2;
        if (quadTree1.isLeaf) return quadTree2;
        if (quadTree2.isLeaf) return quadTree1;
        Node topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
        Node topRight = intersect(quadTree1.topRight, quadTree2.topRight);
        Node bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        Node bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);
        if (isTrueLeaf(topLeft) && isTrueLeaf(topRight)
                && isTrueLeaf(bottomLeft) && isTrueLeaf(bottomRight)) {
            Node node = new Node(true, true, null,
                    null, null, null);
            return node;
        }
        Node node = new Node(false, false, topLeft,
                topRight, bottomLeft, bottomRight);
        return node;
    }

}
