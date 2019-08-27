import java.util.Stack;

public class lc739 {
    class Node {
        int val;
        int index;

        Node(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    public int[] dailyTemperatures(int[] T) {
        int[] warmer = new int[T.length];
        Stack<Node> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            Node node = new Node(T[i], i);
            while (!stack.empty()) {
                Node top = stack.peek();
                if (top.val < node.val) {
                    warmer[top.index] = node.index - top.index;
                    stack.pop();
                } else break;
            }
            stack.push(node);
        }
        return warmer;
    }

}
